import java.util.Arrays;

public class sort {

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void wiggleSort(int[] nums) {
        //Given an integer array nums, reorder it such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
        //You may assume the input array always has a valid answer.

        //stratagey:
        //sort the array
        //swap every other element starting from index 1
        Arrays.sort(nums);
        for (int i = 1; i < nums.length - 1; i += 2) {
            swap(nums, i, i + 1);
        }
    }


    public static void main(String[] args) {
        //test wiggleSort
        int[] nums = {3, 5, 2, 1, 6, 4};
        sort s = new sort();
        s.wiggleSort(nums);
        System.out.println(Arrays.toString(nums));
    }
    
}
