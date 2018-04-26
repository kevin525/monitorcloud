package com.apps.daily.middleware.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apps.daily.middleware.domain.AsopMiddleware;
import com.apps.daily.middleware.service.MiddlewareService;
import com.apps.daily.server.domain.AsopServer;
import com.apps.utils.TelnetUtil;
import com.common.FormatDateUtil;
import com.common.StringHelpers;
import com.common.constants.Constant;
import com.common.osplugins.JsonForm;
import com.common.pagetag.PageGridLoad;
import com.common.pagetag.PageGridPost;
import com.sys.domain.model.User;
import com.sys.service.BaseService;
/**
 * @ClassName: MiddlewareController
 * @Description: 中间件控制层
 * @author LG
 * @date 2017年9月22日 上午10:39:52
 */
@Controller
@RequestMapping(value="/middleware")
public class MiddlewareController {
	
	@Autowired
	private BaseService baseService;
	
	@Autowired
	private MiddlewareService middlewareService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * @Title: getMiddlewareList
	 * @Description: 分页获取中间件列表
	 * @param middleware
	 * @param pageGridPost
	 * @return PageGridLoad
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/getMiddlewareList.do")
	public PageGridLoad getMiddlewareList(AsopMiddleware middleware,PageGridPost pageGridPost){
		PageGridLoad postGridLoad = new PageGridLoad();
		List<AsopMiddleware> middlewareList = middlewareService.getAsopMiddlewareList(middleware,pageGridPost);
		postGridLoad.setPageData(middlewareList);
		postGridLoad.setTotalCount(pageGridPost.getTotalCount());
		return postGridLoad;
	}
	
	@ResponseBody
	@RequestMapping("/getMiddlewareListNoPage.do")
	public List<AsopMiddleware> getMiddlewareListNoPage(AsopMiddleware middleware){
		List<AsopMiddleware> middlewareList = middlewareService.getAsopMiddlewareList(middleware);
		return middlewareList;
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
	@RequestMapping("/saveMiddleware.do")
	public JsonForm saveMiddleware(HttpServletRequest request,final AsopMiddleware middleware){
		try {
			User loginUser = (User) request.getSession().getAttribute("loginUser");
			if(middleware !=null){
					//新增
				middleware.setCreateUserId(loginUser.getUserId());
				middleware.setCreateDate(FormatDateUtil.formatDateToString("yyyy-MM-dd HH:mm:ss", new Date()));
				middleware.setModifyDate(FormatDateUtil.formatDateToString("yyyy-MM-dd HH:mm:ss", new Date()));
				middleware.setModifyUserId(loginUser.getUserId());
				baseService.saveOrUpdate(middleware);
			}
			
			return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
		}
	}
	@ResponseBody
	@RequestMapping("/getMiddlewareInfo.do")
	public AsopMiddleware getMiddlewareInfo(long id) {
		AsopMiddleware middleware = (AsopMiddleware)baseService.findById(AsopMiddleware.class,id);
		return middleware;
	}
	
	@ResponseBody
	@RequestMapping("/updateMiddleware.do")
	public JsonForm updateMiddleware(HttpServletRequest request,final AsopMiddleware middleware){
		try {
			User loginUser = (User) request.getSession().getAttribute("loginUser");
			if(middleware !=null){
				middleware.setModifyDate(FormatDateUtil.formatDateToString("yyyy-MM-dd HH:mm:ss", new Date()));
				middleware.setModifyUserId(loginUser.getUserId());
				AsopMiddleware middle = middlewareService.getAsopMiddlewareById(middleware.getId());
				if(null == middle){
					return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
				}
				middleware.setCheckCount(middle.getCheckCount());
				middleware.setLastCheckDate(middle.getLastCheckDate());
				baseService.saveOrUpdate(middleware);
			}
			
			return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
		}
	}
	/**
	 * 删除中间件
	 * @param deleteMiddleware
	 */
	@ResponseBody
	@RequestMapping("/deleteMiddleware.do")
	public JsonForm deleteMiddleware(String ids){
		try {
			if(!StringHelpers.isNull(ids)){
				String[] arr = ids.split(",");
				List<String> list = java.util.Arrays.asList(arr);
				middlewareService.deleteAsopMiddleware(list);
				return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
	}
	
	
	/**
	 * @Title: setMiddlewareUse
	 * @Description: 设置服务器启用、禁用状态
	 * @param id
	 * @return JsonForm
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/setMiddlewareUse.do")
	public JsonForm setMiddlewareUse(long id,String val){
		try {
			if(!StringHelpers.isNull(val)){
				middlewareService.isUseAsopMiddleware(id, val);
				return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
	}
	
	/**
	 * @Title: testTelnetConn
	 * @Description: 测试telnet连通性
	 * @param ip
	 * @return JsonForm
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/testTelnetConn.do")
	public JsonForm testTelnetConn(String ip,String port){
		try {
			if(!StringHelpers.isNull(ip) && !StringHelpers.isNull(port)){
				boolean result = TelnetUtil.testTelnet(ip, Integer.parseInt(port));
				return JsonForm.warpJsonForm(true, result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		//异常返回fail
		return JsonForm.warpJsonForm(false, "fail");
	}
	
	
}
