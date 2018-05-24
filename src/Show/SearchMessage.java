package Show;

// import java.awt.*;
import java.io.IOException;
// import javax.swing.*;


/**
 * Created by zhouyu on 04/12/2016.
 * 显示搜索结果
 */

public class SearchMessage extends GUI
{
    public void showSearchMessage(int length, String text, long t) throws IOException
    {
        jta.setText("共检索到"+length+"条含有关“"+text+"”的结果"+"\n");
        jta.setText(jta.getText()+"查找索引耗时：" + t + "ms\n");
        jta.setText(jta.getText() + "*************************************************" + "\n");

    }

    public void showSearchResult(String result) throws IOException
    {
        jta.setText(jta.getText() + result);
        //

    }

}
