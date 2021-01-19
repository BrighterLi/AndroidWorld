package com.xiaoming.a005algorithm.datastructure.linkedlist;

import java.util.ArrayList;

//判断给定的链表中是否有环。如果有环则返回true，否则返回false。
//你能给出空间复杂度的解法么？
//https://www.nowcoder.com/practice/650474f313294468a4ded3ce0f7898b9?tpId=194&&tqId=35772&rp=1&ru=/activity/oj&qru=/ta/job-code-high-client/question-ranking
public class CycleLinkedList {

    //方法1：将节点放入ArrayList，移动链表指针，判断前面节点是否包含后面节点
    //空间复杂度O(n)
    public boolean hasCycle(ListNode head) {
        //一定要先考虑特殊情况
        if(head == null ||  head.next == null) {
            return false;
        }
        ArrayList list = new ArrayList();
        list.add(head);
        ListNode curNode = head.next;
        while(curNode != null) {
            if(list.contains(curNode)) {
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
        if(head == null ||  head.next == null) {
            return false;
        }
        ListNode curNode = head;
        ListNode curNode2 = head;
        while(curNode != null && curNode2 != null) {
            if(curNode.next != null && curNode2.next != null && curNode2.next.next != null) {
                curNode = curNode.next; //慢指针走一步
                curNode2 = curNode2.next.next; //快指针走两步
                if(curNode == curNode2) {
                    return true;
                }
            } else {
                return false;
            }

        }
        return false;
    }
}
