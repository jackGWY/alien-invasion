package Tool;

import java.io.*;

/**
 * Created by zhouyu on 30/11/2016.
 * 用来获取指定路径下的所有文件
 */

public class FileList
{
    private static final String SEP = "/ "; // 带空格便于split
    private static StringBuffer sb = new StringBuffer("");

    //返回数组
    public static String[] getFiles(File f) throws IOException
    {
        if(f.isDirectory())
        {
            File[] fs=f.listFiles();
            // for(int i=0;i<fs.length;i++)
            //     getFiles(fs[i]);
            for (File i : fs)
            {
                getFiles(i);
            }
        }
        else
        {
            sb.append(f.getPath()).append(SEP); //在文件路径后添加SEP
        }
        String s = sb.toString();
        String[] list;
        list = s.split(SEP);
        return list;
    }

    //返回数组--重载
    public static String[] getFiles(String t) throws IOException
    {
        File f = new File(t);
        if(f.isDirectory())
        {
            File[] fs=f.listFiles();
            for (File i : fs)
            {
                IsTextFile isTextFile =IsTextFile.getInstance();//得到单件类的唯一实例
                if(isTextFile.isTxtFile(i.getName()))
                getFiles(i);
            }
        }
        // else
        // {
        //     sb.append(f.getPath()).append(SEP);
        //     // sb.append(f.getPath() + SEP);
        // }
        String s = sb.toString();
        // String[] list = s.split(SEP);
        String[] list;
        list = s.split(SEP);
        return list;
    }

    //主函数，测试
    // public static void main(String[] args) throws IOException
    // {
    //     String s[] = FileList.getFiles("/Users/zhouyu/Documents/Lucene/LuceneProject/TestFile");
    //     for (String i : s)
    //     {
    //         System.out.println(i);
    //     }
    // }
}
