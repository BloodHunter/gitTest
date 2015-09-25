package com.wbl.test.RTTI;

import java.util.ArrayList;

/**
 * Created by Simple_love on 2015/9/24.
 */
public class Pets {
        public static final PetCreator creator = new LiteralPetCreator();
        public static Pet randomPet(){
                return creator.randomPet();
        }
        public static Pet[] createArray(int size){
                return creator.createArray(size);
        }
        public static ArrayList<Pet> arrayList(int size){
                return creator.arrayList(size);
        }
        public static void main(String[] args){
                PetCount.countPets(Pets.creator);
        }
}
class test extends LiteralPetCreator{
        public test(){
                System.out.println("dd");
        }
}
