package com.endava.sport;

public class Football implements Sport {
    @Override
    public void play() {
        System.out.println("Kicking foot ball");
    }

    @Override
    public void beginGame(){
        System.out.println("Football game is starting");
    }

}
