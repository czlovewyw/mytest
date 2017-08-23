package org.cz;

//import org.apache.lucene.analysis.TokenStream;
//import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
//import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
//import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
//import org.wltea.analyzer.lucene.IKAnalyzer;
//
//import java.io.IOException;
//import java.io.StringReader;

/**
 * Created by chenzhe8 on 2017/6/26.
 */
public class IKAnalzyerDemo {
//    public IKAnalzyerDemo() {
//    }
//
//    public static void main(String[] args) {
//        IKAnalyzer analyzer = new IKAnalyzer(true);
//        TokenStream ts = null;
//
//        try {
//            ts = analyzer.tokenStream("myfield", new StringReader("这是一个中文分词的例子，你可以直接运行它！IKAnalyer can analysis english text too"));
//            OffsetAttribute e = (OffsetAttribute)ts.addAttribute(OffsetAttribute.class);
//            CharTermAttribute term = (CharTermAttribute)ts.addAttribute(CharTermAttribute.class);
//            TypeAttribute type = (TypeAttribute)ts.addAttribute(TypeAttribute.class);
//            ts.reset();
//
//            while(ts.incrementToken()) {
//                System.out.println(e.startOffset() + " - " + e.endOffset() + " : " + term.toString() + " | " + type.type());
//            }
//
//            ts.end();
//        } catch (IOException var14) {
//            var14.printStackTrace();
//        } finally {
//            if(ts != null) {
//                try {
//                    ts.close();
//                } catch (IOException var13) {
//                    var13.printStackTrace();
//                }
//            }
//
//        }
//
//    }
}