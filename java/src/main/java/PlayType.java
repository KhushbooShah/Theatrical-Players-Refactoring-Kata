public abstract class PlayType {
    abstract public int getBaseAmount();
    abstract public int getMinimumAudienceCount();
    abstract public int getWeight();
    abstract public int getAdujstement();
    abstract public int getAudienceMultiplier();
    abstract public long calculateAmount(int audienceCount);
}
