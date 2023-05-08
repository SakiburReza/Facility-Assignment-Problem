import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class SimulationSigmaGreedy {
    public static void main(String[] args) throws IOException {
        int N, C;
        int noOfTestCases = 1;
        File file = new File(".\\Single Capacity Input Output\\input1_10.txt");
        Scanner scanner = new Scanner(file);

        for (int t = 0; t < noOfTestCases; t++) {

            N = scanner.nextInt();
            C = scanner.nextInt();
           // System.out.println("N = " + N + " C= " + C);
            SigmaRandomizedGreedy sgreedy = new SigmaRandomizedGreedy(N, C);
            for (int i = 0; i < N; i++) {
                // int temp = scanner.nextInt();
                Facility f = new Facility(i + 1, 1);
                sgreedy.setFacility(f);
            }

            for (int i = 0; i < C; i++) {
                for (int j = 0; j < N; j++) {
                    sgreedy.setDistance(i, j, scanner.nextDouble());
                }
            }

            //System.out.println("Cost: " + greedy.makeAssignment());
            //greedy.showAssignmentByCustomer();
           
            sgreedy.output();
        }
        
    }

}
