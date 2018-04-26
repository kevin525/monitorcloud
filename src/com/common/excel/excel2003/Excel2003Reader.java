package com.common.excel.excel2003;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.LazyDynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

/**
 * @author 作者 李涛
 * @version v1 创建时间：2015年3月25日上午10:53:24 类说明：Read the Excel 2003
 */
public class Excel2003Reader {
	public static Map<String, String> attrNameMap;

	public static List<DynaBean> readXls(String path) {
		InputStream is;
		try {
			is = new FileInputStream(path);
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
			return parseWorkbook(hssfWorkbook);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解析excel的sheet
	 * 
	 * @param wb
	 * @return
	 */
	@SuppressWarnings("unused")
	private static List<DynaBean> parseWorkbook(HSSFWorkbook wb) {
		List<DynaBean> list = new Vector<DynaBean>();
		try {
			int sheetCount = wb.getNumberOfSheets();
			for (int sheetIndex = 0; sheetIndex < 1; sheetIndex++) {
				HSSFSheet sheet = wb.getSheetAt(sheetIndex);
				list = readExcel(sheet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 读取数据
	 * 
	 * @param sheet
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private static List<DynaBean> readExcel(HSSFSheet sheet) {
		attrNameMap = new LinkedHashMap<String, String>();
		List<DynaBean> list = new Vector<DynaBean>();
		try {
			int size = sheet.getLastRowNum();
			for (int i = 0; i <=size; i++) {
				DynaBean bean = new LazyDynaBean();
				HSSFRow row = sheet.getRow(i);
				// columnKey = row.getLastCellNum();
				for (short colnum = row.getFirstCellNum(); colnum <= row
						.getLastCellNum(); colnum++) {
					HSSFCell cell = row.getCell(colnum);
					if (null == cell) {
						continue;
					}
					String cellstr = "";
					if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {// 数值
						cellstr = getRightStr(String.valueOf(cell.getNumericCellValue()));
					} else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {// 字符串
						cellstr = cell.getStringCellValue();
					} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
						cellstr = String.valueOf(cell.getBooleanCellValue());
					} else {
						continue;
					}

					if (null == cellstr || "".equals(cellstr)) {
						continue;
					}
					String key = String.valueOf(colnum);
					// 第一行为属性名称
					if (i == 0) {
						attrNameMap.put(key, cellstr);
					} else {
						String attrName = attrNameMap.get(key);
						if (!StringUtils.isEmpty(attrName)) {
							bean.set(attrName, cellstr);
						} else {
							System.out.println(attrName + "key:" + key);
						}
					}
				}
				if (i > 0) {
					list.add(bean);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	private static String getRightStr(String sNum)   
    {   
        DecimalFormat decimalFormat = new DecimalFormat("#.000000");   
        String resultStr = decimalFormat.format(new Double(sNum));   
        if (resultStr.matches("^[-+]?\\d+\\.[0]+$"))   
        {   
            resultStr = resultStr.substring(0, resultStr.indexOf("."));   
        }   
        return resultStr;   
    } 
}
