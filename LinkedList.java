
public class LinkedList {
    public void deleteNode(ListNode node) {
        //There is a singly-linked list head and we want to delete a node node in it.
        ListNode nextN = node.next;
        node.val = nextN.val;
        node.next = nextN.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        //Given the head of a linked list, remove the nth node from the end of the list and return its head.
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int length  = 0;
        ListNode first = head;
        while (first != null) {
            length++;
            first = first.next;
        }
        length -= n;
        first = dummy;
        while (length > 0) {
            length--;
            first = first.next;
        }
        first.next = first.next.next;
        return dummy.next;
    }

    public ListNode reverseList(ListNode head) {
        //Given the head of a singly linked list, reverse the list, and return the reversed list.
        ListNode prev = null;
        ListNode cur = head;
        while(cur!=null){
            ListNode nextN = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nextN;
        }
        
        return prev;
    }

    public ListNode swapPairs(ListNode head) {
        //Given a linked list, swap every two adjacent nodes and return its head. 
        //You must solve the problem without modifying the values in the list's nodes 
        //(i.e., only nodes themselves may be changed.)

        //strategy: 
        //start at head while cur is not null
        //get the next node athat the cur node poitns to and the prev node-store value and its next
        //set cur.next to the next node .next 
        //next.next points to cur
        //if prev exists set prev.next to cur.next
        //increment cur to be what cur is now pointint to
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        while(head != null && head.next != null){
            ListNode firstNode = head;
            ListNode secondNode = head.next;
            

            //perform swap
            firstNode.next = secondNode.next;
            secondNode.next = firstNode;
            prev.next = secondNode;

            //iterate
            prev = firstNode;
            head= firstNode.next;
        }
        return dummy.next;
    }

    


    public static void main(String[] args) {
        //test deleteNode
        ListNode head = new ListNode(4);
        ListNode node1 = new ListNode(5);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(9);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        System.out.println("Before deleting node: ");
        ListNode temp = head;
        while(temp != null){
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
        System.out.println("");
        LinkedList ll = new LinkedList();
        ll.deleteNode(node1);
        System.out.println("After deleting node: ");
        temp = head;
        while(temp != null){
            System.out.print(temp.val + " ");
            temp = temp.next;
        }

        //test removeNthFromEnd
        System.out.println("\n\nBefore removing nth node from end(2): ");
        temp = head;
        while(temp != null){
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
        System.out.println("");
        ll.removeNthFromEnd(head, 2);
        System.out.println("After removing nth node from end(2): ");
        temp = head;
        while(temp != null){
            System.out.print(temp.val + " ");
            temp = temp.next;
        }

        //test reverseList
        ListNode head2 = new ListNode(1);
        ListNode node4 = new ListNode(2);
        ListNode node5 = new ListNode(6);
        ListNode node6 = new ListNode(3);
        ListNode node7 = new ListNode(4);
        ListNode node8 = new ListNode(5);
        ListNode node9 = new ListNode(6);
        head2.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        node8.next = node9;

        System.out.println("\n\nBefore reversing list: ");
        temp = head2;
        while(temp != null){
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
        System.out.println("");
        ListNode newHead = ll.reverseList(head2);
        System.out.println("After reversing list: ");
        temp = newHead;
        while(temp != null){
            System.out.print(temp.val + " ");
            temp = temp.next;
        }



        //test swap pairs
        ListNode head3 = new ListNode(1);
        ListNode node10 = new ListNode(2);
        ListNode node11 = new ListNode(3);
        ListNode node12 = new ListNode(4);
        head3.next = node10;
        node10.next = node11;
        node11.next = node12;
        System.out.println("\n\nBefore swapping pairs: ");
        temp = head3;
        while(temp != null){
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
        System.out.println("");
        ListNode newHead2 = ll.swapPairs(head3);
        System.out.println("After swapping pairs: ");
        temp = newHead2;
        while(temp != null){
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
        }

    

}
