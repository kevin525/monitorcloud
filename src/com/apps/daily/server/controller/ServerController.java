package com.apps.daily.server.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.apps.daily.server.domain.AsopServer;
import com.apps.daily.server.service.ServerService;
import com.apps.msgRemind.sms.domain.AsopSms;
import com.apps.utils.PingUtil;
import com.common.FormatDateUtil;
import com.common.StringHelpers;
import com.common.constants.Constant;
import com.common.osplugins.JsonForm;
import com.common.pagetag.PageGridLoad;
import com.common.pagetag.PageGridPost;
import com.common.utils.HttpUtil;
import com.sys.domain.model.User;
import com.sys.service.BaseService;
import com.sys.util.Config;
/**
 * @ClassName: ServerController
 * @Description: 服务器控制层
 * @author LG
 * @date 2017年9月22日 上午10:39:52
 */
@Controller
@RequestMapping(value="/server")
public class ServerController {
	
	@Autowired
	private BaseService baseService;
	
	@Autowired
	private ServerService serverService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * @Title: getServerList
	 * @Description: 分页获取服务器列表
	 * @param asopServer
	 * @param pageGridPost
	 * @return PageGridLoad
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/getServerList.do")
	public PageGridLoad getServerList(AsopServer asopServer,PageGridPost pageGridPost){
		PageGridLoad postGridLoad = new PageGridLoad();
		List<AsopServer> asopServerList = serverService.getAsopServerList(asopServer, pageGridPost);
		postGridLoad.setPageData(asopServerList);
		postGridLoad.setTotalCount(pageGridPost.getTotalCount());
		return postGridLoad;
	}
	
	@ResponseBody
	@RequestMapping("/getServerListNoPage.do")
	public List<AsopServer> getServerListNoPage(AsopServer asopServer){
		List<AsopServer> asopServerList = serverService.getAsopServerList(asopServer);
		return asopServerList;
	}
	
	
	/**
	 * @Title: saveOrUpdateServer
	 * @Description: 创建、修改服务器信息
	 * @param request
	 * @param asopServer
	 * @return JsonForm
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/saveServer.do")
	public JsonForm saveServer(HttpServletRequest request,final AsopServer asopServer){
		try {
			User loginUser = (User) request.getSession().getAttribute("loginUser");
			if(asopServer !=null){
					//新增
				asopServer.setCreateUserId(loginUser.getUserId());
				asopServer.setCreateDate(FormatDateUtil.formatDateToString("yyyy-MM-dd HH:mm:ss", new Date()));
				asopServer.setModifyDate(FormatDateUtil.formatDateToString("yyyy-MM-dd HH:mm:ss", new Date()));
				asopServer.setModifyUserId(loginUser.getUserId());
				baseService.saveOrUpdate(asopServer);
			}
			
			return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
		}
	}
	@ResponseBody
	@RequestMapping("/updateServer.do")
	public JsonForm updateServer(HttpServletRequest request,final AsopServer asopServer){
		try {
			User loginUser = (User) request.getSession().getAttribute("loginUser");
			if(asopServer !=null){
				asopServer.setModifyDate(FormatDateUtil.formatDateToString("yyyy-MM-dd HH:mm:ss", new Date()));
				asopServer.setModifyUserId(loginUser.getUserId());
				AsopServer server = serverService.getAsopServerById(asopServer.getId());
				if(null == server){
					return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
				}
				asopServer.setCheckCount(server.getCheckCount());
				asopServer.setLastCheckDate(server.getLastCheckDate());
				baseService.saveOrUpdate(asopServer);
			}
			
			return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
		}
	}
	/**
	 * 删除服务器
	 * @param deleteServer
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteServer.do")
	public JsonForm deleteServer(String ids){
		try {
			if(!StringHelpers.isNull(ids)){
				String[] arr = ids.split(",");
				List<String> list = java.util.Arrays.asList(arr);
				serverService.deleteAsopServer(list);
				return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
	}
	
	/**
	 * @Title: getServerInfo
	 * @Description: 获取服务器信息后，跳转到指定的页面
	 * @param id
	 * @param url
	 * @return
	 * @return ModelAndView
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/getServerInfo.do")
	public AsopServer getServerInfo(long id) {
		AsopServer server = (AsopServer)baseService.findById(AsopServer.class,id);
		return server;
	}
	
	/**
	 * @Title: setServerUse
	 * @Description: 设置服务器启用、禁用状态
	 * @param id
	 * @return
	 * @return JsonForm
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/setServerUse.do")
	public JsonForm setServerUse(long id,String val){
		try {
			if(!StringHelpers.isNull(val)){
				serverService.isUseServer(id, val);
				return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
	}
	
	/**
	 * @Title: testServerConn
	 * @Description: 测试ip是否通畅
	 * @param ip
	 * @return JsonForm
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/testServerConn.do")
	public JsonForm testServerConn(String ip){
		try {
			if(!StringHelpers.isNull(ip)){
				//true代表可以ping通，false代表不通
				boolean result = PingUtil.ping(ip,1, 3000);
				return JsonForm.warpJsonForm(true, result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		//异常返回fail
		return JsonForm.warpJsonForm(false, "fail");
	}
	
	
}
