package com.xiaoming.a005algorithm.datastructure.linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * 有环链表
 */
public class CycleLinkedList {

    //链表中环的入口节点
    // 判断给定的链表中是否有环。如果有环则返回true，否则返回false。
   //你能给出空间复杂度的解法么？
   //https://www.nowcoder.com/practice/650474f313294468a4ded3ce0f7898b9?tpId=194&&tqId=35772&rp=1&ru=/activity/oj&qru=/ta/job-code-high-client/question-ranking

    //方法1：将节点放入ArrayList，移动链表指针，判断前面节点是否包含后面节点
    //空间复杂度O(n)
    public boolean hasCycle(ListNode head) {
        //一定要先考虑特殊情况
        if (head == null || head.next == null) {
            return false;
        }
        ArrayList list = new ArrayList();
        list.add(head);
        ListNode curNode = head.next;
        while (curNode != null) {
            if (list.contains(curNode)) {
                return true;
            }
            list.add(curNode);
            curNode = curNode.next;
        }
        return false;
    }

    //方法2：快慢指针法，一个指针移动一个步距，一个指针移动两个步距，有相遇则有圈
    public boolean hasCycle2(ListNode head) {
        //一定要先考虑特殊情况
        ////空间复杂度O(1)
        if (head == null || head.next == null) {
            return false;
        }
        ListNode curNode = head;
        ListNode curNode2 = head;
        while (curNode != null && curNode2 != null) {
            if (curNode.next != null && curNode2.next != null && curNode2.next.next != null) {
                curNode = curNode.next; //慢指针走一步
                curNode2 = curNode2.next.next; //快指针走两步
                if (curNode == curNode2) {
                    return true;
                }
            } else {
                return false;
            }

        }
        return false;
    }

    //对于一个给定的链表，返回环的入口节点，如果没有环，返回null
    //  拓展：
    //  你能给出不利用额外空间的解法么？

    //方法1：先将链表前面节点存起来，在遍历过程发现存起来的节点已经有该节点，说明有环且该节点即环的入口点
    //使用了额外空间
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        List<ListNode> nodeList = new ArrayList<>();
        nodeList.add(head);
        ListNode curNode = head.next;
        while (curNode != null) {
            if (nodeList.contains(curNode)) {
                return curNode;
            }
            nodeList.add(curNode);
            curNode = curNode.next;
        }
        return null;
    }

    //方法2：快慢指针法，一个指针移动一个步距，一个指针移动两个步距，有相遇则有圈；相遇点即环入口点
}
