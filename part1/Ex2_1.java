package part1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Ex2_1 {
    private String[] files;
    private int numOfLines;

    /**
     * get method for class's array of file names
     * @return - array of file name's
     */
    public String[] getFiles() {
        return files;
    }

    /**
     * get method for class's number of lines in total of files
     * @return - number of lines in total of files
     */
    public int getNumOfLines() {
        return numOfLines;
    }

    /**
     * default constructor for class Ex2_1
     */
    public Ex2_1(){
        this.files = null;
        this.numOfLines = 0;
    }

    /**
     * constructor with arguments
     * @param files - string array of file names
     * @param numOfLines - number of lines in file's
     */
    public Ex2_1(String[] files, int numOfLines){
        this.files = files;
        this.numOfLines  = numOfLines;
    }

    public static int[] randomNumOfLines(int n, int seed, int bound){
        int arrOfLengths[] = new int[n];
        Random rand = new Random(seed);
        for (int i = 1; i <= n; i++) {
            int x = rand.nextInt(bound);
            arrOfLengths[i-1] = x;
        }
        return arrOfLengths;
    }

    public static int getRandomChar(int min,int max){
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static String[] createTextFiles(int n, int seed, int bound){
        String defaultName = "file_";
        String[] files = new String[n];
        //defining our abc
        int[] AbcAsciiRange = {32,126};
        char newline = '\n';

        for(int i = 1; i<=n; i++){
            files[i-1] = defaultName + String.valueOf(i);
        }

        //genarate random number of lines to write on file's
        int[] randomNumOfLines;
        randomNumOfLines = randomNumOfLines(n,seed,bound);
        int randomNumberOfLinesCounter = 0;
        for(String file: files){

            try{
                FileWriter fileWriter = new FileWriter(file);

                for(int i = 0; i<randomNumOfLines[randomNumberOfLinesCounter]; i++){
                    int charPerRow = getRandomChar(10,126);
                    String strToWrite = "";
                    for(int j = 0; j<charPerRow; j++){
                        int randomChar = getRandomChar(AbcAsciiRange[0],AbcAsciiRange[1]);
                        strToWrite += (char)(randomChar);
                    }
                    fileWriter.write(strToWrite);
                    fileWriter.write(newline);

                }
                fileWriter.close();
                randomNumberOfLinesCounter++;
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }

        return files;
    }

    public static int getNumOfLines(String[] fileNames){

        int numOfTotalLines = 0;

        for(String fileName: fileNames){
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

        return numOfTotalLines;
    }

    public static int getNumOfLinesThreads(String[] fileNames){
        int numOfTotalLines = 0;
        //make n threads , each thread dose one file check.
        for(String fileName: fileNames){
            myThread newThread = new myThread(fileName);
            newThread.start();
            while(newThread.isAlive()){

            }
            numOfTotalLines+= newThread.getNumOfTotalLines();
        }

        return numOfTotalLines;
    }

    public static int getNumOfLinesThreadPool(String[] fileNames) throws Exception {
        int numOfTotalLines = 0;
        ExecutorService pool = null;
        Future<Integer> future = null;
        try {
            pool = Executors.newFixedThreadPool(fileNames.length);

            for(int i = 0; i<fileNames.length; i++){
                future = pool.submit(new callFunction(i,fileNames[i]));
                numOfTotalLines+= future.get();
            }
        }finally {
            pool.shutdown();
        }

        return numOfTotalLines;
    }


}