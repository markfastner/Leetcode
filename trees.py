# Definition for a binary tree node.
import math


class TreeNode(object):
     def __init__(self, val=0, left=None, right=None):
         self.val = val
         self.left = left
         self.right = right


class Solution(object):
    TreeNode5 = TreeNode(6, None, None)
    TreeNode4 = TreeNode(3, None, None)
    TreeNode3 = TreeNode(3, TreeNode4, TreeNode5)
    TreeNode2 = TreeNode(1, None, None)
    TreeNode1 = TreeNode(2, TreeNode2, TreeNode3)
    
    def maxDepth(self, root):
        #Given the root of a binary tree, return its maximum depth.

        if root is None:
            return 0
        else:
            left = self.maxDepth(root.left)
            right = self.maxDepth(root.right)
            return max(left, right) + 1
        """
        :type root: TreeNode
        :rtype: int
        """ 
        
    def isValidBST(self, root):
        #Given the root of a binary tree, determine if it is a valid binary search tree (BST).
        """
        :type root: TreeNode
        :rtype: bool 
        """
        def validate(node, low=-math.inf, high=math.inf):
            # Empty trees are valid BSTs.
            if not node:
                return True
            # The current node's value must be between low and high.
            if node.val <= low or node.val >= high:
                return False

            # The left and right subtree must also be valid.
            return (validate(node.right, node.val, high) and
                   validate(node.left, low, node.val))

        return validate(root)
        
    
S= Solution()
#print(S.maxDepth(S.TreeNode1))
print(S.isValidBST(S.TreeNode1))