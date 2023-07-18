package org.future;

import java.util.concurrent.CompletableFuture;

public class CompletebleFutureTest {
    public static void main(String[] args) {
//        supplyAsync();
//        thenCompose();
//        thenCombine();
//        thenApply(); //thenApplyAsync
//        applyToEither(); //exceptionally


    }

    private static void applyToEither() {
        SmallTool.printTimeAndThread("张三走出餐厅 来到公交站");
        SmallTool.printTimeAndThread( "等待 700路 或者 800路 公交到来");

        CompletableFuture<String> bus = CompletableFuture.supplyAsync(() ->
        {
            SmallTool.printTimeAndThread("700路公交正在赶来");
            SmallTool.sleepMills(500);
            return "700 arived";
        }).applyToEither(CompletableFuture.supplyAsync(() ->
        {
            SmallTool.printTimeAndThread("800路公交正在赶来");
            SmallTool.sleepMills(200);
            return "800 arived";
        }),firstComeBus -> {
//            var a = 0;
            return firstComeBus;
        }).exceptionally(e -> {
            System.out.println(e);
            return null;
        });

        SmallTool.printTimeAndThread(String.format("%s,小白坐车回家", bus.join()));
    }

    private static void thenApply() {
        SmallTool.printTimeAndThread("小白进入餐厅");
        SmallTool.printTimeAndThread( "小白点了 番炒蛋 + 一碗米饭");

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() ->
        {
            SmallTool.printTimeAndThread("服务员收款 500");
            SmallTool.sleepMills(200);
            return "500";
        }).thenApply(money -> {
            SmallTool.printTimeAndThread(String.format("服务员开发票 面额 %s 元", money));
            SmallTool.sleepMills(300);
            return String.format("%s 元发票", money);
        });

        SmallTool.printTimeAndThread("小白在打王者");
        SmallTool.printTimeAndThread(String.format("%s,小白开吃", cf1.join()));
    }

    private static void thenCombine() {
        SmallTool.printTimeAndThread("小白进入餐厅");
        SmallTool.printTimeAndThread( "小白点了 番炒蛋 + 一碗米饭");

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() ->
        {
            SmallTool.printTimeAndThread("厨师炒菜");
            SmallTool.sleepMills(200);
            return "番茄炒蛋";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("服务员蒸饭");
            SmallTool.sleepMills(300);
            return "米饭 做好了";
        }), (dish, rice) -> {
            SmallTool.printTimeAndThread("服务员打饭");
            SmallTool.sleepMills(100);
            return String.format("%s + %s 好了", dish, rice);
        });

        SmallTool.printTimeAndThread("小白在打王者");
        SmallTool.printTimeAndThread(String.format("%s,小白开吃", cf1.join()));
    }

    private static void thenCompose() {
        SmallTool.printTimeAndThread("小白进入餐厅");
        SmallTool.printTimeAndThread( "小白点了 番炒蛋 + 一碗米饭");

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() ->
        {
            SmallTool.printTimeAndThread("厨师炒菜");
            SmallTool.sleepMills(200);
            return "番茄炒蛋";
        }).thenCompose((dish) -> {
            System.out.println();
            return CompletableFuture.supplyAsync(() -> {
                SmallTool.printTimeAndThread("厨师打饭");
                SmallTool.sleepMills(100);
                return dish +"米饭 做好了";
            });
        });

        SmallTool.printTimeAndThread("小白在打王者");
        SmallTool.printTimeAndThread(String.format("%s,小白开吃", cf1.join()));
    }

    private static void supplyAsync() {
        SmallTool.printTimeAndThread("小白进入餐厅");
        SmallTool.printTimeAndThread( "小白点了 番炒蛋 + 一碗米饭");

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() ->
        {
            SmallTool.printTimeAndThread("厨师炒菜");
            SmallTool.sleepMills(200);
            SmallTool.printTimeAndThread("厨师打饭");
            SmallTool.sleepMills(100);
            return "番茄炒蛋 +米饭 做好了";
        });

        SmallTool.printTimeAndThread("小白在打王者");
        SmallTool.printTimeAndThread(String.format("%s,小白开吃", cf1.join()));
    }
}
