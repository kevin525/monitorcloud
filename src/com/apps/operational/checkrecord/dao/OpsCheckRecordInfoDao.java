package com.apps.operational.checkrecord.dao;

import java.util.List;

import com.apps.operational.checkrecord.domain.OpsCheckRecordInfo;

public interface OpsCheckRecordInfoDao {

	public List<OpsCheckRecordInfo> getByRecordId(long recordId);
}
