package com.apps.daily.middleware.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apps.daily.common.view.MonitorInfoView;
import com.apps.daily.middleware.dao.MiddlewareDao;
import com.apps.daily.middleware.domain.AsopMiddleware;
import com.apps.daily.middleware.service.MiddlewareService;
import com.common.StringHelpers;
import com.common.constants.Constant;
import com.common.pagetag.PageGridPost;
import com.sys.service.impl.BaseServiceImpl;

@Service(value="middlewareService")
public class MiddlewareServiceImpl extends BaseServiceImpl implements MiddlewareService {

	@Autowired
	private MiddlewareDao middlewareDao;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	

	@Override
	public void save(Object o) {
        middlewareDao.save(o);
	}

	@Override
	public void saveOrupdateMiddleware(AsopMiddleware middleware) {
		middlewareDao.saveOrupdateMiddleware(middleware);
	}

	@Override
	public AsopMiddleware getAsopMiddlewareById(long id) {
		return middlewareDao.getAsopMiddlewareById(id);
	}

	@Override
	public void deleteAsopMiddleware(List<String> ids) {
		if(ids != null && ids.size() > 0){
			for(String id:ids){
			    if(!StringHelpers.isNull(id)){
			    	middlewareDao.deleteAsopMiddleware(Long.parseLong(id));
			    }
			}
		}
		
	}

	@Override
	public List<AsopMiddleware> getAsopMiddlewareList(
			AsopMiddleware asopMiddleware, PageGridPost pageGridPost) {
		return middlewareDao.getAsopMiddlewareList(asopMiddleware,pageGridPost);
	}

	@Override
	public List<AsopMiddleware> getAsopMiddlewareList(
			AsopMiddleware asopMiddleware) {
		return middlewareDao.getAsopMiddlewareList(asopMiddleware);
	}

	@Override
	public void isUseAsopMiddleware(long id, String val) {
		middlewareDao.isUseAsopMiddleware(id, val);
	}

	@Override
	public List<MonitorInfoView> getMiddlewareMonitor() {
		List<MonitorInfoView> monitorViewList = new ArrayList<MonitorInfoView>();
		AsopMiddleware asopMiddleware = new AsopMiddleware(); 
		asopMiddleware.setIsUse(Constant.ACTIVITY);
		asopMiddleware.setEnvironment(Constant.ENVI_FORMAL);
		List<AsopMiddleware> middlewareList = this.getAsopMiddlewareList(asopMiddleware);
		
		if(middlewareList !=null && middlewareList.size() > 0){
			for(AsopMiddleware middleware:middlewareList){
				MonitorInfoView view = new MonitorInfoView();
				view.setId(String.valueOf(middleware.getId()));
				view.setType(Constant.DICT_MIDDLEWARE);
				view.setTypeName(Constant.DICT_MIDDLEWARE_NAME);
				view.setName(middleware.getName());
				view.setStatus(middleware.getStatus());
				view.setCheckCount(middleware.getCheckCount());
				view.setLastCheckDate(middleware.getLastCheckDate());
				monitorViewList.add(view);
			}
		}
		return monitorViewList;
	}

}
