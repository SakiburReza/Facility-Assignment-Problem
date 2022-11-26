public class Customer {
    private Integer id;
    private Integer assignedFacility;

    public Customer(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
    

    public Integer getAssignedFacility() {
        return assignedFacility;
    }

    public void setAssignedFacility(Integer assignedFacility) {
        this.assignedFacility = assignedFacility;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + "]";
    }
    
    
    
}
