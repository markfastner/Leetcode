import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Map;

public class Heap {
    public int findKthLargest(int[] nums, int k) {
        //Given an integer array nums and an integer k, return the kth largest element in the array.
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int[] largest = new int[k];
        for(int i = 0; i < k; i++){
            minHeap.add(nums[i]);
        }
        for(int i = k; i < nums.length; i++){
            minHeap.add(nums[i]);
            minHeap.poll();
        }

      
        return minHeap.peek();
    }

    public int[] topKFrequent(int[] nums, int k) {
        //Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        int[] result = new int[k];
        //find the largest value in map add it to result and remove it from map. repeat k times
        for(int i = 0; i < k; i++){
            int max = 0;
            int maxKey = 0;
            for(Map.Entry<Integer, Integer> entry: map.entrySet()){
                if(entry.getValue() > max){
                    max = entry.getValue();
                    maxKey = entry.getKey();
                }
            }
            result[i] = maxKey;
            map.remove(maxKey);
        }
        
        return result;
    }

    public int[] topKFrequent2(int[] nums, int k) {
        //same as topKFrequent but uses a priority queue
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Create a priority queue that prioritizes the most frequent values
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(
                (a, b) -> b.getValue() - a.getValue());

        // Add all elements from the freqMap to the priority queue
        pq.addAll(freqMap.entrySet());

        // Extract the top k most frequent elements from the priority queue
        int result[] = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = (pq.poll().getKey());
        }

        return result;

    }

    public static void main(String[] args) {
        //test findKthLargest
        int[] nums = {3,2,3,1,2,4,5,5,6};
        int k = 4;
        Heap h = new Heap();
        //print heap
        System.out.print("Heap:");
        for(int i = 0; i < nums.length; i++){
            System.out.print(" " + nums[i]);
        }
        System.out.println("\n" + k + "th largest: " + h.findKthLargest(nums, k));

        //test topKFrequent
        int[] nums2 = {1,1,1,2,2,3};
        int n = 2;
        //print nums2
        System.out.print("Array:");
        for(int i = 0; i < nums2.length; i++){
            System.out.print(" " + nums2[i]);
        }
        int[] ret = h.topKFrequent2(nums2, n);
        //pritn ret
        System.out.print("\nTop " + n + " frequent: ");
        for(int i = 0; i < ret.length; i++){
            System.out.print(ret[i] + " ");
        }
    }
}
