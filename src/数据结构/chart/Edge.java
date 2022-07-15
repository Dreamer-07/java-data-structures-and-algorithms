package chart;

/**
 * 图结构中表示边的类
 * @author 小丶木曾义仲丶哈牛柚子露丶蛋卷
 * @version 1.0
 * @date 2022/4/21 9:35
 */
public class Edge {

    /**
     * 权重
     */
    public int weight;

    /**
     * 入点
     */
    public Node form;

    /**
     * 出点
     */
    public Node to;

    public Edge(int weight, Node form, Node to) {
        this.weight = weight;
        this.form = form;
        this.to = to;
    }
}
