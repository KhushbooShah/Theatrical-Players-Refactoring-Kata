package entities;
public class Performance {

    private String playID;
    private int audience;

    public Performance(String playID, int audience) {
        this.playID = playID;
        this.audience = audience;
    }

    /**
     * @return String return the playID
     */
    public String getPlayID() {
        return playID;
    }

    /**
     * @param playID the playID to set
     */
    public void setPlayID(String playID) {
        this.playID = playID;
    }

    /**
     * @return int return the audience
     */
    public int getAudience() {
        return audience;
    }

    /**
     * @param audience the audience to set
     */
    public void setAudience(int audience) {
        this.audience = audience;
    }

}
