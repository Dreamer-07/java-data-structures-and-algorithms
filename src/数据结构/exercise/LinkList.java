package exercise;

import java.util.HashMap;
import java.util.Objects;
import java.util.Stack;

/**
 * 链表联系
 *
 * @author 小丶木曾义仲丶哈牛柚子露丶蛋卷
 * @version 1.0
 * @date 2022/3/26 13:57
 */
public class LinkList {

    public static void main(String[] args) {
        // 练习1
        // Node head = new Node(1);
        // Node cur = head;
        // for (int i = 2; i < 10; i++) {
        //     cur.next = new Node(i);
        //     cur = cur.next;
        // }
        //
        // exam01(head);
        //
        // System.out.println(head);


        // 练习2
        // Node2 node1 = new Node2(1);
        // Node2 node2 = new Node2(2);
        // Node2 node3 = new Node2(3);
        // node1.next = node2;
        // node2.next = node3;
        // node2.prev = node1;
        // node3.prev = node2;
        //
        // node1 = exam02(node1);
        //
        // Node2 temp = node1;
        // while (temp != null) {
        //     System.out.println(temp);
        //     temp = temp.next;
        // }

        // 练习3
        // Node head = new Node(1);
        // head.next = new Node(3);
        // head.next.next = new Node(6);
        //
        // Node head2 = new Node(3);
        // head2.next = new Node(3);
        // head2.next.next = new Node(6);
        //
        // exam03(head, head2);

        // 练习4
        // Node head = new Node(1);
        // head.next = new Node(3);
        // head.next.next = new Node(2);
        // head.next.next.next = new Node(3);
        // head.next.next.next.next = new Node(1);
        //
        // System.out.println(exam04Answer02(head));

        // 练习5
        // Node3 node1 = new Node3(1);
        // Node3 node2 = new Node3(2);
        // Node3 node3 = new Node3(3);
        // node1.next = node2;
        // node2.next = node3;
        // node1.rand = node3;
        // node3.rand = node2;
        //
        // Node3 copyNode1 = exam05Answer02(node1);
        // System.out.println(copyNode1);
        // System.out.println(copyNode1.next);
        // System.out.println(copyNode1.next.next);

        // 练习6
        // Node node1 = new Node(5);
        // Node node2 = new Node(6);
        // node1.next = node2;
        // Node node3 = new Node(2);
        // node2.next = node3;
        // Node node4 = new Node(2);
        // node3.next = node4;
        // Node node5 = new Node(1);
        // node4.next = node5;
        //
        // Node node = exam06Answer02(node1, new Node(3));
        // System.out.println(node);

        Node node1 = new Node(1);
        Node node2 = new Node(2);
        node1.next = node2;
        Node node3 = new Node(3);
        node2.next = node3;
        Node node4 = new Node(4);
        node3.next = node4;
        Node node5 = new Node(5);
        node4.next = node5;
        Node otherNode1 = new Node(6);

        // -- 两条都不是回环
        // otherNode1.next = new Node(7);
        // otherNode1.next.next = new Node(8);
        // -- 两条都是回环但是不相交
        // node5.next = node2;
        // Node node7 = new Node(7);
        // otherNode1.next = node7;
        // node7.next = new Node(8);
        // node7.next.next = node7;
        // -- 两条都是回环相交部分包括未回环部分
        // node5.next = node2;
        // otherNode1.next = node2;
        // -- 两条都是回环相交部分不包含未回环部分
        node5.next = node2;
        otherNode1.next = node5;

        System.out.println(Objects.requireNonNull(exam07(node1, otherNode1)).value);


    }

    static class Node {
        public Integer value;
        public Node next;

        public Node(Integer value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + next +
                    '}';
        }

    }

    static class Node2 {
        public Integer value;
        public Node2 prev;
        public Node2 next;

        public Node2(Integer value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node2{" +
                    "value=" + value +
                    '}';
        }
    }

    /**
     * 练习1：反转单向/双向链表
     *
     * @param head
     */
    private static void exam01(Node head) {
        Node cur = head.next;
        head.next = null;
        while (cur != null) {
            Node temp = cur.next;
            cur.next = head;
            head = cur;
            cur = temp;
        }
    }

