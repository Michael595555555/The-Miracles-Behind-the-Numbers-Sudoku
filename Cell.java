import java.util.Random;
import java.awt.Font;
import java.util.ArrayList;
public class Cell {

    int state;
    double offset;
    int ranstate;
    double x, y;
    double size;
    boolean isMutable;
    ArrayList<Integer> pencilmarks;
    boolean showmark;


    public Cell(double x, double y, double size){
        this.x = x;
        this.y = y;
        this.size = size;
        this.isMutable = true;
        this.pencilmarks = new ArrayList<Integer> ();
        this.showmark = false;
    }

    public void reversemark(){
        this.showmark = !this.showmark;
    
    }

    public void drawCell(){
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledSquare(this.x, this.y, this.size);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.square(this.x, this.y, this.size);
    }

    public ArrayList<Integer> possiblenum(){
        return this.pencilmarks;
    }

    public void setWin(boolean m){
        this.isMutable = m;
    }
    public void drawIncorrectCell(){
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledSquare(this.x, this.y, this.size);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.square(this.x, this.y, this.size);
    }

    public double getXpos(){
        return this.x;
    }

    public double getYpos(){
        return this.y;
    }

    public int getState(){
        return this.state;
    }

    public void setState(int m){
        this.state = m;
    }

    public int getLenMarks(){
        return this.pencilmarks.size();
    }

    public int getmark(){
        return pencilmarks.get(0);
        // if(pencilmarks.size() == 1){
        //     return pencilmarks.get(0);
        // }
        // return 0;
    }

    public void generateMark(int num){
        addMarks(num);
        this.offset = this.size/2;
        int a;
        int b;
        if(num%3 == 0)
          a = 3;
        else
          a = num % 3;
    
        if(num%3 == 0)
          b = 4 - num / 3;
        else
          b = 3 - num / 3;
    
        a -= 2;
        b -= 2;

        if(this.showmark){
            Font font = new Font("Arial", Font.BOLD, 9);
            StdDraw.setFont(font);
            String numberstate = Integer.toString(num);
            StdDraw.text(x + offset * a, y + offset * b, numberstate, 0);
        }

    }

    public void addMarks(int n){
        this.pencilmarks.add(n);
    }

    public void removeMarks(){
        this.pencilmarks.clear();
    }
    // public void pencilMarks()
    // {
    //     for(int i = 1; i < 10; i++){

    //         generateMark(i);
    //     }
    // }
    

    public void showNumber(){
        if(this.isMutable == true){
            if(this.state != 0){
                Font font = new Font("Arial", Font.BOLD, 40);
                StdDraw.setFont(font);
                String numberstate = Integer.toString(this.state);
                StdDraw.text(this.x, this.y-0.01, numberstate, 0);
                //StdDraw.show();
            }
            else if(this.state == 0){
                drawCell();
               // StdDraw.show();

            }
        }
    }

    public boolean getMute(){
        return this.isMutable;
    }
}

    
