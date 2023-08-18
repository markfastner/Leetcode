import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

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

    public int longestOnes(int[] nums, int k) {
        //Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.

        //strategy: sliding window
        //inc rease sliding window to right until we have reaching k amount of 0s then increment left

        int left = 0;
        int longest = 0;
        int zerotracker = 0;
        int sizetracker= 0;
        int right;
        for(right = 0; right < nums.length; right++){
            int cur = nums[right];
            if(cur == 0){
                zerotracker++;
            }
            if(zerotracker > k){
                if(nums[left] == 0){
                    zerotracker--;
                }
                left++;
                sizetracker--;
            }
            else{
                sizetracker++;
            }
            if(sizetracker > longest){
                longest = sizetracker;
            }
            
        }
        return right - left;
    }

    public int largestAltitude(int[] gain) {
        //There is a biker going on a road trip. The road trip consists of n + 1 points at different altitudes. The biker starts his trip on point 0 with altitude equal 0.
        //You are given an integer array gain of length n where gain[i] is the net gain in altitude between points i​​​​​​ and i + 1 for all (0 <= i < n). Return the highest altitude of a point.
        //strategy: one loop
        //loop through thge array and perform addition on each i. 
        //have4 a int value storing largest. update largest through iteration
        int highest = 0;
        int currentelevation = 0;
        for(int i: gain){
            currentelevation += i; 
            if(currentelevation > highest){
                highest = currentelevation;
            }
        }
        return highest;
    }

    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        //Given two 0-indexed integer arrays nums1 and nums2, return a list answer of size 2 where:
        //answer[0] is a list of all distinct integers in nums1 which are not present in nums2.
        //answer[1] is a list of all distinct integers in nums2 which are not present in nums1.
        
        //strategy: hashmap
        //create hashmap for nums1
        //create hashmap for nums2
        //iterate through nums1 and check if value exists in nums2 hashmap
        //if it does not add to list
        //iterate through nums2 and check if value exists in nums1 hashmap
        //if it does not add to list
        //return list

        HashMap<Integer, Integer> map1 = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<List<Integer>> ret = new ArrayList<>();

        for(int i: nums1){
            map1.put(i, 1);
        }

        for(int i: nums2){
            map2.put(i, 1);
        }

        for(int i: nums1){
            if(!map2.containsKey(i) && list1.contains(i) == false){
                list1.add(i);
            }
        }

        for(int i: nums2){
            if(!map1.containsKey(i) && list2.contains(i) == false){
                list2.add(i);
            }
        }
       
        ret.add(list1);
        ret.add(list2);
        return ret;

    }

    public boolean uniqueOccurrences(int[] arr) {
        //Given an array of integers arr, return true if the number of occurrences of each value in the array is unique or false otherwise.
        //createa a hashmap and fill it keeping track of number of occurances in the array

        HashMap<Integer, Integer> occurances = new HashMap<>();
        for(int i: arr){
            if(!occurances.containsKey(i)){
                occurances.put(i,1);
            }
            else{
                occurances.put(i,occurances.get(i) + 1);
            }
        }
        ArrayList<Integer> newvals = new ArrayList<>();
        for(int i: occurances.values()){
            if(newvals.contains(i)){
                return false;
            }
            else{
                newvals.add(i);
            }
        }
        return true;
    }

    public boolean closeStrings(String word1, String word2) {
        /*
         * Two strings are considered close if you can attain one from the other using the following operations:
            Operation 1: Swap any two existing characters.
            For example, abcde -> aecdb
            Operation 2: Transform every occurrence of one existing character into another existing character, and do the same with the other character.
            For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)
            You can use the operations on either string as many times as necessary.

            Given two strings, word1 and word2, return true if word1 and word2 are close, and false otherwise.
         */

         //opservation:
         //to solve this problem we make 2 important opservations:
         //1. in order for 2 strings to be close each character needs to exist in the other string
         //2. in order for 2 strings to be clsoe each characters occurance needs to match anothers characters occurance
         //if both conditions are true then hte strings are clsoe else they are not
         
         //strategy: hashmap
         //crteate two hashmaps for each string and fill in each character and the amount of times it appears
         //use the 2 hashmaps to test our 2 opservations

         //create pur 2 hashmaps
         HashMap<Character, Integer> word1map = new HashMap<>();
         HashMap<Character, Integer> word2map = new HashMap<>();

         //fill our two hashmaps
         for(char i: word1.toCharArray()){
            word1map.put(i, word1map.getOrDefault(i, 0) + 1);
         }

         for(char i: word2.toCharArray()){
            word2map.put(i, word2map.getOrDefault(i, 0) + 1);
         }
         //note to self: dont knwo if hashmap starts with freq 0 or 1

        //use hashmap to test both our opservations

        //first test to see if each character exists in both strings
        for(Character i: word1map.keySet()){
            if(!word2map.containsKey(i)){
                return false;
            }
        }

        //second test: make sure each frequency exists in both hashamps
        //we can do this by creating 2 lists that contain the frequencies of each cahracter in the hashmap
        //and then sort the lsits and check if they are the same
        List<Integer> word1FrequencyList = new ArrayList<>(word1map.values());
        List<Integer> word2FrequencyList = new ArrayList<>(word2map.values());
        Collections.sort(word1FrequencyList);
        Collections.sort(word2FrequencyList);
        
        if(!word1FrequencyList.equals(word2FrequencyList)){
            return false;
        }


        //if we get here both our conditions are validated and we know that the two strings are clsoe
        return true;

    }

    public int equalPairs(int[][] grid) {
        /* 
         * Given a 0-indexed n x n integer matrix grid, return the number of pairs (ri, cj) such that row ri and column cj are equal.
            A row and column pair is considered equal if they contain the same elements in the same order (i.e., an equal array).
         */

         //strategy:hashmap
         //add each row to hashmap
         //create columns in list and check hashmap if it exists
         HashMap<List<Integer>, Integer> rowmap = new HashMap<>();
         int n = grid.length;
         int equalpairs = 0;
         //loop through grid and add rows to hashmap
         for(int i = 0; i < n; i++){
            List<Integer> row = new ArrayList<>();
            for(int ii = 0; ii < n; ii++){
                row.add(grid[i][ii]);
            }
            rowmap.put(row, rowmap.getOrDefault(row, 0) + 1);
         }
         //loop through grid and add columns to hashmap
         for(int i = 0; i < n; i++){
            List<Integer> column = new ArrayList<>();
            for(int ii = 0; ii < n; ii++){
                column.add(grid[ii][i]);
            }
            if(rowmap.containsKey(column)){
                equalpairs += rowmap.get(column);
            }
         }

         return equalpairs;
    }

    public String removeStars(String s) {
        /*
         * You are given a string s, which contains stars *.

            In one operation, you can:

            Choose a star in s.
            Remove the closest non-star character to its left, as well as remove the star itself.
            Return the string after all stars have been removed.

            Note:

            The input will be generated such that the operation is always possible.
            It can be shown that the resulting string will always be unique.
         */

            //strategy: stack
            //we want to keep a stack taht tracks the left most value
            //as we iterate through the string we add each non start value from the stack
            //when we reach a start we pop the top value and remove it from the string along with the star

            Stack<Character> mystack = new Stack<>();
            
            for(Character i : s.toCharArray()){
                if(i != '*'){
                    mystack.push(i);
                }
                else{
                    mystack.pop();
                }
            }

            StringBuilder sb = new StringBuilder();
            while(!mystack.isEmpty()){
                sb.append(mystack.pop());
            }

            return sb.reverse().toString();

            
    }

    public String decodeString(String s) {
        /*
         * Given an encoded string, return its decoded string.

            The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

            You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc. Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].

            The test cases are generated so that the length of the output will never exceed 105.
         */

         //strategy: stack
         //we iterate through the s
         //when we get to a [ we pop everything in the [] to the stack
         //

         Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ']') {
                List<Character> decodedString = new ArrayList<>();
                // get the encoded string
                while (stack.peek() != '[') {
                    decodedString.add(stack.pop());
                }
                // pop [ from the stack
                stack.pop();
                int base = 1;
                int k = 0;
                // get the number k
                while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    k = k + (stack.pop() - '0') * base;
                    base *= 10;
                }
                // decode k[decodedString], by pushing decodedString k times into stack
                while (k != 0) {
                    for (int j = decodedString.size() - 1; j >= 0; j--) {
                        stack.push(decodedString.get(j));
                    }
                    k--;
                }
            }
            // push the current character to stack
            else {
                stack.push(s.charAt(i));
            }
        }      
        // get the result from stack
        char[] result = new char[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return new String(result);
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
        // String s = "abciiidef";
        // int k = 3;
        // int ret5 = test.maxVowels(s, k);
        // System.out.println(ret5);
        
        //test method 7: longest ones
        // int[] nums6 = {1,1,1,0,0,0,1,1,1,1,0};
        // int k = 2;
        // int ret6 = test.longestOnes(nums6, k);
        // System.out.println(ret6);

        //test method 8: largest altitude
        // int[] gain = {-4,-3,-2,-1,4,3,2};
        // int ret7 = test.largestAltitude(gain);
        // System.out.println(ret7);

        //test method 9: find difference
        // int[] nums1 = {1,2,3};
        // int[] nums2 = {1,4,5};
        // List<List<Integer>> ret8 = test.findDifference(nums1, nums2);
        // System.out.println(ret8);

        //test method 10: unique occurances
        // int[] arr = {1,2,2,1,1,3};
        // boolean ret9 = test.uniqueOccurrences(arr);
        // System.out.println(ret9);

        //test method 11: close strings
        // String word1 = "cabbba";
        // String word2 = "abbbccc";
        // boolean ret10 = test.closeStrings(word1, word2);
        // System.out.println(ret10);

        //test method 12: equal pairs
        // int[][] grid = {{1,2,3},{3,2,1},{2,1,3}};
        // int ret11 = test.equalPairs(grid);
        // System.out.println(ret11);

        //test method 13: remove stars
        String s = "abc*de";
        String ret12 = test.removeStars(s);
        System.out.println(ret12);



    }
    
}
