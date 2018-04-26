package com.apps.daily.tomcat.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.apps.daily.tomcat.dao.TomcatDao;
import com.apps.daily.tomcat.domain.AsopTomcat;
import com.common.StringHelpers;
import com.common.constants.Constant;
import com.common.pagetag.PageGridPost;
import com.sys.dao.impl.BaseDaoImpl;
@Repository(value="tomcatDao")
public class TomcatDaoImpl extends BaseDaoImpl implements TomcatDao {

	@Override
	public void saveOrUpdateTomcat(AsopTomcat tomcat) {
		this.saveOrUpdate(tomcat);
		
	}

	@Override
	public AsopTomcat getTomcatById(long id) {
		return (AsopTomcat) this.findById(AsopTomcat.class, id);
	}

	@Override
	public List<AsopTomcat> getList(AsopTomcat tomcat,
			PageGridPost pageGridPost) {
		  StringBuffer hql=new StringBuffer("from AsopTomcat a where isUse != '2' ");
			if(tomcat!=null){
				if(!StringHelpers.isNull(tomcat.getName())){
					hql.append(" and a.name like '%"+ tomcat.getName() +"%'");
				}
				if(!StringHelpers.isNull(tomcat.getWebAppName())){
					hql.append(" and a.webAppName like '%"+ tomcat.getWebAppName() +"%'");
				}
				if(!StringHelpers.isNull(tomcat.getManagerUrl())){
					hql.append(" and a.managerUrl like '%"+ tomcat.getManagerUrl() +"%'");
				}
				if(!StringHelpers.isNull(tomcat.getTomcatStatus())){
					hql.append(" and a.tomcatStatus = '"+ tomcat.getTomcatStatus()+"'" );
				}
				if(!StringHelpers.isNull(tomcat.getVersion())){
					hql.append(" and a.version = '"+ tomcat.getVersion()+"'" );
				}
				if(!StringHelpers.isNull(tomcat.getIsUse())){
					hql.append(" and a.isUse ="+tomcat.getIsUse());
				}
				
			}
			hql.append(" order by a.isUse asc,a.tomcatStatus desc,a.appOrder asc ");
			List<AsopTomcat> list = ((List<AsopTomcat>) queryByPage(hql.toString(),pageGridPost));
			if(list !=null && list.size()>0){
				return list;
			}
			return null;
	}

	@Override
	public List<AsopTomcat> getList(AsopTomcat tomcat) {
		  StringBuffer hql=new StringBuffer("from AsopTomcat a where isUse != '2' ");
			if(tomcat!=null){
				if(!StringHelpers.isNull(tomcat.getName())){
					hql.append(" and a.name like '%"+ tomcat.getName() +"%'");
				}
				if(!StringHelpers.isNull(tomcat.getWebAppName())){
					hql.append(" and a.webAppName like '%"+ tomcat.getWebAppName() +"%'");
				}
				if(!StringHelpers.isNull(tomcat.getIsUse())){
					hql.append(" and a.isUse ="+tomcat.getIsUse());
				}
				if(!StringHelpers.isNull(tomcat.getManagerUrl())){
					hql.append(" and a.managerUrl like '%"+ tomcat.getManagerUrl() +"%'");
				}
				if(!StringHelpers.isNull(tomcat.getVersion())){
					hql.append(" and a.version = '"+ tomcat.getVersion()+"'" );
				}
				if(!StringHelpers.isNull(tomcat.getTomcatStatus())){
					hql.append(" and a.tomcatStatus = '"+ tomcat.getTomcatStatus()+"'" );
				}
			}
			
			hql.append(" order by a.isUse asc,a.tomcatStatus desc,a.appOrder asc ");
			List<AsopTomcat> list = ((List<AsopTomcat>) query(hql.toString()));
			if(list !=null && list.size()>0){
				return list;
			}
			return null;
	}
	
	@Override
	public void isUseTomcat(long id, String val) {
		AsopTomcat tomcat = (AsopTomcat) this.findById(AsopTomcat.class, id);
		tomcat.setIsUse(val);
		this.update(tomcat);
	}

	@Override
	public void deleteTomcat(long id) {
		AsopTomcat tomcat =  (AsopTomcat) this.findById(AsopTomcat.class, id);
		tomcat.setIsUse(Constant.DELETE);
		this.update(tomcat);
		
	}

	

}
