import java.util.Map;
import utility.Currency;

public class StatementPrinter {

    PlayType playType;

    public String print(Invoice invoice, Map<String, Play> plays) {
        Long totalAmount = 0L;
        int volumeCredits = 0;
        String result = String.format("Statement for %s\n", invoice.getCustomer());

        for (Performance perf : invoice.getPerformances()) {
            Play play = plays.get(perf.getPlayID());
            Long thisAmount = 0L;

            thisAmount = calculateAmountBasedOnPlayType(perf, play, thisAmount);

            volumeCredits = calculateVolumeCredits(volumeCredits, perf, play);

            // print line for this order
            result += String.format("  %s: %s (%s seats)\n", play.getName(), Currency.formatAmount(thisAmount / 100), perf.getAudience());
            totalAmount += thisAmount;
        }
        result += String.format("Amount owed is %s\n", Currency.formatAmount(totalAmount / 100));
        result += String.format("You earned %s credits\n", volumeCredits);
        return result;
    }

    private Long calculateAmountBasedOnPlayType(Performance perf, Play play, Long thisAmount) throws Error {
        switch (play.getType()) {
            case "tragedy":
                playType = new TragedyPlay();
                thisAmount += playType.calculateAmount(perf.getAudience());
                break;
            case "comedy":
                playType = new ComedyPlay();
                thisAmount += playType.calculateAmount(perf.getAudience());
                break;
            default:
                throw new Error("unknown type: "+play.getType());
        }
        return thisAmount;
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
