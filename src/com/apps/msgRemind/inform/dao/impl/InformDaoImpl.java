package com.apps.msgRemind.inform.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.apps.msgRemind.inform.dao.InformDao;
import com.apps.msgRemind.inform.domain.AsopInform;
import com.common.StringHelpers;
import com.common.pagetag.PageGridPost;
import com.sys.dao.impl.BaseDaoImpl;

/**
 * 
 * @ClassName: InformDaoImpl 
 * @Description: 短消息的实现类
 * @author 张梦琦 
 * @date 2018年4月9日 下午4:18:14
 */
@Repository(value="informDao")
public class InformDaoImpl extends BaseDaoImpl implements InformDao{

	@Override
	public List<AsopInform> getListByPage(AsopInform inform, PageGridPost pageGridPost) {
		 StringBuffer hql=new StringBuffer("from AsopInform a where 1=1 ");
			if(inform!=null){
				if(!StringHelpers.isNull(inform.getContent())){
					hql.append(" and a.content like '%"+ inform.getContent() +"%'");
				}
				if(!StringHelpers.isNull(inform.getReadStatus())){
					hql.append(" and a.readStatus like '"+ inform.getReadStatus() +"'");
				}
			}
			hql.append(" order by a.sendDate desc ");
			List<AsopInform> list = ((List<AsopInform>) queryByPage(hql.toString(),pageGridPost));
			if(list !=null && list.size()>0){
				return list;
			}
			return null;
	}

	@Override
	public void deleteInform(long id) {
		AsopInform inform = (AsopInform) this.findById(AsopInform.class, id);
		delete(inform);
	}

	@Override
	public void saveOrupdateInform(AsopInform inform) {
		saveOrUpdate(inform);
		
	}

	@Override
	public AsopInform getInformById(long id) {
		return (AsopInform) findById(AsopInform.class, id);
	}

	@Override
	public List<AsopInform> getListNoPage(AsopInform inform) {
		StringBuffer hql=new StringBuffer("from AsopInform a where 1=1 ");
		if(inform!=null){
			if(!StringHelpers.isNull(inform.getContent())){
				hql.append(" and a.content like '%"+ inform.getContent() +"%'");
			}
			if(!StringHelpers.isNull(inform.getReadStatus())){
				hql.append(" and a.readStatus like '"+ inform.getReadStatus() +"'");
			}
		}
		hql.append(" order by a.sendDate desc ");
		List<AsopInform> list = ((List<AsopInform>) query(hql.toString()));
		if(list !=null && list.size()>0){
			return list;
		}
		return null;
	}


}
