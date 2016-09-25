
public class NumberTracker {

    private int max, min;
    private int longestRun = 0, currentRun = 1, previousVal = -1, intOfLongestRun;

    public NumberTracker() {
        max = -1; // okay because only positive numbers are accepted
        min = Integer.MAX_VALUE;
    }


    /**
     * updates the stream with a new number
     * @param val of the  number, must be positive
     * @return true if the method executes successfully, false if a negative value is passed in
     */
    public boolean add(int val) {
        if (val < 1)
            return false;

        if (val > max)
            max = val;

        if (val < min)
            min = val;

        doWorkForLongestRun(val);

        return true;
    }

    /**
     * @return formatted string with info about longest run
     */
    public String longestRun() {
        return String.format("%d consecutive %d(s)", longestRun, intOfLongestRun);
    }

    /**
     * @return max so far in data stream
     */
    public int getMax() {
        return max;
    }

    /**
     * @return min so far in data stream
     */
    public int getMin() {
        return min;
    }

    /**
     * performs the bookkeeping for longest run. cleaner to extract this into a private method.
     * @param val most recently added to the stream
     */
    private void doWorkForLongestRun(int val) {
        if (val == previousVal)
            currentRun++;
        else
            currentRun = 1;

        if (currentRun > longestRun) {
            longestRun = currentRun;
            intOfLongestRun = val;
        }

        previousVal = val;
    }


}
