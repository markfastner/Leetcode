class Solution {
    public int climbStairs(int n) {
        // You are climbing a staircase. It takes n steps to reach the top.
        // Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
        int num1 = 0;
        int num2 = 1;
        for(int i = 1; i <= n; i++){
            int newNum = num1 + num2;
            num1 = num2;
            num2 = newNum;
        }
        return num2;
    }

    public int maxProfit(int prices[]) {
        // you are given an array prices where prices[i] is the price of a given stock on the ith day.
        // You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
        // Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice)
                minprice = prices[i];
            else if (prices[i] - minprice > maxprofit)
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }

    public int rob(int[] nums) {
        // You are a professional robber planning to rob houses along a street. 
        // Each house has a certain amount of money stashed, 
        // the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
        // Given an integer array nums representing the amount of money of each house,
        //  return the maximum amount of money you can rob tonight without alerting the police.
        if (nums.length == 1) return nums[0];
        
        int[] dp = new int[nums.length];
        
        // Base cases
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]); // Recurrence relation
        }
        
        return dp[nums.length - 1];
    }
    
    
    // public static int factorial(int number){
    //     int fact=1;
    //     for(int i=1;i<=number;i++){
    //     fact=fact*i;
    //     }
    //     return fact;
    // }


    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.climbStairs(9));
        System.out.println(s.maxProfit(new int[]{7,1,5,3,6,4}));
        System.out.println(s.rob(new int[]{1,2,3,1}));
    }
}

