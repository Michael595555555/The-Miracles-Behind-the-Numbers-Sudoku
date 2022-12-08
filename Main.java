import java.applet.*;
import java.awt.*;
public class Main extends Applet{
    public static void main(String args[]){
        StdDraw.enableDoubleBuffering();
        int xSize = 1200;
        int ySize = xSize * 2/3;
        StdDraw.setCanvasSize(xSize, ySize);
        StdDraw.setXscale(0, 1.5);
        StdDraw.clear(StdDraw.BOOK_BLUE);
        game();
    }
    public static void game()
    {
        Board board = new Board(0.9);
        board.boardGenerator();
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
            //First, make sure the button is pressed,.
            //Second, erase everything from the board
            if(board.checkbuttonpressed(7))
            {
                board = null;
                game();
            }
            // if(board.checkbuttonpressed(4))
            // {
            //     board.solver2();
            // }
        }
    }
}
