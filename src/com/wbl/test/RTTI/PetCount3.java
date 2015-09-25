package com.wbl.test.RTTI;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Simple_love on 2015/9/25.
 */
public class PetCount3 {
        static class PetCounter extends LinkedHashMap<Class<? extends Pet>,Integer>{
                public PetCounter(){
                        super(MapData(LiteralPetCreator.allTypes));
                }

                public void count(Pet pet){
                        for (Map.Entry<Class<? extends Pet>,Integer>pari:entrySet())
                                if (pari.getKey().isInstance(pet))
                                        put(pari.getKey(),pari.getValue() + 1);
                }

                public String toString(){
                        StringBuilder result = new StringBuilder("{");
                        for (Map.Entry<Class<? extends Pet>,Integer> pair: entrySet()){
                                result.append(pair.getKey().getSimpleName());
                                result.append("=");
                                result.append(pair.getValue());
                                result.append(",");
                        }
                        result.delete(result.length() - 1, result.length());
                        result.append("}");
                        return result.toString();
                }
        }
        static public Map<Class<? extends Pet>,Integer> MapData(List<Class<? extends  Pet>> allTypes){
                Map<Class<? extends Pet>,Integer> map = new LinkedHashMap<>();
                for (Class<? extends Pet> obj:allTypes){
                        map.put(obj,0);
                }
                return map;
        }

        public static void main(String[] args){
                PetCounter petCount = new PetCounter();
                for (Pet pet: Pets.createArray(20)){
                        System.out.print(pet.getClass().getSimpleName() + " ");
                        petCount.count(pet);
                }
                System.out.println();
                System.out.println(petCount);
        }
}
