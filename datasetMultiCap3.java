import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class datasetMultiCap3 {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("\\Multiple Capacity Input Output\\inputM3_10.txt"));
        BufferedWriter bw2 = new BufferedWriter(new FileWriter("\\Multiple Capacity Input Output\\inputM3_FP_10.txt"));
        Random random = new Random();

        int noOfTestCases = 50, numOfCustomer;
        int[] facilitiesPositon, cust;

        for (int i = 0; i < noOfTestCases; i++) {
            int numOfFacility = 10;// random.nextInt(20);
            facilitiesPositon = new int[numOfFacility];
            //bw.write(numOfFacility + "\n"); // No. of Facility
            numOfCustomer = 0;
            for (int f = 0; f < numOfFacility; f++) {
                facilitiesPositon[f] = myReader1.nextInt();//myReader1 read from fp_10.txt file
            }
            for (int f = 0; f < numOfFacility; f++) {
                cust[f] = myReader1.nextInt();//myReader1 read from fp_10.txt file
            }
            for(int cap = 0;cap<numOfFacility;cap++){
                bw.write(facilitiesPositon[f]+" ");
            }
            bw.write("\n");
            for(int cap = 0;cap<numOfFacility;cap++){
                int capacity = random.nextInt(5)+1;
                numOfCustomer += capacity;
                bw.write(capacity+" ");
            }
            bw.write("\n");
            //bw.write(numOfCustomer+ "\n");
            bw2.write("\n");
            //Generate and write Customers
            for (int j = 0; j < numOfCustomer; j++) {
                double customerPosition = random.nextDouble((numOfFacility + 1) * 5);
             
                for (int k = 1; k < numOfFacility + 1; k++) {

                    // System.out.println("Customer Postion: "+customerPosition);
                    double distance = Math.abs(facilitiesPositon[k-1] - customerPosition);
                    // System.out.println("Distance: "+distance);
                    bw.write(String.format("%.4f", distance) + " ");
                }
                bw.write("\n");

            }
            bw.write("\n");
          
        }
        bw.close();
        bw2.close();
    }
}
