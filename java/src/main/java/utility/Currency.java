package utility;

import java.text.NumberFormat;
import java.util.Locale;
/**
 * Utility class for formating the amount in any locale's currency format
 */
public final class Currency {

    /**
     * Returns number in given locale's currency format
     * @param amount
     * @return
     */
    public static String formatAmount(long amount, Locale locale) {
        return getCurrencyFormat(locale).format(amount);
    }

    private static NumberFormat getCurrencyFormat(Locale locale) {
        return NumberFormat.getCurrencyInstance(locale);
    }
}