    /**
     * 练习2：反转双向链表
     *
     * @param head
     */
    private static Node2 exam02(Node2 head) {
        Node2 reverseHead = head;
        while (true) {
            Node2 temp = reverseHead.next;
            reverseHead.next = reverseHead.prev;
            reverseHead.prev = temp;

            if (temp == null) {
                break;
            }

            reverseHead = temp;
        }
        return reverseHead;
    }

    /**
     * 练习3：求两个有序链表的公共部分
     *
     * @param head1
     * @param head2
     */
    private static void exam03(Node head1, Node head2) {
        Node index01 = head1;
        Node index02 = head2;
        while (index01 != null && index02 != null) {
            if (index01.value.equals(index02.value)) {
                System.out.println("相同值:" + index01.value);
                index01 = index01.next;
                index02 = index02.next;
            } else if (index01.value < index02.value) {
                index01 = index01.next;
            } else {
                index02 = index02.next;
            }

        }
    }

    /**
     * 练习4答案1：判断一个单向链表是否是回文链表(从前到后和从后到前遍历的元素数据相同)
     *  - 针对笔试，使用额外的堆结构方便实现
     *
     * @param head
     */
    private static Boolean exam04Answer01(Node head) {
        Node temp = head;
        // 使用栈先将元素依次压入栈中
        Stack<Node> nodeStack = new Stack<>();
        while (temp != null) {
            nodeStack.push(temp);
            temp = temp.next;
        }
        // 从头开始重新遍历链表，和栈中的元素进行比较(栈是后进先出)
        temp = head;
        while (temp != null) {
            // 弹出的栈顶元素
            Node popNode = nodeStack.pop();
            if (!popNode.value.equals(temp.value)) {
                return false;
            }
            temp = temp.next;
        }
        return true;
    }


