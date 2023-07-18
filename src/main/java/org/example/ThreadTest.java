package org.example;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {
        // way 1: override run method
        Thread thread = new Thread(){
            @Override
            public void run() {
                System.out.println("I am thread 1");
            }
        };
        thread.start();
        System.out.println(thread.getName());
        System.out.println("main end");


        //way 2 Thread constructor get a runnable target
        Thread thread2 = new Thread(
                () -> System.out.println("I am a tread 2")
        );
        thread2.start();
        Thread thread3 = new Thread(new ImpRunnable());
        thread3.start();

        // way 3 future task, can get return value, use Callable
//        1, geta a callable object
//        2, pass callable obj to future task
//        3, pass task to Thread
        Callable<String> callable = () -> {
                System.out.println("I am callable task");
                return "Hello";
        };
        FutureTask<String> task = new FutureTask<>(callable);
        Thread thread4 = new Thread(task);
        thread4.start();
        //get thread4 return or exception
        try {
            System.out.println(task.get());
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
