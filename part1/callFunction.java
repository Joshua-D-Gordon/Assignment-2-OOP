package part1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class callFunction implements Callable {
    //callfunction's number of total lines
    int numOfTotalLines = 0;
    //filename to operate on
    String fileName;

    /**
     * constructor
     * @param fileName - filename to call on
     */
    public callFunction(String fileName){
        this.fileName = fileName;
    }

    /**
     * computes number of total line's of this file
     * @return - number of total lines
     * @throws Exception - from original parent method / not needed in our case
     */
    @Override
    public Object call() throws Exception {
        try{
            File myObj = new File(this.fileName);
            Scanner sc = new Scanner(myObj);
            while(sc.hasNextLine()){
                this.numOfTotalLines++;
                sc.nextLine();
            }
        }catch (FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return this.numOfTotalLines;
    }
}
