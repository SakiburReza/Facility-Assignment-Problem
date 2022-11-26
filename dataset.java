import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class dataset {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("input3.txt"));
        BufferedWriter bw2 = new BufferedWriter(new FileWriter("input3_FP.txt"));
        Random random = new Random();

        int noOfTestCases = 50;
        int[] facilitiesPositon;

        for (int i = 0; i < noOfTestCases; i++) {
            int numOfFacility = 10;// random.nextInt(20);
            facilitiesPositon = new int[numOfFacility];
            bw.write(numOfFacility + "\n"); // No. of Facility
            bw.write(numOfFacility + "\n"); // No. of Customer
            for(int z = 0 ;z<numOfFacility;z++){
                while (true) {
                    boolean flag = false;
                    facilitiesPositon[z] = random.nextInt(numOfFacility * 5);
                    for (int y = 0; y < z; y++) {
                        if (facilitiesPositon[z] == facilitiesPositon[y] || facilitiesPositon[z]==0) {
                            flag = true;
                            break;
                        }
                    
                    }
                    if (flag == false)
                    break;
                }
            }
            Arrays.sort(facilitiesPositon);
            for (int j = 0; j < numOfFacility; j++) {
                double customerPosition = random.nextDouble((numOfFacility + 1) * 5);

               
                bw2.write(facilitiesPositon[j] + " ");
                for (int k = 1; k < numOfFacility + 1; k++) {

                    // System.out.println("Customer Postion: "+customerPosition);
                    double distance = Math.abs(facilitiesPositon[k-1] - customerPosition);
                    // System.out.println("Distance: "+distance);
                    bw.write(String.format("%.4f", distance) + " ");
                }
                bw.write("\n");

            }
            bw.write("\n");
            bw2.write("\n");
        }
        bw.close();
        bw2.close();
    }
}
