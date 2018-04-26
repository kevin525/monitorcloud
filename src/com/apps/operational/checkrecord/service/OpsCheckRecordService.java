package com.apps.operational.checkrecord.service;

import java.util.List;

import com.apps.operational.checkrecord.domain.OpsCheckRecord;
import com.common.pagetag.PageGridPost;

/**
 * 
 * @ClassName: OpsCheckRecordService 
 * @Description: 运维记录serviec层
 * @author 张梦琦 
 * @date 2017年11月29日 下午3:38:27
 */
public interface OpsCheckRecordService {
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
	 * 分页获取符合条件
	 * @param checkRecord
	 * @param pageGridPost
	 * @return
	 */
	public List<OpsCheckRecord> getList(OpsCheckRecord checkRecord,PageGridPost pageGridPost);
	/**
	 * 管理员可以查看所有人的巡检记录
	 * @param checkRecord
	 * @param pageGridPost
	 * @return
	 */
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
	 * @param ids
	 */
	public void deleteCheckRecord(List<String> ids);
}