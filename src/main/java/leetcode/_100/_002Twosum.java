package leetcode._100;

import leetcode.LeetCodeConstant;


/**
 * <a href="https://leetcode.cn/problems/add-two-numbers/">第2题 两数求和</a>
 */
@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.LinkedList,
        LeetCodeConstant.TagEnum.Math,
        LeetCodeConstant.TagEnum.Recursion
})
public class _002Twosum {

    public static void main(String[] args) {
        ListNode listNode = new _002Twosum().addTwoNumbers(parse("9999999"), parse("9999"));
        System.out.println(listNode.val);
        while ((listNode = listNode.next) != null) {
            System.out.println(listNode.val);
        }
    }

    public static ListNode parse(String input){
        ListNode retNode = new ListNode(Integer.parseInt(input.substring(0, 1)));
        ListNode curNode = retNode;
        for (int i = 1; i < input.length(); i++) {
            curNode.next = new ListNode(Integer.parseInt(input.substring(i, i + 1)));
            curNode = curNode.next;
        }
        return retNode;
    }

    /**
     * 每个链表中的节点数在范围 [1, 100] 内
     * 0 <= Node.val <= 9
     * 题目数据保证列表表示的数字不含前导零
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode retNode = new ListNode((l1.val + l2.val) % 10);
        ListNode curNode = retNode;
        int cover = (l1.val + l2.val) / 10;


        l1 = l1.next;
        l2 = l2.next;
        for (; l1 != null || l2 != null || cover > 0; ) {

            int l1Val = l1 != null ? l1.val : 0;
            int l2Val = l2 != null ? l2.val : 0;
            int sum = l1Val + l2Val + cover;

            ListNode newNode = new ListNode(sum % 10);
            cover = sum / 10;
            curNode.next = newNode;
            curNode = newNode;

            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }

        return retNode;
    }

    public static class ListNode {
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
}
