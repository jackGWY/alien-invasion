package Indexer;

import Show.*;
import Tool.*;
import Extractor.*;

import org.apache.lucene.analysis.Analyzer;
// import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.FieldType;
import org.wltea.analyzer.lucene.IKAnalyzer;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zhouyu on 01/12/2016.
 * Indexer.java  建立索引
 * 可以继续使用外观模式，此处省略
 */

public class Indexer
{
    // private static final String INDEX_DIR = "/Users/zhouyu/Documents/Lucene/LuceneProject/IndexFile/";//索引存放的位置
    // private static final String DATA_DIR = "/Users/zhouyu/Documents/Lucene/LuceneProject/TestFile";//文件的位置
    // private  Analyzer analyzer;
    // private  Directory directory ;
    // private  IndexWriter indexWriter ;

    public boolean index(String filesPath, String indexPath) throws IOException
    {
        Date date1 = new Date();
        IndexMessage indexMessage=new IndexMessage();

        Analyzer analyzer;
        Directory directory ;
        IndexWriter indexWriter ;

        //生成索引之前先将之前的索引删除，防止再次生成索引时重复插入索引。
        File fileIndex = new File(indexPath);
        if(DeleteIndex.deleteDir(fileIndex))
        {
            fileIndex.mkdir();//mkdir（）创建此抽象路径名指定的目录
        }
        else
        {
            fileIndex.mkdir();
        }

        String s[] = FileList.getFiles(filesPath); //利用FileList.getFiles()方法获得path下的文件列表
        for (String file : s)
        {
            File f = new File(file);
            // System.out.println(f); //f是文件路径

            String content = ExtractorAll.getExt(f.toString()); // 调用getExt方法获得不同文件格式的文件内容
            System.out.println(f.getName()); //输出文件名
            System.out.println(f.toString()); //输出文件路径

            try{
                //词法分析器
                // analyzer = new StandardAnalyzer(Version.LUCENE_40);
                analyzer = new IKAnalyzer();//中文分词包

                // 索引存储位置
                directory = FSDirectory.open(new File(indexPath));
                // 索引文件夹不存在则新建一个
                File indexFile = new File(indexPath);
                if (!indexFile.exists())
                {
                    indexFile.mkdirs();
                }

                IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_40, analyzer);
                indexWriter = new IndexWriter(directory, config);

                Document document = new Document();
                //
                FieldType type=new FieldType(TextField.TYPE_STORED);
                type.setStoreTermVectorOffsets(true);//记录相对增量，用于高亮
                type.setStoreTermVectorPositions(true);//记录位置信息
                type.setStoreTermVectors(true);//存储向量信息
                type.freeze();//阻止改动信息
                Field field = new Field("content", content ,type);
                document.add(field);
                // field = new Field("filename", f.getName() ,type);
                // document.add(field);
                // field = new Field("path", f.getPath() ,type);
                // document.add(field);
                document.add(new TextField("filename", f.getName(), Field.Store.YES));
                // document.add(new TextField("content", content, Field.Store.YES));
                document.add(new TextField("path", f.getPath(), Field.Store.YES));
                Date dt=new Date(f.lastModified());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd E ");
                String cdate = sdf.format(dt);
                document.add(new TextField("cdate", cdate, Field.Store.YES));
                // field = new Field("cdate", cdate ,type);
                // document.add(field);


                indexWriter.addDocument(document);
                indexWriter.commit();
                indexWriter.close();
            }
            catch(Exception e)
            {
                System.out.println("wrong !");
                e.printStackTrace();
            }

            indexMessage.showIndexMessage(f.getName());// 在面板显示index的过程


        }

        Date date2 = new Date();
        System.out.println("创建索引成功-----耗时：" + (date2.getTime() - date1.getTime()) + "ms\n");

        indexMessage.indexSuccess(date2.getTime() - date1.getTime());

        return true;
    }

    public static void main(String[] args) throws IOException
    {
        final String DATA_DIR = "/Users/zhouyu/Documents/Lucene/LuceneProject/TestFile";//文件的位置
        final String INDEX_DIR = "/Users/zhouyu/Documents/Lucene/LuceneProject/IndexFile/";//索引存放的位置
        new Indexer().index(DATA_DIR,INDEX_DIR);
    }
}


