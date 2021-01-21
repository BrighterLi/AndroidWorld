package com.xiaoming.a005algorithm.datastructure.tree.treetraverse;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class TreeTraverse2 {


    /**
     * 前序遍历，非递归实现
     * 1，先入栈根节点，输出根节点val值，再先后入栈其右节点、左结点；
     * 2，出栈左节点，输出其val值，再入栈该左节点的右节点、左节点；直到遍历完该左节点所在子树。
     * 3，再出栈右节点，输出其val值，再入栈该右节点的右节点、左节点；直到遍历完该右节点所在子树。
     */
    public void PreOrderTraverse(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>(); //堆栈：先进后出
        if (root != null) {
            stack.push(root);
        }
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            System.out.print(node.val);
            //右结点先入栈，左结点后入栈
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
    }


    /**
     * 中序遍历，非递归实现
     * 1，首先从根节点出发一路向左，入栈所有的左节点；
     * 2，出栈一个节点，输出该节点val值，查询该节点是否存在右节点，
     * 若存在则从该右节点出发一路向左入栈该右节点所在子树所有的左节点；
     * 3，若不存在右节点，则出栈下一个节点，输出节点val值，同步骤2操作；
     * 4，直到节点为null，且栈为空。
     */
    public void middleOrderTraverse(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.empty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            if (!stack.empty()) {
                TreeNode node = stack.pop();
                System.out.print(node.val);
                root = node.right;
            }
        }
    }

    // 层序遍历（广度优先遍历）
    //非递归法
    public void LayerOrderTraverse(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>(); //队列：先进先出
        if (root != null) queue.offer(root); //将根节点放入队列中
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll(); //移出队列头部的元素
            System.out.print(node.val);
            if (node.left != null) queue.offer(node.left); //将二叉树的左节点放入队列
            if (node.right != null) queue.offer(node.right); //将二叉树的右节点放入队列
        }
    }

    //TreeNode内部类
    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
    }

}
