package com.common.pagetag;

import java.util.Collection;

/**
 * @author 作者 李涛
 * @version v1 
 * 创建时间：2014年11月2日下午3:54:11
 * 类说明：
 */
public class PageGridLoad {

	private Long totalCount;// 总数
	private Collection<?> pageData;// 数据集合

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Collection<?> getPageData() {
		return pageData;
	}

	public void setPageData(Collection<?> pageData) {
		this.pageData = pageData;
	}

	public static PageGridLoad getPageGridLoad(Collection<?> list,PageGridPost pageGridPost) {
		PageGridLoad extGridLoad = new PageGridLoad();
		extGridLoad.setTotalCount(pageGridPost.getTotalCount());
		extGridLoad.setPageData(list);
		return extGridLoad;
	}

	public static PageGridLoad getPageGridLoad(Collection<?> list) {
		PageGridLoad extGridLoad = new PageGridLoad();
		extGridLoad.setPageData(list);
		return extGridLoad;
	}

}
