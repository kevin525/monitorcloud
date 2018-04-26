package com.common.excel;

import java.util.List;

import org.apache.commons.beanutils.DynaBean;

import com.common.StringHelpers;

/**
 *@author 作者  李涛
 *@version v1
 *创建时间：2015年3月25日上午10:17:22
 *类说明：
 */
public class ReadExcelMain {
	
	/**
	 * @param filePath
	 * @return
	 */
	public static List<DynaBean> readExcel(String filePath){
		if(StringHelpers.isNull(filePath)){
			return null;
		}else{
			try {
				return ExcelReaderUtil.readExcel(filePath);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}

}
