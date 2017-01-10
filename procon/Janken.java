import java.util.Scanner;

class Janken {

    public static void main (String[] args) {
        Scanner reader = new Scanner(System.in);

        System.out.print("Enter n: ");
        int n = reader.nextInt();

        System.out.print("Enter k: ");
        int k = reader.nextInt();

        if ( !validateParams(n, k) ) {
            return;
        }

        double ans = ProbAtLeastKConsecutiveWinsInNGames(n, k);
        System.out.println(ans);
    }

    private static boolean validateParams(int n, int k) {
        boolean invalidCheck = false;
        if (n < 1 || k < 1) {
            invalidCheck = true;
            System.out.println("n and k cannot be negative");
        }
        if (n < k) {
            invalidCheck = true;
            System.out.println("n should be greater or equal to k");
        }
        if (invalidCheck) { return false; }
        return true;
    }

    private static double ProbAtLeastKConsecutiveWinsInNGames(int n, int k) {
        State state = new State();
        double ans = nextState(state, n, k);
        return ans;
    }

    private static double nextState(State state, int n, int k) {
        //System.out.println("Called nextState(" + state.getTally() + ", " + n + ", " + k + ")");
        double sum = 0;
        State state1 = new State(state.getTally());
        State state2 = new State(state.getTally());

        // Add a win
        state1.addWin();
        sum += evaluateState(state1, n, k);

        // Add a loss
        state2.addLoss();
        sum += evaluateState(state2, n, k);

        //System.out.println("nextState(" + state.getTally() + ", " + n + ", " + k + ") = " + sum);
        return sum;
    }

    private static double evaluateState(State state, int n, int k) {
        //System.out.println("Called evaluateState(" + state.getTally() + ", " + n + ", " + k + ")");

        // if k consecutive wins reached, exit
        if (state.hasKConsecWins(k)) {
            return state.getProb();
        }

        // if max trials reached, dismiss
        if (state.getCount() == n ) {
            return 0;
        }

       	//if dismissable, dismiss
        if (state.canBeDismissed(n, k)) {
            //System.out.println("Dismissing " + state.getTally());
            return 0;
        }

        // if max trials not reached continue
        return nextState(state, n, k);
    }
}

