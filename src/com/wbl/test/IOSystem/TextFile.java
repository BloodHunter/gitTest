package com.wbl.test.IOSystem;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by Simple_love on 2015/10/18.
 */
public class TextFile extends ArrayList<String> {
        public static String read(String fileName){
                StringBuilder sb = new StringBuilder();
                try {
                        BufferedReader in = new BufferedReader(new FileReader(new File(fileName).getAbsoluteFile()));
                        try {
                                String s;
                                while ((s = in.readLine()) != null){
                                        sb.append(s);
                                        sb.append("\n");
                                }
                        }finally {
                                in.close();
                        }
                }catch (IOException e){
                        throw new RuntimeException(e);
                }
                return sb.toString();
        }

        public static void wirte(String fileName,String text){
                try {
                        PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
                        try {
                                out.print(text);
                        }finally {
                                out.close();
                        }
                }catch (IOException e){
                        throw new RuntimeException(e);
                }
        }

        public TextFile(String fileName,String splitter){
                super(Arrays.asList(read(fileName).split(splitter)));
                if (get(0).equals(""))
                        remove(0);
        }

        public TextFile(String fileName){
                this(fileName, "\n");
        }

        public void wirte(String fileName){
                try{
                        PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
                        try {
                                for (String item : this){
                                        out.println(item);
                                }
                        }finally {
                                out.close();
                        }
                }catch (IOException e){
                        throw new RuntimeException(e);
                }
        }


        public static void main(String[] args){
                /*String file = read("src/com/wbl/test/IOSystem/TextFile.java");
                wirte("text.txt", file);*/
                TextFile textFile = new TextFile("text.txt");
                Map<Character,Integer> map = new HashMap<>();
                for (String text: textFile){
                        text = text.replaceAll(" ","");
                        for (int i = 0; i < text.length(); i++){
                                Character c = text.charAt(i);
                                if (Pattern.compile("\\w").matcher(String.valueOf(c)).matches()){
                                        if (map.containsKey(c)){
                                                map.put(c,map.get(c) + 1);
                                        }else {
                                                map.put(c, 1);
                                        }
                                }
                        }
                }
                System.out.println(map);
        }
}
