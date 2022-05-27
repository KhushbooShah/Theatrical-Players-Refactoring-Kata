
public class PlayPrintLine {
    private String playName;
    private Long performanceAmount;
    private int noOfSeats;

    public PlayPrintLine(String playName, Long performanceAmount, int noOfSeats) {
        this.playName = playName;
        this.performanceAmount = performanceAmount;
        this.noOfSeats = noOfSeats;
    }

    /**
     * @return String return the playName
     */
    public String getPlayName() {
        return playName;
    }

    /**
     * @param playName the playName to set
     */
    public void setPlayName(String playName) {
        this.playName = playName;
    }

    /**
     * @return Long return the performanceAmount
     */
    public Long getPerformanceAmount() {
        return performanceAmount;
    }

    /**
     * @param performanceAmount the performanceAmount to set
     */
    public void setPerformanceAmount(Long performanceAmount) {
        this.performanceAmount = performanceAmount;
    }

    /**
     * @return int return the noOfSeats
     */
    public int getNoOfSeats() {
        return noOfSeats;
    }

    /**
     * @param noOfSeats the noOfSeats to set
     */
    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

}
