import java.util.Arrays;

public class sorting {
    
    
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
        //The overall run time complexity should be O(log (m+n)).
        int[] nums3 = new int[nums1.length + nums2.length];
        System.arraycopy(nums1, 0, nums3, 0, nums1.length);  
        System.arraycopy(nums2, 0, nums3, nums1.length, nums2.length); 
        
        Arrays.sort(nums3);

        //print nums3
        System.out.print("nums3:");
        for(int i = 0; i < nums3.length; i++){
            System.out.print(" " + nums3[i]);
        }
        
        //find the median of nums 3
        int mid = nums3.length / 2;
        double median;
        if(nums3.length % 2 == 0){
            //is even
            median = (nums3[mid] + nums3[mid - 1]) / 2.0;
        }
        else{
            //is odd
            median = nums3[mid];
        }
        return median;
    }

    public static void main(String[] args) {
        //test findMedianSortedArrays
        int[] nums1 = {1,2};
        int[] nums2 = {3,4};
        sorting s = new sorting();
        System.out.println(s.findMedianSortedArrays(nums1, nums2));
        
    }
}
