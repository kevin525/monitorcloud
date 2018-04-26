package com.apps.daily.database.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.apps.daily.database.dao.DatabaseDao;
import com.apps.daily.database.domain.AsopDatabase;
import com.common.StringHelpers;
import com.common.constants.Constant;
import com.common.pagetag.PageGridPost;
import com.sys.dao.impl.BaseDaoImpl;
@Repository(value="databaseDao")
public class DatabaseDaoImpl extends BaseDaoImpl implements DatabaseDao {

	@Override
	public void saveOrUpdateDatabase(AsopDatabase database) {
		this.saveOrUpdate(database);
		
	}

	@Override
	public AsopDatabase getDatabaseById(long id) {
		return (AsopDatabase) this.findById(AsopDatabase.class, id);
	}

	@Override
	public List<AsopDatabase> getList(AsopDatabase database,
			PageGridPost pageGridPost) {
		  StringBuffer hql=new StringBuffer("from AsopDatabase a where isUse != '2' ");
			if(database!=null){
				if(!StringHelpers.isNull(database.getName())){
					hql.append(" and a.name like '%"+ database.getName() +"%'");
				}
				
				if(!StringHelpers.isNull(database.getIsUse())){
					hql.append(" and a.isUse ="+database.getIsUse());
				}
				
				if(!StringHelpers.isNull(database.getDataBaseType())){
					hql.append(" and a.dataBaseType like '%"+ database.getDataBaseType() +"%'");
				}
				if(!StringHelpers.isNull(database.getDataBaseName())){
					hql.append(" and a.dataBaseName = "+database.getDataBaseName());
				}
				if(!StringHelpers.isNull(database.getDataBaseNetStatus())){
					hql.append(" and a.dataBaseNetStatus = "+database.getDataBaseNetStatus());
				}
				if(!StringHelpers.isNull(database.getDataBaseIp())){
					hql.append(" and a.dataBaseIp like  '%"+database.getDataBaseIp()+"%'");
				}
				if(!StringHelpers.isNull(database.getEnvironment())){
					hql.append(" and a.environment = "+database.getEnvironment());
				}
			}
			
			hql.append(" order by a.isUse asc,a.dataBaseNetStatus desc,a.appOrder asc ");
			List<AsopDatabase> list = ((List<AsopDatabase>) queryByPage(hql.toString(),pageGridPost));
			if(list !=null && list.size()>0){
				return list;
			}
			return null;
	}

	@Override
	public List<AsopDatabase> getList(AsopDatabase database) {
		  StringBuffer hql=new StringBuffer("from AsopDatabase a where isUse != '2' ");
			if(database!=null){
				if(!StringHelpers.isNull(database.getName())){
					hql.append(" and a.name like '%"+ database.getName() +"%'");
				}
				if(!StringHelpers.isNull(database.getIsUse())){
					hql.append(" and a.isUse ="+database.getIsUse());
				}
				if(!StringHelpers.isNull(database.getDataBaseType())){
					hql.append(" and a.dataBaseType = "+ database.getDataBaseType());
				}
				if(!StringHelpers.isNull(database.getDataBaseName())){
					hql.append(" and a.dataBaseName = "+database.getDataBaseName());
				}
				if(!StringHelpers.isNull(database.getDataBaseNetStatus())){
					hql.append(" and a.dataBaseNetStatus = "+database.getDataBaseNetStatus());
				}
				if(!StringHelpers.isNull(database.getDataBaseIp())){
					hql.append(" and a.dataBaseIp = "+database.getDataBaseIp());
				}
				if(!StringHelpers.isNull(database.getEnvironment())){
					hql.append(" and a.environment = "+database.getEnvironment());
				}
			}
			
			hql.append(" order by a.isUse asc,a.dataBaseNetStatus desc,a.appOrder asc ");
			List<AsopDatabase> list = ((List<AsopDatabase>) query(hql.toString()));
			if(list !=null && list.size()>0){
				return list;
			}
			return null;
	}
	
	@Override
	public void isUseDatabase(long id, String val) {
		AsopDatabase database = (AsopDatabase) this.findById(AsopDatabase.class, id);
		database.setIsUse(val);
		this.update(database);
	}

	@Override
	public void deleteDatabase(long id) {
		AsopDatabase database =  (AsopDatabase) this.findById(AsopDatabase.class, id);
		database.setIsUse(Constant.DELETE);
		this.update(database);
		
	}

	

}
