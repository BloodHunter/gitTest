package com.wbl.test.RTTI;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Simple_love on 2015/9/24.
 */
public class LiteralPetCreator extends PetCreator{
        public static final List<Class<? extends Pet>> allTypes = Collections.unmodifiableList(
                Arrays.asList(Pet.class, Dog.class, Cat.class, Rodent.class, Mutt.class, Pug.class, Manx.class,
                        Cymric.class, Rat.class, Mouse.class)
        );
        private static final List<Class<? extends Pet>> types = allTypes.subList(allTypes.indexOf(Mutt.class),allTypes.size());
        public List<Class<? extends  Pet>> types(){
                return types;
        }
        public static void main(String[] args){
                System.out.println(types);
        }
}
