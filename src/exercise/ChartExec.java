package exercise;

import chart.Edge;
import chart.Graph;
import chart.Node;

import java.util.*;

/**
 * 有关图的联系
 *
 * @author 小丶木曾义仲丶哈牛柚子露丶蛋卷
 * @version 1.0
 * @date 2022/4/21 9:40
 */
public class ChartExec {

    public static void main(String[] args) {

    }

    /**
     * 图的宽度优先遍历
     *
     * @param node
     */
    public void exec1(Node node) {
        // 使用队列辅助完成
        Queue<Node> queue = new LinkedList<>();
        // 使用 set 集合去重
        HashSet<Node> set = new HashSet<>();
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()) {
            Node node1 = queue.poll();

            /*
             * 在进行图的宽度优先遍历时对数据的操作
             * */
            System.out.println(node1.value);

            for (Node next : node1.nexts) {
                // 如果 set 集合中还没有 next 节点，就表示它还没有遍历过，就加入到队列中
                if (!set.contains(next)) {
                    queue.add(next);
                    set.add(next);
                }
            }
        }
    }

    /**
     * 图的深度优先遍历
     *
     * @param node
     */
    public void exec2(Node node) {
        Stack<Node> nodeStack = new Stack<>();
        // 去重
        HashSet<Node> set = new HashSet<>();
        nodeStack.push(node);
        set.add(node);
        while (!nodeStack.isEmpty()) {
            Node node1 = nodeStack.pop();
            System.out.println(node1.value);
            for (Node next : node1.nexts) {
                if (!set.contains(next)) {
                    nodeStack.push(next);
                    set.add(next);
                }
            }
        }
    }

    /**
     * 拓扑排序:
     * - 要求有向图，且有入度为 0 的节点，并且没有环
     * - 说明：在开发的实际过程中，如果我们引用了很多 Maven 依赖，
     * 那么如何确定依赖打包的顺序(避免依赖循环)，就是拓扑排序所需要解答的
     * - 思路：先找到入度为 0 的节点(不依赖其他依赖)，
     * 然后将其 nexts(需要依赖当前节点的依赖) 的入度 -1(表示它需要的依赖已经搞定一个)
     * 依次类推，直到没有入度为 0 的节点
     *
     * @param graph
     */
    public ArrayList<Node> exec3(Graph graph) {
        // 只有入度为 0 的节点才能进入的队列
        Queue<Node> zeroInQueue = new LinkedList<>();
        // 统计所有节点的入度信息
        HashMap<Node, Integer> nodeInMap = new HashMap<>();
        for (Node node : graph.nodes.values()) {
            nodeInMap.put(node, node.in);
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }
        ArrayList<Node> result = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            Node pollNode = zeroInQueue.poll();
            result.add(pollNode);
            for (Node next : pollNode.nexts) {
                nodeInMap.put(next, nodeInMap.get(next) - 1);
                if (nodeInMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return result;
    }


    /**
     * 一个简单版本的"并查集"结构
     * 主要功能：
     * 1) 合并：提供两个不交集(Disjoint sets，一系列没有重复元素的集合)的合并
     * 2) 查询：查询两个元素是否在一个集合内
     */
    public static class UnionFind {
        /**
         * key - 图中的每个节点
         * value -
         */
        public HashMap<Node, List<Node>> setMap;

        public UnionFind(Collection<Node> nodes) {
            for (Node node : nodes) {
                ArrayList<Node> nodeList = new ArrayList<>();
                nodeList.add(node);
                setMap.put(node, nodeList);
            }
        }

        /**
         * 查询两个节点是否在一个集合内
         *
         * @param node1
         * @param node2
         * @return
         */
        public boolean isSameSet(Node node1, Node node2) {
            return setMap.get(node1) == setMap.get(node2);
        }

        /**
         * 合并两个集合
         *
         * @param node1
         * @param node2
         */
        public void unionSet(Node node1, Node node2) {
            List<Node> node1List = setMap.get(node1);
            List<Node> node2List = setMap.get(node2);
            for (Node node : node2List) {
                node1List.add(node);
                setMap.put(node, node1List);
            }
        }
    }

    /**
     * 最小生成树 - k 算法
     *
     * @param graph
     * @return
     */
    public List<Edge> kruskal(Graph graph) {
        UnionFind unionFind = new UnionFind(graph.nodes.values());
        // 使用小根堆来辅助排序
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        priorityQueue.addAll(graph.edges);
        ArrayList<Edge> result = new ArrayList<>();
        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            // 如果 edge 的 form/to 不在最小生成树对应的集合内，就它们加进去
            if (!unionFind.isSameSet(edge.form, edge.to)) {
                unionFind.unionSet(edge.form, edge.to);
                result.add(edge);
            }
        }
        return result;
    }

    private class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    /**
     * 最小生成树 - p 算法
     *
     * @param graph
     * @return
     */
    public List<Edge> prim(Graph graph) {
        // 去重集合
        HashSet<Node> set = new HashSet<>();
        // 小根堆
        PriorityQueue<Edge> queue = new PriorityQueue<>(new EdgeComparator());
        // 最小生成树的边
        ArrayList<Edge> result = new ArrayList<>();

        for (Node node : graph.nodes.values()) {
            queue.addAll(node.edges);
            set.add(node);
            while (!queue.isEmpty()) {
                Edge edge = queue.poll();
                Node next = edge.to;
                if (!set.contains(next)) {
                    set.add(next);
                    queue.addAll(next.edges);
                    result.add(edge);
                }
            }

        }

        return result;
    }


    /**
     * 迪杰斯特拉
     * @param head
     * @return
     */
    public HashMap<Node, Integer> dijkstra(Node head) {
        // 设置一个哈希表，复杂记录 head 节点到每个节点的最短距离
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        // 当然了，包括到自己的距离(呲牙.jpg)
        distanceMap.put(head, 0);
        // 设置一个可以用来保存被锁定的节点的结构
        HashSet<Node> selectNodes = new HashSet<>();
        // 找到 distanceMap 结构中距离最小且没有被锁定的节点
        Node minNode = getMinDistanceAndNuSelectedNode(distanceMap, selectNodes);
        while (minNode != null) {
            int distance = distanceMap.get(minNode);
            for (Edge edge : minNode.edges) {
                Node to = edge.to;
                int weight = edge.weight;
                // 如果距离表中没有该节点，那么直接新增即可
                if (!distanceMap.containsKey(to)) {
                    distanceMap.put(to, distance + weight);
                } else {
                    distanceMap.put(to, Math.min(distanceMap.get(to), distance + weight));
                }
            }
            // 锁定当前节点
            selectNodes.add(minNode);
            // 重新找
            minNode = getMinDistanceAndNuSelectedNode(distanceMap, selectNodes);
        }
        return distanceMap;
    }

    /**
     * 找到 distanceMap 结构中距离最小且没有被锁定的节点
     * @param distanceMap
     * @param selectNodes
     * @return
     */
    private Node getMinDistanceAndNuSelectedNode(HashMap<Node, Integer> distanceMap,
                                                 HashSet<Node> selectNodes) {
        Node minNode = null;
        int minValue = Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> nodeIntegerEntry : distanceMap.entrySet()) {
            Node node = nodeIntegerEntry.getKey();
            Integer distance = nodeIntegerEntry.getValue();
            if (!selectNodes.contains(node) && minValue > distance) {
                minNode = node;
                minValue = distance;
            }
        }
        return minNode;
    }
}
