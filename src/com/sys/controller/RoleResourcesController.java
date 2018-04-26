package com.sys.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.osplugins.JsonForm;
import com.sys.domain.model.Resources;
import com.sys.domain.model.Role;
import com.sys.domain.model.RoleResources;
import com.sys.domain.model.User;
import com.sys.domain.model.UserRoles;
import com.sys.service.MenuResourceService;
import com.sys.service.RoleResourcesService;
import com.sys.service.RoleService;
import com.sys.service.UserService;

@Controller
@RequestMapping(value="/roleResources")
public class RoleResourcesController {
	@Autowired
	private RoleResourcesService roleResourcesService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private MenuResourceService menuResourceService;
	
	@Autowired
	private UserService userService;
	 
		/**
		 * 保存角色对菜单的设置
		 * @param role
		 * @param pageGridPost
		 * @return
		 */
		@ResponseBody
		@RequestMapping("/save.do")
		public JsonForm getRoleListByCriteria(String roId,String checkIds,String nocheckIds){
			if(!"".equals(checkIds)){
			String[] rsIdcheck=checkIds.split(",");
			for (String string : rsIdcheck) {
				
				RoleResources oldrResources =	roleResourcesService.getByRoidRsid(roId, string);
				if(null != oldrResources){
					oldrResources.setrPrivState(1);
					roleResourcesService.saveOrUpdateRoleResources(oldrResources);
				}else{
					RoleResources roleResources = new RoleResources();
					Role role = roleService.getRoleByRoleId(Long.parseLong(roId));
					roleResources.setRole(role);
					Resources resources =menuResourceService.getResourcesById(Long.parseLong(string));
					roleResources.setResources(resources);
					roleResources.setRoId(Long.parseLong(roId));
					roleResources.setRsId(Long.parseLong(string));
					roleResources.setrPrivState(1);
					roleResourcesService.saveOrUpdateRoleResources(roleResources);
				}
			}
			}
			if(!"".equals(nocheckIds)){
			String[] rsIdnocheck=nocheckIds.split(",");
			for (String string : rsIdnocheck) {
				
				RoleResources oldrResources =	roleResourcesService.getByRoidRsid(roId, string);
				if(null != oldrResources){
					oldrResources.setrPrivState(2);
					roleResourcesService.saveOrUpdateRoleResources(oldrResources);
				}else{
					RoleResources roleResources = new RoleResources();
					Role role = roleService.getRoleByRoleId(Long.parseLong(roId));
					roleResources.setRole(role);
					Resources resources =menuResourceService.getResourcesById(Long.parseLong(string));
					roleResources.setResources(resources);
					roleResources.setRoId(Long.parseLong(roId));
					roleResources.setRsId(Long.parseLong(string));
					roleResources.setrPrivState(2);
					roleResourcesService.saveOrUpdateRoleResources(roleResources);
				}
			}
			}
			return JsonForm.warpJsonForm(true,"保存成功");
		}
		/**
		 * 获得登录的用户所拥有的权限
		 * @param request
		 * @return
		 */
		@ResponseBody
		@RequestMapping("/getResources.do")
		public JsonForm  getResources(HttpServletRequest request){
			User  user=(User) request.getSession().getAttribute("loginUser");
			UserRoles userRoles =userService.getUserRolesByUserId(user.getUserId()).get(0);
			List<Long> list =roleResourcesService.getResourcesIdByRoleId(userRoles.getrId());
			List<Resources> resourceslist=menuResourceService.getResourcesList(null);//全部的菜单
			List<Resources> removeList= new ArrayList<Resources>();//要移除的菜单
			for (Resources resources : resourceslist) {
				if(!list.contains(resources.getResourcesId())){
					removeList.add(resources);
				}
			}
			for (Resources resources : removeList) {
				resourceslist.remove(resources);
			}
			/*for(int i =0;i<resourceslist.size();i++){
				if(!list.contains(resourceslist.get(i).getResourcesId())){
					removeList.remove(resourceslist.get(i));
					i--;
				}
			}*/
			return JsonForm.warpJsonForm(true,resourceslist);
		}
}
