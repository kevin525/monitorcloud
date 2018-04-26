package com.apps.daily.project.service;

import java.util.List;

import com.apps.daily.appsystem.domain.AsopAppSystem;
import com.apps.daily.project.domain.AsopProject;
import com.common.pagetag.PageGridPost;

/**
 * @ClassName: ProjectService
 * @Description: 项目业务逻辑处理层
 * @author LG
 * @date 2017年9月22日 上午10:02:51
 */
public interface ProjectService {

	
	/**
	 * @Title: saveOrupdateProject
	 * @Description: 保存或者更新项目
	 * @param project
	 * @return void
	 * @throws
	 */
	public void saveOrupdateProject(AsopProject project);
	
	/**
	 * @Title: getProjectById
	 * @Description: 根据应用系统id获取项目
	 * @param id
	 * @return AsopProject
	 * @throws
	 */
	public AsopProject getProjectById(long id);
	
	
	
	/**
	 * @Title: deleteProject
	 * @Description: 删除项目
	 * @param id
	 * @return void
	 * @throws
	 */
	public void deleteProject(List<String> ids);
	
	
	/**
	 * @Title: save
	 * @Description: 保存对象
	 * @param o
	 * @return void
	 * @throws
	 */
	public void save(Object o);
	
	
	/**
	 * @Title: getProjectList
	 * @Description: 分页展示项目列表
	 * @param project
	 * @param pageGridPost
	 * @return List<AsopProject>
	 * @throws
	 */
	public List<AsopProject> getProjectList(AsopProject project,PageGridPost pageGridPost);
	
	/**
	 * @Title: getProjectList
	 * @Description: 不分页展示项目列表
	 * @param project
	 * @return List<AsopProject>
	 * @throws
	 */
	public List<AsopProject> getProjectList(AsopProject project);
	
	/**
	 * @Title: isUseProject
	 * @Description: 是否启用或者禁用项目
	 * @param id
	 * @param val
	 * @return void
	 * @throws
	 */
	public void isUseProject(long id,String val);

}