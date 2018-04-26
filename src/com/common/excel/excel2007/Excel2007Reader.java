package com.common.excel.excel2007;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.LazyDynaBean;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author 作者 李涛
 * @version v1 创建时间：2015年3月25日上午10:53:46 类说明：Read the Excel 2007
 */
public class Excel2007Reader {
	public static Map<String, String> attrNameMap;

	public static List<DynaBean> readXlsx(String path) {
		InputStream is;
		try {
			is = new FileInputStream(path);
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
			return parseWork2007book(xssfWorkbook);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private static List<DynaBean> parseWork2007book(XSSFWorkbook wb) {
		List<DynaBean> list = new Vector<DynaBean>();
		try {
			//int sheetCount = wb.getNumberOfSheets();
			for (int sheetIndex = 0; sheetIndex < 1; sheetIndex++) {
				XSSFSheet sheet = wb.getSheetAt(sheetIndex);
				list = readExcel2007(sheet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private static List<DynaBean> readExcel2007(XSSFSheet sheet) {
		attrNameMap = new HashMap<String, String>();
		List<DynaBean> list = new Vector<DynaBean>();
		int size = sheet.getLastRowNum();
		for (int i = 0; i <= size; i++) {
			DynaBean bean = new LazyDynaBean();
			XSSFRow row = sheet.getRow(i);
			if(row!=null){
				for (short colnum = row.getFirstCellNum(); colnum <= row
						.getLastCellNum(); colnum++) {
					XSSFCell cell = row.getCell(colnum);
					if (null == cell) {
						continue;
					}
					String cellstr = "";
					if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
						cellstr = getRightStr(String.valueOf(cell.getNumericCellValue()));
					} else if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
						cellstr = cell.getStringCellValue();
					} else if (cell.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {
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
						bean.set(attrName, cellstr);
					}
				}
				if (i > 0)
					list.add(bean);
			}
			// columnKey = row.getLastCellNum();
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
