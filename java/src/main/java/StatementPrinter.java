import java.util.Map;
import utility.Currency;

public class StatementPrinter {

    public String print(Invoice invoice, Map<String, Play> plays) {
        var totalAmount = 0;
        var volumeCredits = 0;
        var result = String.format("Statement for %s\n", invoice.getCustomer());

        for (var perf : invoice.getPerformances()) {
            var play = plays.get(perf.getPlayID());
            var thisAmount = 0;

            switch (play.getType()) {
                case "tragedy":
                    thisAmount = 40000;
                    if (perf.getAudience() > 30) {
                        thisAmount += 1000 * (perf.getAudience() - 30);
                    }
                    break;
                case "comedy":
                    thisAmount = 30000;
                    if (perf.getAudience() > 20) {
                        thisAmount += 10000 + 500 * (perf.getAudience() - 20);
                    }
                    thisAmount += 300 * perf.getAudience();
                    break;
                default:
                    throw new Error("unknown type: "+play.getType());
            }

            volumeCredits = calculateVolumeCredits(volumeCredits, perf, play);

            // print line for this order
            result += String.format("  %s: %s (%s seats)\n", play.getName(), Currency.formatAmount(thisAmount / 100), perf.getAudience());
            totalAmount += thisAmount;
        }
        result += String.format("Amount owed is %s\n", Currency.formatAmount(totalAmount / 100));
        result += String.format("You earned %s credits\n", volumeCredits);
        return result;
    }

    private int calculateVolumeCredits(int volumeCredits, Performance perf, Play play) {
        // add volume credits
        volumeCredits += Math.max(perf.getAudience() - 30, 0);
        return calculateExtraVolumeCredits(volumeCredits, perf, play);
    }

    private int calculateExtraVolumeCredits(int volumeCredits, Performance perf, Play play) {
        // add extra credit for every ten comedy attendees
        if ("comedy".equals(play.getType())) volumeCredits += Math.floor(perf.getAudience() / 5);
        return volumeCredits;
    }

}
