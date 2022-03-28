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
        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        treeNode.left = treeNode2;
        treeNode.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode4.left = new TreeNode(6);
        treeNode3.right = new TreeNode(7);

        System.out.println(exam02Answer02(treeNode));

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


}
