package com.wbl.test.Enum;

import java.util.EnumMap;
import static com.wbl.test.Enum.Outcome.*;
/**
 * Created by Simple_love on 2015/10/20.
 */
public enum RoShamBo3 implements Competitor<RoShamBo3> {
        PAPER,SCISSORS,ROCK;
        static EnumMap<RoShamBo3,EnumMap<RoShamBo3,Outcome>>
                table = new EnumMap<RoShamBo3, EnumMap<RoShamBo3, Outcome>>(RoShamBo3.class);
        static {
                for (RoShamBo3 it: RoShamBo3.values()){
                        table.put(it,new EnumMap<RoShamBo3, Outcome>(RoShamBo3.class));
                }
                initRow(PAPER,DRAW,LOSE,WIN);
                initRow(SCISSORS,WIN,DRAW,LOSE);
                initRow(ROCK,LOSE,WIN,DRAW);
        }
        static void initRow(RoShamBo3 it,Outcome vPaper,Outcome vScissors,Outcome vRock){
                EnumMap<RoShamBo3,Outcome> row = table.get(it);
                row.put(PAPER,vPaper);
                row.put(SCISSORS,vScissors);
                row.put(ROCK,vRock);
        }
        public Outcome compete(RoShamBo3 it){
                return table.get(this).get(it);
        }

        public static void main(String[] args){
                RoShamBo.play(RoShamBo3.class,20);
        }
}
