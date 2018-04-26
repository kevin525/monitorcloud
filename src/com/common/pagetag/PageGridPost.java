package com.common.pagetag;

import java.io.Serializable;

/**
 * @author 作者 李涛
 * @version v1 
 * 创建时间：2014年11月2日下午4:01:39 
 * 类说明：
 */
public class PageGridPost implements Serializable {
	private static final long serialVersionUID = 2837480041430890963L;
	private Integer start;// 数据开始
	private Integer pageSize;//每页展示条数
	private String dir;
	private String sort;// 排序字段
	private Object queryPara;// 查询条件
	private Long totalCount;// 总数
	private Boolean isPage = Boolean.valueOf(true);
	private Boolean isCountPage = Boolean.valueOf(true);
	private Integer limit;

	public PageGridPost() {
	}

	public PageGridPost(Boolean isPage) {
		this.isPage = isPage;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Object getQueryPara() {
		return queryPara;
	}

	public void setQueryPara(Object queryPara) {
		this.queryPara = queryPara;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Boolean getIsPage() {
		return isPage;
	}

	public void setIsPage(Boolean isPage) {
		this.isPage = isPage;
	}

	public Boolean getIsCountPage() {
		return isCountPage;
	}

	public void setIsCountPage(Boolean isCountPage) {
		this.isCountPage = isCountPage;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

}
