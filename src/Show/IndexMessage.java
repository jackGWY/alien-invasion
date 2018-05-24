package Show;

import java.io.IOException;
import javax.swing.*;
// import java.awt.*;

/**
 * Created by zhouyu on 04/12/2016.
 * 面板显示index结果
 */
public class IndexMessage extends GUI
{

    public void showIndexMessage(String f) throws IOException
    {

        jta.setText(jta.getText() + "已经归入索引： " + f + "\n");
    }

    public void indexSuccess(long t) throws IOException
    {
        jta.setText(jta.getText() + "创建索引耗时： " + t + "ms\n");
        JOptionPane.showMessageDialog(null, "索引建立完毕", "提示", JOptionPane.INFORMATION_MESSAGE);
    }

}
