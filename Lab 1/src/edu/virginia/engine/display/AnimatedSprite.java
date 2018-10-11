package edu.virginia.engine.display;

import edu.virginia.engine.util.GameClock;

import java.util.ArrayList;
import java.awt.Point;
import java.awt.Graphics;

public class AnimatedSprite extends Sprite {

    private ArrayList<Animation> animations;

    private Boolean playing;

    private String fileName;

    private ArrayList<String> frames;

    private int currentFrame;

    private int startFrame;

    private int endFrame;

    static final int DEFAULT_ANIMATION_SPEED = 1000;

    private double animationSpeed;

    private GameClock gameClock; //GameClock class??


    //constructor that takes a ID, filename, and position
    public AnimatedSprite(String id, String filename, Point position) {
        super(id, filename, position);

        this.startFrame = -1;

        //initialize a GameClock
        initGameClock();
    }

    public void initGameClock(){

        if(this.gameClock == null){
            this.gameClock = new GameClock();
        }
        return;
    }


    //check this function
    public void setAnimations(ArrayList<Animation> animations){
        this.animations = animations;
    }

    public void setAnimationSpeed(double animationSpeed){
       this.animationSpeed = animationSpeed;
    }

    @Override
    public void draw(Graphics g){

       if (this.startFrame == -1) {
           return;
       }

       for(this.currentFrame = this.startFrame; this.currentFrame <= this.endFrame; this.currentFrame++){
            //while time not up
           while(this.gameClock.getElapsedTime() < this.animationSpeed) {
               //set curr frame
               super.draw(g);
           }
           this.gameClock.resetGameClock();
       }
    }
}

