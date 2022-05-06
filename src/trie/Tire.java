package trie;

/**
 * @author 小丶木曾义仲丶哈牛柚子露丶蛋卷
 * @version 1.0
 * @date 2022/4/28 21:53
 */
public class Tire {

    private TireNode root;

    public Tire() {
        this.root = new TireNode();
    }

    /**
     * 将字符串插入到前缀树中
     *
     * @param str
     */
    private void insert(String str) {
        // 获取字符数组
        char[] chars = str.toCharArray();
        root.pass++;
        TireNode temp = root;
        for (int i = 0; i < chars.length; i++) {
            // 通过 字符相减规律 可以得到当前字符在数组中 index(a=0, b=1....z=25)

            int index = chars[i] - 'a';
            if (temp.nodes[index] == null) {
                temp.nodes[index] = new TireNode();
            }
            // 经过当前字符的节点数量++
            temp.nodes[index].pass++;
            // 下移到当前字符的节点
            temp = temp.nodes[index];
        }
        // 以当前字符的节点作为结尾的个数++
        temp.end++;
    }

    /**
     * 查询字符串在前缀树中出现的次数
     *
     * @param str
     * @return
     */
    private int search(String str) {
        if (str == null) {
            return 0;
        }
        // 获取字符数组
        char[] chars = str.toCharArray();
        TireNode temp = root;
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            // 字符串还未遍历完，已经没有对应的字符节点了，代表该字符不存在于前缀树中，直接返回0
            if (temp.nodes[index] == null) {
                return 0;
            }
            temp = temp.nodes[index];
        }
        return temp.end;
    }

    /**
     * 删除前缀树中的某个字符串
     *
     * @param str
     */
    private void delete(String str) {
        // 一定要保证该字符串是存在于树中的
        if (search(str) != 0) {
            char[] chars = str.toCharArray();
            TireNode temp = root;
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (--temp.nodes[index].pass == 0) {
                    // 如果当前字符节点已经没有经过的字符了，就直接置空即可
                    temp.nodes[index] = null;
                    return;
                }
                temp = temp.nodes[index];
            }
            temp.end--;
        }
    }

    /**
     * 查找以指定字符作为前缀的字符串数据个数
     *
     * @param str
     * @return
     */
    private int prefixNumber(String str) {
        if (str == null) {
            return 0;
        }
        char[] chars = str.toCharArray();
        TireNode temp = root;
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            // 字符串还未遍历完，已经没有对应的字符节点了，代表该字符不存在于前缀树中，直接返回0
            if (temp.nodes[index] == null) {
                return 0;
            }
            temp = temp.nodes[index];
        }
        return temp.pass;
    }

}