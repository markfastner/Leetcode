import java.util.ArrayList;
import java.util.PriorityQueue;

class Array {
    public static int removeDuplicates(int[] nums) {
        //Given an integer array nums sorted in non-decreasing order, 
        //remove the duplicates in-place such that each unique element appears only once.
        // The relative order of the elements should be kept the same.
        //convert duplicatenumbers to -99
        int removeCount = 0;
        int cur = nums[0];
        int final_count = nums.length - removeCount;
        for(int i = 1; i < nums.length; i++){
            final_count = nums.length - removeCount;
            if(i > final_count){
                break;
            }
            else if(nums[i] == cur){
                nums[i] = -99;
                moveToEnd(i, nums);
                i--;
                removeCount++;
            }
            else{
                cur = nums[i];
            }
            
        }
        
        return final_count;
        
    }

    public static int removeElement(int[] nums, int val) {
        //Given an integer array nums and an integer val, remove all occurrences of val in nums in-place. 
        //The order of the elements may be changed. Then return the number of elements in nums which are not equal to val.
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

    public static void moveToEnd(int index, int[] list){
    //method that takes in a list and index and moves that index to end of list
    //used in removeDuplicates
        int value = list[index];
        for(int i = index; i < list.length; i++){
            if(i == list.length - 1){
                list[i] = value;
            }
            else{
                list[i] = list[i+1];
            }
        }
    }

    public static int maxProfit(int[] prices) {
        //You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
        //On each day, you may decide to buy and/or sell the stock. 
        //You can only hold at most one share of the stock at any time. 
        //However, you can buy it then immediately sell it on the same day.
        //Find and return the maximum profit you can achieve.
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
    }

    public static void rotate(int[] nums, int k) {
        //Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
        int[] a = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
          a[(i + k) % nums.length] = nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
          nums[i] = a[i];
        }
      }

      public void rotate2(int[] nums, int k) {
        //Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
        //rotate using reverse function
        //space complexity 0(1) as apposed to rotate which is O(n)
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
      }
      public void reverse(int[] nums, int start, int end) {
        while (start < end) {
          int temp = nums[start];
          nums[start] = nums[end];
          nums[end] = temp;
          start++;
          end--;
        }
      }

      public static int singleNumber(int[] nums) {
        //Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
        //You must implement a solution with a linear runtime complexity and use only constant extra space.
        PriorityQueue<Integer> q = new PriorityQueue<Integer>();
        for(int i = 0; i < nums.length; i++){
            if(q.contains(nums[i])){
                q.remove(nums[i]);
            }
               else{
                   q.add(nums[i]);
               }
        }
               return q.peek();
    }

    public static int[] plusOne(int[] digits) {
        //You are given a large integer represented as an integer array digits, where each digits[i] is the ith digit 
        //of the integer. The digits are ordered from most significant to least significant in left-to-right order. 
        //The large integer does not contain any leading 0's.
        //Increment the large integer by one and return the resulting array of digits.

        //thoughtprocess:
        //add 1 to ending digit
        //if ending integer is anything but 9: easy just add 1 to it
        //if ending integer is 9 then:
        //go to the next array taht doesnt end with 9, increment by 1 and change all following integers to 0
        //if no array element exists then create new array 
        int end = digits[digits.length -1];
        if(end != 9){
            digits[digits.length -1]++;
            return digits;
        }
        else{
            if(digits.length == 1){
                int[] newdigits = new int[digits.length + 1];
                newdigits[0] = 1;
                newdigits[1] = 0;
                return newdigits;
            }
            digits[digits.length-1] = 0;
            if(digits.length-2 >=0){
                for(int i = digits.length-2; i >=0; i--){
                    if(digits[i] !=9){
                        digits[i]++;
                        return digits;
                    }
                    else{
                        digits[i] = 0;
                    }
                }
                int[] newdigits = new int[digits.length + 1];
                newdigits[0] = 1;
                for(int i = 1; i < newdigits.length; i++){
                    newdigits[i] = 0;
                }
                return newdigits;
            }   
            }
        
        //return array
        return digits;
    }

    public static int averageValue(int[] nums) {
        //Given an integer array nums of positive integers, return the average value of all even integers that are divisible by 3.
        //loop through array and find all even numbers divisible by 3 and add them to a new array
        ArrayList<Integer> newnums = new ArrayList<Integer>();
        //we use arraylist since wedont know the size and can easily add numbers to it
        for(int i: nums){
            if(i % 2 == 0 && i % 3 == 0){
                newnums.add(i);
            }
        }

        //now we have all the numbers that satisify the given conditions
        //finally we need to find the average of the numbers in the array
        //Note that the average of n elements is the sum of the n elements divided by n and rounded down to the nearest integer.
        int sum = 0;
        for(int i: newnums){
            sum += i;
        }
        //make sure we dont divide by 0
        if(newnums.size() == 0){
            return 0;
        }
        else{
        return sum / newnums.size();
        }
    }

