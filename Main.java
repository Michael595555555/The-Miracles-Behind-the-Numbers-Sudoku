import java.applet.*;
import java.awt.*;
public class Main extends Applet{
    public static void main(String args[]){
        StdDraw.enableDoubleBuffering();
        int xSize = 1200;
        int ySize = xSize * 2/3;
        StdDraw.setCanvasSize(xSize, ySize);
        StdDraw.setXscale(0, 1.5);
        StdDraw.clear(StdDraw.ORANGE);
        StdDraw.setPenColor(StdDraw.BLACK);
        Font font = new Font("Arial", Font.BOLD, 60);
        // StdDraw.setFont(font);
        // StdDraw.text(0.7, 0.8, "Welcome to Sudoku!", 0);
        // makebutton("Easy", 0.4, 0.5);
        // makebutton("Medium", 0.7, 0.5);
        // makebutton("Hard", 1, 0.5);
        // game(detector());
        Mainpage mainpage = new Mainpage();
            // StdDraw.pause(100);
    }
    // public static void game(String difficulty)
    // {
    //     Board board = new Board(0.9, difficulty);
    //     board.boardGenerator();
    //     board.makeallbuttons(); 
    //     // board.pencils();
    //     // StdDraw.show();
    //     while(true){

    //         board.reset();
    //         board.update();
    //         if(StdDraw.isKeyPressed(83))
    //         {
    //             board.solver2();
    //         }
    //         StdDraw.show();
    //         //First, make sure the button is pressed,.
    //         //Second, erase everything from the board
    //         if(board.checkbuttonpressed(7))
    //         {
    //             board = null;
    //             StdDraw.pause(125);
    //             game(difficulty);
    //         }
    //         // if(board.checkbuttonpressed(4))
    //         // {
    //         //     board.solver2();
    //         // }
    //     }
    // }
    // public static void makebutton(String a, double xcord, double ycord ){
    //     StdDraw.setPenColor(StdDraw.GRAY);
    //     StdDraw.filledRectangle(xcord, ycord, 0.1, 0.04);
    //     StdDraw.setPenColor(StdDraw.BLACK);
    //     Font font = new Font("Time New Roman", Font.BOLD, 30);
    //     StdDraw.setFont(font);
    //     StdDraw.text(xcord, ycord, a, 0);
    //     StdDraw.show();
    // }

    // public static boolean detect(double x, double y){
    //     if(StdDraw.isMousePressed()){
    //         double xcord = StdDraw.mouseX();
    //         double ycord = StdDraw.mouseY();
    //         if(Math.abs(xcord-x) <= 0.1 && Math.abs(ycord-y) <= 0.04){
    //             return true;
    //         }
    //         return false;
    //     }
    //     return false;
    // }

    // public static String detector(){
    //     while(true){
    //         if(detect(0.4, 0.5))
    //             return "easy";
    //         else if(detect(0.7, 0.5))
    //             return "medium";
    //         else if(detect(1, 0.5))
    //             return "hard";
    //     }

    // }
}
