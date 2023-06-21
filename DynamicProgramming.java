import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    

    public int numIslands(char[][] grid) {
        //Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
        //An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
        //You may assume all four edges of the grid are all surrounded by water.
        int islands = 0;
        int rl = grid.length;
        int cl = grid[0].length;

        for (int i = 0; i < rl; i++) {
            for (int j = 0; j < cl; j++) {
                if (grid[i][j] == '1') {
                    islands++;
                    dfs(grid, i, j);
                }
            }
        }
        return islands;
    }

    public void dfs(char[][] grid, int i, int j) {
        int rl = grid.length;
        int cl = grid[0].length;

        if (i < 0 || i >= rl || j < 0 || j >= cl || grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0';
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }
    
    // public static int factorial(int number){
    //     int fact=1;
    //     for(int i=1;i<=number;i++){
    //     fact=fact*i;
    //     }
    //     return fact;
    // }


    public int maxArea(int[] height) {
        //You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
        //Find two lines that together with the x-axis form a container, such that the container contains the most water.
        //Return the maximum amount of water a container can store.

        //strategy: 2 pointer approach
        //create variable max area
        //start on either end of the array and calculate area
        //area = min of the 2 lengths * distance between them
        //move either the left or right pointer based on whichether lenght is shorter
        //calculate area and check/update maxarea
        //finish looping when left and right collide
        //return max area

        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        for(int i = 0; i < height.length; i+=1){
            
            int distance = Math.abs(left - right);
            int area = Math.min(height[left], height[right]) * distance;
            if(area > maxArea){
                maxArea = area;
            }
            if(height[right] < height[left] ){
                right -=1;
            }
            else{
                left +=1;
            }
        }
        return maxArea;
        }

        public List<List<Integer>> threeSum(int[] nums) {
            //Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
            //Notice that the solution set must not contain duplicate triplets.

            

            //stragey: 2 pointer
            //sort nums
            //start at beginng of nums and loop through
                //the current number needs to have 2 numbers that when added together equals the target value (inverse of the current number)
                //to find if those numbers exist we use 2 pointers one at the end of hte lsiut(high pointer) and one at the next element from the current elemetn(low pointer)
                //find the sums of the two poitners and adjust the pointer followign the rule:
                //if the sum is less then the target value increment the low pointer to the right
                //if the sums is largert  than the target decrease the high pointer to the left
                //if we find the target add the triple to the return list
                //stopp loping when the high/low pointer meet

                List<List<Integer>> triples = new ArrayList<>();
                Arrays.sort(nums);
            
                for(int i = 0; i < nums.length - 2; i++) {
                    if(i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                        int low = i + 1;
                        int high = nums.length - 1;
                        int target = -nums[i];
                        
                        while(low < high) {
                            if(nums[low] + nums[high] == target) {
                                triples.add(Arrays.asList(nums[i], nums[low], nums[high]));
            
                                while(low < high && nums[low] == nums[low + 1]) low++;
                                while(low < high && nums[high] == nums[high - 1]) high--;
            
                                low++;
                                high--;
                            } else if(nums[low] + nums[high] > target) {
                                high--;
                            } else {
                                low++;
                            }
                        }
                    }
                }
            
                return triples;
        }

        public int divide(int dividend, int divisor) {
            ///Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.
            //The integer division should truncate toward zero, which means losing its fractional part. For example, 8.345 would be truncated to 8, and -2.7335 would be truncated to -2.
            //Return the quotient after dividing dividend by divisor.

            //reasonming
            //there are 4 different cases
            //1: both dividend and divisor are positive
            //2: both dividend and divisor are negative
            //3: dividend is positive and divisor is negative
            //4: dividend is negative and divisor is positive
            //stratgey implement each case individually
            
            int count = 0;
            //case 1
            if(dividend > 0 && divisor > 0){
                while(dividend - divisor >= 0) {
                    dividend -= divisor;
                    count++;
                }
                return count;
            }
            //case 2    
            else if(dividend < 0 && divisor < 0){
                while(dividend - divisor <= 0){
                    dividend -= divisor;
                    count++;
                }
                return count;
            }
            
            //case 3
            else if(dividend > 0 && divisor < 0){
                System.out.println("HSA");
                while(dividend + divisor >= 0){
                    dividend += divisor;
                    count++;
                }
                count = -count;
                return count;
            }
            //case 4
            else if(dividend < 0 && divisor > 0){
                System.out.println("Q");
                while(dividend + divisor <= 0){
                    dividend += divisor;
                    count++;
                }
                count = -count;
                return count;
            }

            return count;
        }
 
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println("climb stares with n = 9" + s.climbStairs(9));
        System.out.println("max profit for list 7,1,5,3,6,4: " + s.maxProfit(new int[]{7,1,5,3,6,4}));
        System.out.println("rob with lsit 1,2,3,1: " + s.rob(new int[]{1,2,3,1}));
        char[][] islands = 
        {
            {'1','1','0','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'}
        };
        System.out.println("num of islands " + s.numIslands(islands));

        //test container with most water: maxArea
        int[] heights = {1,8,6,2,5,4,8,3,7};
        System.out.println("max area: " + s.maxArea(heights));

        //test 3sum
        int[] nums = {-1,0,1,2,-1,-4};
        System.out.println("3sum: " + s.threeSum(nums));


        //test divide
        System.out.println("divide: " + s.divide(-2147483648, -1));


    }
}

