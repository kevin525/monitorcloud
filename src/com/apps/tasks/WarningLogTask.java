package com.apps.tasks;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.stereotype.Component;

import com.apps.msgRemind.inform.domain.AsopInform;
import com.apps.msgRemind.inform.service.InformService;
import com.apps.msgRemind.mail.domain.AsopMail;
import com.apps.msgRemind.mail.service.MailService;
import com.apps.msgRemind.sms.domain.AsopSms;
import com.apps.msgRemind.sms.service.SmsService;
import com.apps.utils.InformUtil;
import com.apps.utils.MailUtil;
import com.apps.utils.SmsUtil;
import com.apps.warn.domain.AsopWarning;
import com.apps.warn.domain.WarningLog;
import com.apps.warn.service.WarningLogService;
import com.apps.warn.service.WarningService;
import com.common.FormatDateUtil;
import com.common.StringHelpers;
import com.common.constants.Constant;
import com.common.context.ApplicationContextUtil;
import com.common.servlet.ChartCacheDateServlet;
import com.common.servlet.InitServlet;
import com.common.threadPool.WarningLogPool;
import com.common.utils.HttpUtil;
import com.common.utils.TimeUtil;
import com.common.utils.UnicomSmsUtil;
import com.sys.domain.model.Person;
import com.sys.service.PersonService;
import com.sys.util.Config;

/**
 * @ClassName: WarningLogTask
 * @Description: 告警日志任务处理线程
 * @author LG
 * @date 2017年11月29日 下午2:09:39
 */
@Component 
public class WarningLogTask extends Thread{
	
	public WarningLogTask(){
		super();
	}
	
