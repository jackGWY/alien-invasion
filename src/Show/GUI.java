package Show;


import Indexer.Indexer;
import Searcher.Searcher;

import javax.swing.*;
import java.awt.*;
// import java.io.IOException;

/**
 * Created by zhouyu on 01/12/2016.
 * 用户图形界面
 * 输入：文件路径和索引存放路径
 * 输出：搜索结果
 *
 */
public class GUI
{
    static JTextArea jta;//文本显示区域, 子类indexMessage需要调用
    private Indexer indexer;
    private Searcher searcher;
    private JFileChooser fc;

    public GUI() //使用外观模式，子系统为indexer 和searcher
    {
        indexer=new Indexer();
        searcher=new Searcher();
        fc=new JFileChooser();
    }

    public void showGUI()
    {
        JTextField jtfa;//被索引的文件存放路径
        JButton jba;// 选择文件路径按钮
        JTextField jtfb;//选择索引存放路径
        JButton jbb;//选择索引路径按钮
        JButton jbc;//建立索引按钮
        JButton jbd;//搜索按钮
        JTextField jtfd;//输入关键字


        JFrame frame=new JFrame("基于Lucene的全文信息搜索引擎V1.0");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 窗口关闭方式
        frame.setSize(800,800);//窗口大小

        // final JFileChooser fc=new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); //文件路径选择

        Container con= frame.getContentPane();
        con.setLayout(new BorderLayout()); //边框布局

        JPanel jPanel=new JPanel();
        jPanel.setLayout(new GridLayout(4,2)); //网格布局，4行2列

        jtfa=new JTextField();
        jba=new JButton("选择文件存放路径");

        jtfb=new JTextField();
        jbb=new JButton("选择索引存放路径");

        JLabel jl=new JLabel("");
        jbc=new JButton("建立索引");
        jbc.setEnabled(false);

        jtfd=new JTextField("");
        jbd=new JButton("搜索");
        jbd.setEnabled(false);

        jPanel.add(jtfa);
        jPanel.add(jba);
        jPanel.add(jtfb);
        jPanel.add(jbb);
        jPanel.add(jl);
        jPanel.add(jbc);
        jPanel.add(jtfd);
        jPanel.add(jbd);

        jta = new JTextArea(""); // 空白显示结果区域；
        JScrollPane jsp=new JScrollPane(jta); // 为文本区域创建滚动条

        con.add(jPanel,BorderLayout.NORTH); // 将jPanel网格布局置于北位置，上方
        con.add(jsp,BorderLayout.CENTER); // 将jsp置于中心位置

        //使用 Lambda 表达式， interesting！！
        jba.addActionListener(e ->
        {
            int r=fc.showOpenDialog(null);
            if(r==JFileChooser.APPROVE_OPTION)
            {
                jtfa.setText(fc.getSelectedFile().getPath());
                jbc.setEnabled(true);
            }

        });

        jbb.addActionListener(e ->
        {
            int r=fc.showOpenDialog(null);
            if(r==JFileChooser.APPROVE_OPTION)
            {
                jtfb.setText(fc.getSelectedFile().getPath());
                jbc.setEnabled(true);
                jbd.setEnabled(true);
            }

        });

        jbc.addActionListener(e ->
        {
            try
            {
                indexer.index(jtfa.getText(),jtfb.getText());
                jbc.setEnabled(false);
                jbd.setEnabled(true);
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
                jbc.setEnabled(true);
                JOptionPane.showMessageDialog(null,"索引创建失败！");
                System.out.println(ee.getMessage());
            }
        });

        jbd.addActionListener(e ->
        {
            try
            {
                searcher.searchIndex(jtfd.getText(), jtfb.getText());
                // jsp.getHorizontalScrollBar()
                jta.setCaretPosition(0);//滚动条置顶
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null,"搜索失败！","提示",JOptionPane.ERROR_MESSAGE);

            }
        });


        // frame.pack(); //根据窗口里面的布局及组件的perferred Size来确定frame的最佳大小。
        frame.setVisible(true);

    }

}
