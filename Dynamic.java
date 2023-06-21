import java.util.Arrays;

class Dynamic {
    public int minCostClimbingStairs(int[] cost) {
    //You are given an integer array cost where cost[i] is the cost of ith step on a staircase. 
    //Once you pay the cost, you can either climb one or two steps.
    //You can either start from the step with index 0, or the step with index 1.
    //Return the minimum cost to reach the top of the floor.

        //int start = (cost[0]>cost[1]) ? 1:0;
        int[] minumum =new int[cost.length + 1];
  
        for(int i = 2; i < minumum.length; i++){
            int onestep = minumum[i-1] + cost[i-1];
            int twostep = minumum[i-2] + cost[i-2];
            minumum[i] = Math.min(onestep,twostep);

        }
        
        return minumum[minumum.length - 1];
    }

    public int climbStairs(int n) {
        //You are climbing a staircase. It takes n steps to reach the top.
        //Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
        int num1 = 0;
        int num2 = 1;
        for(int i = 1; i <= n; i++){
            int newNum = num1 + num2;
            num1 = num2;
            num2 = newNum;
        }
        return num2;
    }

    public int coinChange(int[] coins, int amount) {
        //You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
        //Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
        //You may assume that you have an infinite number of each kind of coin.

        // Set a maximum value for comparison
        int max = amount + 1;
        // Create a new array to store the minimum number of coins required for each amount
        int[] dp = new int[amount + 1];
        // Fill the array with the maximum value
        Arrays.fill(dp, max);
        // Set the value of dp[0] to 0 since 0 coins are needed to make 0 amount
        dp[0] = 0;
        // Iterate through each amount from 1 to amount
        for (int i = 1; i <= amount; i++) {
        // Iterate through each coin denomination
        for (int j = 0; j < coins.length; j++) {
        // If the coin denomination is less than or equal to the current amount
            if (coins[j] <= i) {
            // Calculate the minimum number of coins required to make the current amount
            dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
            }
        }
    }
    // If dp[amount] is greater than amount, there is no solution
    // Return -1, otherwise, return the minimum number of coins required to make the amount
    return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        //test minCostClimbingStairs
        Dynamic d = new Dynamic();
        int[] cost = {10, 15, 20};
        System.out.println(d.minCostClimbingStairs(cost));

        //test climbStairs
        System.out.println(d.climbStairs(3));

        //test coinChange
        int[] coins = {1,2,5};
        System.out.println(d.coinChange(coins, 11));
        
    }
}