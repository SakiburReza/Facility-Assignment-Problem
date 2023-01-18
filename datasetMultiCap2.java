import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class datasetMultiCap2 {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(".\\Multiple Capacity Input Output\\inputM3_100.txt"));
        BufferedWriter bw2 = new BufferedWriter(new FileWriter(".\\Multiple Capacity Input Output\\inputM3_FP_100.txt"));
        Random random = new Random();

        int noOfTestCases = 50, numOfCustomer,numOfFacility;
        int[] facilitiesPositon;

        for (int i = 0; i < noOfTestCases; i++) {
            numOfFacility = 100;// random.nextInt(20);
            facilitiesPositon = new int[numOfFacility];
            bw.write(numOfFacility + "\n"); // No. of Facility
            numOfCustomer = 0;
            for(int cap = 0;cap<numOfFacility;cap++){
                int capacity = random.nextInt(5)+1;
                numOfCustomer += capacity;
                bw.write(capacity+" ");
            }
            bw.write("\n");
            bw.write(numOfCustomer+ "\n");
            for(int z = 0 ;z<numOfFacility;z++){
                while (true) {
                    boolean flag = false;
                    facilitiesPositon[z] = random.nextInt(numOfFacility * 5)+1;
                    for (int y = 0; y < z; y++) {
                        if (facilitiesPositon[z] == facilitiesPositon[y]) {
                            flag = true;
                            break;
                        }

                    }
                    if (flag == false)
                    break;
                }
            }
            Arrays.sort(facilitiesPositon);
            for(int f=0; f<numOfFacility;f++){
                bw2.write(facilitiesPositon[f] + " ");
            }
            bw2.write("\n");
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
