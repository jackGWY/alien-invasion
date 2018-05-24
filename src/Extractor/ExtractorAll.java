package Extractor;

import java.io.*;

/**
 * Created by zhouyu on 01/12/2016.
 * 抽取文件的格式，根据格式用不用的方法处理
 * 使用策略模式
 */

public class ExtractorAll
{
    public static String getExt(String FileName)
    {
        Context context = new Context(); //创建上下文对象
        String text;
        try
        {
            // 根据扩展名判断文件类型
            String ext = FileName.substring(FileName.lastIndexOf(".")+1);

            if(ext.equalsIgnoreCase("doc"))
            {
                //处理.doc文件
                // text = ExtractorDOC.getText(FileName);
                context.setStrategy(new ExtractorDOC()); //使用策略一解析doc
                text=context.getStrategy(FileName);
            }
            else if(ext.equalsIgnoreCase("docx"))
            {
                //处理.docx文件
                // text = ExtractorDOCX.getText(FileName);
                context.setStrategy(new ExtractorDOCX()); //使用策略二解析docx
                text=context.getStrategy(FileName);
            }
            else if(ext.equalsIgnoreCase("xls"))
            {
                //处理Excel文件
                // text = ExtractorExcel.getText(FileName);
                context.setStrategy(new ExtractorExcel()); //使用策略三解析xls
                text=context.getStrategy(FileName);
            }
            // else if(ext.equalsIgnoreCase("pdf"))
            // {
            //     //处理PDF文件
            //     text = ExtractorPDF.getText(FileName);
            // }
            // else if(ext.equalsIgnoreCase("htm") || ext.equalsIgnoreCase("html"))
            // {
            //     //处理HTML文件
            //     text = ExtractorHTML.getText(FileName);
            // }
            // else if(ext.equalsIgnoreCase("xml"))
            // {
            //     //处理XML文件
            //     text = ExtractorXML.getText(FileName);
            // }
            else
            {
                //对于其他扩展名的文件都假设文件是文本文件
                // text = ExtractorTXT.getText(FileName);
                context.setStrategy(new ExtractorTXT());//使用策略四解析TXT
                text=context.getStrategy(FileName);
            }
        }
        catch(Exception e)
        {
            text = "Something Wrong !";
        }

        return text;
    }

    //将文件doc转化为txt
    public static void toTextFile(String doc,String txt) throws Exception
    {
        // 提取文本
        String text = getExt(doc);

        //写入文本文件
        PrintWriter pw=new PrintWriter(new FileWriter(new File(txt)));
        pw.write(text);
        pw.flush();
        pw.close();

        System.out.println("成功写入文本文件 " + txt);
    }

    //test
    public static void main(String[] args) throws Exception
    {
        String text = getExt("Youth.txt");
        System.out.println(text);
        toTextFile("Youth.xml","Youth.txt");
    }
}
