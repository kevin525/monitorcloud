package com.apps.daily.tomcat.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apps.daily.appsystem.domain.AsopAppSystem;
import com.apps.daily.tomcat.domain.AsopTomcat;
import com.apps.daily.tomcat.service.TomcatService;
import com.common.FormatDateUtil;
import com.common.StringHelpers;
import com.common.constants.Constant;
import com.common.osplugins.JsonForm;
import com.common.pagetag.PageGridLoad;
import com.common.pagetag.PageGridPost;
import com.common.utils.TomcatHTMLUtil;
import com.sys.domain.model.User;
import com.sys.service.BaseService;
import com.sys.util.DBUtil;
/**
 * 
 * @ClassName: TomcatController 
 * @Description: 中间件控制层
 * @author 张梦琦 
 * @date 2017年11月9日 下午4:08:21
 */
@Controller
@RequestMapping(value="/tomcat")
public class TomcatController {
	
	@Autowired
	private BaseService baseService;
	
	@Autowired
	private TomcatService tomcatService;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * @Title: getList
	 * @Description: 分页获取中间件列表
	 * @param pageGridPost
	 * @return PageGridLoad
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/getTomcatList.do")
	public PageGridLoad getList(AsopTomcat tomcat,PageGridPost pageGridPost){
		PageGridLoad postGridLoad = new PageGridLoad();
		List<AsopTomcat> tomcatList = tomcatService.getList(tomcat, pageGridPost);
		postGridLoad.setPageData(tomcatList);
		postGridLoad.setTotalCount(pageGridPost.getTotalCount());
		return postGridLoad;
	}
	
	
	/**
	 * @Title: saveOrUpdate
	 * @Description: 创建、修改中间件信息
	 * @param request
	 * @param 
	 * @return JsonForm
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/saveTomcat.do")
	public JsonForm saveTomcat(HttpServletRequest request,final AsopTomcat tomcat){
		try {
			User loginUser = (User) request.getSession().getAttribute("loginUser");
			if(tomcat !=null){
					
				tomcat.setCreateUserId(loginUser.getUserId());
				tomcat.setCreateDate(FormatDateUtil.formatDateToString("yyyy-MM-dd HH:mm:ss", new Date()));
				tomcat.setModifyDate(FormatDateUtil.formatDateToString("yyyy-MM-dd HH:mm:ss", new Date()));
				tomcat.setModifyUserId(loginUser.getUserId());
				baseService.saveOrUpdate(tomcat);
			}
			return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
		}
	}
	@ResponseBody
	@RequestMapping("/udateTomcat.do")
	public JsonForm udateTomcat(HttpServletRequest request,final AsopTomcat tomcat){
		try {
			User loginUser = (User) request.getSession().getAttribute("loginUser");
			if(tomcat !=null){
				tomcat.setModifyDate(FormatDateUtil.formatDateToString("yyyy-MM-dd HH:mm:ss", new Date()));
				tomcat.setModifyUserId(loginUser.getUserId());
				AsopTomcat t = tomcatService.getTomcatById(tomcat.getId());
				if(null == t){
					return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
				}
				tomcat.setCheckCount(t.getCheckCount());
				tomcat.setLastCheckDate(t.getLastCheckDate());
				baseService.saveOrUpdate(tomcat);
			}
			return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
		}
	}
	
	/**
	 * 删除中间件
	 * @param delete
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delete.do")
	public JsonForm delete(String ids){
		try {
			if(!StringHelpers.isNull(ids)){
				String[] arr = ids.split(",");
				List<String> list = java.util.Arrays.asList(arr);
				tomcatService.deleteTomcat(list);
				return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
	}
	
	
	/**
	 * 根据id获取中间件信息
	 */
	@ResponseBody
	@RequestMapping("/getById.do")
	public JsonForm getById(long id) {
		
		return JsonForm.warpJsonForm(true, tomcatService.getTomcatById(id));
	}
	/**
	 * 设置数据启用禁用
	 * @param id
	 * @param val
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/setUse.do")
	public JsonForm setUse(long id,String val){
		try {
			if(!StringHelpers.isNull(val)){
				tomcatService.isUseTomcat(id, val);
				return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
	}
	@ResponseBody
	@RequestMapping("/testTomcatConn.do")
	public JsonForm testTomcatConn(AsopTomcat tomcat){
		String webAppName = tomcat.getWebAppName().split(",")[0];
		boolean result = false;
		try {
			result=	TomcatHTMLUtil.startWebApp(tomcat.getManagerUrl(),webAppName, tomcat.getManagerUserName(), tomcat.getManagerPassword(),Integer.parseInt( tomcat.getVersion()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonForm.warpJsonForm(true, result);
	}
	
	@ResponseBody
	@RequestMapping("/getTomcatListNoPage.do")
	public List<AsopTomcat> getTomcatListNoPage(AsopTomcat asopTomcat){
		List<AsopTomcat> asopTomcatList = tomcatService.getList(asopTomcat);
		return asopTomcatList;
	}
}