    public static void sortColors(int[] nums) {
            //Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
            //We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
            //You must solve this problem without using the library's sort function.

            //thought process:
            //we will use a counting sort algorithm
            //we will count the number of 0s, 1s, and 2s in the array
            //then we will loop through the array and add the correct number of 0s, 1s, and 2s to the array

            int count0 = 0;
            int count1 = 0;
            int count2 = 0;

            for(int cur: nums){
                if(cur == 0){
                    count0++;
                }
                else if(cur == 1){
                    count1++;
                }
                else{
                    count2++;
                }
            }

            //set array
            int set = 0;
            for(int i = 0; i < nums.length; i++){
                if(i < count0){
                    set = 0;
                }
                if(i >= count0 && i < count0 + count1){
                    set = 1;
                }
                if(i >= count0 + count1){
                    set = 2;
                }
                nums[i] = set;
            }
        }
    





    public static void main(String[] args) {

        //test for removeDuplicates
        System.out.println("");
        int[] nums = new int[]{1,1,2};
        System.out.print("Before removing duplicates: ");
        for(int i = 0; i < nums.length; i++){
            System.out.print(nums[i] + ", ");
        }
        removeDuplicates(nums);
        System.out.print("\nAfter removing duplicates: ");
        for(int i = 0; i < nums.length; i++){
            System.out.print(nums[i] + ", ");
        }

        //test for maxProfit
        int[] prices = new int[]{7,1,5,3,6,4};
        System.out.print("\n\nprices: ");
        for(int i = 0; i < prices.length; i++){
            System.out.print(prices[i] + ", ");
        }
        System.out.println("\nmax profits: " + maxProfit(prices));

        //test for rotate
        int[] nums2 = new int[]{1,2,3,4,5,6,7};
        System.out.print("\nBefore rotating: ");
        for(int i = 0; i < nums2.length; i++){
            System.out.print(nums2[i] + ", ");
        }
        rotate(nums2, 3);
        System.out.print("\nAfter rotating: ");
        for(int i = 0; i < nums2.length; i++){
            System.out.print(nums2[i] + ", ");
        }

        //test for single number
        int[] nums3 = new int[]{4,1,2,1,2};
        System.out.print("\n\nnums3: ");
        for(int i = 0; i < nums3.length; i++){
            System.out.print(nums3[i] + ", ");
        }
        System.out.println("\nsingle number: " + singleNumber(nums3));

        //test for plusOne
        int[] nums4 = new int[]{9,9,9,9};
        System.out.print("\nnums4: ");
        for(int i = 0; i < nums4.length; i++){
            System.out.print(nums4[i] + ", ");
        }
        System.out.print("\nplusOne: ");
        int[] nums4plusOne = plusOne(nums4);
        for(int i = 0; i < nums4plusOne.length; i++){
            System.out.print(nums4plusOne[i] + ", ");
        }
        System.out.println();

        //test for averageValue
        int[] nums5 = new int[]{1,2,3,4,5,6,7,8,9,12};
        System.out.print("\nnums5: ");
        for(int i = 0; i < nums5.length; i++){
            System.out.print(nums5[i] + ", ");
        }
        System.out.println("\naverageValue: " + averageValue(nums5));

        //test removeelement
        int[] nums6 = new int[]{3,2,2,3};
        System.out.print("\nnums6: ");
        for(int i = 0; i < nums6.length; i++){
            System.out.print(nums6[i] + ", ");
        }

        System.out.println("\nremoveElement: " + removeElement(nums6, 3));
        System.out.print("nums6: ");
        for(int i = 0; i < nums6.length; i++){
            System.out.print(nums6[i] + ", ");
        }
        
        //test for sortColors
        int[] nums7 = new int[]{2,0,2,1,1,0};
        System.out.print("\n\nnums7: ");
        for(int i = 0; i < nums7.length; i++){
            System.out.print(nums7[i] + ", ");
        }
        sortColors(nums7);
        System.out.print("\nsortColors: ");
        for(int i = 0; i < nums7.length; i++){
            System.out.print(nums7[i] + ", ");
        }
    }
}