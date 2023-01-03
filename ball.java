import java.util.ArrayList;
import java.util.HashSet;
import java.lang.Math;
import java.awt.Font;
import java.util.Random;
import java.util.*;
public class ball {
    double xpos;
    double ypos;
    double xvelocity;
    double yvelocity;
    double radius;
    int num;
    public ball(double xpos, double ypos, double xvelocity, double yvelocity, double radius, int number){
        this.xpos = xpos;
        this.ypos = ypos;
        this.xvelocity = xvelocity;
        this.yvelocity = yvelocity;
        this.radius = radius;
        this.num = number;
    }
    
   
    public void draw(){
        StdDraw.filledCircle(xpos, ypos, radius);
        String number = Integer.toString(this.num);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(this.xpos, this.ypos, number, 0);
        StdDraw.show();
    }

    public void move(){
        this.xpos += xvelocity;
        this.ypos += yvelocity;
        this.draw();
        if(xpos >= 1.5 - radius || xpos <= radius){
            this.xvelocity = 0 - xvelocity;
        }
        if(ypos >= 1 - radius || ypos <= radius){
            this.yvelocity = 0 - yvelocity;
        }
        // StdDraw.show();
    }
}
