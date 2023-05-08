import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class SigmaRandomizedGreedyNonUniform {
    private ArrayList<Facility> facilities;
    private ArrayList<Customer> customers;
    private double totalCost;
    private double[][] distances;
    private int N;
    private int C;
    private double sigma;
    long start;
    long finish;
    private int[] facility_position;

    public SigmaRandomizedGreedyNonUniform(int N, int C) { // N --- no. of facility //C --- no. of customers
        this.facilities = new ArrayList<>(N);
        this.customers = new ArrayList<>(C);
        this.totalCost = 0;
        distances = new double[C][N];
        facility_position = new int[N];
        this.N = N;
        this.C = C;
        sigma = 0.3;

    }

    public void setFacility(Facility f) {
        this.facilities.add(f);
    }

    public void setCustomer(Customer c) {
        this.customers.add(c);
    }

    public double getDistance(int i, int j) {
        return distances[i][j];
    }

    public void setDistance(int i, int j, double dist) {
        this.distances[i][j] = dist;
    }

    public int getFacility_position(int i) {
        return facility_position[i];
    }

    public void setFacility_position(int i, int pos) {
        this.facility_position[i] = pos;
    }

    public Facility getFacility(int i) {
        return facilities.get(i);
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public int getN() {
        return N;
    }

    public int getC() {
        return C;
    }

    public double[][] getDistances() {
        return distances;
    }

    public double makeAssignment() {

        for (int i = 0; i < C; i++) {

            double min = 99999999;
            int index = -1, customerPosIndex = 0, lowest = 0;
            int fpDistance = 0;
            double temp = 0;

            for (int j = 0; j < N; j++) {
                if (facilities.get(j).getCapacity() > 0 && distances[i][j] < min) {
                    min = distances[i][j];
                    index = j;
                }
            }
            double min2 = 99999999;
            for (int j = 0; j < N; j++) {
                if (distances[i][j] < min2) {
                    min2 = distances[i][j];
                    lowest = j;
                }
            }
            if (lowest == 0) {
                fpDistance = Math.abs(facility_position[1] - facility_position[0]);
                temp = distances[i][1] + distances[i][0];
                if (fpDistance == temp) {
                    customerPosIndex = 0;
                } else
                    customerPosIndex = -1;
            }
            else if(lowest == N - 1){
                fpDistance = Math.abs(facility_position[N-1] - facility_position[N-2]);
                temp = distances[i][N-1] + distances[i][N-2];
                if (fpDistance == temp) {
                    customerPosIndex = N-2;
                } else
                    customerPosIndex = N-1;
            }
            else{
                fpDistance = Math.abs(facility_position[lowest] - facility_position[lowest-1]);
                temp = distances[i][lowest] + distances[i][lowest-1];
                if (fpDistance == temp) {
                    customerPosIndex = lowest-1;
                } else
                    customerPosIndex = lowest;
            }

            if (min >= sigma) {
                System.out.println("Random Greedy: ");
                Random random = new Random();
                int toss = random.nextInt(100);
                if (toss % 2 == 0) {
                    System.out.println("Right"); // toss = 0 ---> head ---> right
                    for (int k = customerPosIndex + 1; k < N; k++) {
                        if (facilities.get(k).getCapacity() > 0) {
                            min = distances[i][k];
                            index = k;
                            break;
                        }
                    }
                } else {
                    System.out.println("Left");
                    for (int k = customerPosIndex; k >= 0; k--) {
                        if (facilities.get(k).getCapacity() > 0) {
                            min = distances[i][k];
                            index = k;
                            break;
                        }
                    }
                }
            }
            System.out.println("CP Index: "+customerPosIndex);
            System.out.println("Index: " + index + "  distance = " + distances[i][index]);
            Customer c = new Customer(i + 1);
            c.setAssignedFacility(index + 1);
            customers.add(c);
            facilities.get(index).setCustomer(c);
            facilities.get(index).setCapacity(facilities.get(index).getCapacity() - 1);

            totalCost += min;
        }
        return totalCost;
    }

    public void output() throws IOException {
        start = System.nanoTime();
        this.makeAssignment();
        finish = System.nanoTime();
        long elapsedTime = finish - start;
        BufferedWriter bw = new BufferedWriter(
                new FileWriter(".\\Single Capacity Input Output\\a_sgreedy_NonU.txt", true));
        bw.write(String.format("%.4f", totalCost) + "\t" + elapsedTime + "\t");
        for (int i = 0; i < C; i++) {
            bw.write(customers.get(i).getAssignedFacility() + " ");
        }
        bw.write("\n");
        bw.close();
    }
}
