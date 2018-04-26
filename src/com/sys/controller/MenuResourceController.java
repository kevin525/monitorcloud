package com.sys.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.StringHelpers;
import com.common.constants.Constant;
import com.common.osplugins.JsonForm;
import com.common.pagetag.PageGridLoad;
import com.common.pagetag.PageGridPost;
import com.sys.domain.model.Resources;
import com.sys.domain.model.User;
import com.sys.service.BaseService;
import com.sys.service.MenuResourceService;
import com.sys.service.RoleResourcesService;
/**
 * @ClassName: MenuResourceController
 * @Description: 菜单资源控制类
 * @author LG
 * @date 2017年9月29日 下午3:04:22
 */
@Controller
@RequestMapping(value="/menuResource")
public class MenuResourceController {
	
	@Autowired
	private BaseService baseService;
	
	@Autowired
	private MenuResourceService menuResourceService;
	
	@Autowired
	private RoleResourcesService roleResourcesService;
	
	/**
	 * @Title: getMenuResourceList
	 * @Description: 分页获取菜单资源列表
	 * @param resources
	 * @param pageGridPost
	 * @return PageGridLoad
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/getMenuResourceList.do")
	public PageGridLoad getMenuResourceList(Resources resources,PageGridPost pageGridPost){
		PageGridLoad postGridLoad = new PageGridLoad();
		List<Resources> resourcesList = menuResourceService.getResourcesList(resources, pageGridPost);
		postGridLoad.setPageData(resourcesList);
		postGridLoad.setTotalCount(pageGridPost.getTotalCount());
		return postGridLoad;
	}
	
	/**
	 * @Title: getFirstMenuList
	 * @Description: 获得一级导航菜单列表
	 * @param resources
	 * @return
	 * @return PageGridLoad
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/getFirstMenuList.do")
	public JsonForm getFirstMenuList(Resources resources){
		resources.setNodeValue(1);
		resources.setResourcesPid(0);
		List<Resources> resourcesList = menuResourceService.getResourcesList(resources);
		return JsonForm.warpJsonForm(true, resourcesList);
	}
	/**
	 * @Title: getFirstMenuList
	 * @Description: 获得所有导航菜单列表
	 * @param resources
	 * @return
	 * @return PageGridLoad
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/getAllMenuList.do")
	public JsonForm getXMenuList(String roleid){
		List<Resources> resourcesList = menuResourceService.getResourcesList(null);
		List<Long> longs = roleResourcesService.getResourcesIdByRoleId(Long.parseLong(roleid));
		
		String s="";
		if(null !=longs && longs.size()>0 ){
			for (Long l : longs) {
				s += l+",";
			}
			return JsonForm.warpJsonForm(true, resourcesList,s);
		}else{
			return JsonForm.warpJsonForm(true, resourcesList,null);
		}
	}
	/**
	 * @Title: saveOrUpdateMenuResource
	 * @Description: 创建、修改菜单资源
	 * @param request
	 * @param resources
	 * @return JsonForm
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/saveOrUpdateMenuResource.do")
	public JsonForm saveOrUpdateMenuResource(HttpServletRequest request,Resources resources){
		try {
			User loginUser = (User) request.getSession().getAttribute("loginUser");
			if(resources!=null){
				if(resources.getResourcesId()!=0){
					resources.setModifyDate(new Date());
					resources.setModifyUserId(loginUser.getUserId());
					resources.setCreateDate(new Date());
				}else{
					resources.setCreateDate(new Date());
					resources.setCreateUserId(loginUser.getUserId());
					resources.setCreateDate(new Date());
				}
			}
			baseService.saveOrUpdate(resources);
			return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
		}
	}
	
	/**
	 * 删除菜单资源
	 * @param deleteMenuResource
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteMenuResource.do")
	public JsonForm deleteMenuResource(String ids){
		try {
			if(!StringHelpers.isNull(ids)){
				String[] arr = ids.split(",");
				List<String> list = java.util.Arrays.asList(arr);
				roleResourcesService.deleteRoleResources(list);
				menuResourceService.deleteResources(list);
				return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
	}
	
	/**
	 * @Title: getServerInfo
	 * @Description: 获取指定菜单资源
	 * @param id
	 * @return
	 * @return Resources
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/getMenuResource.do")
	public JsonForm getMenuResource(long id) {
		Resources resources = (Resources)baseService.findById(Resources.class,id);
		if(resources.getNodeValue()==2){
			//说明是二级菜单
			Resources PResources = (Resources)baseService.findById(Resources.class,resources.getResourcesPid());
			return JsonForm.warpJsonForm(true, resources,PResources.getResourcesId()+"");
		}else{
			return JsonForm.warpJsonForm(true, resources);
		}
	}
	
	/**
	 * @Title: setServerUse
	 * @Description: 设置应用系统启用、禁用状态
	 * @param id
	 * @return
	 * @return JsonForm
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/setUseStatus.do")
	public JsonForm setUseStatus(long id,String val){
		try {
			if(!StringHelpers.isNull(val)){
				menuResourceService.isUseResources(id, Integer.parseInt(val));
				return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
	}
	
}
