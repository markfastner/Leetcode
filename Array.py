class Solution(object):
    nums = [0,0,1,1,1,2,2,3,3,4]
    nums2 = [1,1,2,2,3,3,4,5,5]
    nums3 = [9,9,9]
   
    def removeDuplicates(self, nums):
        #remove duplicates from sorted array
        """
        :type nums: List[int]
        :rtype: int
        """
        cur = 0
        prev = nums[0] - 1
        for i in nums:
            if(i != prev):
                nums[cur] = i
                cur+=1
            prev = i
            
        for i in range(cur, len(nums)):
            nums.pop()
            
    def maxProfit(self, prices):
    #  You are given an integer array prices where prices[i] is the price of a given stock on the ith day.

    # On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However, you can buy it then immediately sell it on the same day.

    # Find and return the maximum profit you can achieve.
        """
        :type prices: List[int]
        :rtype: int
        """
        maxProfit = 0
        for i in range(1, len(prices)):
            if(prices[i] > prices[i-1]):
                maxProfit += prices[i] - prices[i-1]
        return maxProfit
    
    def rotate(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: None Do not return anything, modify nums in-place instead.

        Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
        """
        
        nums = nums[len(nums)-k:] + nums[:len(nums)-k]
        #print(nums)
        return nums
    
    def singleNumber(self, nums):
        """
        :type nums: List[int]
        :rtype: int

        Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.

        You must implement a solution with a linear runtime complexity and use only constant extra space.
        """
    
        # for i in range(0, len(nums)):
        #     print(nums[i+1:])
        #     if nums[i] not in nums[i:]:
        #         return nums[i]
        # return -1
        #return 2 * sum(set(nums)) - sum(nums)
        #not constant space
        a = 0
        for i in nums:
            a ^= i
        return a
    
    def plusOne(self, digits):
        """You are given a large integer represented as an integer array digits, where each digits[i] is the ith digit of the integer. The digits are ordered from most significant to least significant in left-to-right order. The large integer does not contain any leading 0's.

        Increment the large integer by one and return the resulting array of digits."""
        n = len(digits)

        # move along the input array starting from the end
        for i in range(n):
            idx = n - 1 - i
            # set all the nines at the end of array to zeros
            if digits[idx] == 9:
                digits[idx] = 0
            # here we have the rightmost not-nine
            else:
                # increase this rightmost not-nine by 1
                digits[idx] += 1
                # and the job is done
                return digits

        # we're here because all the digits are nines
        return [1] + digits

    def sortedSquares(self, nums):
        #Given an integer array nums sorted in non-decreasing order, 
        # return an array of the squares of each number sorted in non-decreasing order.
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        for i in range(len(nums)):
            nums[i] = nums[i] * nums[i]
        nums.sort()
        return nums

s = Solution()
print("original list:")
#print(s.nums)
#s.removeDuplicates(s.nums)
#print("list after removing duplicates:")
#print(s.nums)
#print("max profit:")
#print(s.maxProfit(s.nums))
#print("list after rotating:")
#print(s.rotate(s.nums, 3))
# print("original list:")
# print(s.nums2)
# print("single number:")
# print(s.singleNumber(s.nums2))
print(s.nums3)
print(s.plusOne(s.nums3))
nums4 = [-4,-1,0,3,10]
print("sorted squares", s.sortedSquares(nums4))
    