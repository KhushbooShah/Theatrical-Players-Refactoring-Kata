package helper;
import entities.Performance;
import entities.Play;

public final class VolumeCredits {

    private static final int VOLUME_CREDIT_CONSTANT = 30;
    private static final int EXTRA_VOLUME_CREDIT_CONSTANT = 5;

    /**
     * Calculates volume credits
     * @param volumeCredits int
     * @param perf @Performance
     * @param play @Play
     * @return int
     */
    public static int calculateVolumeCredits(int volumeCredits, Performance perf, Play play) {
        // add volume credits
        volumeCredits += Math.max(perf.getAudience() - VOLUME_CREDIT_CONSTANT, 0);
        if (isPlayEligibleForExtraVolumeCredit(play))
            return calculateExtraVolumeCredits(volumeCredits, perf);
        return volumeCredits;
    }

    /**
     * Calculates extra volume credits based on audience count
     * @param volumeCredits int
     * @param perf @Performance
     * @return int
     */
    private static int calculateExtraVolumeCredits(int volumeCredits, Performance perf) {
        // add extra credit for every ten comedy attendees
        volumeCredits += Math.floor(perf.getAudience() / EXTRA_VOLUME_CREDIT_CONSTANT);
        return volumeCredits;
    }

    /**
     * Checks if given play is eligible for extra volume credit or not
     * @param play @Play
     * @return Boolean
     */
    private static Boolean isPlayEligibleForExtraVolumeCredit(Play play) {
        if("comedy".equals(play.getType())) {
            return true;
        }
        return false;
    }
}
