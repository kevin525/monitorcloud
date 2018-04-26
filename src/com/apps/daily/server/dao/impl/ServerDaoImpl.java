package com.apps.daily.server.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.apps.daily.server.dao.ServerDao;
import com.apps.daily.server.domain.AsopServer;
import com.common.StringHelpers;
import com.common.constants.Constant;
import com.common.pagetag.PageGridPost;
import com.sys.dao.impl.BaseDaoImpl;

/**
 * @ClassName: ServerDaoImpl
 * @Description: ServerDao实现类
 * @author LG
 * @date 2017年9月22日 上午10:16:19
 */
@Repository(value="serverDao")
public class ServerDaoImpl extends BaseDaoImpl implements ServerDao {

	@Override
	public void saveOrupdateServer(AsopServer server) {
		saveOrUpdate(server);
	}

	@Override
	public AsopServer getAsopServerById(long id) {
		return (AsopServer) this.findById(AsopServer.class,id);
	}

	@Override
	public void deleteAsopServer(long id) {
		AsopServer asopServer = (AsopServer) this.findById(AsopServer.class,id);
		asopServer.setIsUse(Constant.DELETE);
		this.update(asopServer);
	}

	@Override
	public void save(Object o) {
		this.save(o);
	}

	@Override
	public List<AsopServer> getAsopServerList(AsopServer asopServer,
			PageGridPost pageGridPost) {
		StringBuffer hql=new StringBuffer("from AsopServer a where isUse != '2' ");
		
		if(asopServer!=null){
			if(!StringHelpers.isNull(asopServer.getName())){
				hql.append(" and a.name like '%"+ asopServer.getName() +"%'");
			}
			if(!StringHelpers.isNull(asopServer.getIp())){
				hql.append(" and a.ip like '%"+ asopServer.getIp() +"%'");
			}
			if(!StringHelpers.isNull(asopServer.getIsUse())){
				hql.append(" and a.isUse ="+asopServer.getIsUse());
			}
			if(!StringHelpers.isNull(asopServer.getStatus())){
				hql.append(" and a.status ="+asopServer.getStatus());
			}
			if(!StringHelpers.isNull(asopServer.getEnvironment())){
				hql.append(" and a.environment ="+asopServer.getEnvironment());
			}
		}
		hql.append(" order by a.isUse asc,status desc,a.serverOrder asc ");
		List<AsopServer> asopServerList = ((List<AsopServer>) queryByPage(hql.toString(),pageGridPost));
		if(asopServerList !=null && asopServerList.size()>0){
			return asopServerList;
		}
		return null;
	}

	@Override
	public List<AsopServer> getAsopServerList(AsopServer asopServer) {
        StringBuffer hql=new StringBuffer("from AsopServer a where isUse != '2' ");
		
		if(asopServer!=null){
			if(!StringHelpers.isNull(asopServer.getName())){
				hql.append(" and a.name like '%"+ asopServer.getName() +"%'");
			}
			if(!StringHelpers.isNull(asopServer.getIsUse())){
				hql.append(" and a.isUse ="+asopServer.getIsUse());
			}
			if(!StringHelpers.isNull(asopServer.getStatus())){
				hql.append(" and a.status ="+asopServer.getStatus());
			}
			if(!StringHelpers.isNull(asopServer.getEnvironment())){
				hql.append(" and a.environment ="+asopServer.getEnvironment());
			}
		}
		hql.append(" order by a.isUse,a.serverOrder desc ");
		List<AsopServer> asopServerList = ((List<AsopServer>) this.query(hql.toString()));
		if(asopServerList !=null && asopServerList.size()>0){
			return asopServerList;
		}
		return null;
	}

	@Override
	public void isUseServer(long id, String val) {
		AsopServer asopServer = (AsopServer) this.findById(AsopServer.class,id);
		asopServer.setIsUse(val);
		this.update(asopServer);

	}

}
