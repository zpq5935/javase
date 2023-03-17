package interview.od;

public class Test2 {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        int[] arr = new int[]{6, -1, 5, 4, -7};
//        int[] arr = new int[]{-6, -1, 5, 4, -7, 8};
        solve(arr, arr.length);
    }

    public static void solve(int[] arr, int num) {
        int[] dp = new int[num];
        dp[0] = arr[0];
        int max = dp[0];
        int begin = 0, end = 0;
        for (int i = 1; i < num; i++) {
            dp[i] = Math.max(dp[i - 1] + arr[i], arr[i]);
            if (dp[i] > max) {
                max = dp[i];
                end = i;
            }
            if (dp[i] == arr[i]) {
                begin = i;
            }
        }
        System.out.println(max + " " + begin + " " + end);
    }

}
