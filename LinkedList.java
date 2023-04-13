import java.util.ArrayList;

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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
        }
        return dummyHead.next;
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

        //test addTwoNumbers
        ListNode head3 = new ListNode(2);
        ListNode node10 = new ListNode(4);
        ListNode node11 = new ListNode(3);
        head3.next = node10;
        node10.next = node11;

        ListNode head4 = new ListNode(5);
        ListNode node12 = new ListNode(6);
        ListNode node13 = new ListNode(4);
        head4.next = node12;
        node12.next = node13;

        System.out.println("\n\nBefore adding two numbers: ");
        temp = head3;
        while(temp != null){
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
        System.out.println("");
        temp = head4;
        while(temp != null){
            System.out.print(temp.val + " ");
            temp = temp.next;
        }

        ListNode newHead2 = ll.addTwoNumbers(head3, head4);
        System.out.println("\nAfter adding two numbers: ");
        temp = newHead2;
        while(temp != null){
            System.out.print(temp.val + " ");
            temp = temp.next;
        }


    }
}
