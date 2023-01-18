import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class SimulationM1 {
    public static void main(String[] args) throws IOException {
        int N, C;
        int noOfTestCases = 50;
        File file = new File(".\\Multiple Capacity Input Output\\inputM1_10_mod.txt");
        Scanner scanner = new Scanner(file);
        int[] facilityCapacity;

        for (int t = 0; t < noOfTestCases; t++) {

            N = scanner.nextInt();
            facilityCapacity = new int[N];
           // C = 0;

            for(int i =0;i<N;i++){
                facilityCapacity[i] = scanner.nextInt();
               
            }

            C = scanner.nextInt();
            Greedy greedy = new Greedy(N, C);

            for (int i = 0; i < N; i++) {
                Facility f = new Facility(i + 1, facilityCapacity[i]);
                greedy.setFacility(f);
            }

            for (int i = 0; i < C; i++) {
                for (int j = 0; j < N; j++) {
                    double temp = scanner.nextDouble();
                    greedy.setDistance(i, j, temp);
                }
            }

            //System.out.println("Cost: " + greedy.makeAssignment());
            //greedy.showAssignmentByCustomer();
            greedy.output();
           
        }
        
    }

}
