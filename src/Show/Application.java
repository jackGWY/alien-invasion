package Show;

import java.io.IOException;

/**
 * Created by zhouyu on 11/12/2016.
 * 主程序入口
 * 外观模式
 */
public class Application
{
    public static void main(String[] args) throws IOException
    {
        // GUI f=new GUI();
        // f.showGUI();
        // new GUI().showGUI();
        GUI gui;
        gui = new GUI();
        gui.showGUI();
    }
}
