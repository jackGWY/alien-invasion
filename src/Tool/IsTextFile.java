package Tool;

/**
 * Created by zhouyu on 05/12/2016.
 * 过滤不需要的文件，只留需要解析的文本文件
 * 单件模式
 */
public class IsTextFile
{
    // private static IsTextFile uniqueInstance = new IsTextFile();
    private static IsTextFile uniqueInstance ;
    public static  synchronized  IsTextFile getInstance() //同步方法
    {
        if(uniqueInstance==null)
        {
            uniqueInstance= new IsTextFile();
        }
        return uniqueInstance;
    }
    public boolean isTxtFile(String fileName)
    {
        if (fileName.lastIndexOf(".txt") > 0)
        {
            return true;
        }
        else if ((fileName.lastIndexOf(".xls") > 0)||(fileName.lastIndexOf(".xlsx") > 0))
        {
            return true;
        }
        else if ((fileName.lastIndexOf(".doc") > 0)||(fileName.lastIndexOf(".docx") > 0))
        {
            return true;
        }
        else if ((fileName.lastIndexOf(".htm") > 0)||(fileName.lastIndexOf(".html") > 0))
        {
            return true;
        }
        return false;
    }

}
