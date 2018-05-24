package Searcher;

import Show.SearchMessage;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.*;
import org.apache.lucene.analysis.Analyzer;
// import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.apache.lucene.search.highlight.*;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.util.*;


/**
 * Created by zhouyu on 01/12/2016.
 * 建立搜索过程
 * 也可以使用外观模式
 */
public class Searcher
{
    //private static final String INDEX_DIR = "/Users/zhouyu/Documents/Lucene/LuceneProject/IndexFile/";//索引存放的位置

    //text 为查询目标
    public void searchIndex(String text,String indexPath)
    {

        SearchMessage searchMessage = new SearchMessage();
        Analyzer analyzer;
        Directory directory;

        try
        {
            Date date1 = new Date();
            directory = FSDirectory.open(new File(indexPath));

            // analyzer = new StandardAnalyzer(Version.LUCENE_40);
            analyzer = new IKAnalyzer();//中文分词包

            DirectoryReader reader = DirectoryReader.open(directory);
            IndexSearcher searcher = new IndexSearcher(reader);

            QueryParser parser = new QueryParser(Version.LUCENE_40, "content", analyzer);
            Query query = parser.parse(text);

            // ScoreDoc[] hits = searcher.search(query, null, 1000).scoreDocs;
            TopDocs top=searcher.search(query, 100);
            ScoreDoc[] hits = top.scoreDocs;

            //高亮
            SimpleFragmenter sf = new SimpleFragmenter(60);
            SimpleHTMLFormatter shf=new SimpleHTMLFormatter("<span style=\"color:red;\">", "</span>");//定制高亮标签
            Highlighter highlighter =new Highlighter(shf,new QueryScorer(query));
            // Highlighter highlighter =new Highlighter(sf,new QueryScorer(query));
            highlighter.setTextFragmenter( sf );

            Date date2 = new Date();

            System.out.println("共检索到"+hits.length+"条含有关"+text+"的结果");

            searchMessage.showSearchMessage(hits.length,text,date2.getTime() - date1.getTime());



            // for (int i = 0; i < hits.length; i++)
            int j=0;
            for (ScoreDoc i : hits)
            {
                j+=1;
                Document hitDoc = searcher.doc(i.doc);
                if(hitDoc == null)
                {
                    continue;
                }

                StringBuilder sb=new StringBuilder("");
                sb.append("--------------------  第"+j+"条搜索结果:"+" --------------------"+"\n");
                sb.append("文件名："+hitDoc.get("filename") +"\n");
                sb.append("文件路径:"+hitDoc.get("path") + "\n");
                sb.append("文件最后修改时间:"+hitDoc.get("cdate") + "\n");
                int maxNumFragmentsRequired = 3;
                QueryScorer score=new QueryScorer(query, "content");//传入评分
                // String fragmentSeparator = "...";
                String content=hitDoc.get("content");
                TokenStream token=TokenSources.getAnyTokenStream(searcher.getIndexReader(), i.doc, "content", analyzer);
                // Fragmenter  fragment=new SimpleSpanFragmenter(score);
                // highlighter.setTextFragmenter(fragment);
                String str=highlighter.getBestFragment(token,content);//获取高亮的片段，可以对其数量进行限制
                // sb.append("文件内容："+hitDoc.get("content") +"\n");
                sb.append("相关结果："+str +"\n");
                sb.append("______________________________________________________________" + "\n\n");


                searchMessage.showSearchResult(sb.toString());

                System.out.println("搜索-----耗时：" + (date2.getTime() - date1.getTime()) + "ms\n");
                System.out.println("____________________________");
                System.out.println(hitDoc.get("filename"));
                System.out.println(hitDoc.get("content"));
                System.out.println(hitDoc.get("path"));
                System.out.println("____________________________");
            }


            reader.close();
            directory.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    //test main
    public static void main(String[] args) throws IOException
    {
        final String INDEX_DIR = "/Users/zhouyu/Documents/Lucene/LuceneProject/IndexFile/";//索引存放的位置
        new Searcher().searchIndex("standing", INDEX_DIR);

    }
}
