package exercise;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树习题
 *
 * @author 小丶木曾义仲丶哈牛柚子露丶蛋卷
 * @version 1.0
 * @date 2022/3/28 19:47
 */
public class BinaryTreeExec {

    public static void main(String[] args) {
        // TreeNode treeNode = new TreeNode(1);
        // TreeNode treeNode2 = new TreeNode(2);
        // TreeNode treeNode3 = new TreeNode(3);
        // TreeNode treeNode4 = new TreeNode(4);
        // TreeNode treeNode5 = new TreeNode(5);
        // treeNode.left = treeNode2;
        // treeNode.right = treeNode3;
        // treeNode2.left = treeNode4;
        // treeNode2.right = treeNode5;
        // treeNode4.left = new TreeNode(6);
        // treeNode3.right = new TreeNode(7);
        //
        // System.out.println(exam02Answer02(treeNode));


        // TreeNode treeNode = new TreeNode(1);
        // TreeNode node1 = new TreeNode(2);
        // TreeNode node2 = new TreeNode(3);
        // treeNode.left = new TreeNode(4);
        // treeNode.left.left = node1;
        // treeNode.left.right = new TreeNode(6);
        // treeNode.left.left.left = node2;
        // treeNode.right = new TreeNode(5);
        // // treeNode.right.left = node2;
        // System.out.println(exam03(treeNode, node1, node2).value);

        // TreeNode2 treeNode = new TreeNode2(1);
        // TreeNode2 node = new TreeNode2(888);
        // treeNode.left = new TreeNode2(2);
        // treeNode.left.parent = treeNode;
        // // treeNode.left.left = node;
        // // node.parent = treeNode.left;
        // treeNode.left.right = node;
        // node.parent = treeNode.left;
        // treeNode.right = new TreeNode2(3);
        // treeNode.right.parent = treeNode;
        // // treeNode.right.right = node;
        // // node.parent = treeNode.right;
        // System.out.println(exam04(treeNode, node).value);

        exam05(1, 2, true);

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
     * 树的宽度优先遍历
     *
     * @param root
     */
    public static void exam01(TreeNode root) {
        // 在 java 中 LinkedList 就是队列
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()) {
            TreeNode treeNode = nodeQueue.poll();
            System.out.println(treeNode.value);
            if (treeNode.left != null) {
                nodeQueue.add(treeNode.left);
            }
            if (treeNode.right != null) {
                nodeQueue.add(treeNode.right);
            }
        }
    }

    /**
     * 求出树的最大宽度(答案1): 求出树的最大宽度
     *
     * @param root
     * @return
     */
    public static int exam02Answer01(TreeNode root) {
        HashMap<TreeNode, Integer> levelMap = new HashMap<>();
        int curLevel = 1;
        int curCount = 0;
        int maxCount = Integer.MIN_VALUE;
        levelMap.put(root, 1);
        // 在 java 中 LinkedList 就是队列
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()) {
            TreeNode treeNode = nodeQueue.poll();
            Integer level = levelMap.get(treeNode);
            if (level == curLevel) {
                curCount++;
            } else {
                maxCount = Math.max(maxCount, curCount);
                curCount = 1;
                curLevel++;
            }
            if (treeNode.left != null) {
                nodeQueue.add(treeNode.left);
                levelMap.put(treeNode.left, curLevel + 1);
            }
            if (treeNode.right != null) {
                nodeQueue.add(treeNode.right);
                levelMap.put(treeNode.right, curLevel + 1);
            }

        }
        return maxCount;
    }

    /**
     * 求出树的最大宽度(答案2)：求出树的最大宽度
     *
     * @param root
     * @return
     */
    public static int exam02Answer02(TreeNode root) {
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        int curLevel = 1;
        int curCount = 0;
        int maxCount = Integer.MIN_VALUE;
        // 当前层(curLevel)的最后一个节点
        TreeNode curLevelEndTreeNode = root;
        // 下一层的最后一个节点
        TreeNode nextLevelEndTreNode = null;
        while (!nodeQueue.isEmpty()) {
            TreeNode treeNode = nodeQueue.poll();

            if (treeNode.left != null) {
                nodeQueue.add(treeNode.left);
                nextLevelEndTreNode = treeNode.left;
            }
            if (treeNode.right != null) {
                nodeQueue.add(treeNode.right);
                nextLevelEndTreNode = treeNode.right;
            }

            if (treeNode == curLevelEndTreeNode) {
                maxCount = Math.max(++curCount, maxCount);
                curLevel++;
                curCount = 0;
                curLevelEndTreeNode = nextLevelEndTreNode;
                nextLevelEndTreNode = null;
            } else {
                curCount++;
            }


        }
        return maxCount;
    }

    /**
     * 给定两个二叉树的节点 node1, node2 找到它们最低公共祖先节点
     * 解决方法：
     * 1. 适用格外数据结构：
     * 1.1 首选使用一个哈希表将所有节点的父子关系保存起来
     * 1.2 查找 node1 节点的父节点直到找到头节点，并将这路上所有找到的祖先元素都保存到一个 Set 中(包括 node1 节点也要保存 Set 中)
     * 1.3 对 node2 进行 [1.2] 的操作，但每次找到祖先元素时都判断该元素是否存在与 Set 中(包括 node2 节点也要进行判断)
     * 如果存在就返回该元素，反之就继续往上面找
     * 2. 不使用额外数据结构(递归)
     * 2.1 判断当前递归节点是否是 node1 / node2; 如果是就直接返回
     * 2.2 如果都不是就进行左右子树递归
     * 2.3 如果左右子树都找到了 node1 & node2，那么当前元素就是两个元素的最低公共祖先元素
     * 2.4 如果只找到一个，就返回那个找到的节点(node1 / node2)就好了
     *
     * @param head
     * @param node1
     * @param node2
     * @return
     */
    public static TreeNode exam03(TreeNode head, TreeNode node1, TreeNode node2) {
        if (head == null) {
            return null;
        }

        if (head == node1) {
            return node1;
        } else if (head == node2) {
            return node2;
        }

        TreeNode leftNode = exam03(head.left, node1, node2);
        TreeNode rightNode = exam03(head.right, node1, node2);

        if (leftNode != null && rightNode != null) {
            return head;
        }

        return leftNode == null ? rightNode : leftNode;
    }


    /**
     * 找到一个元素的后继节点
     * tips: 二叉树的中序遍历的结果，节点的下一个节点就是它的后继节点
     * 思路：
     *
     * @param head
     * @param node
     * @return
     */
    public static TreeNode2 exam04(TreeNode2 head, TreeNode2 node) {
        TreeNode2 parent = node.parent;
        while (parent != null) {
            if (node == parent.left) {
                break;
            }
            node = parent;
            parent = parent.parent;
        }
        return parent;
    }

    private static class TreeNode2 {
        private int value;
        // 头节点
        private TreeNode2 parent;
        private TreeNode2 left;
        private TreeNode2 right;

        public TreeNode2(int value) {
            this.value = value;
        }

        public TreeNode2(int value, TreeNode2 parent, TreeNode2 left, TreeNode2 right) {
            this.value = value;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 微软真题(emm)
     * @param i
     * @param N
     * @param flag
     */
    private static void exam05(int i, int N, boolean flag) {
        if (i > N) {
            return;
        }
        exam05(i + 1, N, true);
        System.out.println(flag ? "down" : "up");
        exam05(i + 1, N, false);
    }


}
