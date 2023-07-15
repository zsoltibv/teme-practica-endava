package com.endava.sport;

public interface Sport {
    void play();
    default void beginGame(){
        System.out.println("Game is starting");
    }

    static void encourage(){
        System.out.println("yuhuuuu");
    }
}
