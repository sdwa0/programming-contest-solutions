import org.apache.commons.lang.StringUtils;

class State {
    private String tally;

    public State() {
	tally = "";
    }

    public State(String tally1) {
	tally = tally1;
    }

    public String getTally() {
	return tally;
    }

    public void addWin() {
        tally += 'W';
    }

    public void addLoss() {
	tally += 'L';
    }

    public int getWins() {
	return StringUtils.countMatches(tally, "W");
    }

    public int getLosses() {
	return StringUtils.countMatches(tally, "L");
    }

    public int getCount() {
	return tally.length();
    }

    public boolean hasKConsecWins(int k) {
	return StringUtils.contains(tally, StringUtils.repeat("W", k));
    }

    public double getProb() {
        double P_win = 1.0 / 3;
        return Math.pow(P_win, getWins()) * Math.pow(1.0-P_win, getLosses());
    }

    public boolean canBeDismissed(int n, int k) {
	return (getWins() + n - getCount() < k);
    }
}
