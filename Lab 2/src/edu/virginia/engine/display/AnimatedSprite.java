package edu.virginia.engine.display;

import edu.virginia.engine.util.GameClock;
import edu.virginia.engine.display.Animation;
import java.awt.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;
import edu.virginia.engine.display.Animation;

public class AnimatedSprite extends Sprite {

    private ArrayList<Animation> animations = new ArrayList<Animation>();

    private Boolean playing;

    private String fileName;

    private ArrayList<Image> frames = new ArrayList<Image>();

    private int currentFrame;

    private int startFrame;

    private int endFrame;

    static final int DEFAULT_ANIMATION_SPEED = 1000;

    private double animationSpeed;

    private GameClock gameClock; //GameClock class??

    private ArrayList<String> filenames = new ArrayList<String>();

    //constructor that takes a ID, filename, and position
    public AnimatedSprite(String id, String filename, Point position) {
        super(id, filename, position);

        filenames.add("mariojump1.jpg");
        filenames.add("mariojump2.jpg");
        filenames.add("mariokick1.jpg");
        filenames.add("mariokick2.jpg");
        initializeFrames(filenames);

        //initialize a GameClock
        initGameClock();
    }

    public void initGameClock(){

        if(this.gameClock == null){
            this.gameClock = new GameClock();
        }
        return;
    }

    public void setAnimations(ArrayList<Animation> animations){ this.animations = animations; }

    public void setAnimationSpeed(double animationSpeed){

        if(this.animationSpeed - animationSpeed < 0){
            this.animationSpeed = 0;
        }

        this.animationSpeed = animationSpeed;
    }

    public double getAnimationSpeed(){ return this.animationSpeed;}

    public void setPlaying(Boolean bool){ this.playing = bool;}

    public Boolean getPlaying(){ return this.playing;}

    @Override
    public void draw(Graphics g){

       if (!playing){
           return;
       }

       if(this.gameClock.getElapsedTime() < this.animationSpeed) {
           //increment frames
           this.currentFrame = this.currentFrame + 1;
           super.draw(g);
       }
       this.gameClock.resetGameClock();
    }

    public void initializeFrames(ArrayList<String> frameFiles){
        int i;
        for(i = 0; i < frameFiles.size(); i++){
            this.frames.add(i, readImage(frameFiles.get(i)));
        }

        Animation jump = new Animation("jump", 0, 1);
        Animation kick = new Animation("kick", 2, 3);

        //initialize animations
        this.animations.add(0, jump);
        this.animations.add(1, kick);
    }

    public Animation getAnimation (String id){
        int i;
        for(i = 0; i < this.animations.size(); i++){
            if(id == this.animations.get(i).getId()){
                return this.animations.get(i);
            }
        }
        return null;
    }

    public void animate(int startFrame, int endFrame){
        this.startFrame = startFrame;
        this.endFrame = endFrame;
    }

    public void animate(Animation obj){
        this.startFrame = obj.getStartFrame();
        this.endFrame = obj.getEndFrame();
    }

    public void animate(String str){
        this.playing = true;
        Animation animation = new Animation();
        animation = getAnimation(str);
        this.startFrame = animation.getStartFrame();
        this.endFrame = animation.getEndFrame();
    }


    public void stopAnimation(int frameNumber){
        this.endFrame = frameNumber;
        setPlaying(false);
    }

    //default stop at first frame
    public void stopAnimation(){
        stopAnimation(this.startFrame);
        setPlaying(false);
    }
}

