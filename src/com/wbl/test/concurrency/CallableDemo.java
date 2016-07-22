package com.wbl.test.concurrency;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Created by Simple_love on 2015/10/20.
 */
class TaskWithResult implements Callable<String>{
        private int id;
        public TaskWithResult(int id){
                this.id = id;
        }
        public String call(){
                return "result of TaskWithResult " + id;
        }
}

class Fab implements Callable<Integer>{
        private int n;
        public Fab(int n){
                this.n = n;
        }
        private int calcute(int n){
                if (n == 1 || n == 2)
                        return n;
                else
                        return calcute(n-1) + calcute(n-2);
        }
        public Integer call(){
                return calcute(n);
        }
}
public class CallableDemo {
        public static void main(String[] args){
                ExecutorService exec = Executors.newCachedThreadPool();
                ArrayList<Future<String>> results = new ArrayList<>();
                for (int i = 0; i < 10; i++){
                        results.add(exec.submit(new TaskWithResult(i)));
                }

                for (Future<String> fs : results){
                        try {
                                System.out.println(fs.get());
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        } catch (ExecutionException e) {
                                e.printStackTrace();
                        }finally {
                                exec.shutdown();
                        }
                }

                ExecutorService executorService = Executors.newCachedThreadPool();
                ArrayList<Future<Integer>> result = new ArrayList<>();
                for (int i = 1; i < 10; i ++){
                        result.add(executorService.submit(new Fab(i)));
                }
                int sum = 0;
                for (Future<Integer> fs: result){
                        try {
                                System.out.println(fs.get());
                                sum += fs.get();
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        } catch (ExecutionException e) {
                                e.printStackTrace();
                        }
                }
                System.out.println(sum);
        }
}
