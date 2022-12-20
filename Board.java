import java.util.ArrayList;
import java.util.HashSet;
import java.lang.Math;
import java.awt.Font;
import java.util.Random;
import java.util.*;
public class Board {

    Cell[][] cellArr;
    Cell currentcell;
    double size;
    int currentX;
    int currentY;
    boolean state;
    double sidelength;
    int currentstate;
    ArrayList<Integer> currentarr;
    int n;
    ArrayList<int[]> pastmoves;
    boolean solvenow;
    Set <Cell[][]> workedsol;

    public Board(double size){
        this.drawBoard(size);
        this.pastmoves = new ArrayList<int[]>();
        this.n=0;
        this.solvenow = true;
        this.workedsol = new HashSet<Cell[][]>();
    }

    public void togglemarks(){
        for(Cell[] arr: this.cellArr){
            for(Cell i: arr){
                i.reversemark();
            }
        }
    }

    public void undo(boolean show){
        int[] arr = pastmoves.get(pastmoves.size()-1);
        cellArr[arr[0]][arr[1]].setState(arr[2]);
        cellArr[arr[0]][arr[1]].drawCell();
        cellArr[arr[0]][arr[1]].showNumber();
        if(show){
            StdDraw.show();
        }
        pastmoves.remove(pastmoves.size()-1);
    }

    public void drawBoard(double size){
        this.size = size;
        this.sidelength = size / 18;
        double x, y;
        this.cellArr = new Cell[9][9];
        for(int j = 0; j < 9; j++){
            for(int i = 0; i < 9; i++){
                x = 0.1 + this.sidelength * 2 * i;
                y = 0.1 + this.sidelength * 2 * j;
                this.cellArr[i][j] = new Cell(x, y, sidelength);
                this.cellArr[i][j].drawCell();
                //this.cellArr[i][j].showNumber();
                //StdDraw.pause(1000);
            }
        }
    }
    
   public void randomtenintegers(){
       Random rand = new Random();
       for(int i = 0; i <= 9; i++){
            int a = rand.nextInt(9);
            int b = rand.nextInt(9);
            int c = rand.nextInt(9)+1;
            if(Checker(a, b, c)){
                if(cellArr[a][b].getState() == 0)
                    cellArr[a][b].setState(c);
                    cellArr[a][b].showNumber(); 
                cellArr[a][b].setWin(false);
            }
            else{
                cellArr[a][b].drawCell();
                //cellArr[a][b].showNumber();
            }
            //continue;
           }
       }

       public void reset(){
        for(int i = 0; i <= 8; i++){
            for(int j = 0; j <= 8; j++){
                if(cellArr[i][j].getState() == 0)
                    cellArr[i][j].drawCell(); // remove all pencil marks
                cellArr[i][j].removeMarks(); //remove all numbers from the arraylist
            }
        }
    }


 

    public void updateCell(){ //player's function
            pencils();
            double m = StdDraw.mouseX();
            double n = StdDraw.mouseY();
            double centerx = cellArr[4][4].getXpos();
            double centery= cellArr[4][4].getYpos();
            double halflength = size / 2;
            if((Math.abs(m - centerx) >= halflength || Math.abs(n - centery) >= halflength) && StdDraw.isMousePressed() ){
            this.state = false;
            }
            for(int j = 0; j < 9; j++){
                for(int i = 0; i < 9; i++){
                    if(Math.abs(m - cellArr[i][j].getXpos()) <= this.sidelength && Math.abs(n - cellArr[i][j].getYpos()) <= this.sidelength && StdDraw.isMousePressed()){
                        this.state = true;
                        this.currentX = i;
                        this.currentY = j;
                    }
        }
    }
            if(!state){
                return;
            }
            for(int i = 48; i <= 57; i++){
                if(cellArr[currentX][currentY].getMute())
                {
                    if(StdDraw.isKeyPressed(i)){
                        if(Checker(this.currentX, this.currentY, i-48)){
                            int[] arr = {currentX, currentY, cellArr[this.currentX][this.currentY].getState()};
                            this.pastmoves.add(arr);//add pastmoves
                            cellArr[this.currentX][this.currentY].setState(i - 48);
                            cellArr[this.currentX][this.currentY].drawCell();
                            cellArr[this.currentX][this.currentY].showNumber();
                            StdDraw.show();
                            StdDraw.pause(300);
                            
                        }
                        else{
                            cellArr[this.currentX][this.currentY].setState(i - 48);
                            cellArr[this.currentX][this.currentY].drawIncorrectCell();
                            cellArr[this.currentX][this.currentY].showNumber();
                            StdDraw.show();
                            StdDraw.pause(300);
                            cellArr[this.currentX][this.currentY].drawCell();
                            cellArr[this.currentX][this.currentY].setState(0);
                        }
                    }    
                }
            }
            StdDraw.show();
            }
        
    
    public void pencils(){
        for(int xvalue = 0; xvalue < 9; xvalue++){
            for(int yvalue = 0; yvalue < 9; yvalue++){
                for(int i = 1; i < 10; i++)
                {
                    if(Checker(xvalue, yvalue, i) && cellArr[xvalue][yvalue].getState() == 0)
                        cellArr[xvalue][yvalue].generateMark(i);
                }
            }
        }
    }

