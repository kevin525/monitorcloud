package com.sys.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.common.FormatDateUtil;
import com.common.StringHelpers;
import com.common.constants.Constant;
import com.common.osplugins.JsonForm;
import com.common.pagetag.PageGridLoad;
import com.common.pagetag.PageGridPost;
import com.sys.domain.model.Role;
import com.sys.domain.model.User;
import com.sys.service.BaseService;
import com.sys.service.RoleService;

@Controller
@RequestMapping(value="/role")
public class RoleController {
	
	@Autowired
	private BaseService baseService;
	
	@Autowired
	private RoleService roleService;
	
	/**
	 * 通过查询条件获得角色的展示列表
	 * @param role
	 * @param pageGridPost
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getPageRoleDataAndView.do")
	public PageGridLoad getPageRoleDataAndView(Role role,PageGridPost pageGridPost){
		PageGridLoad postGridLoad = new PageGridLoad();
		List<Role> listRoleView=roleService.getRoleList(role, pageGridPost);
		postGridLoad.setPageData(listRoleView);
		postGridLoad.setTotalCount(pageGridPost.getTotalCount());
		return postGridLoad;
	}
	 
	/**
	 * 通过查询条件获得角色的展示列表，用于用户管理模块
	 * @param role
	 * @param pageGridPost
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getRoleListByCriteria.do")
	public JsonForm getRoleListByCriteria(Role role){
		List<Role> listRoleView=roleService.getRoleListByCriteria(role);
		return JsonForm.warpJsonForm(true,listRoleView);
	}
	 
	
	/**
	 * 创建、修改角色
	 * @param request
	 * @param role
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveOrUpdateRole.do")
	public JsonForm saveOrUpdateRole(HttpServletRequest request,Role role){
		try {
			User loginUser = (User) request.getSession().getAttribute("loginUser");
			if(null == loginUser){
				return JsonForm.warpJsonForm(false, "noLogin");
			}
			if(role!=null){
				if(role.getRoleId()!=0){
					role.setModifyDate(new Date());
					role.setModifyUserId(loginUser.getUserId());
					role.setRoleCreatTime(FormatDateUtil.formatDateToString("yyyy-MM-dd HH:mm:ss", new Date()));
				}else{
					role.setCreateDate(new Date());
					role.setCreateUserId(loginUser.getUserId());
					role.setRoleCreatTime(FormatDateUtil.formatDateToString("yyyy-MM-dd HH:mm:ss", new Date()));
				}
				baseService.saveOrUpdate(role);
			}
			return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
		}
	}
	
	
	/**
	 * 通过用户id获得角色的分页列表(只包含角色启用的，不启用的不在列表中)
	 * @param userId
	 * @param pageGridPost
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getPageUserRoleByUserId.do")
	public PageGridLoad getPageUserRoleByUserId(long userId,String roleName,PageGridPost pageGridPost){
		PageGridLoad postGridLoad = new PageGridLoad();
		try {
			List<Role> listRoleView=roleService.getPageUserRolesByuserId(userId,roleName, pageGridPost);
			postGridLoad.setPageData(listRoleView);
			postGridLoad.setTotalCount(pageGridPost.getTotalCount());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return postGridLoad;
	}
	
	/**
	 * 删除角色
	 * @param roleId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteRole.do")
	public JsonForm deleteRole(long roleId){
		try {
			roleService.deleteRole(roleId);
			return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
		}
	}
	
	/**
	 * 批量删除
	 * @param deleteRoles
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteRoles.do")
	public JsonForm deleteRoles(String roleIds){
		try {
			if(!StringHelpers.isNull(roleIds)){
				String[] arr = roleIds.split(",");
				List<String> list = java.util.Arrays.asList(arr);
				roleService.deleteRoles(list);
				return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
	}
	
	/**
	 * 获得角色的信息后，跳转到指定的页面
	 * @param opId
	 * @param url
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getRoleInforByRoleId.do")
	public Role getRoleInforByRoleId(long roleId) {
		Role role = (Role) baseService.findById(Role.class,roleId);
		return role;
	}
}
