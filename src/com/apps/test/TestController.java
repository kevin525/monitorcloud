package com.apps.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.common.constants.Constant;
import com.common.osplugins.JsonForm;
import com.common.pagetag.PageGridLoad;
import com.common.pagetag.PageGridPost;
import com.sys.domain.model.Role;
import com.sys.domain.model.User;
import com.sys.service.BaseService;
import com.sys.service.RoleService;

@Controller
@RequestMapping(value="/test")
public class TestController {
	
	@Autowired
	private BaseService baseService;
	
	@Autowired
	private RoleService roleService;
	
	
	@ResponseBody
	@RequestMapping("/getList2.do")
	public PageGridLoad getList2(PageGridPost pageGridPost){
		PageGridLoad postGridLoad = new PageGridLoad();
		List<User> userList = new ArrayList<User>();
		User user1 = new User();
		user1.setLoginName("kevin");
		user1.setLoginPwd("123456");
		userList.add(user1);
		
		User user2 = new User();
		user2.setLoginName("tom");
		user2.setLoginPwd("123456");
		userList.add(user2);
		
		postGridLoad.setPageData(userList);
		postGridLoad.setTotalCount(Long.parseLong("15"));
		return postGridLoad;
	}
	
	/**
	 * @Title: getString 
	 * @Description: 向页面返回字符串 
	 * @return    
	 * @return JsonForm    
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/getString.do")
	public JsonForm getString(){
		try {
			return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
		}
	}
	
	/**
	 * 获得角色的信息后，跳转到指定的页面
	 * @param opId
	 * @param url
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getRoleInforByRoleId.do")
	public ModelAndView getRoleInforByRoleId(long roleId, String url) {
		ModelAndView mav = new ModelAndView(url);
		mav.addObject("roleInfor", baseService.findById(Role.class,roleId));
		return mav;
	}
	
	
}
