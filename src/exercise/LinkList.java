package exercise;

import java.util.Stack;

/**
 * 链表联系
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
        Node head = new Node(1);
        head.next = new Node(3);
        head.next.next = new Node(2);
        head.next.next.next = new Node(3);
        head.next.next.next.next = new Node(1);

        System.out.println(exam04Answer02(head));
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

    /*
    * 练习5：复制含有随机指针节点的链表
    * */

    /**
     * 练习1：反转单向/双向链表
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
     *      - 针对笔试，使用额外的堆结构方便实现
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
     *      - 面试：保证时间复杂度的同时减小空间复杂度
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

}
