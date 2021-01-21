package com.xiaoming.a005algorithm.datastructure.tree.treetraverse;

import java.util.ArrayList;
import java.util.List;

//分别按照二叉树先序，中序和后序打印所有的节点。
//https://www.nowcoder.com/practice/a9fec6c46a684ad5a3abd4e365a9d362?tpId=194&&tqId=35806&rp=1&ru=/activity/oj&qru=/ta/job-code-high-client/question-ranking

//树的遍历：前序，中序，后序
//递归法
public class TreeTraverse {

    List preList = new ArrayList<Integer>(); //保存前序的节点值
    List middleList = new ArrayList<Integer>(); //保存中序的节点值
    List tailList = new ArrayList<Integer>(); //保存后序的节点值

    public int[][] threeOrders(TreeNode root) {
        // write code here
        if (root == null) {
            return null;
        }
        preOrderTraverse(root); //进行前序
        middleOrderTraverse(root); //进行中序
        tailOrderTraverse(root); //进行后序

        int size = preList.size();
        int[][] resultArray = new int[3][size];
        for (int i = 0; i < size; i++) {
            resultArray[0][i] = (int) preList.get(i);
            resultArray[1][i] = (int) middleList.get(i);
            resultArray[2][i] = (int) tailList.get(i);
        }
        return resultArray;
    }

    //前序递归法
    private void preOrderTraverse(TreeNode node) {
        if (node != null) {
            preList.add(node.val);
            preOrderTraverse(node.left);
            preOrderTraverse(node.right);
        }
    }

    //中序递归法
    private void middleOrderTraverse(TreeNode node) {
        if (node != null) {
            middleOrderTraverse(node.left);
            middleList.add(node.val);
            middleOrderTraverse(node.right);
        }
    }

    //后序递归法
    private void tailOrderTraverse(TreeNode node) {
        if (node != null) {
            tailOrderTraverse(node.left);
            tailOrderTraverse(node.right);
            tailList.add(node.val);
        }
    }

    //TreeNode内部类
    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
    }
}
