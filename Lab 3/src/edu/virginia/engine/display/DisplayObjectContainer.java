package edu.virginia.engine.display;

import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class DisplayObjectContainer extends DisplayObject{

    private ArrayList<DisplayObject> children;

    public DisplayObjectContainer(String id) { super(id);}

    public DisplayObjectContainer(String id, String imageFileName, Point position){
        super(id, imageFileName, position);
    };

    public DisplayObjectContainer(String id, String imageFileName, Point position, ArrayList<DisplayObject> list) {
        super(id, imageFileName, position);
        this.children = list;
    }

    public void addChild(DisplayObject child){
        this.children.add(child);
        child.setParent(this);
    }

    public void addChildatIndex(int index, DisplayObject child){
        this.children.add(index, child);
        child.setParent(this);
    }

    public void removeChildren(String id){
        for(int i = 0; i < this.children.size() - 1; i++){
            if(children.get(i).getId() == id);
                children.remove(i);
        }
    }

    public void removeByIndex(int i){
        children.remove(i);
    }

    public void removeAll(){
        int number = this.children.size();
        for(int i = 0; i < number; i++){
            children.remove(0);
        }
    }

    public Boolean contains(DisplayObject child){
        for(int i = 0; i < this.children.size(); i++){
            if (children.get(i) == child);
                return true;
        }
        return false;
    }

    public ArrayList<DisplayObject> getArrayList(){
        return this.children;
    }

    @Override
    public void draw(Graphics g){
        super.draw(g);
        Graphics2D g2d = (Graphics2D) g;
        applyTransformations(g2d);
        for(int i = 0; i < children.size(); i++){
            children.get(i).draw(g);
        }
        reverseTransformations(g2d);
    }
}
