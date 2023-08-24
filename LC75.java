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

    public ListNode deleteMiddle(ListNode head) {
        //You are given the head of a linked list. Delete the middle node, and return the head of the modified linked list.
        
        //strategy: 2 poitner approach linked list
        // iterate through linked list with fast and slow pointer- fast moves 2 slow moves 1
        //when fast poitner gets to end slow point is at teh node before the one we wnat to delete
        //delete node and have previous node point to the next one

        if (head.next == null)
            return null;
        ListNode slow = head;
        ListNode fast = head.next.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        slow.next = slow.next.next;

        return head;
    }


    public ListNode oddEvenList(ListNode head) {
        /*
         * Given the head of a singly linked list, group all the nodes with odd indices together 
         * followed by the nodes with even indices, and return the reordered list.
         * You must solve the problem in O(1) extra space complexity and O(n) time complexity.
         */

         //strategy:
         //since it needs to be 0(n) memory and we cant allocate aditional memory we need to solve problem with one iteration and withuot addition data structure
         //to do that lets iterate through and if is odd placement link to next odd(next next) and link weven with next even
         //eventaully the next next will be null(in which case we need to find if we are on an odd or even n)
         //if its even  and next next is null point to first even
         //if its odd and next next is null keep it pointing to null
         //

         if(head == null) return null;

         ListNode odd = head;
         ListNode even = head.next;
         ListNode evenHead = even;
         while(even != null && even.next != null){
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
         }

         odd.next = evenHead;

         return head;

    }

    public int pairSum(ListNode head) {
        /*
         * In a linked list of size n, where n is even, the ith node (0-indexed) of the linked list is known as the twin of the (n-1-i)th node, if 0 <= i <= (n / 2) - 1.

            For example, if n = 4, then node 0 is the twin of node 3, and node 1 is the twin of node 2. These are the only nodes with twins for n = 4.
            The twin sum is defined as the sum of a node and its twin.

            Given the head of a linked list with even length, return the maximum twin sum of the linked list.
         */

         //strategy:use a stack
         //iterate through the linked list with two poitners(fast moves 2 slow moves 1)
         //add each value for the slow pointer to stack]
         //when we get to  past half half way create a new value = stack.pop + next val
         //find greates parisum
         if(head == null) return 0;
         Stack<Integer> values = new Stack<>();
         int greatestPairSum = 0;
         ListNode slow = head, fast = head.next;

         while(fast != null){
            values.add(slow.val);
            //move fast by 2 slow by 1
            if(fast.next!=null)
            {
                fast = fast.next.next;
                slow= slow.next;
            }
            else{
                fast = fast.next;
            }
         }
         //slow should be at n/2 -1\
         int pairsum = 0;
         while(slow.next!=null){
            slow = slow.next;
            pairsum = slow.val + values.pop();
            if(pairsum > greatestPairSum){
                greatestPairSum = pairsum;
            }
         }
         return greatestPairSum;
    }

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        /*
         * Consider all the leaves of a binary tree, from left to right order, the values of those leaves form a leaf value sequence.
            Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.
         */
        //strategy: dfs
        //create two lists and add the leaf values to each list
        //compare lists and return

        //note definition of leaf: leaf is node that has no children

        ArrayList<Integer> leafvaluesequence1 = new ArrayList<>();
        ArrayList<Integer> leafvaluesequence2 = new ArrayList<>();

        //dfs first tree and add leaf values 
        leafValueSequencedfs(root1, leafvaluesequence1);
        //dfs second tree and add leaf values 
        leafValueSequencedfs(root2, leafvaluesequence2);

        return leafvaluesequence1.equals(leafvaluesequence2);
    }

    //depth first search to add leaves to leafvaluesequewnce
    public void leafValueSequencedfs(TreeNode node, ArrayList<Integer> leafvalues){
        if(node == null){
            return;
        }
        if(node.left == null && node.right == null){
            leafvalues.add(node.val);
            return;
        }
        leafValueSequencedfs(node.left, leafvalues);
        leafValueSequencedfs(node.right, leafvalues);


    }

    private int goodNodesCount = 0;
    public int goodNodes(TreeNode root) {
        /*
         * Given a binary tree root, a node X in the tree is named good if in the path from root to X there are no nodes with a value greater than X.
           Return the number of good nodes in the binary tree.
         */

         //strategy: dfs
        goodnodesdfs(root, Integer.MIN_VALUE);
        return goodNodesCount;
    }

    public void goodnodesdfs(TreeNode node, int greatest){
        if(node.val >= greatest){
            goodNodesCount++;
        }

        if(node.left !=null){
            goodnodesdfs(node.left, Math.max(greatest,node.val));
        }
        if(node.right !=null){
            goodnodesdfs(node.right, Math.max(greatest,node.val));
        }

    }

    int count = 0;
    int k;
    HashMap<Long, Integer> h = new HashMap();
    
    public void preorder(TreeNode node, long currSum) {
        if (node == null)
            return;
        
        // current prefix sum
        currSum += node.val;

        // here is the sum we're looking for
        if (currSum == k)
            count++;
        
        // number of times the curr_sum − k has occured already, 
        // determines the number of times a path with sum k 
        // has occured upto the current node
        count += h.getOrDefault(currSum - k, 0);
        
        // add the current sum into hashmap
        // to use it during the child nodes processing
        h.put(currSum, h.getOrDefault(currSum, 0) + 1);

        // process left subtree
        preorder(node.left, currSum);
        // process right subtree
        preorder(node.right, currSum);

        // remove the current sum from the hashmap
        // in order not to use it during 
        // the parallel subtree processing
        h.put(currSum, h.get(currSum) - 1);
    }    
            
    public int pathSum(TreeNode root, int sum) {
        k = sum;
        preorder(root, 0L);
        return count;
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
        // String s = "abc*de";
        // String ret12 = test.removeStars(s);
        // System.out.println(ret12);


        //test method 14: decode string
        // String s = "3[a]2[bc]";
        // String ret13 = test.decodeString(s);
        // System.out.println(ret13);

        //test method 15: delete middle
        // ListNode head = new ListNode(1);
        // head.next = new ListNode(2);
        // head.next.next = new ListNode(3);
        // head.next.next.next = new ListNode(4);
        // head.next.next.next.next = new ListNode(5);
        // ListNode ret14 = test.deleteMiddle(head);
        // while(ret14 != null){
        //     System.out.println(ret14.val);   
        //     ret14 = ret14.next;
        // }

        //test method 16: odd even list
        // ListNode head = new ListNode(1);
        // head.next = new ListNode(2);
        // head.next.next = new ListNode(3);
        // head.next.next.next = new ListNode(4);
        // head.next.next.next.next = new ListNode(5);
        // ListNode ret15 = test.oddEvenList(head);
        // while(ret15 != null){
        //     System.out.println(ret15.val);   
        //     ret15 = ret15.next;
        // }

        //test method 17: pair sum

        // ListNode head = new ListNode(1);
        // head.next = new ListNode(2);
        // head.next.next = new ListNode(3);
        // head.next.next.next = new ListNode(4);
        // int ret16 = test.pairSum(head);
        // System.out.println(ret16);
        
        //test method 18: leaf similar
        // TreeNode root1 = new TreeNode(3);
        // root1.left = new TreeNode(5);
        // root1.right = new TreeNode(1);
        // root1.left.left = new TreeNode(6);
        // root1.left.right = new TreeNode(2);  
        // root1.left.right.left = new TreeNode(7);
        // root1.left.right.right = new TreeNode(4);
        // root1.right.left = new TreeNode(9);
        // root1.right.right = new TreeNode(8);
        
        // TreeNode root2 = new TreeNode(3);
        // root2.left = new TreeNode(5);
        // root2.right = new TreeNode(1);
        // root2.left.left = new TreeNode(6);
        // root2.left.right = new TreeNode(7);
        // root2.right.left = new TreeNode(4);
        // root2.right.right = new TreeNode(2);
        // root2.right.right.left = new TreeNode(9);
        // root2.right.right.right = new TreeNode(8);

        // boolean ret17 = test.leafSimilar(root1, root2);
        // System.out.println(ret17);


        //test method 19: count good nodes
        // TreeNode root1 = new TreeNode(3);
        // root1.left = new TreeNode(1);
        // root1.right = new TreeNode(4);
        // root1.left.left = new TreeNode(3);
        // root1.right.left = new TreeNode(1);
        // root1.right.right = new TreeNode(5);
        
        // System.out.println(test.goodNodes(root1));


        //test method 20: path sum
        TreeNode root1 = new TreeNode(10);
        root1.left = new TreeNode(5);
        root1.right = new TreeNode(-3);
        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(2);
        root1.right.right = new TreeNode(11);
        root1.left.left.left = new TreeNode(3);
        root1.left.left.right = new TreeNode(-2);
        root1.left.right.right = new TreeNode(1);
        System.out.println(test.pathSum(root1, 8));




    }
    
}
