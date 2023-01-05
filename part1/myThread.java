package part1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class myThread extends Thread{
    private String fileName;
    private int numOfTotalLines;

    public String getFileName() {
        return fileName;
    }

    public int getNumOfTotalLines() {
        return numOfTotalLines;
    }

    public myThread(String fileName){
        this.fileName = fileName;
        this.numOfTotalLines = 0;
    }

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
