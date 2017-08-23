package org.cz;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import java.io.IOException;

/**
 * 用来测试 Lucene 库的索引和搜索功能
 *
 * Created by chenzhe8 on 2017/6/26.
 */
public class LuceneTester {

    String indexDir = "e:\\tmp\\lucene\\data";
    String dataDir = Thread.currentThread().getContextClassLoader().getResource("").getPath();
//    String dataDir = LuceneTester.class.getResource("/").getPath();
    Indexer indexer;
    Searcher searcher;

    public static void main(String[] args) {
//        LuceneTester tester;
//        try {
//            tester = new LuceneTester();
////            tester.createIndex();
//            tester.search("文里里里里里里");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
    }

    private void createIndex() throws IOException{
        indexer = new Indexer(indexDir);
        int numIndexed;
        long startTime = System.currentTimeMillis();
        numIndexed = indexer.createIndex(dataDir, new TextFileFilter());
        long endTime = System.currentTimeMillis();
        indexer.close();
        System.out.println(numIndexed+" File indexed, time taken: "
                +(endTime-startTime)+" ms");
    }

    private void search(String searchQuery) throws IOException{
        searcher = new Searcher(indexDir);
        long startTime = System.currentTimeMillis();
        TopDocs hits = searcher.search(searchQuery);
        long endTime = System.currentTimeMillis();

        System.out.println(hits.totalHits +
                " documents found. Time :" + (endTime - startTime));
        for(ScoreDoc scoreDoc : hits.scoreDocs) {
            Document doc = searcher.getDocument(scoreDoc);
            System.out.println("File: "
                    + doc.get(LuceneConstants.FILE_PATH)+":"+doc.get(LuceneConstants.CONTENTS));
        }
        searcher.close();
    }
}
