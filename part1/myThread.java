package part1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class myThread extends Thread{
    //file name that thread shall use to run
    private String fileName;
    //number of total lines thread shall callculate
    private int numOfTotalLines;

    /**
     * getter for numberoftotallines
     * @return - mythreads number of total lines
     */
    public int getNumOfTotalLines() {
        return numOfTotalLines;
    }

    /**
     * constructor
     * @param fileName - file name given to thread
     */
    public myThread(String fileName){
        this.fileName = fileName;
        this.numOfTotalLines = 0;
    }

    /**
     * run function for my thread
     */
    public void run(){
        try{
            File myObj = new File(fileName);
            Scanner sc = new Scanner(myObj);
            while(sc.hasNextLine()){
                numOfTotalLines++;
                sc.nextLine();
            }
        }catch (FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
