class Solution(object):
    #The algorithm takes an integer as input and tries to find the maximum possible number by changing at most one digit from 6 to 9. 
    # It achieves this by converting the integer to a string and iterating through the string, 
    # changing the first occurrence of '6' to '9'. This algorithm makes a locally optimal choice at each stage by 
    # changing the first '6' to '9' that it encounters. The algorithm assumes that changing the first '6' to '9' will 
    # result in the maximum possible number, which is not always true. Therefore, it may not always produce the optimal solution, 
    # but it aims to find a reasonably good solution in a reasonable amount of time.
    def maximum69Number (self, num):
        #You are given a positive integer num consisting only of digits 6 and 9.
        #Return the maximum number you can get by changing at most one digit (6 becomes 9, and 9 becomes 6).
        """
        :type num: int
        :rtype: int
        """
        #convert num to string
        num = str(num)
        #iterate through string
        for i in range(len(num)):
            #if num[i] is 6, replace with 9
            if(num[i] == '6'):
                num = num[:i] + '9' + num[i+1:]
                break
        #convert back to int
        num = int(num)
        return num

    print(maximum69Number(0, 9669))

    def maxNumberOfApples(self, weight):
            #You have some apples and a basket that can carry up to 5000 units of weight.
            #Given an integer array weight where weight[i] is the weight of the ith apple, 
            #return the maximum number of apples you can put in the basket.
            """
            :type weight: List[int]
            :rtype: int
            """
            #sort weight
            weight.sort()
            #initialize basket weight
            basket = 0
            #initialize apple count
            apples = 0
            #iterate through weight
            for i in range(len(weight)):
                #if basket weight + apple weight is less than 5000, add apple weight to basket weight
                if(basket + weight[i] <= 5000):
                    basket += weight[i]
                    apples += 1
                #else, break
                else:
                    break
            return apples
    print(maxNumberOfApples(0, [100,200,150,1000]))

    def maximumUnits(self, boxTypes, truckSize):
        #You are assigned to put some amount of boxes onto one truck. You are given a 2D array boxTypes, where boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi]:
        #numberOfBoxesi is the number of boxes of type i.
        #numberOfUnitsPerBoxi is the number of units in each box of the type i.
        #You are also given an integer truckSize, which is the maximum number of boxes that can be put on the truck. You can choose any boxes to put on the truck as long as the number of boxes does not exceed truckSize.
        #Return the maximum total number of units that can be put on the truck.
        """
        :type boxTypes: List[List[int]]
        :type truckSize: int
        :rtype: int
        """
        #sort boxTypes by number of units per box
        boxTypes.sort(key=lambda x: x[1], reverse=True)
        #initialize total units
        totalUnits = 0
        #iterate through boxTypes
        for i in range(len(boxTypes)):
            #if truckSize is greater than number of boxes, add number of boxes * number of units per box to total units
            if(truckSize >= boxTypes[i][0]):
                totalUnits += boxTypes[i][0] * boxTypes[i][1]
                truckSize -= boxTypes[i][0]
            #else, add truckSize * number of units per box to total units
            else:
                totalUnits += truckSize * boxTypes[i][1]
                break
        return totalUnits
    print(maximumUnits(0, [[1,3],[2,2],[3,1]], 4))

    def minSetSize(self, arr):
        #You are given an integer array arr. You can choose a set of integers and remove all the occurrences of these integers in the array.
        #Return the minimum size of the set so that at least half of the integers of the array are removed.
        """
        :type arr: List[int]
        :rtype: int
        """
        #create hashmap
        hashmap = {}
        #iterate through arr
        for i in range(len(arr)):
            #if arr[i] is in hashmap, increment value
            if(arr[i] in hashmap):
                hashmap[arr[i]] += 1
            #else, add arr[i] to hashmap with value 1
            else:
                hashmap[arr[i]] = 1
        #sort hashmap by value in reverse order
        hashmap = sorted(hashmap.items(), key=lambda x: x[1], reverse=True)
        #initialize count
        count = 0
        #initialize total
        total = 0
        #iterate through hashmap
        for i in range(len(hashmap)):
            #add value to total
            total += hashmap[i][1]
            #increment count
            count += 1
            #if total is greater than or equal to half the length of arr, break
            if(total >= len(arr)/2):
                break
        return count
    

    def wiggleSort(nums):
        for i in range(len(nums)-1):
            if (i & 1) == (nums[i] < nums[i+1]): 
                nums[i], nums[i+1] = nums[i+1], nums[i]
        return nums
    print(wiggleSort([3,5,2,1,6,4]))
    