
public class Trees {
    public int maxDepth(TreeNode root) {
        //Given the root of a binary tree, return its maximum depth.
        //A binary tree's maximum depth is the number of nodes along the longest path from the root 
        //node down to the farthest leaf node.
        if(root == null){
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }

    public void printTree(TreeNode root){
        if(root == null){
            return;
        }
        System.out.print("\n value: " + root.val );
        printTree(root.left);
        printTree(root.right);
    }

    public boolean isValidBST(TreeNode root) {
        //Given the root of a binary tree, determine if it is a valid binary search tree (BST).

        //A valid BST is defined as follows:
        //The left subtree of a node contains only nodes with keys less than the node's key.
        //The right subtree of a node contains only nodes with keys greater than the node's key.
        //Both the left and right subtrees must also be binary search trees.

        return validate(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
     public boolean validate(TreeNode n, int min, int max){
        if(n == null){
            return true;
        }
        if(n.val <= min || n.val >= max){
            return false;
        }
        return validate(n.left, min, n.val) && validate(n.right, n.val, max);

     }
    public static void main(String[] args) {
        //test for maxDepth
        TreeNode node4 = new TreeNode(3,null,null);
        TreeNode node3 = new TreeNode(9,null,null);
        TreeNode node2 = new TreeNode(20,null,null);
        TreeNode node1 = new TreeNode(15, node3, node4);
        TreeNode root = new TreeNode(7, node1, node2);
        root.left = node1;
        root.right = node2;
        node2.left = node3;
        node2.right = node4;
        Trees t = new Trees();
        //print tree
        //System.out.println("Tree: ");
        //t.printTree(root);
        System.out.println("\nmax depth: " + t.maxDepth(root));
        
        //test for isValidBST
        System.out.println("Is valid BST: " + t.isValidBST(root));
    }
}
