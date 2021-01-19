package com.xiaoming.a005algorithm.datastructure.linkedlist;

public class ReverseLinkedList {

    //反转链表，输出链表后的头部
    public ListNode reverseList(ListNode head) {
        ListNode preNode = null; //前一个节点
        ListNode nextNode = null; //后一个节点
        ListNode curNode = head; //当前节点
        if(head == null) {
            return null; //记得判空
        } else if(head.next == null) {
            return head; //记得特殊情况拿出来
        }
        while(curNode != null) {
            nextNode = curNode.next; //保存下一个节点
            curNode.next = preNode; //反转指针指向
            preNode = curNode; //保存并更新前面一个节点
            curNode = nextNode;  //更新当前
        }
        return preNode;
    }
}
