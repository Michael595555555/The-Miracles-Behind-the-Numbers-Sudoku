import java.util.ArrayList;
import java.util.HashSet;
import java.lang.Math;
import java.awt.Font;
import java.util.Random;
import java.util.*;
public class Mainpage {
    Board board;
    boolean draw;
    String difficulty;
    ball ballone;

    // int score;
    int prescore;
    public Mainpage(){
        // this.score = 0;
        this.drawpage();
        this.ball();
        this.detector();
        this.function();
        
        // this.board = new Board(0.9, difficulty, prescore);
    }

    public void ball(){
        this.ballone = new ball(0.1, 0.4, 0.01, 0.02, 0.05, 1);
        StdDraw.setPenColor(StdDraw.GREEN);
        ballone.draw();
        StdDraw.setPenColor(StdDraw.BLACK);
    }
    public void drawpage(){
        StdDraw.clear(StdDraw.ORANGE);
        StdDraw.setPenColor(StdDraw.BLACK);
        Font font = new Font("Arial", Font.BOLD, 60);
        StdDraw.setFont(font);
        StdDraw.text(0.75, 0.8, "Welcome to Sudoku!", 0);
        makebutton("Easy", 0.3, 0.63);
        makebutton("Medium", 0.6, 0.63);
        makebutton("Hard", 0.9, 0.63);
        makebutton("Devil", 1.2, 0.63);
        StdDraw.show();
        
    }
    public void makebutton(String a, double xcord, double ycord ){
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.filledRectangle(xcord, ycord, 0.1, 0.04);
        StdDraw.setPenColor(StdDraw.BLACK);
        Font font = new Font("Time New Roman", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.text(xcord, ycord, a, 0);
    }

    public void function(){
        game(detector());
    }

    public void game(String difficulty)
    {
        this.board=new Board(0.9, this.difficulty, this.prescore);
        board.boardGenerator();
        board.score(this.prescore);
        System.out.println(this.prescore);
        board.makeallbuttons(); 
        // board.pencils();
        // StdDraw.show();
        while(true){

            board.reset();
            board.update();
            if(StdDraw.isKeyPressed(83))
            {
                board.solver2();
            }
            StdDraw.show();
            // StdDraw.show();
            //First, make sure the button is pressed,.
            //Second, erase everything from the board
            // if(board.checkbuttonpressed(5))
            // {
            //     this.prescore = board.getscore(); 
            //     board = null;
            //     StdDraw.pause(125);
            //     game(difficulty, this.prescore);
            // }
            this.check();
            // if(board.checkbuttonpressed(4))
            // {
            //     board.solver2();
            // }
        }
    }



    public void check(){
        if(board.checkbuttonpressed(5) && board.checkgame())
        {
            this.prescore = this.board.getscore() + 1; 
            this.newgame();
        }
        else if(board.checkbuttonpressed(7)){
            this.newgame();
        }
    }

    public void newgame(){
        board = null;
        StdDraw.pause(120);
        game(difficulty);
    }

    public boolean detect(double x, double y){
        if(StdDraw.isMousePressed()){
            double xcord = StdDraw.mouseX();
            double ycord = StdDraw.mouseY();
            if(Math.abs(xcord-x) <= 0.1 && Math.abs(ycord-y) <= 0.04){
                return true;
            }
            return false;
        }
        return false;
    }

    public String detector(){
        while(true){
            if(detect(0.3, 0.63)){
                this.difficulty = "easy";
            }
            else if(detect(0.6, 0.63)){
                this.difficulty = "medium";
            }
            else if(detect(0.9, 0.63)){
                this.difficulty = "hard";
            }
            else if(detect(1.2, 0.63)){
                this.difficulty = "devil";
            }
            else{
                StdDraw.clear();
                this.drawpage();
                StdDraw.setPenColor(StdDraw.GREEN);
                ballone.move();
                StdDraw.pause(20);
                continue;
            }
            return(this.difficulty);
        }

    }
    


}
