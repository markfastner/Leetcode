class Solution(object):
# Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.

# Assume the environment does not allow you to store 64-bit integers (signed or unsigned).

    def reverse(self, x):
        """
        :type x: int
        :rtype: int
        """
        #check if x falls inside 32 bit range
        if -2147483648 <= x <= 1534236468:
            #check if x is negative
            if x < 0:
                reverse = str(x)[1:][::-1]
                return int(reverse) * -1
            else:
                reverse = str(x)[::-1]
                return int(reverse)
        else:
            return 0

#test reverse
print(Solution().reverse(-123))