    ////////////////Checker//////////////////////////////

    public boolean testRow(int x, int y, int num){
        for(int i = 0; i < 9; i++){
            if(x == i)
                continue;
            if(cellArr[i][y].getState() == num ){
                return false;
            }
        }
        return true;
    }

    public boolean testColumn(int x, int y, int num){
        for(int i = 0; i < 9; i++){
            if(y == i)
                continue;
            if(cellArr[x][i].getState() == num){
                return false;
            }
        }
        return true;
    }

    public boolean testBox(int x, int y, int num){
        int boxX = x - x%3;
        int boxY = y - y%3;
        for(int j = boxY; j <= boxY+2; j++){
            for(int i = boxX; i <= boxX+2; i++){
                if(i == x && j == y)
                    continue;
               if(this.cellArr[i][j].getState() == num){
                   return false;
               }
            }
        }
        return true;
    }

    public boolean Checker(int x, int y, int num){
        if(testBox(x, y, num) && testColumn(x, y, num) && testRow(x, y, num)){
            return true;
        }
        else if(cellArr[x][y].getState() == 0)
            return false;
        return false;
    }

    /////////Checker/////////////////////////////////////////////////

   
    public boolean solver(){ //AI solver
        for(int j = 0; j < 9; j++)
        {
            for(int i = 0; i < 9; i++)
            {
                if(this.cellArr[i][j].getState() == 0)
                {
                    for(int x = 1; x <= 9; x++)
                    {
                        if(this.Checker(i, j, x))
                        {
                            this.n++;
                            //this.cellArr[i][j].drawCell();
                            this.cellArr[i][j].setState(x);
                            // this.cellArr[i][j].showNumber();
                            if(this.solver())
                            {
                                return true;
                            }
                            else{
                                this.cellArr[i][j].setState(0);
                            }
                        }
                    }
                    return false;
                }
            }    
        }
        StdDraw.show();
        this.draw();
        // this.pencils();
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                cellArr[i][j].setWin(false);
            }
        }
        return true;

    }


    public boolean solver3(){
        for(int j = 0; j < 9; j++)
        {
            for(int i = 0; i < 9; i++)
            {
                if(this.cellArr[i][j].getState() == 0)
                {
                    for(int x = 1; x <= 9; x++)
                    {
                        if(this.Checker(i, j, x))
                        {
                            this.n++;
                            this.cellArr[i][j].drawCell();
                            this.cellArr[i][j].setState(x);
                            this.cellArr[i][j].showNumber();
                            StdDraw.show();
                            if(this.solver3())
                            {
                                return true;
                            }
                            else{
                                this.cellArr[i][j].setState(0);
                            }
                        }
                    }
                    return false;
                }
            }    
        }
        StdDraw.show();
        this.draw();
        return true;

    }
    public boolean tester(){
        for(int i = 0; i < 9; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                if(!Checker(i, j, cellArr[i][j].getState())){//checking each cell
                    return false;
                }
            }
        }
        return true;
    }

    public void showtest(){
        double m = StdDraw.mouseX();
        double n = StdDraw.mouseY();
        double x = cellArr[8][8].getXpos();
        double y = cellArr[8][8].getYpos();
        double i = cellArr[8][4].getXpos();
        double j = cellArr[8][4].getYpos();
        if(Math.abs(m - i - 0.3) <= 0.1 && Math.abs(n - j) <= 0.04){
            if(StdDraw.isMousePressed())
            {
                if(this.tester()){
                    this.drawblue();
                    StdDraw.setPenColor(StdDraw.GREEN);
                    Font font = new Font("Time New Roman", Font.BOLD, 40);
                    StdDraw.setFont(font);
                    String s = "Great job!";
                    StdDraw.text(x + 0.3, y, s, 0);
                    this.endgame();
                }
                else{
                    this.drawblue();
                    StdDraw.setPenColor(StdDraw.RED);
                    Font font = new Font("Time New Roman", Font.BOLD, 40);
                    StdDraw.setFont(font);
                    String k = "Keep trying!";
                    StdDraw.text(x + 0.3, y, k, 0);
                    StdDraw.pause(500);
                }
            }
        }
    }
    

    public void endgame(){
        for(Cell[] arr : cellArr)
        {
            for(Cell a : arr)
            {
                a.setWin(false);
            }
        }
    }
    public void drawblue(){
        double x = cellArr[8][8].getXpos();
        double y = cellArr[8][8].getYpos();
        StdDraw.setPenColor(StdDraw.BOOK_BLUE);
        StdDraw.filledRectangle(x + 0.3, y, 0.16, 0.04);

    }

    public void solver2()//algorithm: First 
    {
        System.out.println(solvenow);
        if(this.solvenow){
            boolean k = true;
            while(k == true)
            {
                k = false;
                for(int j = 0; j < 9; j++)
                {
                    for(int i = 0; i < 9; i++)
                    {
                        if(cellArr[i][j].getLenMarks() == 1 && cellArr[i][j].getState() == 0){ 
                            //System.out.println("yes!");//check the size of the arraylist & state = 0
                            StdDraw.pause(20);
                            cellArr[i][j].setState(cellArr[i][j].getmark());
                            cellArr[i][j].drawCell();
                            cellArr[i][j].showNumber();
                            k = true;
                            this.reset();
                            this.pencils();
                        }
                        else if (cellArr[i][j].getLenMarks() > 1){
                            //System.out.println("no!");
                            if(pencilMarkIsUnique(i, j)!=0){
                                System.out.println("wow");
                                StdDraw.pause(20);
                                cellArr[i][j].setState(this.pencilMarkIsUnique(i, j));
                                cellArr[i][j].drawCell();
                                cellArr[i][j].showNumber();
                                k = true;
                                this.reset();
                                this.pencils();
                            }
                        }
                        StdDraw.show();
                        //Do the exacrt same steps you did above
                    }
                }
            }
            StdDraw.pause(20);
            System.out.println("wala!");
            solver3();
            this.checkbuttons();
        }
        this.solvenow = false;
    } 

    public int pencilMarkIsUnique(int a, int b){
        int Xlowerbound = a - a % 3;
        int XUpperbound = a - a % 3 + 3;
        int Ylowerbound = b - b % 3;
        int YUpperbound = b - b % 3 + 3;
       for(int i : cellArr[a][b].possiblenum()){ //looping through
           //checkingrows
           int counter = 0;
           for(int j = 0; j < 9; j++)
           {
                ArrayList<Integer> assessor = cellArr[a][j].possiblenum();
               for(int k : assessor)
               {
                   if(k == i && j!=b)
                   {
                       counter++;
                   }
                   
               }
            }
            if(counter == 0){
                return i;
            }
            int counter2 = 0;
            for(int m = 0; m < 9; m++)
            {
                ArrayList<Integer> assessor = cellArr[m][b].possiblenum();
               for(int k : assessor)
               {
                   if(k == i && m!=a)//checking whether its equal or not(uniqueness)
                   {
                        counter2++; 
                   }
               }
           }
           if(counter2 == 0)
                return i;
           int counter3 = 0;
           for(int s = Xlowerbound; s < XUpperbound; s++)
           {
                for(int n = Ylowerbound; n < YUpperbound; n++)
                {
                    if(!(s == a && n == b) && this.occur(i, cellArr[s][n].possiblenum()))
                    {
                        counter3++;
                    }
                }
           }
           if(counter3 == 0){
                return i;
           }
        }
        return 0;
          
          
       }

    public void makeallbuttons(){
        this.makeButton(7, "New Game");
        this.makeButton(6, "Hint");
        this.makeButton(5, "Submit");
        this.makeButton(4, "AI Solve");
        this.makeButton(3, "Win %");
        this.makeButton(2, "Reset");
        this.makeButton(1, "Undo");
        this.makeButton(0, "Marks");
    }

    public void makeButton(int a, String s){
        double x = cellArr[8][a].getXpos();
        double y = cellArr[8][a].getYpos();
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.filledRectangle(x + 0.3, y, 0.1, 0.04);
        StdDraw.setPenColor(StdDraw.BLACK);
        Font font = new Font("Time New Roman", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.text(x + 0.3, y, s, 0);
    }

    public boolean checkbuttonpressed(int height)
    {
        double m = StdDraw.mouseX();
        double n = StdDraw.mouseY();
        double i = cellArr[8][height].getXpos();
        double j = cellArr[8][height].getYpos();
        if(Math.abs(m - i - 0.3) <= 0.1 && Math.abs(n - j) <= 0.04 && StdDraw.isMousePressed()){
            return true;
        }
        return false;
    }

    public void checkbuttons(){
        if(this.checkbuttonpressed(5))
        {
            this.submitgame();

        }
        else if(this.checkbuttonpressed(4))
        {
            this.reverseai();
        }
        else if (this.checkbuttonpressed(1))
        {
            this.undo(true);
        }
        else if (this.checkbuttonpressed(2))
        {
            this.empty();
        }
        else if (this.checkbuttonpressed(0))
        {
            this.togglemarks();
        }
        else
        {
            return;
        }
        StdDraw.pause(300);
    }

    public void reverseai(){
        if(solvenow)
            this.makeButton(4, "Pause");
        else{
            this.makeButton(4, "AI Solve");
            //System.out.println("wo");
        }
        this.solver2();
        this.solvenow = !this.solvenow;
    }

    public void submitgame(){ //submit button
        double x = cellArr[8][8].getXpos();
        double y = cellArr[8][8].getYpos();
        if(this.tester()){
            this.drawblue();
            StdDraw.setPenColor(StdDraw.GREEN);
            Font font = new Font("Time New Roman", Font.BOLD, 40);
            StdDraw.setFont(font);
            String s = "Great job!";
            StdDraw.text(x + 0.3, y, s, 0);
            this.endgame();
        }
        else{
            this.drawblue();
            StdDraw.setPenColor(StdDraw.RED);
            Font font = new Font("Time New Roman", Font.BOLD, 40);
            StdDraw.setFont(font);
            String k = "Keep trying!";
            StdDraw.text(x + 0.3, y, k, 0);
        }
    }

    public boolean occur(int a, ArrayList<Integer> b){
        for(int i = 0; i < b.size(); i++){
            if(b.get(i) == a)
                return true;
        }
        return false;
    }

    public void draw(){
        // for(Cell[] row : this.cellArr){
        //     for(Cell cell : row){
        //         cell.showNumber();
        //     }
        // }
        for(int j = 0; j < 9; j++)
        {
            for(int i = 0; i < 9; i++)
            {
                cellArr[i][j].showNumber();
            }
        }
    }

    public void empty(){
        // int s = this.pastmoves.size();
        // for(int i = 0; i < s; i++)
        // {
        //     this.undo(false);
        //     StdDraw.show();
        //     StdDraw.pause(300);
        // }
        for(Cell[] arr : this.cellArr){
            for(Cell c : arr){
                if(c.isMutable){
                    c.setState(0);
                    c.drawCell();
                    c.showNumber();
                }
            }
        }
    }
       
    public void boardGenerator(){
        Random rand = new Random();
        randomtenintegers();
        solver();
        for(int k = 0; k < 20; k++){
            int a = rand.nextInt(9);
            int b = rand.nextInt(9);
            cellArr[a][b].setState(0);
            cellArr[a][b].setWin(true);
            cellArr[a][b].showNumber();
        }
        //this.draw();
        pencils();
        StdDraw.show();
    }

    public void update(){
        this.updateCell();
        this.checkbuttons();
    }
    //////Probability/////////////////////////

    public ArrayList<Cell> returnsol(){
        ArrayList<Cell> omega = new ArrayList<Cell>();
        for(Cell[] arr : this.cellArr ){
            for(Cell c : arr){
                omega.add(c);
            }
        }
        return omega;
    }
    public int total(){
        int total = 1;
        for(Cell[] arr : this.cellArr ){
            for(Cell c : arr){
                total *= c.getLenMarks();
            }
        }
        return total;
    }

    public void trystate(Cell c){
        for(int i : c.possiblenum()){
            c.setState(i);
            this.solver2();
        }

    }

    public int possibility(){
        int sum = 0;
        for(Cell[] arr : this.cellArr ){
            for(Cell c : arr){
                if(c.getState() == 0){
                    
                }
            }
        }
        return sum;
    }

}
    
    



