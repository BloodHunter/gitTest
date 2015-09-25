package com.wbl.test.RTTI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simple_love on 2015/9/24.
 */
public class FornameCreator extends PetCreator {
        private static List<Class<? extends Pet>> types = new ArrayList<>();
        private static String[] typesName = {
                "com.wbl.test.RTTI.Mutt",
                "com.wbl.test.RTTI.Pug",
                "com.wbl.test.RTTI.Manx",
                "com.wbl.test.RTTI.Cymric",
                "com.wbl.test.RTTI.Rat",
                "com.wbl.test.RTTI.Mouse"
        };
        private static void loader(){
                try {
                        for (String name:typesName){
                                types.add((Class<? extends Pet>) Class.forName(name));
                        }
                }catch (ClassNotFoundException e){
                        throw new RuntimeException(e);
                }
        }
        static {loader();}
        public List<Class<? extends Pet>> types(){
                return types;
        }
}
