package com.chat.web.common.util;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: franky
 * @CreateDate: 2019-07-17 15:26
 * @Version: 1.0
 */
public class ExcelUtil {
    public static String[][] getData(InputStream inputStream, int ignoreRows)
            throws IOException, InvalidFormatException {
        List<String[]> result = new ArrayList<>();
        int rowSize = 0;
        Workbook wb = WorkbookFactory.create(inputStream);
        Cell cell;
        for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
            Sheet st = wb.getSheetAt(sheetIndex);

            for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
                Row row = st.getRow(rowIndex);
                if (row == null) {
                    continue;
                }
                if (rowIndex < 0) {
                    continue;
                }
                int tempRowSize = row.getLastCellNum() + 1;
                if (tempRowSize > rowSize) {
                    rowSize = tempRowSize;
                }

                String[] values = new String[rowSize];
                Arrays.fill(values, "");
                boolean hasValue = false;
                for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
                    String value = "";
                    cell = row.getCell(columnIndex);
                    if (cell != null) {
                        switch (cell.getCellTypeEnum()) {
                            //CellType#NUMERIC
                            case NUMERIC:
                                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                    Date date = cell.getDateCellValue();
                                    if (date != null) {
                                        value = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
                                    } else {
                                        value = "";
                                    }
                                } else {
                                    long longVal = Math.round(cell.getNumericCellValue());
                                    double doubleVal = cell.getNumericCellValue();
                                    if (Double.parseDouble(longVal + ".0") == doubleVal)
                                        value = String.valueOf(longVal);
                                    else
                                        value = String.valueOf(doubleVal);
                                }
                                break;
                            case STRING:
                                value = cell.getStringCellValue();
                                break;
                            case FORMULA:
                                // 导入时如果为公式生成的数据则无值
                                if (!cell.getStringCellValue().equals("")) {
                                    value = cell.getStringCellValue();
                                } else {
                                    value = cell.getNumericCellValue() + "";
                                }
                                break;
                            case BLANK:
                                break;
                            case ERROR:
                                value = "";
                                break;
                            case BOOLEAN:
                                value = (cell.getBooleanCellValue() == true ? "Y"
                                        : "N");
                                break;
                            default:
                                value = "";
                        }
                    }
                    if (columnIndex == 0 && value.trim().equals("")) {
                        break;
                    }
                    values[columnIndex] = rightTrim(value);
                    hasValue = true;
                }
                if (hasValue) {
                    result.add(values);
                }
            }

        }
        String[][] returnArray = new String[result.size()][rowSize];
        for (int i = 0; i < returnArray.length; i++) {
            returnArray[i] = result.get(i);
        }
        return returnArray;
    }

    /**
     *
     * 去掉字符串右边的空格
     *
     * @param str
     *            要处理的字符串
     *
     * @return 处理后的字符串
     */
    public static String rightTrim(String str) {
        if (RStringUtils.isEmpty(str)) return "";
        int length = str.length();
        for (int i = length - 1; i >= 0; i--) {
            if (str.charAt(i) != 0x20) {
                break;
            }
            length--;
        }
        return str.substring(0, length);
    }

}
