class Solution(object):
    def isPalindrome(x):
        """
        :type x: int
        :rtype: bool
        """
        #Given an integer x, return true if x is a palindrome and false otherwise.
        if(x < 0):
            return False
        reverse = str(x)[::-1]
        if(x == int(reverse)):
            return True
        else:
            return False 


    #print(isPalindrome(121))    



    def romanToInt(s):
        """
        :type s: str
        :rtype: int
        """
        #Given a roman numeral, convert it to an integer.
            #Symbol       Value
            #I             1
            #X             10
            #L             50
            #C             100
            #D             500
            #M             1000
            #I can be placed before V (5) and X (10) to make 4 and 9. 
            #X can be placed before L (50) and C (100) to make 40 and 90. 
            #C can be placed before D (500) and M (1000) to make 400 and 900.

        #strategy: create return integer. 
        # #read string from lefet to right. add correct value based on character
        #check to special cases
        sum = 0;
        i=0
        while i < len(s):
            if s[i] == 'M':
                sum += 1000
            elif s[i] == 'D':
                sum += 500
            elif s[i] == 'C':
                if i != len(s)-1:
                    if s[i+1] == 'D':
                        sum+= 400
                        i+=1
                    elif s[i+1] == 'M':
                        sum+=900
                        i+=1
                    else:
                        sum += 100
                else:
                        sum += 100
            elif s[i] == 'L':
                sum += 50
            elif s[i] == 'X':
                if i != len(s)-1:
                    if s[i+1] == 'L':
                        sum+= 40
                        i+=1
                    elif s[i+1] == 'C':
                        sum+=90
                        i+=1
                    else:
                        sum += 10
                else:
                        sum += 10
            elif s[i] == 'V':
                sum += 5
            elif s[i] == 'I':
                if i != len(s)-1:
                    if s[i+1] == 'V':
                        sum+= 4
                        i+=1
                    elif s[i+1] == 'X':
                        sum+=9
                        i+=1
                    else:
                        sum += 1
                else:
                        sum += 1
            i+=1
        return sum
    print(romanToInt("III"))
            


    def intToRoman(num):
        """
        :type num: int
        :rtype: str
        """
        #Given an integer, convert it to a roman numeral.

        values = {
            "I": 1,
            "V": 5,
            "X": 10,
            "L": 50,
            "C": 100,
            "D": 500,
            "M": 1000,
            "IV": 4,
            "IX": 9,
            "XL": 40, 
            "XC": 90,
            "CD": 400,
            "CM": 900
        }

        #strategy:
        #for each symbol (starting at largest going down)
        #add as many as possible to ret meaning while num >= value 
        #add to ret and decrease from num
        ret = ""
        for symbol, value in sorted(values.items(), key=lambda x: x[1], reverse=True):
            while num >= value:
                ret += symbol
                num -= value

        return ret
    print(intToRoman(1994))




    
