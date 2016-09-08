package com.malsolo.java8.lambdas;

import java.util.logging.Logger;

public class FunnyThreads {

    private final static Logger LOGGER = Logger.getLogger(FunnyThreads.class.getName());

    public static void main(String[] args) {
        oldSpice();
        newKidsOnTheBlock();
    }

    public static void oldSpice() {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                // System.out.println("In another thread");
                LOGGER.info("In another thread (OLD)");
            }
        });
        t.start();

        // System.out.println("In main");
        LOGGER.info("In main (OLD)");
    }

    public static void newKidsOnTheBlock() {
        Thread t = new Thread(() -> LOGGER.info("In another thread (NEW)"));
        t.start();

        // System.out.println("In main");
        LOGGER.info("In main (NEW)");

    }
}
