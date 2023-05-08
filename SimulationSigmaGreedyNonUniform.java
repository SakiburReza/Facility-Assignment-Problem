import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class SimulationSigmaGreedyNonUniform {
    public static void main(String[] args) throws IOException {
        int N, C;
        int noOfTestCases = 1;
        File file = new File(".\\Single Capacity Input Output\\input3_10.txt");
        File fpfile = new File(".\\Single Capacity Input Output\\input3_FP_10.txt");
        Scanner scanner = new Scanner(file);
        Scanner scanner2 = new Scanner(fpfile);

        for (int t = 0; t < noOfTestCases; t++) {

            N = scanner.nextInt();
            C = scanner.nextInt();
           
            SigmaRandomizedGreedyNonUniform sNonUniformGreedy = new SigmaRandomizedGreedyNonUniform(N, C);
            for (int i = 0; i < N; i++) {
                Facility f = new Facility(i + 1, 1);
                sNonUniformGreedy.setFacility(f);
                sNonUniformGreedy.setFacility_position(i, scanner2.nextInt());
            }

            for (int i = 0; i < C; i++) {
                for (int j = 0; j < N; j++) {
                    sNonUniformGreedy.setDistance(i, j, scanner.nextDouble());
                }
            }
           
            sNonUniformGreedy.output();
        }
        
    }

}
