package com.sys.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.common.AppConstant;
import com.common.constants.Constant;
import com.common.osplugins.JsonForm;
import com.common.pagetag.PageGridLoad;
import com.common.pagetag.PageGridPost;
import com.sys.domain.model.Person;
import com.sys.domain.model.Role;
import com.sys.domain.model.User;
import com.sys.domain.model.UserRoles;
import com.sys.domain.view.PersonView;
import com.sys.service.BaseService;
import com.sys.service.PersonService;
import com.sys.service.RoleService;
import com.sys.service.UserService;
import com.sys.service.impl.LoginServiceImpl;

@Controller
@RequestMapping("/user")
public class UserController {
	private static final Class<Person> Person = null;

	private static final Class<User> User = null;
	@Autowired
	private BaseService baseService;
	@Autowired
	private UserService userService;
	@Autowired
	private PersonService personService;
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private LoginServiceImpl loginServiceImpl;

	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 跳转到指定URL，并带有一个参数(点击的那个菜单)
	 * 
	 * @param url
	 * @param menu
	 * @return
	 */
	@RequestMapping("/accessPersonView.do")
	public ModelAndView accessPersonView(String url, String menu) {
		ModelAndView mdv = new ModelAndView(url);
		mdv.addObject("menu", menu);
		return mdv;
	}

	
	/**
	 * 多表分页查询用户信息
	 * 
	 * @param pageGridPost
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/userList2.do")
	public PageGridLoad getList22(PageGridPost pageGridPost) {
		PageGridLoad postGridLoad = new PageGridLoad();
		List<User> userList = new ArrayList<User>();
		userList = loginServiceImpl.personList(pageGridPost);
		List<PersonView> listView = new ArrayList<PersonView>();
		
		for (int i = 0; i < userList.size(); i++) {
			PersonView p=new PersonView();
			p.setUserId(userList.get(i).getUserId());
			List<Role> roles =roleService.getUserRolesByuserId(userList.get(i).getUserId());
			if(null != roles && roles.size()>0 ){
				p.setRole(roles.get(0));
			}
			p.setLoginName(userList.get(i).getLoginName());
			p.setPersonId(userList.get(i).getPerson().getPersonId());
			p.setPersonName(userList.get(i).getPerson().getPersonName());
			p.setPersonTel(userList.get(i).getPerson().getPersonTel());
			p.setCreateDate( df.format(userList.get(i).getPerson().getCreateDate()));
			p.setPersonState(userList.get(i).getPerson().getPersonState());
			p.setSex(userList.get(i).getPerson().getSex());
			listView.add(p);
		};
		postGridLoad.setPageData(listView);
		String count = loginServiceImpl.countPerson();
		postGridLoad.setTotalCount(Long.parseLong(count));
		return postGridLoad;
	}
	
	@ResponseBody
	@RequestMapping("/getAllUser.do")
	public List<User> getAllUser() {
		List<User> userList = new ArrayList<User>();
		userList = loginServiceImpl.getAllPersonList();
		return userList;
	}

	/**
	 * @Title: getString @Description: 向页面返回字符串 @return @return JsonForm @throws
	 */
	@ResponseBody
	@RequestMapping("/userString.do")
	public JsonForm getString() {
		try {
			return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
		}
	}

	



