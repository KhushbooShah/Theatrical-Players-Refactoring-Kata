package play;
public class ComedyPlay extends PlayType{

    @Override
    public int getBaseAmount() {
        return 30000;
    }

    @Override
    public int getMinimumAudienceCount() {
        return 20;
    }

    @Override
    public int getWeight() {
        return 500;
    }

    @Override
    public int getAdujstement() {
        return 10000;
    }

    @Override
    public int getAudienceMultiplier() {
        return 300;
    }

    @Override
    public long calculateAmount(int audienceCount) {
        long thisAmount = getBaseAmount();
        if (audienceCount > getMinimumAudienceCount()) {
            thisAmount += getAdujstement() + getWeight() * (audienceCount - getMinimumAudienceCount());
        }
        thisAmount += getAudienceMultiplier() * audienceCount;
        return thisAmount;
    }
    
}
