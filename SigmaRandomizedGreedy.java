import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class SigmaRandomizedGreedy {
    private ArrayList<Facility> facilities;
    private ArrayList<Customer> customers;
    private double totalCost;
    private double[][] distances;
    private int N;
    private int C;
    private double sigma;
    long start;
    long finish;

    public SigmaRandomizedGreedy(int N, int C) { // N --- no. of facility //C --- no. of customers
        this.facilities = new ArrayList<>(N);
        this.customers = new ArrayList<>(C);
        this.totalCost = 0;
        distances = new double[C][N];
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

            double min = 999999;
            int index = -1, customerPosition = 0;
            int secondCustomerPosition=0;

            for (int j = 0; j < N; j++) {
                if (facilities.get(j).getCapacity() > 0 && distances[i][j] < min) {
                    min = distances[i][j];
                    index = j;
                }
            }

            customerPosition =(int) Math.floor(distances[i][0]);
            secondCustomerPosition =(int) Math.floor(distances[i][1]);
            if(customerPosition == 0 && customerPosition == secondCustomerPosition) {
                customerPosition = 1;
            }


            System.out.println("i = "+i);
            System.out.println("Customer Pos: "+ customerPosition);
       

            if (min >= sigma){
                Random random = new Random();
                int toss = random.nextInt(100);
                if(toss % 2 == 0){        
                    System.out.println("Right");             //toss = 0 ---> head  ---> right
                    for(int k = customerPosition + 1; k < N; k++){
                        if(facilities.get(k).getCapacity() > 0){
                            min = distances[i][k];
                            index = k;
                            break;
                        }
                    }
                }
                else{
                    System.out.println("Left");
                    for(int k = customerPosition; k >= 0; k--){
                        if(facilities.get(k).getCapacity() > 0){
                            min = distances[i][k];
                            index = k;
                            break;
                        }
                    }
                }
            }
            System.out.println("Index: "+ index + "  distance = "+ distances[i][index]);
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
                new FileWriter(".\\Single Capacity Input Output\\a_sgreedy_check.txt", true));
        bw.write(String.format("%.4f", totalCost) + "\t" + elapsedTime + "\t");
        for (int i = 0; i < C; i++) {
            bw.write(customers.get(i).getAssignedFacility() + " ");
        }
        bw.write("\n");
        bw.close();
    }
}
