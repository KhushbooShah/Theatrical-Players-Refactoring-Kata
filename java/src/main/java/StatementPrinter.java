import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import utility.Currency;

public class StatementPrinter {

    PlayType playType;
    Customer customer;

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
            
            performanceAmount = calculateAmountBasedOnPlayType(perf, play, performanceAmount);
            volumeCredits = calculateVolumeCredits(volumeCredits, perf, play);
            playPrintLineList.add(new PlayPrintLine(play.getName(), performanceAmount, perf.getAudience()));
            totalAmount += performanceAmount;
        }
        customer.setPlayOrders(playPrintLineList);
        customer.setAmountOwed(totalAmount);
        customer.setCreditsEarned(volumeCredits);
        return generateStatement(customer);
    }

    private Long calculateAmountBasedOnPlayType(Performance perf, Play play, Long amount) throws Error {
        switch (play.getType()) {
            case "tragedy":
                playType = new TragedyPlay();
                amount += playType.calculateAmount(perf.getAudience());
                break;
            case "comedy":
                playType = new ComedyPlay();
                amount += playType.calculateAmount(perf.getAudience());
                break;
            default:
                throw new Error("unknown type: "+play.getType());
        }
        return amount;
    }

    private int calculateVolumeCredits(int volumeCredits, Performance perf, Play play) {
        // add volume credits
        volumeCredits += Math.max(perf.getAudience() - 30, 0);
        if (isPlayEligibleForExtraVolumeCredit(play))
            return calculateExtraVolumeCredits(volumeCredits, perf);
        return volumeCredits;
    }

    private int calculateExtraVolumeCredits(int volumeCredits, Performance perf) {
        // add extra credit for every ten comedy attendees
        volumeCredits += Math.floor(perf.getAudience() / 5);
        return volumeCredits;
    }

    private Boolean isPlayEligibleForExtraVolumeCredit(Play play) {
        if("comedy".equals(play.getType())) {
            return true;
        }
        return false;
    }

    private String generateStatement(Customer customer) {
        String statement = String.format("Statement for %s\n", customer.getCustomerName())
        .concat(generatePlayOrderStatement(customer.getPlayOrders()))
        .concat(String.format("Amount owed is %s\n", Currency.formatAmount(customer.getAmountOwed() / 100)))
        .concat(String.format("You earned %s credits\n", customer.getCreditsEarned()));
        return statement;
    }

    private String generatePlayOrderStatement(List<PlayPrintLine> playPrintLines) {
        // print line for each order
        String playOrder = "";
        for (PlayPrintLine playPrintLine: playPrintLines) {
            playOrder += String.format(
                "  %s: %s (%s seats)\n", 
                playPrintLine.getPlayName(),
                Currency.formatAmount(playPrintLine.getPerformanceAmount() / 100),
                playPrintLine.getNoOfSeats()
                );
        }
        return playOrder;
    }
}
