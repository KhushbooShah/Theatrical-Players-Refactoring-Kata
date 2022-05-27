import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import entities.Customer;
import entities.Invoice;
import entities.Performance;
import entities.Play;
import entities.PlayPrintLine;
import helper.AmountCalculation;
import helper.VolumeCredits;
import utility.Currency;

public class StatementPrinter {

    
    Customer customer;

    public static final int DIVISION_CONSTANT = 100;

    public String print(Invoice invoice, Map<String, Play> plays) {
        Long totalAmount = 0L;
        int volumeCredits = 0;
        customer = new Customer();
        customer.setCustomerLocale(Locale.US);
        customer.setCustomerName(invoice.getCustomer());

        List<PlayPrintLine> playPrintLineList = new ArrayList<>();
        for (Performance perf : invoice.getPerformances()) {
            Play play = plays.get(perf.getPlayID());
            Long performanceAmount = 0L;
            
            performanceAmount = AmountCalculation.calculateAmountBasedOnPlayType(perf, play, performanceAmount);
            volumeCredits = VolumeCredits.calculateVolumeCredits(volumeCredits, perf, play);
            playPrintLineList.add(new PlayPrintLine(play.getName(), performanceAmount, perf.getAudience()));
            totalAmount += performanceAmount;
        }
        customer.setPlayOrders(playPrintLineList);
        customer.setAmountOwed(totalAmount);
        customer.setCreditsEarned(volumeCredits);
        return generateStatement(customer);
    }
    

    /**
     * Generates full statement containing following details
     *  - Customer name
     *      - Play name, Play Price, No. of Audience
     *      - Play name, Play Price, No. of Audience
     *      ...
     *  - Total amount customer owes
     *  - Total earned credits
     * @param customer @Customer
     * @return String
     */
    private String generateStatement(Customer customer) {
        String statement = String.format("Statement for %s\n", customer.getCustomerName())
        .concat(generatePlayOrderStatement(customer))
        .concat(String.format("Amount owed is %s\n", Currency.formatAmount(customer.getAmountOwed() / DIVISION_CONSTANT, customer.getCustomerLocale())))
        .concat(String.format("You earned %s credits\n", customer.getCreditsEarned()));
        return statement;
    }

    /**
     * Generates print line for each of the play customer selected
     * @param customer @Customer
     * @return String
     */
    private String generatePlayOrderStatement(Customer customer) {
        // print line for each order
        String playOrder = "";
        for (PlayPrintLine playPrintLine: customer.getPlayOrders()) {
            playOrder += String.format(
                "  %s: %s (%s seats)\n", 
                playPrintLine.getPlayName(),
                Currency.formatAmount(playPrintLine.getPerformanceAmount() / DIVISION_CONSTANT, customer.getCustomerLocale()),
                playPrintLine.getNoOfSeats()
                );
        }
        return playOrder;
    }
}
