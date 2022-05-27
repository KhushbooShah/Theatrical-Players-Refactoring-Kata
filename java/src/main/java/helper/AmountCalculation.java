package helper;

import entities.Performance;
import entities.Play;
import play.ComedyPlay;
import play.PlayType;
import play.TragedyPlay;

public final class AmountCalculation {

    static PlayType playType;

    /**
     * Calculates price for any play type
     * @param perf @Performance
     * @param play @Play
     * @param amount Long
     * @return Long
     * @throws Error
     */
    public static Long calculateAmountBasedOnPlayType(Performance perf, Play play, Long amount) throws Error {
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
}