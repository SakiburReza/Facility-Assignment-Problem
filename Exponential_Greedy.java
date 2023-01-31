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

    public void makeAssignment(){

        for(int i=0; i<greedy.getN();i++){
            Facility_Distance fd = greedy.makePartialAssignment(i);
            Facility fx = fd.getF();
            double d = fd.getDistance();
        }
    }


    
}
