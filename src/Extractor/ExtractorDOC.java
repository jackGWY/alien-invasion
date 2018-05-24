package Extractor;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

import java.io.*;

/**
 * Created by zhouyu on 01/12/2016.
 * 处理.doc格式文件， 返回doc文件的所有内容
 */

public class ExtractorDOC implements Strategy
{
    public  String getText(String Name)
    {
        String result = "";
        try
        {
            FileInputStream fis = new FileInputStream(new File(Name));
            HWPFDocument doc = new HWPFDocument(fis);
            Range rang = doc.getRange();
            result += rang.text();
            fis.close();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return result;
    }

    // public static void main(String[] args) throws Exception
    // {
    //     String text = getText("xxx.doc");
    //     System.out.println(text);
    //     // toTextFile("xxx.doc","word.txt");
    // }

}
