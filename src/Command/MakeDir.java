package Command;

import java.io.File;

/**
 * Created by zhouyu on 11/12/2016.
 * 接受者
 */
public class MakeDir
{
    public void creatDir(String name)
    {
        File dir = new File(name);
        dir.mkdir();

    }
    public void  deleteDir(String name)
    {
        File dir = new File(name);
        dir.delete();

    }

}
