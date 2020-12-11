package com.gc.network;

import com.gc.utils.ConstantUtils;
import com.google.common.util.concurrent.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author join wick
 * @version 1.0.0
 * @className CallBackServiceDemo.java
 * @description call back service
 * @createDate 2020/12/11 21:50
 * @since 1.0.0
 */
public class CallBackServiceDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(CallBackServiceDemo.class);
    public static String getCurrentThreadName(){
        return Thread.currentThread().getName();
    }

    public static void main(String[] args) {
        DrinkTeaJob drinkTeaJob = new DrinkTeaJob();

        Thread mainThread = new Thread(drinkTeaJob);
        mainThread.setName("主线程");
        mainThread.start();

        Callable<Boolean> hotWaterJob = new HotWaterJob();
        Callable<Boolean> washJob = new WashJob();

        ExecutorService executorService = Executors.newFixedThreadPool(ConstantUtils.DEFAULT_THREAD_COUNT);
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(executorService);

        ListenableFuture<Boolean> hotWaterFuture = listeningExecutorService.submit(hotWaterJob);
        Futures.addCallback(hotWaterFuture, new FutureCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                if (result){
                    drinkTeaJob.hotWaterOk = true;
                }
            }
            @Override
            public void onFailure(Throwable t) {
                LOGGER.error("烧水失败,无法喝茶,原因<{}>", t.toString());
            }
        }, listeningExecutorService);

        ListenableFuture<Boolean> washFuture = listeningExecutorService.submit(washJob);
        Futures.addCallback(washFuture, new FutureCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                if (result){
                    drinkTeaJob.washOk = true;
                }
            }
            @Override
            public void onFailure(Throwable t) {
                LOGGER.error("清洗失败,无法喝茶,原因<{}>", t.toString());
            }
        }, listeningExecutorService);
    }

    static class DrinkTeaJob implements Runnable{
        boolean hotWaterOk = false;
        boolean washOk = false;
        @Override
        public void run() {
            boolean drinkOk = false;
            while (!drinkOk){
                try {
                    Thread.sleep(ConstantUtils.DEFAULT_SELECTOR_LOOP_TIME / 10);
                    LOGGER.debug("读书去了");
                } catch (InterruptedException e) {
                    LOGGER.error("<{}> 失败", getCurrentThreadName());
                }
                drinkOk = drinkTea(hotWaterOk, washOk);
            }
        }

        private boolean drinkTea(boolean hotWaterOk, boolean washOk){
            if (hotWaterOk && washOk){
                LOGGER.debug("泡茶喝");
                return true;
            }
            if (!hotWaterOk){
                LOGGER.error("水未烧开");
            }
            else {
                LOGGER.debug("清洗未完成");
            }
            return false;
        }
    }

    static class HotWaterJob implements Callable<Boolean> {
        @Override
        public Boolean call() {
            try {
                LOGGER.debug("洗好水壶...");
                LOGGER.debug("灌上凉水...");
                LOGGER.debug("放在火上...");
                Thread.sleep(ConstantUtils.DEFAULT_SELECTOR_LOOP_TIME);
                LOGGER.debug("水烧开了...");
            }
            catch (InterruptedException e){
                LOGGER.error("烧水工作异常...");
                return false;
            }
            LOGGER.debug("烧水工作结束...");
            return true;
        }
    }

    static class WashJob implements Callable<Boolean>{
        @Override
        public Boolean call() {
            try {
                LOGGER.debug("清洗茶壶...");
                LOGGER.debug("清洗茶杯...");
                LOGGER.debug("获取茶叶...");
                Thread.sleep(ConstantUtils.DEFAULT_SELECTOR_LOOP_TIME);
                LOGGER.debug("清洗完了...");
            } catch (InterruptedException e) {
                LOGGER.error("清洗工作异常...");
                return false;
            }
            LOGGER.debug("清洗工作结束...");
            return true;
        }
    }


}
