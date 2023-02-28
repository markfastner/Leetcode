# Definition for singly-linked list.
class ListNode(object):
     def __init__(self, x):
         self.val = x
         self.next = None

class Solution(object):
     l1 = ListNode(1)
     l2 = ListNode(2) 
     l3 = ListNode(3)
     l1.next = l2
     l2.next = l3

     def deleteNode(self, node):
        """
        :type node: ListNode
        :rtype: void Do not return anything, modify node in-place instead.
        """
        nextNode = node.next

        node.val = nextNode.val

        node.next = nextNode.next

        nextNode.next = None

        del(nextNode)

     def reverseList(self, head):  # Iterative
        #Given the head of a singly linked list, reverse the list, and return the reversed list.
        prev, curr = None, head
        while curr:
            curr.next, prev, curr = prev, curr, curr.next
        return prev
     def printLinkedList(self, head):
        #print list given head
        while head:
            print(head.val)
            head = head.next

     
        
#print linkedlist
s = Solution()
print("Linked List:")
s.printLinkedList(s.l1)

# s.deleteNode(s.l2)
s.reverseList(s.l1)
print("Linked List after:")
s.printLinkedList(s.l3)



     
