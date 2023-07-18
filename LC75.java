import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LC75 {
    
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        // There are n kids with candies. You are given an integer array candies, where each candies[i] represents the number of candies the ith kid has, and an integer extraCandies, denoting the number of extra candies that you have.
        // Return a boolean array result of length n, where result[i] is true if, after giving the ith kid all the extraCandies, they will have the greatest number of candies among all the kids, or false otherwise.
        // Note that multiple kids can have the greatest number of candies.
        List<Boolean> ret = new ArrayList<>();

        //first strategy:
        //go through candies and find the max
        //go throuigh candies again and compair candy i + extra with max
        //add to ret accordingly
        int max = 0;
        for(int i: candies){
            if(i > max){
                max = i;
            }
        }//we now have greatest in list

        for(int i = 0; i < candies.length; i++){
            int newval = candies[i] + extraCandies;
            if(newval >= max){
                ret.add(true);
            }
            else{
                ret.add(false);
            }
        }

        return ret;
    }


    public boolean increasingTriplet(int[] nums) {
        //Given an integer array nums, return true if there exists a triple of indices (i, j, k) such that i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exists, return false.


        //important note; i j k can be ot of value
        //example 1,2,0,3 should be true

        //strategy:
        //create 2 int variables 2 keep track of 2 lowest nubmers
        //loop through numbers
        //update our first and second variables so that they track the lowest 2 numbers
        //finally if we find a 3rd number that is greater than the 2 numbers we have stroed we have foudn a triplet and can return true
        //else return false
        

        int first_num = Integer.MAX_VALUE;
        int second_num = Integer.MAX_VALUE;

        for(int i: nums){
            if(i <= first_num){
                first_num = i;
            }
            else if (i <= second_num){
                second_num = i;
            }
            else{
                return true;
            }
        }
        return false;
    }


    public void moveZeroes(int[] nums) {
        //Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
        //Note that you must do this in-place without making a copy of the array.

        //STRATEGY: 2 POINTER APPROACH
        //iternate through array and use otehr point to track non 0's index
        int first = 0;

        for(int second = 0; second < nums.length; second++){
            int cur = nums[second];
            if(cur != 0){
                nums[first] = nums[second];
                first++;
            }
        }

        //fill in the zeros

        for(int i = first; i < nums.length; i++){
            nums[i] = 0;
        }
    }



    public int maxOperations(int[] nums, int k) {
        //You are given an integer array nums and an integer k.

        // In one operation, you can pick two numbers from the array whose sum equals k and remove them from the array.

        // Return the maximum number of operations you can perform on the array.


        //strategy:
        //create hashmap containing nums value and occurances then iterate through nums
        //if current nums target value exists in teh hashmap remove them and increase count
        //else add current nums value to hashmap

        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            int complement = k - current;
            if (map.getOrDefault(complement, 0) > 0) {
                // remove complement from the map
                map.put(complement, map.get(complement) - 1);
                count++;
            } else {
                 // add current element in the map
                map.put(current, map.getOrDefault(current, 0) + 1);
            }
        }
        return count;
    }

    public double findMaxAverage(int[] nums, int k) {
        //You are given an integer array nums consisting of n elements, and an integer k.
        //Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value. 
        //Any answer with a calculation error less than 10-5 will be accepted.

        //strategy: sliding window
        //implement a sliding windowa that goes through the list. the sliding window should include 4 elements

        int sum=0;

        for(int i = 0; i < k; i++){
            sum += nums[i];
        }

        double average = (double) sum/k;
        double maxaverage = average;
        //implement sliding window

        for(int i = k; i < nums.length;i++){
            sum += nums[i];
            sum -= nums[i-k];
            average = (double) sum/k;
            if(average > maxaverage){
                maxaverage = average;
            }
        }
        return maxaverage;
    }

    public int maxVowels(String s, int k) {
        //Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.
        // Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.
        //strategy: sliding window
        //use same algo as prebious problem

        int vowelcount = 0;

        //count howp many are in the first k
        for(int i = 0; i < k; i++){
            char cur = s.charAt(i);
            if(cur =='a' || cur =='e' || cur =='i' || cur =='o' || cur =='u'){
                vowelcount++;
            }
        }

        int finalvowelcount = vowelcount;


        //implement sliding window

        for(int i = k; i < s.length(); i++){
            char cur = s.charAt(i);
            char prev = s.charAt(i-k);
            if(cur =='a' || cur =='e' || cur =='i' || cur =='o' || cur =='u'){
                vowelcount++;
            }

            if(prev =='a' || prev =='e' || prev =='i' || prev =='o' || prev =='u'){
                vowelcount--;
            }

            if(vowelcount > finalvowelcount){
                finalvowelcount = vowelcount;
            }
        }

        return finalvowelcount;
    }



    public static void main(String[] args) {

        LC75 test = new LC75();


        // //test method 1: kids with candies
        // int[] candies = {2,3,5,1,3};
        // int extraCandies = 3;
        // List<Boolean> ret = test.kidsWithCandies(candies, extraCandies);
        // System.out.println(ret);


        // //test method 2: increasing triuuples
        // int[] nums = {1,2,3,4,5};
        // boolean ret2 = test.increasingTriplet(nums);
        // System.out.println(ret2);


        // //test method 3: move zeroes
        // int[] nums2 = {0,1,0,3,12};
        // test.moveZeroes(nums2);
        // //print array
        // for(int i: nums2){
        //     System.out.print(i + " ");
        // }

        //test method 4: max operations
        // int[] nums3 = {1,2,3,4};
        // int k = 5;
        // int ret3 = test.maxOperations(nums3, k);
        // System.out.println(ret3);

        //test method 5: max average
        // int[] nums4 = {1,12,-5,-6,50,3};
        // int k = 4;
        // double ret4 = test.findMaxAverage(nums4, k);
        // System.out.println(ret4);

        //test method 6: max vowels
        String s = "abciiidef";
        int k = 3;
        int ret5 = test.maxVowels(s, k);
        System.out.println(ret5);
        


    }
    
}
