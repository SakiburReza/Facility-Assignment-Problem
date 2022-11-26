import java.util.ArrayList;

public class Facility {
    private Integer id;
    private Integer capacity;
    private ArrayList<Customer> customers;
    
    public Facility(Integer id, Integer capacity) {
        this.id = id;
        this.capacity = capacity;
        customers = new ArrayList<>(capacity);
    }

    public Integer getId() {
        return id;
    }

    public Integer getCapacity() {
        return capacity;
    }
    

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomer(Customer c) {
        this.customers.add(c);
    }

    
    
    
    
}
