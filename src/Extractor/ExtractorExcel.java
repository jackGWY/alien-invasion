package Extractor;


import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by zhouyu on 01/12/2016.
 * 处理.xls格式文件， 返回xls文件的所有内容
 */

public class ExtractorExcel implements Strategy
{
    public String getText(String Name)
    {
        String result = "";
        try
        {
            FileInputStream fis = new FileInputStream(new File(Name));
            StringBuilder sb = new StringBuilder();
            jxl.Workbook rwb = Workbook.getWorkbook(fis);
            Sheet[] sheet = rwb.getSheets();
            for (int i = 0; i < sheet.length; i++)
            {
                Sheet rs = rwb.getSheet(i);
                for (int j = 0; j < rs.getRows(); j++)
                {
                    Cell[] cells = rs.getRow(j);
                    for (int k = 0; k < cells.length; k++)
                        sb.append(cells[k].getContents());
                }
            }
            fis.close();
            result += sb.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }



    // public static void main(String[] args) throws Exception
    // {
    //     String text = getText("xxx.xls");
    //     System.out.println(text);
    // }

}
