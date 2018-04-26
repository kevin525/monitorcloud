package com.apps.daily.appsystem.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.cxf.binding.corba.wsdl.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apps.daily.appsystem.dao.AppSystemDao;
import com.apps.daily.appsystem.domain.AsopAppSystem;
import com.apps.daily.appsystem.service.AppSystemService;
import com.apps.daily.appsystem.service.MonitorSystemContainer;
import com.apps.daily.common.view.MonitorInfoView;
import com.apps.daily.server.domain.AsopServer;
import com.apps.msgRemind.sms.domain.AsopSms;
import com.apps.utils.PingUtil;
import com.common.StringHelpers;
import com.common.constants.Constant;
import com.common.pagetag.PageGridPost;
import com.common.utils.HttpUtil;
import com.sys.service.impl.BaseServiceImpl;
import com.sys.util.Config;

@Service(value="appSystemService")
public class AppSystemServiceImpl extends BaseServiceImpl implements AppSystemService {

	@Autowired
	private AppSystemDao appSystemDao;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public void saveOrupdateAppSystem(AsopAppSystem appSystem) {
		appSystemDao.saveOrupdateAppSystem(appSystem);
	}

	@Override
	public AsopAppSystem getAppSystemById(long id) {
		return appSystemDao.getAppSystemById(id);
	}

	@Override
	public void deleteAppSystem(List<String> ids) {
		if(ids != null && ids.size() > 0){
			for(String id:ids){
			    if(!StringHelpers.isNull(id)){
			    	appSystemDao.deleteAppSystem(Long.parseLong(id));
			    }
			}
		}
	}

	@Override
	public List<AsopAppSystem> getAppSystemList(AsopAppSystem appSystem,
			PageGridPost pageGridPost) {
		return appSystemDao.getAppSystemList(appSystem, pageGridPost);
	}

	@Override
	public List<AsopAppSystem> getAppSystemList(AsopAppSystem appSystem) {
		return appSystemDao.getAppSystemList(appSystem);
	}

	@Override
	public void isUseAppSystem(long id, String val) {
		appSystemDao.isUseAppSystem(id, val);
	}

	@Override
	public void save(Object o) {
		this.save(o);
	}

	@Override
	public List<MonitorInfoView> getAppSystemMonitor() {
		List<MonitorInfoView> monitorViewList = new ArrayList<MonitorInfoView>();
		AsopAppSystem appSystem = new AsopAppSystem();
		appSystem.setIsUse(Constant.ACTIVITY);
		appSystem.setEnvironment(Constant.ENVI_FORMAL);
		List<AsopAppSystem> appSystemList = this.getAppSystemList(appSystem);
		
		if(appSystemList !=null && appSystemList.size() > 0){
			for(AsopAppSystem app:appSystemList){
				MonitorInfoView view = new MonitorInfoView();
				view.setId(String.valueOf(app.getId()));
				view.setType(Constant.DICT_APP_SYSTEM);
				view.setTypeName(Constant.DICT_APP_SYSTEM_NAME);
				view.setName(app.getAppName());
				view.setStatus(app.getAppStatus());
				view.setCheckCount(app.getCheckCount());
				view.setLastCheckDate(app.getLastCheckDate());
				monitorViewList.add(view);
			}
		}
		return monitorViewList;
	}

}
