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
import helper.StatementGenerator;
import helper.VolumeCredits;

public class StatementPrinter {

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
            
            performanceAmount = AmountCalculation.calculateAmountBasedOnPlayType(perf, play, performanceAmount);
            volumeCredits = VolumeCredits.calculateVolumeCredits(volumeCredits, perf, play);
            playPrintLineList.add(new PlayPrintLine(play.getName(), performanceAmount, perf.getAudience()));
            totalAmount += performanceAmount;
        }
        customer.setPlayOrders(playPrintLineList);
        customer.setAmountOwed(totalAmount);
        customer.setCreditsEarned(volumeCredits);
        return StatementGenerator.generateStatement(customer);
    }
}
