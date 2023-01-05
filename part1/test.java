package part1;

public class test {

    public static void main(String[] args) throws Exception {
        String[] files = Ex2_1.createTextFiles(10,2,100);
        System.out.println(Ex2_1.getNumOfLines(files));
        System.out.println(Ex2_1.getNumOfLinesThreads(files));
        System.out.println(Ex2_1.getNumOfLinesThreadPool(files));
    }
}
