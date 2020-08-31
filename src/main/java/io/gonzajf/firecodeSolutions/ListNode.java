package io.gonzajf.firecodeSolutions;

public class ListNode {

	int data;
	ListNode next;

	ListNode(int data) {
		this.data = data;
	}

	public static ListNode findMiddleNode(ListNode head) {

		if (head != null) {
			ListNode slow = head;
			ListNode fast = head.next;
			while (fast != null) {
				if (fast.next == null) {
					return slow;
				}
				slow = slow.next;
				fast = (fast.next).next;
			}
			return slow;
		}
		return null;
	}

	public static ListNode insertAtTail(ListNode head, int data) {

		ListNode newNode = new ListNode(data);

		if(head == null) {
			head = newNode;
		} else {
			ListNode pointer = head;
			while(pointer.next != null) {
				pointer = pointer.next;
			}
			pointer.next = newNode;
		}		
		return head;
	}

	public static ListNode deleteAtTail(ListNode head) {
		
		if(head == null || head.next == null) {
			return null;
		} else {
			ListNode prev = head;
			ListNode curr = head.next;
			while(curr.next != null) {
				curr = curr.next;
				prev = prev.next;
			}
			prev.next = null;
		}		
		return head;
	}
}