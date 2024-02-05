package com.ledboot.main.bits;

/**
 * @author: Fred
 * @date: 2024/2/4 17:24
 * @description: (类的描述)
 */
public class BubbleSort {

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(3);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(6);

        printList(head);
        head = bubbleSort(head);

        System.out.println("sorted finished: " + head.val + " " + head.next.val + " " + head.next.next.val + " " + head.next.next.next.val);

    }

    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }

    public static ListNode bubbleSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode current;
        boolean wasChanged;

        do {
            current = head;
            wasChanged = false;
            while (current.next != null) {
                if (current.val > current.next.val) {
                    int temp = current.val;
                    current.val = current.next.val;
                    current.next.val = temp;
                    wasChanged = true;
                }
                current = current.next;

            }
        }
        while (wasChanged);
        return head;

    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