	public void run(){
		WarningLogPool pool = InitServlet.getPool();
		while(true){
			try{
				WarningLog warning = pool.loadMessage();
				if (null != warning) {
					judgeWarningRule(warning);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * @Title: handleWarningLog
	 * @Description: 处理告警日志
	 * @param warning
	 * @return void
	 * @throws
	 */
	private void handleWarningLog(WarningLog warningLog,AsopWarning warningSet){
		String warnWay = "1".equals(warningSet.getIsSendInform())? "短消息 ":"";
		warnWay += "1".equals(warningSet.getIsSendMessage())? "短信 ":"";
		warnWay += "1".equals(warningSet.getIsSendEmail())? "邮件 ":"";
		warningLog.setWarnWay(warnWay);
		
		WarningLogService warningLogService = (WarningLogService) ApplicationContextUtil.getBean("warningLogService");
		warningLogService.saveOrupdateWarning(warningLog);
		String flag = Config.getAttribute("isUseMemcache");
    	if(flag != null && "true".equals(flag)){
    		//更新图表异常数据缓存
    		ChartCacheDateServlet.createChartCache();
    	}
		
	}
	
	/**
	 * 根据告警规则来判断告警方式
	 * @param warning
	 */
	private void judgeWarningRule(WarningLog warningLog){
		AsopWarning warning =	getWarningRule(warningLog);
		//正常日志
		if(("0").equals(warningLog.getStatus())){
			//当发过告警监测点恢复正常时发送一次告警，0：不发送，1：发送； 
			if(("1").equals(warning.getIsNormal()) ){
				sendWarning(warning,warningLog);
			}
		}
		//异常日志 
		if((("1").equals(warningLog.getStatus()))){
		
			
			if(StringHelpers.isNull(warning.getCount()) && StringHelpers.isNull(warning.getMinute())){
				sendWarning(warning,warningLog);
				return;
			}
			List<WarningLog> logs= getListByWarningLog(warningLog);
			
			//以前有同MonitorType，同MonitorId，并且为异常的，未处理的告警日志，需要根据告警规则来判断进行怎样的操作
			if(null != logs && logs.size()>0){
				WarningLog lasetlog =logs.get(0);//最近的一条未处理的同id type  的异常日志
				String time = lasetlog.getWarnTime();
				int count = logs.size();
				//规则：(1)事件连续发生 xx次后，停止发送告警=====================
				if(!StringHelpers.isNull(warning.getCount()) && StringHelpers.isNull(warning.getMinute())){
					if(count < Integer.parseInt(warning.getCount())){
						sendWarning(warning,warningLog);
						return;
					}
				}
				
			
					
				//当前时间点；精确到分钟
				String nowTime =TimeUtil.GetCurDateTime().substring(0, 12);
				//规则：(1)事件连续发生 xx次后，停止发送告警     (2)间隔 xx分钟，再次发送告警   =========================================
				if(!StringHelpers.isNull(warning.getCount()) && !StringHelpers.isNull(warning.getMinute())){
					//间隔XX分钟，之后的时间点，如果当前时间在该时间点之前，不发送短信 精确到分钟
					String afterTime =TimeUtil.getAfterTime(time, Integer.parseInt(warning.getMinute()), "yyyy-MM-dd HH:mm:ss");
					
					if(count < Integer.parseInt(warning.getCount()) && Long.parseLong(nowTime) >= Long.parseLong(afterTime)){
						//获得最新的同一个告警日志，判断时间，如果当前时间大于临界时间，并且 发生次数小于规则次数 
						sendWarning(warning,warningLog);
						return;
					}
				}
				
				//规则：(2)间隔 xx分钟，再次发送告警 ===================================
				if(StringHelpers.isNull(warning.getCount()) && !StringHelpers.isNull(warning.getMinute())){
					//间隔XX分钟，之后的时间点，如果当前时间在该时间点之前，不发送短信 精确到分钟
					String afterTime =TimeUtil.getAfterTime(time, Integer.parseInt(warning.getMinute()), "yyyy-MM-dd HH:mm:ss");
					
					if(Long.parseLong(nowTime) >= Long.parseLong(afterTime)){
						sendWarning(warning,warningLog);
						return;
					}
				}
			}else{
				//以前没有的 ，直接发短信，日志入库
				sendWarning(warning,warningLog);
				return;
			}
		}
		
		
	}
	/**
	 * 以前有同MonitorType，同MonitorId，并且为异常的，未处理的告警日志
	 * @param warningLog
	 * @return
	 */
	private List<WarningLog> getListByWarningLog(WarningLog warningLog){
		WarningLog log = new WarningLog();
		log.setStatus(Constant.EXCEPTION_STATUS);
		log.setMonitorId(warningLog.getMonitorId());
		log.setMonitorType(warningLog.getMonitorType());
		log.setFlag(Constant.WARN_NOT_HANDLE);
		WarningLogService warningLogService = (WarningLogService) ApplicationContextUtil.getBean("warningLogService");
		List<WarningLog> logs= warningLogService.getWarningLogList(log);//以前有告警
		return logs;
	}

	/**
	 * 获取告警规则
	 * @param warningLog
	 * @return
	 */
	private AsopWarning getWarningRule(WarningLog warningLog){
		WarningService warningService = (WarningService) ApplicationContextUtil.getBean("warningService");
		//根据告警类别找告警规则
		AsopWarning asopWarning = new AsopWarning();
		asopWarning.setWarningType(warningLog.getMonitorType());
		asopWarning.setIsUse(Constant.ACTIVITY);
		AsopWarning  warning =warningService.getWarningList(asopWarning).get(0);
		return warning;
	}
	
	public void sendWarning(AsopWarning warning,WarningLog warningLog){

		handleWarningLog(warningLog, warning);
		if("1".equals(warning.getIsSendEmail())){
			//邮件
			sendMail(warningLog);
		}
		if("1".equals(warning.getIsSendInform())){
			//短消息
			sendInform(warningLog);
		}
		if("1".equals(warning.getIsSendMessage())){
			//短信
			sendMsg(warningLog);
		}
		
	}
	/**
	 * 发短消息
	 * @param warning
	 */
	private void sendInform(WarningLog warning){
		System.out.println("==========开始发短消息===============");
		InformService informService =(InformService) ApplicationContextUtil.getBean("informService");
		String content = warning.getWarnTime()+warning.getWarnName();
		AsopInform inform = InformUtil.buildAsopInform(warning, content);
		informService.saveOrupdateInform(inform);
		System.out.println("==========结束发短消息===============");
		
	}
	/**
	 * 发短信
	 * @param warning
	 */
	private void sendMsg(WarningLog warning){
		System.out.println("==========开始发短信===============");
		SmsService smsService = (SmsService) ApplicationContextUtil.getBean("smsService");
		String smsSuffix="";//"【通州政务短信平台】";// Config.getAttribute("smsSuffix");
		String content = smsSuffix +warning.getWarnTime()+warning.getWarnName();
		String defaultTel = Config.getAttribute("warningPhoneNum");
		AsopSms sms = SmsUtil.buildAsopSms(content,Long.parseLong(warning.getMonitorId()),warning.getMonitorType(),defaultTel);
		smsService.saveOrupdateSms(sms);//默认保存发送失败的短信记录
		
		UnicomSmsUtil.sendSmsByGet(content, defaultTel, null);
		
		sms.setSendStatus(Constant.SMS_SEND_SUCCESS);//发送成功
		smsService.saveOrupdateSms(sms);
		System.out.println("==========结束发短信===============");
	}
	/**
	 * 发邮件
	 * @param warning
	 */
	private void sendMail(WarningLog warning){
		System.out.println("==========开始发邮件===============");
		String content = warning.getWarnTime()+warning.getWarnName();
		String receiveMail= Config.getAttribute("receiveMail");
		String subject ="监控告警";
		
		MailService mailService = (MailService) ApplicationContextUtil.getBean("mailService");
		AsopMail mail = MailUtil.buildAsopMail(warning, content, subject, receiveMail);
		mailService.saveOrupdateMail(mail);
		
		try{
            MailUtil.sendHtmlMail(receiveMail,subject,content);
            mail.setSendStatus(Constant.MAIL_SEND_SUCCESS);
            mailService.saveOrupdateMail(mail);
        } catch(AddressException e) {
            e.printStackTrace();
        } catch(MessagingException e) {
            e.printStackTrace();
        } catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
		
		System.out.println("==========结束发邮件===============");
		
	}
}
