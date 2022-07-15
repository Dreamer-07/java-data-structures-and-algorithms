package chart;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 图结构中表示图的数据集
 * @author 小丶木曾义仲丶哈牛柚子露丶蛋卷
 * @version 1.0
 * @date 2022/4/21 9:37
 */
public class Graph {

    public HashMap<Integer, Node> nodes;

    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
