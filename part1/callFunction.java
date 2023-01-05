package part1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class callFunction implements Callable {
    int numOfTotalLines = 0;
    String fileName;

    public callFunction(int callerid,String fileName){
        this.fileName = fileName;
    }
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
