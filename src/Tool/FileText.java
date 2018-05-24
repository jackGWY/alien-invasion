package Tool;

import java.io.*;

/**
 * Created by zhouyu on 30/11/2016.
 * 用来获取指定路径的文本文件内容
 * 后用Extractor实现功能扩展
 */

public class FileText
{
    //具体实现读取一个文件的所有内容，每次读一行
    private static String getText(File f)
    {
        StringBuilder sb=new StringBuilder("");
        try
        {
            // FileReader fr=new FileReader(f);
            // BufferedReader br=new BufferedReader(fr);
            //
            // String s=br.readLine();
            //
            // while(s!=null)
            // {
            //     sb.append(s);
            //     sb.append('\n');
            //     s=br.readLine();
            // }
            // br.close();
        }
        catch(Exception e)
        {
            sb.append("Warning:There are no this file!");
        }

        return sb.toString();
    }

    //获取文件路径，读取文件返回t--重载
    private static String getText(String s)
    {
        String t;
        try
        {
            File f = new File(s);
            t = getText(f);    // call getText(File f)
        }
        catch(Exception e)
        {
            t = "Something Wrong!";
        }

        return t;
    }

    //主函数，测试
    public static void main(String[] args) throws IOException
    {
        String s = FileText.getText("/Users/zhouyu/Documents/Lucene/LuceneProject/TestFile/苏州河.txt");
        System.out.println(s);
    }
}
