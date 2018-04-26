package com.apps.daily.appsystem.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.apps.daily.appsystem.dao.AppSystemDao;
import com.apps.daily.appsystem.domain.AsopAppSystem;
import com.apps.daily.server.domain.AsopServer;
import com.common.StringHelpers;
import com.common.constants.Constant;
import com.common.pagetag.PageGridPost;
import com.sys.dao.impl.BaseDaoImpl;

/**
 * @ClassName: AppSystemImpl
 * @Description: appSystemDao实现类
 * @author LG
 * @date 2017年9月22日 上午10:16:19
 */
@Repository(value="appSystemDao")
public class AppSystemImpl extends BaseDaoImpl implements AppSystemDao {

	@Override
	public void saveOrupdateAppSystem(AsopAppSystem appSystem) {
		this.saveOrUpdate(appSystem);
	}

	@Override
	public AsopAppSystem getAppSystemById(long id) {
		return (AsopAppSystem) this.findById(AsopAppSystem.class,id);
	}

	@Override
	public void deleteAppSystem(long id) {
		AsopAppSystem appSystem = (AsopAppSystem) this.findById(AsopAppSystem.class,id);
		appSystem.setIsUse(Constant.DELETE);
		this.update(appSystem);
	}

	@Override
	public List<AsopAppSystem> getAppSystemList(AsopAppSystem appSystem,
			PageGridPost pageGridPost) {
        StringBuffer hql=new StringBuffer("from AsopAppSystem a where isUse != '2' ");
		
		if(appSystem!=null){
			if(!StringHelpers.isNull(appSystem.getAppName())){
				hql.append(" and a.appName like '%"+ appSystem.getAppName() +"%'");
			}
			if(!StringHelpers.isNull(appSystem.getIsUse())){
				hql.append(" and a.isUse ="+appSystem.getIsUse());
			}
			if(!StringHelpers.isNull(appSystem.getAppIp())){
				hql.append(" and a.appIp like '%"+appSystem.getAppIp()+"%'");
			}
			if(!StringHelpers.isNull(appSystem.getAppNetStatus())){
				hql.append(" and a.appNetStatus ="+appSystem.getAppNetStatus());
			}
			if(!StringHelpers.isNull(appSystem.getDataBaseNetStatus())){
				hql.append(" and a.dataBaseNetStatus ="+appSystem.getDataBaseNetStatus());
			}
			if(!StringHelpers.isNull(appSystem.getAppStatus())){
				hql.append(" and a.appStatus ="+appSystem.getAppStatus());
			}
			if(!StringHelpers.isNull(appSystem.getEnvironment())){
				hql.append(" and a.environment ="+appSystem.getEnvironment());
			}
		}
		hql.append(" order by a.isUse asc,appStatus desc,a.appOrder asc ");
		List<AsopAppSystem> appSystemList = ((List<AsopAppSystem>) queryByPage(hql.toString(),pageGridPost));
		if(appSystemList !=null && appSystemList.size()>0){
			return appSystemList;
		}
		return null;
	}

	@Override
	public List<AsopAppSystem> getAppSystemList(AsopAppSystem appSystem) {
        StringBuffer hql=new StringBuffer("from AsopAppSystem a where 1=1 ");
		
		if(appSystem!=null){
			if(!StringHelpers.isNull(appSystem.getAppName())){
				hql.append(" and a.name like '%"+ appSystem.getAppName() +"%'");
			}
			if(!StringHelpers.isNull(appSystem.getIsUse())){
				hql.append(" and a.isUse ="+appSystem.getIsUse());
			}
			if(!StringHelpers.isNull(appSystem.getAppNetStatus())){
				hql.append(" and a.appNetStatus ="+appSystem.getAppNetStatus());
			}
			if(!StringHelpers.isNull(appSystem.getDataBaseNetStatus())){
				hql.append(" and a.dataBaseNetStatus ="+appSystem.getDataBaseNetStatus());
			}
			if(!StringHelpers.isNull(appSystem.getAppStatus())){
				hql.append(" and a.appStatus ="+appSystem.getAppStatus());
			}
			if(!StringHelpers.isNull(appSystem.getEnvironment())){
				hql.append(" and a.environment ="+appSystem.getEnvironment());
			}
		}
		hql.append(" order by a.isUse,a.appOrder desc ");
		List<AsopAppSystem> appSystemList = ((List<AsopAppSystem>) this.query(hql.toString()));
		if(appSystemList !=null && appSystemList.size()>0){
			return appSystemList;
		}
		return null;
	}

	@Override
	public void isUseAppSystem(long id, String val) {
		AsopAppSystem AsopAppSystem = (AsopAppSystem) this.findById(AsopAppSystem.class,id);
		AsopAppSystem.setIsUse(val);
		this.update(AsopAppSystem);
	}

}
