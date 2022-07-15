package exercise;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 有关 树形dp(动态规划的练习)
 *
 * @author 小丶木曾义仲丶哈牛柚子露丶蛋卷
 * @version 1.0
 * @date 2022/3/31 16:38
 */
public class BinaryTreeDpExec {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(4);
        treeNode.left = new TreeNode(3);
        treeNode.left.left = new TreeNode(2);
        treeNode.right = new TreeNode(7);
        treeNode.right.left = new TreeNode(6);
        treeNode.right.right = new TreeNode(10);
        System.out.println(isBST(treeNode));
        System.out.println(isBST2(treeNode).flag);

        // TreeNode treeNode = new TreeNode(4);
        // treeNode.left = new TreeNode(3);
        // treeNode.right = new TreeNode(2);
        // treeNode.left.left = new TreeNode(3);
        // treeNode.left.left.left = new TreeNode(3);
        // treeNode.left.right = new TreeNode(4);
        // System.out.println(isCBT(treeNode));

        // TreeNode treeNode = new TreeNode(2);
        // treeNode.left = new TreeNode(2);
        // treeNode.right = new TreeNode(2);
        // treeNode.left.left = new TreeNode(2);
        // treeNode.left.right = new TreeNode(2);
        // treeNode.right.left = new TreeNode(2);
        // treeNode.right.right = new TreeNode(2);
        // FBTInfo fbtInfo = isFBT(treeNode);
        // System.out.println(fbtInfo.size == (1 << fbtInfo.height) - 1);

        // TreeNode treeNode = new TreeNode(2);
        // treeNode.left = new TreeNode(2);
        // treeNode.left.left = new TreeNode(2);
        // treeNode.left.right = new TreeNode(2);
        // treeNode.left.left.left = new TreeNode(2);
        // treeNode.right = new TreeNode(2);
        // treeNode.right.left = new TreeNode(3);
        // System.out.println(isBBT(treeNode).flag);
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
     * 判断一个树是否为搜索二叉树(左子树的所有节点的值一定小于当前节点的值;右子树的所有节点的值一定大于当前节点的值)
     *
     * @param head
     * @return
     */
    public static boolean isBST(TreeNode head) {
        if (head != null) {
            Stack<TreeNode> stack = new Stack<>();
            TreeNode temp = head;
            // 可以设置一个值用来保存前一个节点的值
            // 因为 [左子树的最大值 > 当前节点; 当前节点 > 右子树最小值] 的这个判断逻辑可以通过 "中序遍历" 解决
            // 中序遍历: 先遍历左节点，然后遍历当前节点，再遍历右节点
            int preValue = Integer.MIN_VALUE;
            while (!stack.isEmpty() || temp != null) {
                if (temp != null) {
                    stack.push(temp);
                    temp = temp.left;
                } else {
                    temp = stack.pop();
                    // 如果前一个节点的值大于当前节点的值就代表不是搜索树
                    if (preValue > temp.value) {
                        return false;
                    } else {
                        preValue = temp.value;
                    }
                    temp = temp.right;
                }
            }
        }
        return true;
    }

    /**
     * 判断一颗树是否为搜索二叉树
     *
     * @param node
     * @return
     */
    public static BSTInfo isBST2(TreeNode node) {
        if (node == null) {
            return null;
        }
        BSTInfo left = isBST2(node.left);
        BSTInfo right = isBST2(node.right);
        /*
        * 解释一下 flag：
        *   当 left == null 时代表左边是搜索二叉树
        *   当 left != null 时需要通过
        *       left.flag(左子树是否为搜素二叉树) 以及
        *       将左子树中最大值和当前节点的值进行比较，如果 left.max < node.value 就代表左边是搜索二叉树
        *   right 同理
        * */
        boolean flag = (left == null || (left.flag && left.max < node.value))
                       &&
                       (right == null || (right.flag && right.min > node.value));
        int min = left != null ? Math.min(left.min, node.value) : node.value;
        int max = right != null ? Math.max(right.max, node.value) : node.value;

        return new BSTInfo(flag, min, max);
    }

    private static class BSTInfo {
        // 是否为搜索二叉树
        private boolean flag;
        // 当前子树上的最小值
        private int min;
        // 当前子树上的最大值
        private int max;

        public BSTInfo() {
        }

        public BSTInfo(boolean flag, int min, int max) {
            this.flag = flag;
            this.min = min;
            this.max = max;
        }
    }


    /**
     * 判断一颗树是否为完全二叉树(若设二叉树的深度为k，除第 k 层外，其它各层 (1～k-1) 的结点数都达到最大个数，第k 层所有的结点都连续集中在最左边，这就是完全二叉树。)
     * 可能出现的情况：
     * 1. 当一个节点的 left == null && right != null 则就不是完全二叉树
     * 2. 节点要么是叶子节点，要么 (left & right) != null，要么 left != null && right == null
     * 3. 当出现了 叶子节点或只有一个子节点的节点时 之后的所有节点只能是叶子节点
     *
     * @param head
     * @return
     */
    public static boolean isCBT(TreeNode head) {
        // 定义一个变量用来区分是否出现了 left != null && right == null 的节点
        boolean flag = false;

        // 做树宽度遍历
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            // 当一个节点的 left == null && right != null 则就不是完全二叉树
            if (treeNode.left == null && treeNode.right != null) {
                return false;
            }

            // 当出现了 [(left != null && right == null)或叶子节点] 时之后的所有节点只能是叶子节点
            // 这里可以简化成 left != null 即可，原理可以自己想想
            if (flag && treeNode.left != null) {
                return false;
            }

            // left == null => 叶子节点; right == null => 左节点有右节点没用
            boolean check = treeNode.left == null || treeNode.right == null;

            // 当出现了 叶子节点或只有一个子节点的节点时 之后的所有节点只能是叶子节点
            if (check && !flag) {
                flag = true;
            }

            if (treeNode.left != null) {
                queue.add(treeNode.left);
            }

            if (treeNode.right != null) {
                queue.add(treeNode.right);
            }
        }
        return true;
    }


    /**
     * (树型dp) 判断一颗树是否为满二叉树
     *
     * @param head
     * @return
     */
    public static FBTInfo isFBT(TreeNode treeNode) {
        if (treeNode == null) {
            return new FBTInfo(0, 0);
        }
        FBTInfo left = isFBT(treeNode.left);
        FBTInfo right = isFBT(treeNode.right);
        int height = Math.max(left.height, right.height) + 1;
        int size = left.size + right.size + 1;
        return new FBTInfo(height, size);
    }

    private static class FBTInfo {
        private int height;
        private int size;

        public FBTInfo() {
        }

        public FBTInfo(int height, int size) {
            this.height = height;
            this.size = size;
        }
    }

    /**
     * 判断是否为平衡二叉树
     *
     * @param node
     * @return
     */
    public static BBTInfo isBBT(TreeNode node) {
        if (node == null) {
            return new BBTInfo(true, 0);
        }
        BBTInfo leftInfo = isBBT(node.left);
        BBTInfo rightInfo = isBBT(node.right);
        boolean flag = leftInfo.flag && rightInfo.flag && Math.abs(leftInfo.level - rightInfo.level) < 2;
        int level = Math.max(leftInfo.level, rightInfo.level) + 1;

        return new BBTInfo(flag, level);
    }


    private static class BBTInfo {
        private boolean flag;
        private int level;

        public BBTInfo() {
        }

        public BBTInfo(boolean flag, int level) {
            this.flag = flag;
            this.level = level;
        }
    }


}
