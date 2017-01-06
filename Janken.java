import java.util.Scanner;

class ProCon {
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
        double sum = 0;
        for (int i=k; i<=n; i++) {
            sum += ProbKWinsInIGames(i, k);
        }
        return sum;
    }

    private static double ProbKWinsInIGames(int i, int k) {
        double P_win = 1.0 / 3;
        return Math.pow(P_win, k) * Math.pow(1.0-P_win, i-k);
    }
}
