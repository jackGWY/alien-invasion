package Tool;

import java.io.File;
import java.io.IOException;

/**
 * Created by zhouyu on 05/12/2016.
 * 再次创建index时，把原文件夹下的index文件清空
 */
public class DeleteIndex
{
    /**
     * 删除文件目录下的所有文件
     * @param file 要删除的文件目录
     * @return 如果成功，返回true.
     * 使用command模式去改进
     */
    public static boolean deleteDir(File file) throws IOException
    {
        if(file.isDirectory())
        {
            File[] files = file.listFiles();
            for(int i=0; i<files.length; i++)
            {
                deleteDir(files[i]);
            }
        }
        file.delete();
        return true;
    }
}
