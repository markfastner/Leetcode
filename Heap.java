import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Heap {
    public int findKthLargest(int[] nums, int k) {
        //Given an integer array nums and an integer k, return the kth largest element in the array.
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
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

    public int minMeetingRooms(int[][] intervals) {
        //Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.
        // Check for the base case. If there are no intervals, return 0
        if (intervals.length == 0) {
          return 0;
        }
    
        // Min heap
        PriorityQueue<Integer> allocator =
            new PriorityQueue<Integer>(
                intervals.length,
                new Comparator<Integer>() {
                  public int compare(Integer a, Integer b) {
                    return a - b;
                  }
                });
    
        // Sort the intervals by start time
        Arrays.sort(
            intervals,
            new Comparator<int[]>() {
              public int compare(final int[] a, final int[] b) {
                return a[0] - b[0];
              }
            });
    
        // Add the first meeting
        allocator.add(intervals[0][1]);
    
        // Iterate over remaining intervals
        for (int i = 1; i < intervals.length; i++) {
    
          // If the room due to free up the earliest is free, assign that room to this meeting.
          if (intervals[i][0] >= allocator.peek()) {
            allocator.poll();
          }
    
          // If a new room is to be assigned, then also we add to the heap,
          // If an old room is allocated, then also we have to add to the heap with updated end time.
          allocator.add(intervals[i][1]);
        }
    
        // The size of the heap tells us the minimum rooms required for all the meetings.
        return allocator.size();
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

        //test minMeetingRooms
        int[][] intervals = {{0,30},{5,10},{15,20}};
        System.out.println("\nMin meeting rooms: " + h.minMeetingRooms(intervals));
        
    }
}
