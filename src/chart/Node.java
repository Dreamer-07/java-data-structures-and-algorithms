package chart;

import java.util.ArrayList;

/**
 * 图结构中表示节点的类
 * @author 小丶木曾义仲丶哈牛柚子露丶蛋卷
 * @version 1.0
 * @date 2022/4/21 9:30
 */
public class Node {

    /**
     * 节点的值
     */
    public int value;

    /**
     * 节点的入度
     */
    public int in;

    /**
     * 节点的出度
     */
    public int out;

    /**
     * 由当前节点发散的边所能指向的节点
     */
    public ArrayList<Node> nexts;

    /**
     * 由当前节点发散的边
     */
    public ArrayList<Edge> edges;

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
