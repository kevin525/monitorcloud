package com.apps.operational.checkrecord.dao;

import java.util.List;

import com.apps.operational.checkrecord.domain.OpsCheckRecord;
import com.common.pagetag.PageGridPost;
/**
 * 
 * @ClassName: OpsCheckRecordDao 
 * @Description: 
 * @author 张梦琦 
 * @date 2017年11月29日 下午3:28:12
 */
public interface OpsCheckRecordDao {

	/**
	 * 保存或更新
	 * @param checkRecord
	 */
	public void saveOrUpdateCheckRecord(OpsCheckRecord checkRecord);
	
	/**
	 * 根据id获取
	 * @param id
	 */
	public OpsCheckRecord getById(long id);
	
	/**
	 * 分页获取符合条件的
	 * @param OpsCheckRecord
	 * @param pageGridPost
	 * @return
	 */
	public List<OpsCheckRecord> getList(OpsCheckRecord checkRecord,PageGridPost pageGridPost);
	public List<OpsCheckRecord> getManagerList(OpsCheckRecord checkRecord,PageGridPost pageGridPost);
	
	/**
	 * 判断该记录是否存在
	 * @param name
	 * @param createYMD
	 * @param environment
	 * @return
	 */
	public OpsCheckRecord getCheckRecord(String name,String createYMD,String environment);
	
	
	/**
	 * 删除中间件
	 * @param id
	 */
	public void deleteCheckRecord(long id);

}