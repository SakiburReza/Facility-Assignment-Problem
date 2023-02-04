import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class dataset {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("input1_10_ExpG.txt",true));
        Random random = new Random();

        int noOfTestCases = 1;
        int numOfFacility , numOfCustomer;
        numOfFacility = numOfCustomer = 10;
      
        for (int i = 0; i < noOfTestCases; i++) {
            bw.write(numOfFacility+"\n");
            bw.write(numOfCustomer+"\n");
            for (int j = 0; j < numOfFacility; j++) {
                double customerPosition = random.nextDouble(numOfFacility + 1);
               /* Scanner scan = new Scanner(System.in);
                double customerPosition = scan.nextDouble(); */
                for (int k = 1; k < numOfFacility + 1; k++) {
                    double distance = Math.abs(k-customerPosition);
                    bw.write(String.format("%.4f", distance) + " ");
                }
                bw.write("\n");

            }
            bw.write("\n");
           
       }
        bw.close();
    }
}
