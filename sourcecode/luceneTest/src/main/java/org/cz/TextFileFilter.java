package org.cz;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by chenzhe8 on 2017/6/26.
 */
public class TextFileFilter implements FileFilter {

    public boolean accept(File pathname) {
        return pathname.getName().toLowerCase().endsWith(".txt");
    }
}