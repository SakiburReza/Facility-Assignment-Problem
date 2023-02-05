import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Exponential_Greedy {
    private ArrayList<Facility> f_free;
    private ArrayList<Facility> f_used;
    private Customer c_start;
    private Greedy greedy;
    private double sum;
    private double cost_in_trap;
    private int tolerance;
    long start;
    long finish;

    public Exponential_Greedy(int N, int C) {
        this.f_free = new ArrayList<>(N);
        this.greedy = new Greedy(N, C);
        
        this.f_used = new ArrayList<>(N);
        
        this.sum = 0;
        this.cost_in_trap = 0;
        tolerance = 1;
        c_start = new Customer(1);
    }

    public void setFreeFacility(Facility f) {
        this.f_free.add(f);
    }

    public void setUsedFacility(Facility f) {
        this.f_used.add(f);
    }

    public Greedy getGreedy() {
        return greedy;
    }

    public int[] partOptimal(double[][] cost, int cust) { // (cost[][],cust)
    
        double[][] temp = new double[f_free.size()][f_free.size()];

        for (int i = c_start.getId()-1; i < cust; i++) {
            
            int k = 0;
            for (int j = 0; j < greedy.getN(); j++) {
               

                for (int l = 0; l < f_free.size(); l++) {
                    if (f_free.get(l).getId()-1 == j) {

                        temp[i - (c_start.getId()-1)][k] = cost[i][j];
                      
                        k++;
                        break;
                    }
                }
            }
        }

        Optimal opt = new Optimal(temp);
        int[] result = opt.execute();

        return result;
    }

    public double makeAssignmentExp() {

        for (int i = 0; i < greedy.getN(); i++) {
            f_free.add(greedy.getFacility(i));
        }

        for (int i = 0; i < greedy.getN(); i++) {
            Facility fx = greedy.makePartialAssignment(i);
            double d = greedy.getDistances()[i][fx.getId() - 1];

            // part optimal
            int[] optimal = partOptimal(greedy.getDistances(), i + 1);
            System.out.println("Capacity: "+fx.getCapacity());
            Customer c = new Customer(i + 1);
            greedy.setCustomer(c);
            
            if (optimal[i] == fx.getId()-1) {
                fx.setCustomer(c);
                f_used.add(fx);
                c.setAssignedFacility(fx.getId());
                fx.setCapacity(fx.getCapacity()-1);
                
                sum += d;
                System.out.println("d1 :"+d);
            } else if (cost_in_trap + d < tolerance) {
                fx.setCustomer(c);
                f_used.add(fx);
                c.setAssignedFacility(fx.getId());
                fx.setCapacity(fx.getCapacity()-1);
                sum += d;
                cost_in_trap += d;
                System.out.println("d2 :"+d);
            } else {
                Facility fy = new Facility(optimal[i]+1, 1);
                fy.setCustomer(c);
                f_used.add(fy);
                c.setAssignedFacility(fy.getId());
                fy.setCapacity(fy.getCapacity()-1);
                sum += greedy.getDistances()[i][fy.getId()];
                System.out.println("d3 :"+greedy.getDistances()[i][fy.getId()]);
                for (int ii = 0; ii < f_used.size(); ii++) {
                    f_free.remove(f_used.get(ii));
                }
                f_used = new ArrayList<>(greedy.getN());
                c_start = new Customer(i + 1);
                cost_in_trap = 0;
                tolerance = 2 * tolerance;
            }

        }
        return sum;
    }

    public void outputExp() throws IOException {
        start = System.nanoTime();
        this.makeAssignmentExp();
        finish = System.nanoTime();
        long elapsedTime = finish - start;
        BufferedWriter bw = new BufferedWriter(
                new FileWriter(".\\Single Capacity Input Output\\output1_10_ExpG.txt"));
        bw.write(String.format("%.4f", sum) + "\t" + elapsedTime + "\t");
        for (int i = 0; i < greedy.getC(); i++) {
            bw.write(greedy.getCustomers().get(i).getAssignedFacility() + " ");
        }
        bw.write("\n");
        bw.close();
    }

}
