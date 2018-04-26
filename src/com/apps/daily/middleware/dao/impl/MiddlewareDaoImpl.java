package com.apps.daily.middleware.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.apps.daily.middleware.dao.MiddlewareDao;
import com.apps.daily.middleware.domain.AsopMiddleware;
import com.common.StringHelpers;
import com.common.constants.Constant;
import com.common.pagetag.PageGridPost;
import com.sys.dao.impl.BaseDaoImpl;

/**
 * @ClassName: MiddlewareDaoImpl
 * @Description: MiddlewareDao实现类
 * @author LG
 * @date 2017年9月22日 上午10:16:19
 */
@Repository(value="middlewareDao")
public class MiddlewareDaoImpl extends BaseDaoImpl implements MiddlewareDao {

	@Override
	public void save(Object o) {
		this.save(o);
	}

	@Override
	public void saveOrupdateMiddleware(AsopMiddleware middleware) {
		saveOrUpdate(middleware);
	}

	@Override
	public AsopMiddleware getAsopMiddlewareById(long id) {
		return (AsopMiddleware) this.findById(AsopMiddleware.class,id);
	}

	@Override
	public void deleteAsopMiddleware(long id) {
		AsopMiddleware asopMiddleware = (AsopMiddleware) this.findById(AsopMiddleware.class,id);
		asopMiddleware.setIsUse(Constant.DELETE);
		this.update(asopMiddleware);
		
	}

	@Override
	public List<AsopMiddleware> getAsopMiddlewareList(
			AsopMiddleware asopMiddleware, PageGridPost pageGridPost) {
         StringBuffer hql=new StringBuffer("from AsopMiddleware a where isUse != '2' ");
		
		if(asopMiddleware!=null){
			if(!StringHelpers.isNull(asopMiddleware.getName())){
				hql.append(" and a.name like '%"+ asopMiddleware.getName() +"%'");
			}
			if(!StringHelpers.isNull(asopMiddleware.getIp())){
				hql.append(" and a.ip like '%"+ asopMiddleware.getIp() +"%'");
			}
			if(!StringHelpers.isNull(asopMiddleware.getIsUse())){
				hql.append(" and a.isUse ="+asopMiddleware.getIsUse());
			}
			if(!StringHelpers.isNull(asopMiddleware.getStatus())){
				hql.append(" and a.status ="+asopMiddleware.getStatus());
			}
			if(!StringHelpers.isNull(asopMiddleware.getEnvironment())){
				hql.append(" and a.environment ="+asopMiddleware.getEnvironment());
			}
		}
		hql.append(" order by a.isUse asc,a.status desc,a.middlewareOrder asc ");
		List<AsopMiddleware> middlewareList = ((List<AsopMiddleware>) queryByPage(hql.toString(),pageGridPost));
		if(middlewareList !=null && middlewareList.size()>0){
			return middlewareList;
		}
		return null;
	}

	@Override
	public List<AsopMiddleware> getAsopMiddlewareList(
			AsopMiddleware asopMiddleware) {
        StringBuffer hql=new StringBuffer("from AsopMiddleware a where isUse != '2' ");
		
		if(asopMiddleware!=null){
			if(!StringHelpers.isNull(asopMiddleware.getName())){
				hql.append(" and a.name like '%"+ asopMiddleware.getName() +"%'");
			}
			if(!StringHelpers.isNull(asopMiddleware.getIp())){
				hql.append(" and a.ip like '%"+ asopMiddleware.getIp() +"%'");
			}
			if(!StringHelpers.isNull(asopMiddleware.getIsUse())){
				hql.append(" and a.isUse ="+asopMiddleware.getIsUse());
			}
			if(!StringHelpers.isNull(asopMiddleware.getStatus())){
				hql.append(" and a.status ="+asopMiddleware.getStatus());
			}
			if(!StringHelpers.isNull(asopMiddleware.getEnvironment())){
				hql.append(" and a.environment ="+asopMiddleware.getEnvironment());
			}
		}
		hql.append(" order by a.isUse,a.middlewareOrder desc ");
		List<AsopMiddleware> middlewareList = ((List<AsopMiddleware>) query(hql.toString()));
		if(middlewareList !=null && middlewareList.size()>0){
			return middlewareList;
		}
		return null;
	}

	@Override
	public void isUseAsopMiddleware(long id, String val) {
		AsopMiddleware asopMiddleware = (AsopMiddleware) this.findById(AsopMiddleware.class,id);
		asopMiddleware.setIsUse(val);
		this.update(asopMiddleware);
		
	}

}
