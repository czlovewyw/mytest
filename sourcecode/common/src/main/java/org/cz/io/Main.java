package org.cz.io;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by chenzhe8 on 2017/6/29.
 */
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
//        File directory = new File("d:\\cztemp");
//        directory.mkdirs();
//        File.createTempFile("cz123456","tmp",directory);
//        Files.createTempFile(directory.toPath(),"wwwwwww",".tmp");
//
//        Files.createTempDirectory(directory.toPath(),"avc");
//        File directory2 = new File("d:\\cztemp2\\aaaa");
//        Files.createDirectories(directory2.toPath());

        Files.createDirectories(Paths.get("d:\\aaaaa\\vvvvv"));
        Thread.sleep(1000000);
    }
}
