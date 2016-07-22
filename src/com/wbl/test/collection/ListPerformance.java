package com.wbl.test.collection;

import java.util.*;

/**
 * Created by Simple_love on 2015/10/13.
 */
public class ListPerformance {
        static Random rand = new Random();
        static int reps = 1000;
        static List<Test<List<Integer>>> tests = new ArrayList<>();
        static List<Test<LinkedList<Integer>>> qTests = new LinkedList<>();
        static {
                tests.add(new Test<List<Integer>>("add") {
                        @Override
                        int test(List<Integer> container, TestParam tp) {
                                int loops = tp.loops;
                                int listsize = tp.size;
                                for (int i = 0; i < loops; i++){
                                        container.clear();
                                        for (int j = 0; j < listsize; j++)
                                                container.add(j);
                                }
                                return listsize * loops;
                        }
                });

                tests.add(new Test<List<Integer>>("get") {
                        @Override
                        int test(List<Integer> container, TestParam tp) {
                                int loops = tp.loops * reps;
                                int listsize = tp.size;
                                for (int i = 0; i < loops; i++){
                                        container.get(rand.nextInt(listsize));
                                }
                                return loops;
                        }
                });

                tests.add(new Test<List<Integer>>("set") {
                        @Override
                        int test(List<Integer> container, TestParam tp) {
                                int loops = tp.loops * reps;
                                int listsize = tp.size;
                                for (int i = 0; i < loops; i++){
                                        container.set(rand.nextInt(listsize), 47);
                                }
                                return  loops;
                        }
                });

                tests.add(new Test<List<Integer>>("iteradd") {
                        @Override
                        int test(List<Integer> container, TestParam tp) {
                               final int LOOPS = 10000;
                                int half = container.size() / 2;
                                ListIterator<Integer> it = container.listIterator(half);
                                for (int i = 0; i < LOOPS; i++){
                                        it.add(47);
                                }
                                return  LOOPS;
                        }
                });

                tests.add(new Test<List<Integer>>("insert") {
                        @Override
                        int test(List<Integer> container, TestParam tp) {
                                int loops = tp.loops * reps;
                                for (int i = 0; i < loops; i++){
                                        container.add(5,47);
                                }
                                return  loops;
                        }
                });

                qTests.add(new Test<LinkedList<Integer>>("addFirst") {
                        @Override
                        int test(LinkedList<Integer> container, TestParam tp) {
                                int loops = tp.loops;
                                int size = tp.size;
                                for (int i = 0; i < loops; i++){
                                        container.clear();
                                        for (int j = 0; j < size; j++)
                                                container.addFirst(47);
                                }
                                return loops * size;
                        }
                });

                qTests.add(new Test<LinkedList<Integer>>("addLast") {
                        @Override
                        int test(LinkedList<Integer> container, TestParam tp) {
                                int loops = tp.loops;
                                int size = tp.size;
                                for (int i = 0; i < loops; i++){
                                        container.clear();
                                        for (int j = 0; j < size; j++)
                                                container.addLast(47);
                                }
                                return loops * size;
                        }
                });
        }

        static class ListTester extends Tester<List<Integer>>{
                public ListTester(List<Integer> container,List<Test<List<Integer>>> tests){
                        super(container,tests);
                }
                public static void run(List<Integer> container,List<Test<List<Integer>>> tests){
                        new ListTester(container,tests).timedTest();
                }
        }

        public static void main(String[] args){
                Tester.defaultParams = TestParam.array(10,5000,100,5000,1000,5000,10000,200);
                ListTester.run(new ArrayList<Integer>(),tests);
                ListTester.run(new LinkedList<Integer>(),tests);
                ListTester.run(new Vector<Integer>(),tests);

                Tester<LinkedList<Integer>> qTest = new Tester<>(new LinkedList<Integer>(),qTests);
                qTest.setHeadline("Queue tests");
                qTest.timedTest();
        }
}
