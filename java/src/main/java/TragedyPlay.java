public class TragedyPlay extends PlayType {

    @Override
    public int getBaseAmount() {
        return 40000;
    }

    @Override
    public int getMinimumAudienceCount() {
        return 30;
    }

    @Override
    public int getWeight() {
        return 1000;
    }

    @Override
    public int getAdujstement() {
        return 0;
    }

    @Override
    public int getAudienceMultiplier() {
        return 0;
    }

    @Override
    public long calculateAmount(int audienceCount) {
        long thisAmount = getBaseAmount();
        if (audienceCount > getMinimumAudienceCount()) {
            thisAmount += getWeight() * (audienceCount - getMinimumAudienceCount());
        }
        return thisAmount;
    }
    
}
