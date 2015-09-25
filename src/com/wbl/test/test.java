package com.wbl.test;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Simple_love on 2015/9/20.
 */
class Base{}
class Derived extends Base{}
public class test {
        public static void main(String[] args){
               FamilyVsExactType(new Base());
               FamilyVsExactType(new Derived());
        }
        public static void PatternAndMatcher(){
                String str = "/*! Here's a block of text to use as input to the regular expression matcher. Note that we'll" +
                        "first extract the block of text by looking of the special delimiters, then process the extracted block !*/";
                Pattern p = Pattern.compile("/\\*!(.*)!\\*/", Pattern.DOTALL);
                Matcher m = p.matcher(str);
                if (m.find()){
                        str = m.group(1);
                        System.out.println(str);
                }

                Matcher matcher = Pattern.compile("[aeiou]").matcher(str);
                StringBuffer buffer = new StringBuffer();
                while (matcher.find()){
                        matcher.appendReplacement(buffer,matcher.group().toUpperCase());
                }
                System.out.println(buffer);
                matcher.appendTail(buffer);
                System.out.println(buffer);
        }
        public static void FamilyVsExactType(Object x){
                System.out.println("Testing x of type: " + x.getClass());
                System.out.println("x instanceof base: " + (x instanceof Base));
                System.out.println("x instanceof derived: " + (x instanceof Derived));

                System.out.println("Base.isInstance(x): " + Base.class.isInstance(x));
                System.out.println("Derived.isInstance(x): " + Derived.class.isInstance(x));

                System.out.println("x.getClass() == Base.class: " + (x.getClass() == Base.class));
                System.out.println("x.getClass() == Derived.class: " + (x.getClass() == Derived.class));

                System.out.println("x.getClass().equals(Base.class): " + (x.getClass().equals(Base.class)));
                System.out.println("x.getClass().equals(Derived.class): " + (x.getClass().equals(Derived.class)));
        }
}

