public class JumpGame {

    public int jump(int[] nums) {
//     You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0]
// Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at nums[i], you can jump to any nums[i + j] where:

// 0 <= j <= nums[i] and
// i + j < n
// Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can reach nums[n - 1].



//psuedo code

//loop through nums{
//start at index 0
//find the max jump
//to find max jump loop from the current index to the futhest number we can jump to
//for each index the distance that we would go if we jump there would be the index + the value at that index
//choose the index with the highest distance
//skip to that index
//count how many jumps
//}

int jumpCount = 0;
    int i = 0;
    while (i < nums.length - 1) {
        // find the max jump
        int maxJump = 0;
        int maxJumpIndex = i;
        //check to see if we can jump to the end
        if (i + nums[i] >= nums.length - 1) {
            jumpCount++;
            break;
        }
        for (int j = i + 1; j < nums.length && j <= i + nums[i]; j++) {
            int jump = j + nums[j];
            if (jump > maxJump) {
                maxJump = jump;
                maxJumpIndex = j;
            }
        }
        i = maxJumpIndex;
        jumpCount++;
    }
    return jumpCount;
    }

    public boolean canReach(int[] arr, int start) {
        //Given an array of non-negative integers arr, you are initially positioned at start index of the array. 
       // When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.

       //what is happening here
         //we are checking to see if we can reach 0
         //we are going to start at the start index
         //we are going to check to see if the value at that index is 0
            //if it is we return true
            //if it is not we are going to change the value at that index to negative so we know we have been there
            //we then use recursion to see if we can reach 0 by going to the index to the right or left of the current index

       if (start >= 0 && start < arr.length && arr[start] >= 0) {
        if (arr[start] == 0) {
            return true;
        }
        arr[start] = -arr[start];
        return canReach(arr, start + arr[start]) || canReach(arr, start - arr[start]);
    }
    return false;
}
    
    public static void main(String[] args) {
        //test jumpgame
        int[] nums = {2,3,1,1,4};
        JumpGame j = new JumpGame();
        System.out.println(j.jump(nums));

        //test canReach
        int[] arr = {4,2,3,0,3,1,2};
        int start = 5;
        System.out.println(j.canReach(arr, start));
    }
}
