package Extractor;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by zhouyu on 01/12/2016.
 * 处理.docx格式文件， 返回docx文件的所有内容
 */

public class ExtractorDOCX implements Strategy
{
    public String getText(String Name)
    {
        String text = "";
        try
        {
            FileInputStream fis = new FileInputStream(new File(Name));
            XWPFDocument docx = new XWPFDocument(fis);
            XWPFWordExtractor extractor = new XWPFWordExtractor(docx);
            text = extractor.getText();
            fis.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return text;
    }

    // public static void main(String[] args) throws Exception
    // {
    //     String text = getText("xxx.docx");
    //     System.out.println(text);
    //     toTextFile("xxx.docx","word.txt");
    // }

}
