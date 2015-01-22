package xyz.maxton;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * ShapeButton class
 * After a few rewrites, I settled on manually drawing the shape, treating the GamePanel like a canvas.
 * 
 * @author Max
 */
public class ShapeButton {
    public static final int RED = 0;
    public static final int GREEN = 1;
    public static final int BLUE = 2;
    public static final int PURPLE = 3;
    public static final int x2 = 4;
    public static final int d2 = 5;
    
    private GamePanel panel;
    private int x;
    private int y;
    private int type;
    private Image img;
    
    public ShapeButton(GamePanel p){
        this.panel = p;
        this.x = 0;
        this.y = 0;
        this.type = 0;
        this.img = null;
    }
    
    public ShapeButton setType(int type){
        try {
            this.type = type;
            if(this.type > 5) this.type = 5;
            if(this.type < 0) this.type = 0;
            switch(type){
                case RED:
                    this.img = ImageIO.read(getClass().getResource("/net/maxton/shapeImgs/redSquare.png"));
                    break;
                case GREEN:
                    this.img = ImageIO.read(getClass().getResource("/net/maxton/shapeImgs/greenSquare.png"));
                    break;
                case BLUE:
                    this.img = ImageIO.read(getClass().getResource("/net/maxton/shapeImgs/blueSquare.png"));
                    break;
                case PURPLE:
                    this.img = ImageIO.read(getClass().getResource("/net/maxton/shapeImgs/purpleSquare.png"));
                    break;
                case x2:
                    this.img = ImageIO.read(getClass().getResource("/net/maxton/shapeImgs/x2Square.png"));
                    break;
                case d2:
                    this.img = ImageIO.read(getClass().getResource("/net/maxton/shapeImgs/d2Square.png"));
            }
        } catch (IOException ex) { }
        return this;
    }
    
    /**
     * Sets the current x and y to a random position from (20,20) to (260,420)
     * 
     * @return reference to self (useful for adding anonymous objects into a List and randomizing thir position)
     */
    public ShapeButton randomizePosition(){
        if((int)(Math.random() * 100) == 33){
            this.setType(4);
        }
        this.x = (int)(Math.random() * 240) + 20;
        this.y = (int)(Math.random() * 400) + 20;
        return this;
    }
    
    
    /**
     * Check if mouse click hit the shape.
     * Also adds to the score if the hit is within bounds.
     * 
     * @param x x position of the mouse
     * @param y y position of the mouse
     * @return true if click is within the shape's bounds.
     */
    public boolean hitTest(int x, int y){
        if(this.x <= x && this.x + 48 >= x && this.y <= y && this.y + 48 >= y){
            panel.addToScore(getScoreModifier());
        }
        return (this.x <= x && this.x + 48 >= x && this.y <= y && this.y + 48 >= y);
    }
    
    
    /**
     * Checks the type of shape and current score to get a value to be added to the score.
     * 
     * @return point value to add to the score.
     */
    private long getScoreModifier(){
        long ret = 0;
        switch(this.type){
            case RED:
                ret = -100;
                break;
            case BLUE:
                ret = 10;
                break;
            case x2:
                ret =  panel.getScore();
                this.setType(0);
                break;
            case d2:
                ret = (-1L)*panel.getScore()/2;
                this.setType(1);
                break;
            case PURPLE:
                ret = 50;
                break;
            case GREEN:
                ret = 20;
                break;
        }
        return ret;
    }
    
    public void draw(Graphics g){
        g.drawImage(img, x, y, panel);
    }

}
