package trie;

/**
 * 前缀树节点
 * @author 小丶木曾义仲丶哈牛柚子露丶蛋卷
 * @version 1.0
 * @date 2022/4/28 21:48
 */
public class TireNode {

    /**
     * 以当前节点为前缀的节点个数
     */
    public int pass;

    /**
     * 以当前节点为结尾的节点个数
     */
    public int end;

    /**
     * 当前的节点连接的节点
     *  - 这里的用数组是因为数据量小
     *  - 如果实战中使用，可以用 HashMap<Char, TireNode>
     */
    public TireNode[] nodes;

    public TireNode() {
        pass = 0;
        end = 0;
        //  这里的学习是以 26 个字母组成的字符串为例的，所以用一个拥有 26 个位置的数组来存储
        nodes = new TireNode[26];
    }
}
