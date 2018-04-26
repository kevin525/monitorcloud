package com.apps.daily.database.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apps.daily.appsystem.domain.AsopAppSystem;
import com.apps.daily.database.domain.AsopDatabase;
import com.apps.daily.database.service.DatabaseService;
import com.common.FormatDateUtil;
import com.common.StringHelpers;
import com.common.constants.Constant;
import com.common.osplugins.JsonForm;
import com.common.pagetag.PageGridLoad;
import com.common.pagetag.PageGridPost;
import com.sys.domain.model.User;
import com.sys.service.BaseService;
import com.sys.util.DBUtil;
/**
 * 
 * @ClassName: DatabaseController 
 * @Description: 数据库控制层
 * @author 张梦琦 
 * @date 2017年11月3日 上午10:02:35
 */
@Controller
@RequestMapping(value="/database")
public class DatabaseController {
	
	@Autowired
	private BaseService baseService;
	
	@Autowired
	private DatabaseService databaseService;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * @Title: getList
	 * @Description: 分页获取数据库列表
	 * @param pageGridPost
	 * @return PageGridLoad
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/getDatabaseList.do")
	public PageGridLoad getList(AsopDatabase database,PageGridPost pageGridPost){
		PageGridLoad postGridLoad = new PageGridLoad();
		List<AsopDatabase> databaseList = databaseService.getList(database, pageGridPost);
		postGridLoad.setPageData(databaseList);
		postGridLoad.setTotalCount(pageGridPost.getTotalCount());
		return postGridLoad;
	}
	
	
	/**
	 * @Title: saveOrUpdate
	 * @Description: 创建、修改数据库信息
	 * @param request
	 * @param 
	 * @return JsonForm
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/saveDatabase.do")
	public JsonForm saveDatabase(HttpServletRequest request,final AsopDatabase database){
		try {
			User loginUser = (User) request.getSession().getAttribute("loginUser");
			if(database !=null){
				database.setCreateUserId(loginUser.getUserId());
				database.setCreateDate(FormatDateUtil.formatDateToString("yyyy-MM-dd HH:mm:ss", new Date()));
				database.setModifyDate(FormatDateUtil.formatDateToString("yyyy-MM-dd HH:mm:ss", new Date()));
				database.setModifyUserId(loginUser.getUserId());
				baseService.saveOrUpdate(database);
			}
			return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
		}
	}
	@ResponseBody
	@RequestMapping("/updateDatabase.do")
	public JsonForm updateDatabase(HttpServletRequest request,final AsopDatabase database){
		try {
			User loginUser = (User) request.getSession().getAttribute("loginUser");
			if(database !=null){
				AsopDatabase d= databaseService.getDatabaseById(database.getId());
				if(null == d){
					return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
				}
				database.setCheckCount(d.getCheckCount());
				database.setLastCheckDate(d.getLastCheckDate());
				database.setDataBasePwd("".equals(database.getDataBasePwd())?d.getDataBasePwd():database.getDataBasePwd());
				database.setModifyDate(FormatDateUtil.formatDateToString("yyyy-MM-dd HH:mm:ss", new Date()));
				database.setModifyUserId(loginUser.getUserId());
				baseService.saveOrUpdate(database);
			}
			return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
		}
	}
	
	/**
	 * 删除数据库
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
				databaseService.deleteDatabase(list);
				return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
	}
	
	
	/**
	 * 根据id获取数据库信息
	 */
	@ResponseBody
	@RequestMapping("/getById.do")
	public JsonForm getById(long id) {
		AsopDatabase database = databaseService.getDatabaseById(id);
		database.setDataBasePwd("");
		return JsonForm.warpJsonForm(true,database );
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
		System.out.println(val+"==================");
		try {
			if(!StringHelpers.isNull(val)){
				databaseService.isUseDatabase(id, val);
				return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
	}
	@ResponseBody
	@RequestMapping("/testDataBaseConn.do")
	public JsonForm testDataBaseConn(AsopDatabase database){
		
		boolean result = false;
		
		if(database.getId() !=0){
			AsopDatabase d = databaseService.getDatabaseById(database.getId());
			String pwd = "".equals(database.getDataBasePwd())? d.getDataBasePwd():database.getDataBasePwd();
			database.setDataBasePwd(pwd);
		}
		try {
			result=	 DBUtil.testDataBaseConn(database.getDataBaseIp(), database.getDataBaseUser(), database.getDataBasePwd(), database.getDataBaseType(), database.getDataBaseIns(),database.getDataBaseName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonForm.warpJsonForm(true, result);
	}
	
	@ResponseBody
	@RequestMapping("/getDatabaseListNoPage.do")
	public List<AsopDatabase> getDatabaseListNoPage(AsopDatabase asopDatabase){
		List<AsopDatabase> asopDatabaseList = databaseService.getList(asopDatabase);
		return asopDatabaseList;
	}
}
