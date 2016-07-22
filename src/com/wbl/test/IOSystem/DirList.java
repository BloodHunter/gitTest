package com.wbl.test.IOSystem;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/**
 * Created by Simple_love on 2015/10/14.
 */
public class DirList {
        public static void main(String[] args){
                File path = new File("C:\\Users\\Lulala\\IdeaProjects\\JavaWeb\\src\\com\\wbl\\aop");
                String[] list;
                list = path.list(new DirFilter(".*\\.java$"));
                for (String dirItem: list)
                        System.out.println(dirItem);
        }
}
class DirFilter implements FilenameFilter{
        private Pattern pattern;
        public DirFilter(String regex){
                pattern = Pattern.compile(regex);
        }
        public boolean accept(File dir,String name){
                return pattern.matcher(name).matches();
        }
}
