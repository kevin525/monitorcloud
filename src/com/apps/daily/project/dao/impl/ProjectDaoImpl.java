package com.apps.daily.project.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.apps.daily.appsystem.dao.AppSystemDao;
import com.apps.daily.appsystem.domain.AsopAppSystem;
import com.apps.daily.project.dao.ProjectDao;
import com.apps.daily.project.domain.AsopProject;
import com.apps.daily.server.domain.AsopServer;
import com.common.StringHelpers;
import com.common.constants.Constant;
import com.common.pagetag.PageGridPost;
import com.sys.dao.impl.BaseDaoImpl;

/**
 * @ClassName: ProjectDaoImpl
 * @Description: ProjectDao实现类
 * @author LG
 * @date 2017年9月22日 上午10:16:19
 */
@Repository(value="projectDao")
public class ProjectDaoImpl extends BaseDaoImpl implements ProjectDao {

	@Override
	public void saveOrupdateProject(AsopProject project) {
		this.saveOrUpdate(project);
	}

	@Override
	public AsopProject getProjectById(long id) {
		return (AsopProject) this.findById(AsopProject.class,id);
	}

	@Override
	public void deleteProject(long id) {
		AsopProject project = (AsopProject) this.findById(AsopProject.class,id);
		project.setIsUse(Constant.DELETE);
		this.update(project);
	}

	@Override
	public List<AsopProject> getProjectList(AsopProject project,
			PageGridPost pageGridPost) {
        StringBuffer hql=new StringBuffer("from AsopProject a where isUse != '2' ");
		
		if(project!=null){
			if(!StringHelpers.isNull(project.getProjectName())){
				hql.append(" and a.projectName like '%"+ project.getProjectName() +"%'");
			}
			if(!StringHelpers.isNull(project.getIsUse())){
				hql.append(" and a.isUse ="+project.getIsUse());
			}
			if(!StringHelpers.isNull(project.getProjectStatus())){
				hql.append(" and a.projectStatus ="+project.getProjectStatus());
			}
			if(!StringHelpers.isNull(project.getIsOnline())){
				hql.append(" and a.isOnline ="+project.getIsOnline());
			}
		}
		hql.append(" order by a.isUse asc,projectStatus desc,a.projectOrder asc ");
		List<AsopProject> projectList = ((List<AsopProject>) queryByPage(hql.toString(),pageGridPost));
		if(projectList !=null && projectList.size()>0){
			return projectList;
		}
		return null;
	}

	@Override
	public List<AsopProject> getProjectList(AsopProject project) {
        StringBuffer hql=new StringBuffer("from AsopProject a where isUse != '2' ");
		
		if(project!=null){
			if(!StringHelpers.isNull(project.getProjectName())){
				hql.append(" and a.projectName like '%"+ project.getProjectName() +"%'");
			}
			if(!StringHelpers.isNull(project.getIsUse())){
				hql.append(" and a.isUse ="+project.getIsUse());
			}
			if(!StringHelpers.isNull(project.getProjectStatus())){
				hql.append(" and a.projectStatus ="+project.getProjectStatus());
			}
			if(!StringHelpers.isNull(project.getIsOnline())){
				hql.append(" and a.isOnline ="+project.getIsOnline());
			}
		}
		hql.append(" order by a.isUse asc,projectStatus desc,a.projectOrder asc ");
		List<AsopProject> projectList = ((List<AsopProject>) this.query(hql.toString()));
		if(projectList !=null && projectList.size()>0){
			return projectList;
		}
		return null;
	}

	@Override
	public void isUseProject(long id, String val) {
		AsopProject project = (AsopProject) this.findById(AsopProject.class,id);
		project.setIsUse(val);
		this.update(project);
	}

}
