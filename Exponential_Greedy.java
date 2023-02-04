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
        for(int i=0; i<N ;i++){
            f_free.add(new Facility(i+1, 1));
        }
        this.f_used = new ArrayList<>(N);
        this.greedy = new Greedy(N, C);
        this.sum = 0;
        this.cost_in_trap = 0;
        tolerance = 1;
        c_start = new Customer(1);
    }


    public void setFreeFacility(Facility f){
        this.f_free.add(f);
    }
    public void setUsedFacility(Facility f){
        this.f_used.add(f);
    }
    
    public Greedy getGreedy() {
        return greedy;
    }


    public int[] partOptimal(double[][] cost, int cust){ //(cost[][],cust)
        int r=10,c = 10;
        double[][] temp = new double[r][c];
        
        for(int i=0;i<cust;i++)
            {
                for(int j=0;j<c;j++)
                {
                    temp[i][j] = cost[i][j];
                }
            }

        Optimal opt = new Optimal(temp);
        int[] result = opt.execute();

        return result;
    }


    public double makeAssignment(){

        for(int i=0; i<greedy.getN();i++){
            Facility fx= greedy.makePartialAssignment(i);
            double d = greedy.getDistances()[i][fx.getId()];

            //part optimal
            int[] optimal = partOptimal(greedy.getDistances(), i+1);
            System.out.println(optimal);
            Customer c = new Customer(i+1);
            if(optimal[i] == fx.getId()){
               
                fx.setCustomer(c);
                f_used.add(fx);
                sum += d;
            }
            else if(cost_in_trap + d < tolerance){
                fx.setCustomer(c);
                f_used.add(fx);
                sum += d;
                cost_in_trap += d;
            }
            else{
                Facility fy = new Facility(optimal[i], 1);
                fy.setCustomer(c);
                f_used.add(fy);
                sum += greedy.getDistances()[i][fy.getId()];
                for(int ii=0; ii<f_used.size(); ii++){
                    f_free.remove(f_used.get(ii));
                }
                f_used = new ArrayList<>(greedy.getN());
                c_start = new Customer(i+1);
                cost_in_trap = 0;
                tolerance = 2 * tolerance;
            }

        }
        return sum;
    }


    
}
