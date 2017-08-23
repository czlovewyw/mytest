package org.cz;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.ClassicAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;

import java.io.StringReader;

/**
 * 最简单例子，使用分词器分词并打印分词后的结果
 *
 * Created by chenzhe8 on 2017/6/26.
 */
public class HelloworldTest {
    public static void main(String[] args) {
        String keyWord = "分词效果到底怎么样呢，我们来看一下吧";
        //创建IKAnalyzer中文分词对象
        Analyzer analyzer = new ClassicAnalyzer(Version.LUCENE_40);
        // 打印分词结果
        try {
            printAnalysisResult(analyzer, keyWord);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印出给定分词器的分词结果
     *
     * @param analyzer
     *            分词器
     * @param keyWord
     *            关键词
     * @throws Exception
     */
    private static void printAnalysisResult(Analyzer analyzer, String keyWord)
            throws Exception {
        System.out.println("["+keyWord+"]分词效果如下");
        TokenStream tokenStream = analyzer.tokenStream("content",
                new StringReader(keyWord));
        tokenStream.addAttribute(CharTermAttribute.class);
        while (tokenStream.incrementToken()) {
            CharTermAttribute charTermAttribute = tokenStream
                    .getAttribute(CharTermAttribute.class);
            System.out.println(charTermAttribute.toString());

        }
    }
}