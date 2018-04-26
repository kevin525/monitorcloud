package com.apps.operational.checkrecord.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.apps.operational.checkrecord.dao.OpsCheckRecordInfoDao;
import com.apps.operational.checkrecord.domain.OpsCheckRecordInfo;
import com.sys.dao.impl.BaseDaoImpl;
@Repository(value="opsCheckRecordInfoDao")
public class OpsCheckRecordInfoDaoImpl extends BaseDaoImpl implements OpsCheckRecordInfoDao{

	@Override
	public List<OpsCheckRecordInfo> getByRecordId(long recordId) {
		StringBuffer hql=new StringBuffer("from OpsCheckRecordInfo a where checkRecordId= "+recordId);
		List<OpsCheckRecordInfo> list = ((List<OpsCheckRecordInfo>) query(hql.toString()));
		if(list !=null && list.size()>0){
			return list;
		}
		return null;
	}

}
