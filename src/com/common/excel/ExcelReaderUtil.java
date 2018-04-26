package com.common.excel;

import java.util.List;

import org.apache.commons.beanutils.DynaBean;

import com.common.excel.excel2003.Excel2003Reader;
import com.common.excel.excel2007.Excel2007Reader;


public class ExcelReaderUtil {  
    
    //excel2003
    public static final String OFFICE_EXCEL_2003_POSTFIX= ".xls";  
    //excel2007
    public static final String OFFICE_EXCEL_2007_POSTFIX  = ".xlsx";  
      
    /** 
     * 读取Excel文件，可能是03也可能是07版本 
     * @param excel03 
     * @param excel07 
     * @param fileName 
     */  
    public static List<DynaBean> readExcel(String filePath){  
        try {
			if (filePath.endsWith(OFFICE_EXCEL_2003_POSTFIX)){  //处理excel2003文件 
				return Excel2003Reader.readXls(filePath);
			} else if (filePath.endsWith(OFFICE_EXCEL_2007_POSTFIX)){  //处理excel2007文件
				return Excel2007Reader.readXlsx(filePath);
			} else {  
			    return null; 
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}  
    }  
}  