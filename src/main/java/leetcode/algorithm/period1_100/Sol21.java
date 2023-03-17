package leetcode.algorithm.period1_100;


/**
 * 简单
 */
/**
 * 21. 合并两个有序链表
 * 递归
 * 链表
 * https://leetcode.cn/problems/merge-two-sorted-lists
 */
public class Sol21 {
    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 3, 5};
        int[] arr2 = new int[]{2, 4, 6};

        ListNode l1 = new ListNode();
        ListNode l1Next = l1;
        for (int num1 : arr1) {
            l1Next = l1Next.next = new ListNode(num1);
        }
        ListNode l2 = new ListNode();
        ListNode l2Next = l2;
        for (int num1 : arr2) {
            l2Next = l2Next.next = new ListNode(num1);
        }
        ListNode listNode = new Solution5().mergeTwoLists(l1.next, l2.next);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;

        }

    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 合并 2个升序链表
     */
    static class Solution5 {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode ret = new ListNode();
            ListNode retNext = ret;
            ListNode l1Next = l1;
            ListNode l2Next = l2;
            while (l1Next != null && l2Next != null) {
                if (l1Next.val < l2Next.val) {
                    retNext = retNext.next = new ListNode(l1Next.val);
                    l1Next = l1Next.next;
                } else {
                    retNext = retNext.next = new ListNode(l2Next.val);
                    l2Next = l2Next.next;
                }
            }
            while (l1Next != null) {
                retNext = retNext.next = new ListNode(l1Next.val);
                l1Next = l1Next.next;

            }
            while (l2Next != null) {
                retNext = retNext.next = new ListNode(l2Next.val);
                l2Next = l2Next.next;

            }
            return ret.next;
        }
    }
}
