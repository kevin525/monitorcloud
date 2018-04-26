package com.apps.daily.database.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apps.daily.common.view.MonitorInfoView;
import com.apps.daily.database.dao.DatabaseDao;
import com.apps.daily.database.domain.AsopDatabase;
import com.apps.daily.database.service.DatabaseService;
import com.apps.daily.server.domain.AsopServer;
import com.common.StringHelpers;
import com.common.constants.Constant;
import com.common.pagetag.PageGridPost;
import com.sys.service.impl.BaseServiceImpl;

@Service(value="databaseService")
public class DatabaseServiceImpl extends BaseServiceImpl implements DatabaseService {

	@Autowired
	private DatabaseDao databaseDao;
	

	@Override
	public void saveOrUpdateDatabase(AsopDatabase database) {
		databaseDao.saveOrUpdateDatabase(database);
		
	}

	@Override
	public AsopDatabase getDatabaseById(long id) {
		return databaseDao.getDatabaseById(id);
	}

	@Override
	public List<AsopDatabase> getList(AsopDatabase database,
			PageGridPost pageGridPost) {
		return databaseDao.getList(database, pageGridPost);
	}

	@Override
	public List<AsopDatabase> getList(AsopDatabase database) {
		return databaseDao.getList(database);
	}
	
	@Override
	public void isUseDatabase(long id, String val) {
		
		databaseDao.isUseDatabase(id, val);	
	}

	@Override
	public void deleteDatabase(List<String> ids) {
		if(ids != null && ids.size() > 0){
			for(String id:ids){
			    if(!StringHelpers.isNull(id)){
			    	databaseDao.deleteDatabase(Long.parseLong(id));
			    }
			}
		}
		
	}

	@Override
	public List<MonitorInfoView> getDatabaseMonitor() {
		List<MonitorInfoView> monitorViewList = new ArrayList<MonitorInfoView>();
		AsopDatabase asopDatabase = new AsopDatabase();
		asopDatabase.setIsUse(Constant.ACTIVITY);
		asopDatabase.setEnvironment(Constant.ENVI_FORMAL);
		List<AsopDatabase> databaseList = this.getList(asopDatabase);
		
		if(databaseList !=null && databaseList.size() > 0){
			for(AsopDatabase database:databaseList){
				MonitorInfoView view = new MonitorInfoView();
				view.setId(String.valueOf(database.getId()));
				view.setType(Constant.DICT_DATABASE);
				view.setTypeName(Constant.DICT_DATABASE_NAME);
				view.setName(database.getName());
				view.setStatus(database.getDataBaseNetStatus());
				view.setCheckCount(database.getCheckCount());
				view.setLastCheckDate(database.getLastCheckDate());
				monitorViewList.add(view);
			}
		}
		return monitorViewList;
	}

}
