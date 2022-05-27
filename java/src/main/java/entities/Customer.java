package entities;
import java.util.List;
import java.util.Locale;

public class Customer {
    private Locale customerLocale;
    private String customerName;
    private Long amountOwed;
    private int creditsEarned;
    private List<PlayPrintLine> PlayOrders;
    

    /**
     * @return Locale return the customerLocale
     */
    public Locale getCustomerLocale() {
        return customerLocale;
    }

    /**
     * @param customerLocale the customerLocale to set
     */
    public void setCustomerLocale(Locale customerLocale) {
        this.customerLocale = customerLocale;
    }

    /**
     * @return String return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return Long return the amountOwed
     */
    public Long getAmountOwed() {
        return amountOwed;
    }

    /**
     * @param amountOwed the amountOwed to set
     */
    public void setAmountOwed(Long amountOwed) {
        this.amountOwed = amountOwed;
    }

    /**
     * @return int return the creditsEarned
     */
    public int getCreditsEarned() {
        return creditsEarned;
    }

    /**
     * @param creditsEarned the creditsEarned to set
     */
    public void setCreditsEarned(int creditsEarned) {
        this.creditsEarned = creditsEarned;
    }

    /**
     * @return List<PlayPrintLine> return the PlayOrders
     */
    public List<PlayPrintLine> getPlayOrders() {
        return PlayOrders;
    }

    /**
     * @param PlayOrders the PlayOrders to set
     */
    public void setPlayOrders(List<PlayPrintLine> PlayOrders) {
        this.PlayOrders = PlayOrders;
    }

}
