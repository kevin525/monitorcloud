package com.apps.daily.tomcat.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apps.daily.common.view.MonitorInfoView;
import com.apps.daily.server.domain.AsopServer;
import com.apps.daily.tomcat.dao.TomcatDao;
import com.apps.daily.tomcat.domain.AsopTomcat;
import com.apps.daily.tomcat.service.TomcatService;
import com.common.StringHelpers;
import com.common.constants.Constant;
import com.common.pagetag.PageGridPost;
import com.sys.service.impl.BaseServiceImpl;

@Service(value="tomcatService")
public class TomcatServiceImpl extends BaseServiceImpl implements TomcatService {

	@Autowired
	private TomcatDao tomcatDao;
	

	@Override
	public void saveOrUpdateTomcat(AsopTomcat tomcat) {
		tomcatDao.saveOrUpdateTomcat(tomcat);
		
	}

	@Override
	public AsopTomcat getTomcatById(long id) {
		return tomcatDao.getTomcatById(id);
	}

	@Override
	public List<AsopTomcat> getList(AsopTomcat tomcat,
			PageGridPost pageGridPost) {
		return tomcatDao.getList(tomcat, pageGridPost);
	}

	@Override
	public List<AsopTomcat> getList(AsopTomcat tomcat) {
		return tomcatDao.getList(tomcat);
	}
	
	@Override
	public void isUseTomcat(long id, String val) {
		
		tomcatDao.isUseTomcat(id, val);	
	}

	@Override
	public void deleteTomcat(List<String> ids) {
		if(ids != null && ids.size() > 0){
			for(String id:ids){
			    if(!StringHelpers.isNull(id)){
			    	tomcatDao.deleteTomcat(Long.parseLong(id));
			    }
			}
		}
		
	}

	@Override
	public List<MonitorInfoView> getTomcatMonitor() {
		List<MonitorInfoView> monitorViewList = new ArrayList<MonitorInfoView>();
		AsopTomcat asopTomcat = new AsopTomcat();
		asopTomcat.setIsUse(Constant.ACTIVITY);
		asopTomcat.setEnvironment(Constant.ENVI_FORMAL);
		List<AsopTomcat> tomcatList = this.getList(asopTomcat);
		
		if(tomcatList !=null && tomcatList.size() > 0){
			for(AsopTomcat tomcat:tomcatList){
				MonitorInfoView view = new MonitorInfoView();
				view.setId(String.valueOf(tomcat.getId()));
				view.setType(Constant.DICT_MIDDLEWARE);
				view.setTypeName(Constant.DICT_MIDDLEWARE_NAME);
				view.setName(tomcat.getName());
				view.setStatus(tomcat.getTomcatStatus());
				view.setCheckCount(tomcat.getCheckCount());
				view.setLastCheckDate(tomcat.getLastCheckDate());
				monitorViewList.add(view);
			}
		}
		return monitorViewList;
	}


}
