package algorithm;

import java.util.Stack;

/**
 * 二叉树相关
 *
 * @author 小丶木曾义仲丶哈牛柚子露丶蛋卷
 * @version 1.0
 * @date 2022/3/28 19:47
 */
public class BinaryTree {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        treeNode.left = treeNode2;
        treeNode.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;

        unRecurPostOrder(treeNode);

    }

    public static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    /**
     * 递归序
     */
    public static void recursion(TreeNode root) {
        if (root == null) {
            return;
        }
        // 1
        recursion(root.left);
        // 2
        recursion(root.right);
        // 3.
    }

    /**
     * 先序遍历
     *
     * @param root
     */
    public static void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        // 访问当前节点
        System.out.println(root.value);
        preOrder(root.left);
        preOrder(root.right);
    }

    /**
     * 中序遍历
     *
     * @param root
     */
    public static void midOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        midOrder(root.left);
        // 访问当前节点
        System.out.println(root.value);
        midOrder(root.right);
    }

    /**
     * 后序遍历
     *
     * @param root
     */
    public static void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        // 访问当前节点
        System.out.println(root.value);
    }

    /**
     * 非递归实现先序遍历
     *
     * @param root
     */
    public static void unRecurPreOrder(TreeNode root) {
        // 使用栈实现
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode treeNode = stack.pop();
            System.out.println(treeNode.value);
            if (treeNode.right != null) {
                stack.push(treeNode.right);
            }
            if (treeNode.left != null) {
                stack.push(treeNode.left);
            }
        }
    }

    /**
     * 非递归实现中序遍历
     *
     * @param root
     */
    public static void unRecurMidOrder(TreeNode root) {
        // 使用栈实现
        Stack<TreeNode> stack = new Stack<>();
        TreeNode temp = root;
        while (!stack.isEmpty() || temp != null) {
            // 先往左边走，同时将当前节点压入栈中
            if (temp != null) {
                stack.push(temp);
                temp = temp.left;
            } else {
                // 左边莫得了, 获取上一个压入栈中的节点
                temp = stack.pop();
                // 访问当前节点
                System.out.println(temp.value);
                // 访问右边的节点
                temp = temp.right;
            }
        }
    }

    /**
     * 非递归实现后序遍历
     * @param root
     */
    public static void unRecurPostOrder(TreeNode root) {
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            stack2.push(node);
            if (node.left != null) {
                stack1.push(node.left);
            }
            if (node.right != null) {
                stack1.push(node.right);
            }
        }
        while (!stack2.isEmpty()){
            System.out.println(stack2.pop().value);
        }
    }

}
