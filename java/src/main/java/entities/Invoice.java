package entities;
import java.util.List;

public class Invoice {

    private String customer;
    private List<Performance> performances;

    public Invoice(String customer, List<Performance> performances) {
        this.customer = customer;
        this.performances = performances;
    }

    /**
     * @return String return the customer
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(String customer) {
        this.customer = customer;
    }

    /**
     * @return List<Performance> return the performances
     */
    public List<Performance> getPerformances() {
        return performances;
    }

    /**
     * @param performances the performances to set
     */
    public void setPerformances(List<Performance> performances) {
        this.performances = performances;
    }
}
