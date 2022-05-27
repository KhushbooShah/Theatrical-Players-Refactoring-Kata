package helper;

import entities.Customer;
import entities.PlayPrintLine;
import utility.Currency;

public final class StatementGenerator {
    

    private static final int DIVISION_CONSTANT = 100;

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
    public static String generateStatement(Customer customer) {
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
    private static String generatePlayOrderStatement(Customer customer) {
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
