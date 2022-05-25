package utility;

import java.text.NumberFormat;
import java.util.Locale;
/**
 * Utility class for formating the amount in US currency format
 */
public final class Currency {

    /**
     * Returns number in US currency format
     * @param amount
     * @return
     */
    public static String formatAmount(long amount) {
        return getCurrencyFormat().format(amount);
    }

    private static NumberFormat getCurrencyFormat() {
        return NumberFormat.getCurrencyInstance(Locale.US);
    }
}
