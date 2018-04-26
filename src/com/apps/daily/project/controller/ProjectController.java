package com.apps.daily.project.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.apps.daily.appsystem.domain.AsopAppSystem;
import com.apps.daily.appsystem.service.AppSystemService;
import com.apps.daily.database.domain.AsopDatabase;
import com.apps.daily.database.service.DatabaseService;
import com.apps.daily.middleware.domain.AsopMiddleware;
import com.apps.daily.middleware.service.MiddlewareService;
import com.apps.daily.project.domain.AsopProject;
import com.apps.daily.project.service.ProjectService;
import com.apps.daily.server.domain.AsopServer;
import com.apps.daily.server.service.ServerService;
import com.apps.daily.tomcat.domain.AsopTomcat;
import com.apps.daily.tomcat.service.TomcatService;
import com.common.FormatDateUtil;
import com.common.StringHelpers;
import com.common.constants.Constant;
import com.common.osplugins.JsonForm;
import com.common.pagetag.PageGridLoad;
import com.common.pagetag.PageGridPost;
import com.sys.domain.model.User;
import com.sys.service.BaseService;
/**
 * @ClassName: ProjectController
 * @Description: 项目控制层
 * @author LG
 * @date 2017年9月22日 上午10:39:52
 */
@Controller
@RequestMapping(value="/project")
public class ProjectController {
	
	@Autowired
	private BaseService baseService;
	
	@Autowired
	private ProjectService projectService;
	@Autowired
	private AppSystemService appSystemService;
	@Autowired
	private DatabaseService databaseService;
	@Autowired
	private ServerService serverService;
	@Autowired
	private TomcatService tomcatService;
	@Autowired
	private MiddlewareService middlewareService;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * @Title: getProjectList
	 * @Description: 分页获取应用系统列表
	 * @param appSystem
	 * @param pageGridPost
	 * @return PageGridLoad
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/getProjectList.do")
	public PageGridLoad getProjectList(AsopProject project,PageGridPost pageGridPost){
		PageGridLoad postGridLoad = new PageGridLoad();
		List<AsopProject> projectList = projectService.getProjectList(project, pageGridPost);
		postGridLoad.setPageData(projectList);
		postGridLoad.setTotalCount(pageGridPost.getTotalCount());
		return postGridLoad;
	}
	
	@ResponseBody
	@RequestMapping("/getAllProject.do")
	public List<AsopProject> getAllProject(AsopProject project){
		List<AsopProject> projectList = projectService.getProjectList(project);
		return projectList;
	}
	
	
	/**
	 * @Title: saveOrUpdateAppSystem
	 * @Description: 创建、修改应用系统信息
	 * @param request
	 * @param appSystem
	 * @return JsonForm
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/saveOrUpdateProject.do")
	public JsonForm saveOrUpdateProject(HttpServletRequest request,final AsopProject project){
		try {
			User loginUser = (User) request.getSession().getAttribute("loginUser");
			if(null == loginUser){
				return JsonForm.warpJsonForm(false, "noLogin");
			}
			if(project !=null){
				if(project.getId()==0){
					project.setCreateUserId(loginUser.getUserId());
					project.setCreateDate(FormatDateUtil.formatDateToString("yyyy-MM-dd HH:mm:ss", new Date()));
				}
				project.setModifyDate(FormatDateUtil.formatDateToString("yyyy-MM-dd HH:mm:ss", new Date()));
				project.setModifyUserId(loginUser.getUserId());
				baseService.saveOrUpdate(project);
			}
			return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
		}
	}
	
	/**
	 * 删除项目
	 * @param deleteProject
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteProject.do")
	public JsonForm deleteProject(String ids){
		try {
			if(!StringHelpers.isNull(ids)){
				String[] arr = ids.split(",");
				List<String> list = java.util.Arrays.asList(arr);
				projectService.deleteProject(list);
				return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
	}
	
	/**
	 * @Title: getProject
	 * @Description: 获取项目信息后，跳转到指定的页面
	 * @param id
	 * @param url
	 * @return
	 * @return ModelAndView
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/getProject.do")
	public ModelAndView getProject(long id, String url) {
		ModelAndView mav = new ModelAndView(url);
		mav.addObject("project", baseService.findById(AsopProject.class,id));
		return mav;
	}
	
	/**
	 * 根据id获取项目信息
	 */
	@ResponseBody
	@RequestMapping("/getProjectById.do")
	public JsonForm getProjectById(long id) {
		
		return JsonForm.warpJsonForm(true, baseService.findById(AsopProject.class,id));
	}
	/**
	 * @Title: setProjectUse
	 * @Description: 设置应用系统启用、禁用状态
	 * @param id
	 * @return
	 * @return JsonForm
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/setProjectUse.do")
	public JsonForm setProjectUse(long id,String val){
		try {
			if(!StringHelpers.isNull(val)){
				projectService.isUseProject(id, val);
				return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
	}
	
	@ResponseBody
	@RequestMapping("/getMainTotal.do")
	public int[] getMainTotal(){
		int[] total = new int[5];
		try {
			AsopProject project = new AsopProject();
			project.setIsUse(Constant.ACTIVITY);
			List<AsopProject> projects = projectService.getProjectList(project);
			total[0] = projects==null?0:projects.size();
			
			
			AsopServer server = new AsopServer();
			server.setIsUse(Constant.ACTIVITY);
			List<AsopServer> servers = serverService.getAsopServerList(server);
			total[1] = servers==null?0:servers.size();
			
			AsopDatabase database = new AsopDatabase();
			database.setIsUse(Constant.ACTIVITY);
			List<AsopDatabase> databases = databaseService.getList(database);
			total[2] = databases==null?0:databases.size();
			
			AsopMiddleware middleware = new AsopMiddleware();
			middleware.setIsUse(Constant.ACTIVITY);
			List<AsopMiddleware> middlewares = middlewareService.getAsopMiddlewareList(middleware);
			total[3] = middlewares==null?0:middlewares.size();
			
			AsopAppSystem system = new AsopAppSystem();
			system.setIsUse(Constant.ACTIVITY);
			List<AsopAppSystem> appSystems = appSystemService.getAppSystemList(system);
			total[4] = appSystems==null?0:appSystems.size();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
		
	}
	
	
}
