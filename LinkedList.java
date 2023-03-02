
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
    }
}