    /**
     * 练习4答案2：判断一个单向链表是否是回文链表(从前到后和从后到前遍历的元素数据相同)
     *  - 面试：保证时间复杂度的同时减小空间复杂度
     *
     * @param head
     * @return
     */
    private static Boolean exam04Answer02(Node head) {
        /*
         * 使用快慢索引找到链表中间的元素
         *   n1: 快索引
         *   n2: 慢索引
         * */
        Node n1 = head;
        Node n2 = head;
        // 找到链表中间的位置(链表元素为奇数个时在中间，偶数个时在 4/2 = 2)
        while (n1.next != null && n1.next.next != null) {
            n1 = n1.next.next;
            n2 = n2.next;
        }
        /*
         * 将 n2.next 也就是右边的链表进行一个反转
         *   n1: 用来记录当前遍历的元素
         *   n2: 用来记录当前遍历元素的前一个元素，最后会是链表的最右边的元素
         * */
        n1 = n2.next;
        n2.next = null;
        while (n1 != null) {
            Node temp = n1.next;
            n1.next = n2;
            n2 = n1;
            n1 = temp;
        }
        // 记录当前链表的最后的元素用来重新反转右边的元素
        Node n3 = n2;
        n1 = head;
        boolean flag = true;
        while (n1 != null && n2 != null) {
            if (!n1.value.equals(n2.value)) {
                flag = false;
                break;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        // 重新反转数组
        n1 = n3.next;
        n3.next = null;
        while (n1 != null) {
            Node temp = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = temp;
        }
        return flag;
    }

    /**
     * 练习5答案1：(使用哈希表)复制含有随机指针节点的链表
     *
     * @param head
     * @return
     */
    private static Node3 exam05Answer01(Node3 head) {
        // 创建一个哈希表，key 放原生节点，value 放拷贝元素
        HashMap<Node3, Node3> nodes = new HashMap<>();
        // 先保存拷贝元素的关系
        Node3 temp = head;
        while (temp != null) {
            Node3 copyNode = new Node3(temp.value);
            nodes.put(temp, copyNode);
            temp = temp.next;
        }
        // 为拷贝元素复制数据
        temp = head;
        while (temp != null) {
            Node3 copyNode = nodes.get(temp);
            copyNode.next = nodes.get(temp.next);
            copyNode.rand = nodes.get(temp.rand);
            temp = temp.next;
        }
        return nodes.get(head);
    }

    /*
     *
     *
     * 练习7：两个单链表相交
     * */

    /**
     * 练习5答案2: 判断一个单向链表是否是回文链表(从前到后和从后到前遍历的元素数据相同)
     *
     * @param head
     * @return
     */
    private static Node3 exam05Answer02(Node3 head) {
        // 将拷贝元素放在源元素的 next 节点中
        Node3 temp = head;
        while (temp != null) {
            Node3 copyNode = new Node3(temp.value);
            // 保留原来的 next 节点
            Node3 nextNode = temp.next;
            temp.next = copyNode;
            // 移动到原来的 next 节点
            copyNode.next = temp = nextNode;
        }
        // 修改拷贝元素的 next & rand 属性
        temp = head;
        while (temp != null) {
            // 获取拷贝元素
            Node3 copyNode = temp.next;
            Node3 originNextNode = copyNode.next;
            if (originNextNode != null) {
                copyNode.next = originNextNode.next;
            }
            Node3 originRandNode = temp.rand;
            if (originRandNode != null && originRandNode.rand != null) {
                copyNode.rand = originRandNode.rand.next;
            }
            temp = originNextNode;
        }
        return head.next;
    }

    /**
     * 练习6(答案1)：将单向链表按某值划分成左边小，中间相等，右边大的形式
     *
     * @param head
     * @return
     */
    private static Node exam06Answer01(Node head, Node pivot) {
        int nodeLength = 0;
        Node temp = head;
        // 记录节点数量
        while (temp != null) {
            temp = temp.next;
            nodeLength++;
        }
        Node[] nodes = new Node[nodeLength];
        nodeLength = 0;
        temp = head;
        while (temp != null) {
            nodes[nodeLength++] = temp;
            temp = temp.next;
        }
        // 快速排序
        nodeQuickSort(nodes, pivot);

        // 排序后的数组重新改成链表即可
        for (int i = 1; i < nodes.length; i++) {
            nodes[i - 1].next = nodes[i];
        }
        nodes[nodeLength - 1].next = null;
        return nodes[0];
    }

    /**
     * 练习6(答案2)：将单向链表按某值划分成左边小，中间相等，右边大的形式
     *
     * @param head
     * @param pivot
     * @return
     */
    private static Node exam06Answer02(Node head, Node pivot) {
        /*
         * LH,LE - 小于部分的开始节点和结束节点
         * EH,EE - 等于部分的开始节点和结束节点
         * MH,ME - 大于部分的开始节点和结束节点
         * */
        Node LH = null, LE = null, EH = null, EE = null, MH = null, ME = null;
        Node temp = head;
        while (temp != null) {
            if (temp.value > pivot.value) {
                // 大于
                if (MH == null) {
                    MH = temp;
                } else {
                    ME.next = temp;
                }
                ME = temp;
            } else if (temp.value < pivot.value) {
                // 小于
                if (LH == null) {
                    LH = temp;
                } else {
                    LE.next = temp;
                }
                LE = temp;
            } else {
                // 等于
                if (EH == null) {
                    EH = temp;
                } else {
                    EE.next = temp;
                }
                EE = temp;
            }
            temp = temp.next;
        }
        Node tempE = null;
        if (LH != null) {
            temp = LH;
            tempE = LE;
        }
        if (EH != null) {
            if (tempE == null) {
                temp = EH;
            } else {
                tempE.next = EH;
            }
            tempE = EE;
        }
        if (MH != null) {
            if (tempE == null) {
                temp = MH;
            } else {
                tempE.next = MH;
            }
            tempE = ME;
        }
        tempE.next = null;
        return temp;
    }

    private static void nodeQuickSort(Node[] nodes, Node pivot) {
        // 定义两个下标，一个表示小于区域的下一个元素，一个表示大于区域的下一个元素
        int start = 0;
        int end = nodes.length - 1;
        for (int i = 0; i <= end; ) {
            if (nodes[i].value > pivot.value) {
                swap(nodes, i, end--);
            } else if (nodes[i].value < pivot.value) {
                swap(nodes, i++, start++);
            } else {
                i++;
            }
        }
    }

    public static class Node3 {
        int value;
        Node3 next;
        // 随机指针
        Node3 rand;

        Node3(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node3{" +
                    "value=" + value +
                    '}';
        }
    }

    private static void swap(Node[] arr, int i, int j) {
        Node temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 练习7(答案1)：
     * 判断两个链表是否相交，如果相交就返回第一个相交的节点，如果不相交就返回 null，链表可能回环链表，也可能不是
     *
     * @param head1
     * @param head2
     * @return
     */
    private static Node exam07(Node head1, Node head2) {
        Node loop1 = isLoop(head1);
        Node loop2 = isLoop(head2);
        if (loop1 == null && loop2 == null) {
            return getNoLoopCross(head1, head2);
        } else if (loop1 != null && loop2 != null) {
            return getHasLoopCross(head1, loop1, head2, loop2);
        } else {
            return null;
        }
    }

    /**
     * 获取两个链表都是回环链表的第一个相交节点
     * @param head1
     * @param loop1
     * @param head2
     * @param loop2
     * @return
     */
    private static Node getHasLoopCross(Node head1, Node loop1, Node head2, Node loop2) {
        /*
        * 有三种情况：
        *   1. 两个回环链表不相交
        *   2. 相交部分包括未回环部分(loop1 == loop2)
        *   3. 相交部分不包括未回环部分(loop1 开始走一定可以找到 loop2)
        * */
        if (loop1 == loop2) {
            // 相交部分包括未回环部分(loop1 == loop2)
            // 可以将两个链表看作是到 loop 截至的未回环链表
            // 其实就是找两个未回环链表的相交点
            Node temp = loop1.next;
            loop1.next = null;
            Node loopNode = getNoLoopCross(head1, head2);
            loop1.next = temp;
            return loopNode;
        }
        // 相交部分不包括未回环部分(loop1 开始走一定可以找到 loop2)
        Node temp = loop1.next;
        while (temp != null && temp != loop1) {
            if (temp == loop2) {
                return temp;
            }
            temp = temp.next;
        }
        // 两个回环链表不相交
        return null;
    }

    /**
     * 获取两个链表都不是回环链表的第一个相交节点(相交链表最后一个元素一定是相同的)
     * @param head1
     * @param head2
     * @return
     */
    private static Node getNoLoopCross(Node head1, Node head2) {
        Node temp = head1;
        int count = 0;
        while (temp != null) {
            temp = temp.next;
            count++;
        }
        temp = head2;
        while (temp != null) {
            temp = temp.next;
            count--;
        }
        // count > 0: 第一个链表长; count < 0: 第二个链表长
        temp = count > 0 ? head1 : head2;
        // 记录下短链表的索引
        Node temp2 = temp == head1 ? head2 : head1;
        /*
        * count 其实就是两个链表的长度之差
        * */
        count = Math.abs(count);
        while (count > 0) {
            temp = temp.next;
            count--;
        }
        /*
        * 如果是相交链表最后一个元素一定是相同的，
        * 那么只要确定两个链表的的遍历索引到最后一个节点的 step 相同，就能找到他们相交的节点，如果没有就返回 null
        * */
        while (temp != null && temp != temp2) {
            temp = temp.next;
            temp2 = temp2.next;
        }
        return temp;
    }

    /**
     * 判断一个链表是否为回环链表，如果是就返回回环节点，否则返回 null
     *
     * @param head
     * @return
     */
    private static Node isLoop(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        // 定义快慢指针
        Node idx1 = head.next;
        Node idx2 = head.next.next;

        while (idx2 != idx1) {
            if (idx2.next == null || idx2.next.next == null) {
                return null;
            }
            idx1 = idx1.next;
            idx2 = idx2.next.next;
        };
        // 回环链表不可能遍历完
        if (idx2 == null) {
            return null;
        }
        idx2 = head;
        /*
         * 这里有个规律：
         * 在回环链表上使用快慢索引，当快慢索引第一次相遇时，此时快索引回到 head 节点，
         * 和慢索引同时移动，每次移动一步，当两个节点再次相遇时，就是回环节点
         * */
        while (idx2 != idx1) {
            idx1 = idx1.next;
            idx2 = idx2.next;
        }
        return idx1;
    }
}
