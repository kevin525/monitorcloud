package com.apps.operational.checkrecord.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.apps.operational.checkrecord.dao.OpsCheckRecordDao;
import com.apps.operational.checkrecord.domain.OpsCheckRecord;
import com.common.StringHelpers;
import com.common.constants.Constant;
import com.common.pagetag.PageGridPost;
import com.sys.dao.impl.BaseDaoImpl;
@Repository(value="opsCheckRecordDao")
public class OpsCheckRecordDaoImpl extends BaseDaoImpl implements OpsCheckRecordDao {

	@Override
	public void saveOrUpdateCheckRecord(OpsCheckRecord checkRecord) {
		this.saveOrUpdate(checkRecord);
		
	}

	@Override
	public OpsCheckRecord getById(long id) {
		return (OpsCheckRecord) this.findById(OpsCheckRecord.class, id);
	}

	@Override
	public List<OpsCheckRecord> getList(OpsCheckRecord checkRecord,
			PageGridPost pageGridPost) {
		  StringBuffer hql=new StringBuffer("from OpsCheckRecord a where 1=1 ");
			if(checkRecord!=null){
				if(!StringHelpers.isNull(checkRecord.getName())){
					hql.append(" and a.name like '%"+ checkRecord.getName() +"%'");
				}
				if(!StringHelpers.isNull(checkRecord.getName())){
					hql.append(" and a.personLiable like '%"+ checkRecord.getPersonLiable() +"%'");
				}
				if(checkRecord.getCreateUserId() !=0){
					hql.append(" and a.createUserId = "+ checkRecord.getCreateUserId() );
				}
				if(!StringHelpers.isNull(checkRecord.getEnvironment()) ){
					hql.append(" and a.environment = "+ checkRecord.getEnvironment() );
				}
				if(!StringHelpers.isNull(checkRecord.getIsUse()) ){
					hql.append(" and a.isUse = "+ checkRecord.getIsUse() );
				}
				
			}
			hql.append("  order by a.createDate desc ");
			List<OpsCheckRecord> list = ((List<OpsCheckRecord>) queryByPage(hql.toString(),pageGridPost));
			if(list !=null && list.size()>0){
				return list;
			}
			return null;
	}


	@Override
	public void deleteCheckRecord(long id) {
		OpsCheckRecord checkRecord =  (OpsCheckRecord) this.findById(OpsCheckRecord.class, id);
		checkRecord.setIsUse(Constant.DELETE);
		this.update(checkRecord);
		
	}

	@Override
	public List<OpsCheckRecord> getManagerList(OpsCheckRecord checkRecord,
			PageGridPost pageGridPost) {
		StringBuffer hql=new StringBuffer("from OpsCheckRecord a where isUse != '2' ");
		hql.append(" group by a.createYMD order by a.createDate desc");
		List<OpsCheckRecord> list = ((List<OpsCheckRecord>) query(hql.toString()));
		if(list !=null && list.size()>0){
			return list;
		}
		return null;
	}

	@Override
	public OpsCheckRecord getCheckRecord(String name, String createYMD,String environment) {
		 StringBuffer hql=new StringBuffer("from OpsCheckRecord a where isUse != '2' ");
			hql.append(" and a.name = '"+ name +"'");
			hql.append(" and a.createYMD = '"+ createYMD +"'");
			hql.append(" and a.environment = '"+ environment +"'");
			List<OpsCheckRecord> list = ((List<OpsCheckRecord>) query(hql.toString()));
			if(list !=null && list.size()>0){
				return list.get(0);
			}
			return null;
	}

	

}
