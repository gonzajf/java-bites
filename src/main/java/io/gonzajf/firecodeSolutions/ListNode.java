package io.gonzajf.firecodeSolutions;

public class ListNode {

	int data;
	ListNode next;

	ListNode(int data) {
		this.data = data;
	}

	public static ListNode findMiddleNode(ListNode head) {

		if(head != null) {
			ListNode slow = head;
			ListNode fast = head.next;
			while(fast != null) {
				if(fast.next == null) {
					return slow;
				}
				slow = slow.next;
				fast = (fast.next).next;
			}
			return slow;
		}
		return null;
	}
}
