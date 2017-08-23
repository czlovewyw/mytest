package org.cz.classloader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chenzhe8 on 2017/7/4.
 */
public class T2 {
    public static final int  FILE_SIZE = 1024 * 1024 * 8;
    public static final String  PATH = "F:\\";
    public static final String  FILENAME = "store";
    public static final int  BEI_SHU = 1;
    public static void main(String[] args) {
        createBigFile();
    }

    public static void createBigFile() {


        byte[] memFile = new byte[FILE_SIZE];
        for (int i = 0; i < FILE_SIZE; i++) {
            memFile[i] = (byte) (i % 20);
        }


        RandomAccessFile randomFile = null;
        try {
            randomFile = new RandomAccessFile(PATH+FILENAME, "rw");


            for (int i = 0; i < BEI_SHU; i++) {
                long fileLength = randomFile.length();
                randomFile.seek(fileLength);
                randomFile.write(memFile);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
