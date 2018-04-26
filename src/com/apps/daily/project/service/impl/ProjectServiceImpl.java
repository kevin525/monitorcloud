package com.apps.daily.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apps.daily.project.dao.ProjectDao;
import com.apps.daily.project.domain.AsopProject;
import com.apps.daily.project.service.ProjectService;
import com.common.StringHelpers;
import com.common.pagetag.PageGridPost;
import com.sys.service.impl.BaseServiceImpl;

@Service(value="projectService")
public class ProjectServiceImpl extends BaseServiceImpl implements ProjectService {

	@Autowired
	private ProjectDao projectDao;

	@Override
	public void save(Object o) {
		this.save(o);
	}

	@Override
	public void saveOrupdateProject(AsopProject project) {
		projectDao.saveOrupdateProject(project);
	}

	@Override
	public AsopProject getProjectById(long id) {
		return projectDao.getProjectById(id);
	}

	@Override
	public void deleteProject(List<String> ids) {
		if(ids != null && ids.size() > 0){
			for(String id:ids){
			    if(!StringHelpers.isNull(id)){
			    	projectDao.deleteProject(Long.parseLong(id));
			    }
			}
		}
		
	}

	@Override
	public List<AsopProject> getProjectList(AsopProject project,
			PageGridPost pageGridPost) {
		return projectDao.getProjectList(project,pageGridPost);
	}

	@Override
	public List<AsopProject> getProjectList(AsopProject project) {
		return projectDao.getProjectList(project);
	}

	@Override
	public void isUseProject(long id, String val) {
        projectDao.isUseProject(id, val);		
	}

}