	/**
	 * 查看用户是否存在
	 * 
	 * @param loginName
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/checkUserExists.do")
	public JsonForm checkUserExists(String loginName) {
		try {
			User user = userService.getUserByLoginName(loginName,
					AppConstant.safeValue);
			if (user == null) {
				return JsonForm.warpJsonForm(true, "notExists");
			} else {
				return JsonForm.warpJsonForm(true, "exists");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonForm.warpJsonForm(false, "查询用户是否存在时出错！");
		}
	}

	/**
	 * 修改密码
	 * 
	 * @param userId
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/modifyUserPwd.do")
	public JsonForm modifyUserPwd(HttpServletRequest request, String oldPwd, String newPwd) {
		try {
			User  user=(User) request.getSession().getAttribute("loginUser");
			if (user != null && user.getLoginPwd().equals(oldPwd)) {
				userService.updateUserInfo("loginPwd", newPwd, user.getUserId());
				return JsonForm.warpJsonForm(true, "修改密码成功!");
			} else {
				return JsonForm.warpJsonForm(false, "原密码错误!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonForm.warpJsonForm(false, "修改用户密码出错了！");
		}
	}
		
	
	
	
	/**
	 * 保存用户与角色的关联关系
	 * @param request
	 * @param roles
	 * @param users
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveUserRole.do")
	public JsonForm saveUserRole(HttpServletRequest request,String roles,String users){
		try {
			List<UserRoles> lurs=new ArrayList<UserRoles>();
			if(StringUtils.isNotBlank(roles) && StringUtils.isNotBlank(users)){
				String[] userLists=users.split(",");
				for(String userId:userLists){
					User us=userService.getUserByUserId(new Long(userId));
					String[] rls=roles.split(",");
					List<UserRoles> listUserRoles=userService.getUserRolesByUserId(us.getUserId());
						for(String roleId:rls){
							if(StringUtils.isNotBlank(roleId)){
								UserRoles ur=new UserRoles();
								boolean isExitsUserRole=false;
								if(listUserRoles!=null && listUserRoles.size()>0){
									for(UserRoles urs:listUserRoles){
										if(urs.getrId()==Long.parseLong(roleId)){
											isExitsUserRole=true;
										}
									}
								}
								if(!isExitsUserRole){
									Role rl=roleService.getRoleByRoleId(new Long(roleId));
									ur.setRole(rl);
									ur.setrId(rl.getRoleId());
									ur.setUser(us);
									ur.setUsId(us.getUserId());
									ur.setuRState(1);
									lurs.add(ur);
								}
							}
						}
				}
				if(lurs.size()>0){
					baseService.saveAll(lurs);
				}
			}
			return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
		}
	}
	/**
	 * 保存用户与角色的关联关系
	 * @param request
	 * @param roles
	 * @param users
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveOrUpdateUserRole.do")
	public JsonForm saveOrUpdateUserRole(HttpServletRequest request,String roleId,String userId){
		try {
			User us=userService.getUserByUserId(new Long(userId));
			List<UserRoles> userRoles=userService.getUserRolesByUserId(us.getUserId());
			Role rl=roleService.getRoleByRoleId(new Long(roleId));
			if(null != userRoles && userRoles.size() >0){
				userRoles.get(0).setrId(new Long(roleId));
				userRoles.get(0).setRole(rl);
				userService.saveOrUpdate(userRoles.get(0));
			}else{
				UserRoles ur=new UserRoles();
				ur.setRole(rl);
				ur.setrId(rl.getRoleId());
				ur.setUser(us);
				ur.setUsId(us.getUserId());
				ur.setuRState(1);
				userService.saveOrUpdate(ur);
			}
			
			return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
		}
	}
	
	/**
	 * 删除用户角色关系
	 * @param request
	 * @param roles
	 * @param userId
	 */
	@ResponseBody
	@RequestMapping("/delUserRole.do")
	public JsonForm delUserRole(HttpServletRequest request,String roles,String userId){
		try {
			if(StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(roles)){
				List<UserRoles> listUserRoles=userService.getUserRolesByUserId(new Long(userId));
				if(listUserRoles!=null && listUserRoles.size()>0){
					for(UserRoles ur:listUserRoles){
						roles=","+roles+",";
						if(roles.contains(","+ur.getrId()+",")){
							baseService.delete(UserRoles.class, ur.getUrid());
						}
					}
				}
			}
			return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
		}
	}
	/**
	 * 通过用户id获得用户的详细信息,跳转到编辑页面
	 * 
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getUserByUserId.do")
	public JsonForm getUserInfoByUserId(long userId) {
		User user = (User) baseService.findById(User.class,userId);
		Person person = (Person)baseService.findById(Person.class,user.getPerId());
		PersonView p = new PersonView();
		p.setUserId(user.getUserId());
		List<Role> roles =roleService.getUserRolesByuserId(userId);
		if(null != roles && roles.size()>0 ){
			p.setRole(roles.get(0));
		}
		p.setLoginName(user.getLoginName());
		p.setPersonId(person.getPersonId());
		p.setPersonName(person.getPersonName());
		p.setPersonTel(person.getPersonTel());
		p.setCreateDate( df.format(person.getCreateDate()));
		p.setPersonState(person.getPersonState());
		p.setSex(person.getSex());
		return JsonForm.warpJsonForm(true, p);
	}
	/**
	 * 批量删除
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/delete.do")
	@ResponseBody
	public String deletePerson(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String sourceStr = request.getParameter("str");
		String[] love = sourceStr.split(",");
		for (int i = 0; i < love.length; i++) {
			loginServiceImpl.oneUserDelete(User, Integer.parseInt(love[i]));
			loginServiceImpl.oneDelete(Person,Integer.parseInt(love[i]));
		}
			return "success";

	}

	/**
	 * 单个删除
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/oneDelete.do")
	@ResponseBody
	public String oneDelete(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String sourceStr = request.getParameter("personId");
		int Temp = Integer.parseInt(sourceStr);
		if ( loginServiceImpl.oneUserDelete(User, Temp)>0 && loginServiceImpl.oneDelete(Person, Temp)> 0 ) {
			return "success";
		} else {
			return "error";
		}

	}
	/**
	 * 查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/oneSelect.do")
	@ResponseBody
	public PageGridLoad selectPerson(PageGridPost pageGridPost) {
		String personName =(String) pageGridPost.getQueryPara();// request.getParameter("personName") == null ?"":request.getParameter("personName");
			List<User> userList = new ArrayList<User>();
			userList =loginServiceImpl.oneSelect(personName);
			List<PersonView> listView = new ArrayList<PersonView>();
			for (int i = 0; i < userList.size(); i++) {
				PersonView p=new PersonView();
				p.setUserId(userList.get(i).getUserId());
				p.setLoginName(userList.get(i).getLoginName());
				p.setPersonId(userList.get(i).getPerson().getPersonId());
				p.setPersonName(userList.get(i).getPerson().getPersonName());
				p.setPersonTel(userList.get(i).getPerson().getPersonTel());
				p.setCreateDate(df.format(userList.get(i).getPerson().getCreateDate()));
				p.setPersonState(userList.get(i).getPerson().getPersonState());
				p.setSex(userList.get(i).getPerson().getSex());
				listView.add(p);
			};
			PageGridLoad postGridLoad = new PageGridLoad();
			postGridLoad.setPageData(listView);
			int count = listView.size();
			postGridLoad.setTotalCount((long) count);
			return postGridLoad;

	}
	
	/**
	 * 修改用户
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/oneUpdate.do")
	@ResponseBody
	public String updateUser(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws ParseException {
		String per=request.getParameter("personId");
		int personId=Integer.parseInt(per);
		String personName=request.getParameter("personName");
		String personTel=request.getParameter("personTel");
		String create=request.getParameter("createDate");
		java.text.SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date createDate=  formatter.parse(create);
		String personState=request.getParameter("personState");
		int state=Integer.parseInt(personState);
		String sex1=request.getParameter("sex");
		int sex=Integer.parseInt(sex1);
		Person person=new Person(personId, personName, sex, personTel, state,createDate);
		String role1=request.getParameter("role");
		String userId=request.getParameter("userId");
		saveOrUpdateUserRole(request, role1, userId);
		int p= loginServiceImpl.oneUpdate(person);
		if(p>0){
			return "success";
		}
		return "error";
	}
	/**
	 * 添加用户
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/addUser.do")
	@ResponseBody
	public String addUser(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws ParseException {
		String personName=request.getParameter("personName");
		String personTel=request.getParameter("personTel");
		String personState=request.getParameter("personState");
		int state=Integer.parseInt(personState);
		String sex1=request.getParameter("sex");
		int sex=Integer.parseInt(sex1);
	
		String pinyin=request.getParameter("pinyin");
		String loginName=request.getParameter("loginName");
		Date createDate=  new Date();
		Person person=new Person(personName, sex, personTel, state,pinyin,createDate);
		User user=new User();
		user.setLoginName(loginName);
		int u = loginServiceImpl.oneInsert(person,user);
		
		String role1=request.getParameter("role");
		saveOrUpdateUserRole(request, role1, user.getUserId()+"");
		if(u>0){
			return "success";
		}else{
		return "error";
		}
	}
	
}
