package com.apps.operational.checkrecord.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.apps.operational.checkrecord.dao.opsCheckRecordModelDao;
import com.apps.operational.checkrecord.domain.OpsCheckRecordModel;
import com.common.StringHelpers;
import com.common.pagetag.PageGridPost;
import com.sys.dao.impl.BaseDaoImpl;

@Repository(value="opsCheckRecordModelDao")
public class opsCheckRecordModelDaoImpl extends BaseDaoImpl implements opsCheckRecordModelDao{

	@Override
	public List<OpsCheckRecordModel> getListByModel(OpsCheckRecordModel checkRecordModel) {
		
		StringBuffer hql=new StringBuffer("from OpsCheckRecordModel a where 1=1 ");
		if(checkRecordModel!=null){
			if(!StringHelpers.isNull(checkRecordModel.getName())){
				hql.append(" and a.name like '%"+ checkRecordModel.getName() +"%'");
			}
			if(!StringHelpers.isNull(checkRecordModel.getShortName())){
				hql.append(" and a.shortName like '%"+ checkRecordModel.getShortName() +"%'");
			}
			if(checkRecordModel.getOwnerId()!=0){
				hql.append(" and a.ownerId = "+ checkRecordModel.getOwnerId() );
			}
			if(checkRecordModel.getFatherId() !=0 ){
				hql.append(" and a.fatherId = "+ checkRecordModel.getFatherId() );
			}
			if(checkRecordModel.getNodeValue() != 0){
				hql.append(" and a.nodeValue ="+ checkRecordModel.getNodeValue());
			}
			if(!StringHelpers.isNull(checkRecordModel.getIsUse())){
				hql.append(" and a.isUse ="+ checkRecordModel.getIsUse());
			}
			if(!StringHelpers.isNull(checkRecordModel.getCreateDate())){
				hql.append(" and a.createDate like '%"+ checkRecordModel.getCreateDate()+"%'");
			}
			
		}
		hql.append(" order by  a.isUse asc,a.modelOrder asc  ");
		List<OpsCheckRecordModel> list = ((List<OpsCheckRecordModel>) query(hql.toString()));
		if(list !=null && list.size()>0){
			return list;
		}
		return null;
	}

	@Override
	public void saveOrUpdateModel(OpsCheckRecordModel checkRecordModel) {
		this.saveOrUpdate(checkRecordModel);
	}

	@Override
	public List<OpsCheckRecordModel> getListByModel(
			OpsCheckRecordModel checkRecordModel, PageGridPost pageGridPost) {
		StringBuffer hql=new StringBuffer("from OpsCheckRecordModel a where 1=1 ");
		if(checkRecordModel!=null){
			if(!StringHelpers.isNull(checkRecordModel.getName())){
				hql.append(" and a.name like '%"+ checkRecordModel.getName() +"%'");
			}
			if(!StringHelpers.isNull(checkRecordModel.getShortName())){
				hql.append(" and a.shortName like '%"+ checkRecordModel.getShortName() +"%'");
			}
			if(checkRecordModel.getOwnerId()!=0){
				hql.append(" and a.ownerId = "+ checkRecordModel.getOwnerId() );
			}
			if(checkRecordModel.getFatherId() !=0 ){
				hql.append(" and a.fatherId = "+ checkRecordModel.getFatherId() );
			}
			if(checkRecordModel.getNodeValue() != 0){
				hql.append(" and a.nodeValue ="+ checkRecordModel.getNodeValue());
			}
			if(!StringHelpers.isNull(checkRecordModel.getIsUse())){
				hql.append(" and a.isUse ="+ checkRecordModel.getIsUse());
			}
			if(!StringHelpers.isNull(checkRecordModel.getCreateDate())){
				hql.append(" and a.createDate like '%"+ checkRecordModel.getCreateDate()+"%'");
			}
			
		}
		hql.append(" order by  a.isUse asc,a.modelOrder asc ");
		List<OpsCheckRecordModel> list = ((List<OpsCheckRecordModel>) queryByPage(hql.toString(),pageGridPost));
		if(list !=null && list.size()>0){
			return list;
		}
		return null;
	}

	@Override
	public void isUseModel(long id, String value) {
		OpsCheckRecordModel model = (OpsCheckRecordModel) this.findById(OpsCheckRecordModel.class, id);
		model.setIsUse(value);
		this.saveOrUpdate(model);
		
	}

}
