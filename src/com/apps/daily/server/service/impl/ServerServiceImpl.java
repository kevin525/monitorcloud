package com.apps.daily.server.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apps.daily.common.view.MonitorInfoView;
import com.apps.daily.server.dao.ServerDao;
import com.apps.daily.server.domain.AsopServer;
import com.apps.daily.server.service.ServerService;
import com.apps.msgRemind.sms.domain.AsopSms;
import com.apps.utils.PingUtil;
import com.common.StringHelpers;
import com.common.constants.Constant;
import com.common.pagetag.PageGridPost;
import com.common.utils.HttpUtil;
import com.sys.service.impl.BaseServiceImpl;
import com.sys.util.Config;

@Service(value="serverService")
public class ServerServiceImpl extends BaseServiceImpl implements ServerService {

	@Autowired
	private ServerDao serverDao;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public void saveOrupdateServer(AsopServer server) {
		serverDao.saveOrupdateServer(server);
	}

	@Override
	public AsopServer getAsopServerById(long id) {
		return serverDao.getAsopServerById(id);
	}

	@Override
	public void deleteAsopServer(List<String> ids) {
		if(ids != null && ids.size() > 0){
			for(String id:ids){
			    if(!StringHelpers.isNull(id)){
			    	serverDao.deleteAsopServer(Long.parseLong(id));
			    }
			}
		}
	}

	@Override
	public void save(Object o) {
        serverDao.save(o);
	}

	@Override
	public List<AsopServer> getAsopServerList(AsopServer asopServer,
			PageGridPost pageGridPost) {
		return serverDao.getAsopServerList(asopServer,pageGridPost);
	}

	@Override
	public List<AsopServer> getAsopServerList(AsopServer asopServer) {
		return serverDao.getAsopServerList(asopServer);
	}

	@Override
	public void isUseServer(long id, String val) {
        serverDao.isUseServer(id, val);
	}

	@Override
	public List<MonitorInfoView> getServerMonitor() {
		List<MonitorInfoView> monitorViewList = new ArrayList<MonitorInfoView>();
		AsopServer asopServer = new AsopServer();
		asopServer.setIsUse(Constant.ACTIVITY);
		asopServer.setEnvironment(Constant.ENVI_FORMAL);
		List<AsopServer> serverList = this.getAsopServerList(asopServer);
		
		if(serverList !=null && serverList.size() > 0){
			for(AsopServer server:serverList){
				MonitorInfoView view = new MonitorInfoView();
				view.setId(String.valueOf(server.getId()));
				view.setType(Constant.DICT_SERVER);
				view.setTypeName(Constant.DICT_SERVER_NAME);
				view.setName(server.getName());
				view.setStatus(server.getStatus());
				view.setCheckCount(server.getCheckCount());
				view.setLastCheckDate(server.getLastCheckDate());
				monitorViewList.add(view);
			}
		}
		return monitorViewList;
	}

}
