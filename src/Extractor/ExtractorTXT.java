package Extractor;

import java.io.*;

/**
 * Created by zhouyu on 01/12/2016.
 * 处理.txt格式文件， 返回txt文件的所有内容
 */

public class ExtractorTXT implements Strategy
{
    public String getText(String Name)
    {
        StringBuilder sb=new StringBuilder("");
        try
        {
            FileReader fr=new FileReader(Name);
            BufferedReader br=new BufferedReader(fr);

            String s=br.readLine();
            while(s!=null)
            {
                sb.append(s);
                sb.append('\n');
                s=br.readLine();
            }
            br.close();
        }
        catch(Exception e)
        {
            sb.append("getText wrong!");
            e.printStackTrace();
        }

        return sb.toString();
    }

    // 在父类中进行这个操作！
    // 其他格式文件转化为TXT文件
    // public static void toTextFile(String FileName,String txt) throws Exception
    // {
    //     // 提取文本
    //     String text = getText(FileName);
    //
    //     //写入文本文件
    //     PrintWriter pw=new PrintWriter(new FileWriter(new File(txt)));
    //     pw.write(text);
    //     pw.flush();
    //     pw.close();
    //
    //     System.out.println("成功写入文本文件 " + txt);
    // }

    //test
    // public static void main(String[] args) throws Exception
    // {
    //     String text = getText("xxx.xxx");
    //     System.out.println(text);
    //     toTextFile("xxx.xxx","txt.txt");
    // }

}
