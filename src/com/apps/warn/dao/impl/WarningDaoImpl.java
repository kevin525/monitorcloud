package com.apps.warn.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.apps.warn.dao.WarningDao;
import com.apps.warn.domain.AsopWarning;
import com.common.StringHelpers;
import com.common.constants.Constant;
import com.common.pagetag.PageGridPost;
import com.sys.dao.impl.BaseDaoImpl;
@Repository(value="warningDao")
public class WarningDaoImpl extends BaseDaoImpl implements WarningDao{

	@Override
	public AsopWarning findUseType(String typeName, String isUse) {
		StringBuffer hql=new StringBuffer("from AsopWarning a where 1=1 ");
		if(StringHelpers.isNull(typeName) || StringHelpers.isNull(isUse)){
			return null;
		}
		hql.append(" and a.warningType = '"+typeName+"'");
		hql.append(" and a.isUse = '"+ isUse+"'");
		hql.append(" order by a.createDate desc ");
		List<AsopWarning> warningList = ((List<AsopWarning>) this.query(hql.toString()));
		if(warningList !=null && warningList.size()>0){
			return warningList.get(0);
		}else{
			return null;
		}
	}

	@Override
	public void saveOrupdateWarning(AsopWarning asopWarning) {
		this.saveOrUpdate(asopWarning);
		
	}

	@Override
	public AsopWarning getWarningById(long id) {
		return (AsopWarning) this.findById(AsopWarning.class,id);
	}

	@Override
	public void deleteWarning(long id) {
		AsopWarning warning = (AsopWarning) this.findById(AsopWarning.class,id);
		warning.setIsUse(Constant.DELETE);
		this.update(warning);
	}

	@Override
	public List<AsopWarning> getWarningList(AsopWarning asopWarning,
			PageGridPost pageGridPost) {
		 StringBuffer hql=new StringBuffer("from AsopWarning a where isUse != '2' ");
			
			if(asopWarning!=null){
				if(!StringHelpers.isNull(asopWarning.getWarningName())){
					hql.append(" and a.warningName like '%"+ asopWarning.getWarningName() +"%'");
				}
				if(!StringHelpers.isNull(asopWarning.getIsUse())){
					hql.append(" and a.isUse ="+asopWarning.getIsUse());
				}
				if(!StringHelpers.isNull(asopWarning.getWarningType())){
					hql.append(" and a.warningType like '%"+asopWarning.getWarningType()+"%'");
				}
			}
			hql.append(" order by a.isUse asc,createDate desc ");
			List<AsopWarning> warningList = ((List<AsopWarning>) queryByPage(hql.toString(),pageGridPost));
			if(warningList !=null && warningList.size()>0){
				return warningList;
			}
			return null;
	}

	@Override
	public List<AsopWarning> getWarningList(AsopWarning asopWarning) {
		StringBuffer hql=new StringBuffer("from AsopWarning a where isUse != '2' ");
		if(asopWarning!=null){
			if(!StringHelpers.isNull(asopWarning.getWarningName())){
				hql.append(" and a.warningName like '%"+ asopWarning.getWarningName() +"%'");
			}
			if(!StringHelpers.isNull(asopWarning.getIsUse())){
				hql.append(" and a.isUse ="+asopWarning.getIsUse());
			}
			if(!StringHelpers.isNull(asopWarning.getWarningType())){
				hql.append(" and a.warningType like '%"+asopWarning.getWarningType()+"%'");
			}
		}
		hql.append(" order by a.isUse asc,createDate desc ");
		List<AsopWarning> warningList = ((List<AsopWarning>) this.query(hql.toString()));
		if(warningList !=null && warningList.size()>0){
			return warningList;
		}
		return null;
	}

	@Override
	public void isUseWarning(long id, String val) {
		AsopWarning warning = (AsopWarning) this.findById(AsopWarning.class,id);
		warning.setIsUse(val);
		this.update(warning);
	}
	
}
