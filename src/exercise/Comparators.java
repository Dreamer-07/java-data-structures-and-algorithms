package exercise;

import java.util.Comparator;

/**
 * 比较器：可以通过按照特定规则进行排序
 * @author 23911
 * @version 1.0
 * @date 2022/3/25 16:19
 */
public class Comparators implements Comparator<Integer> {

    /**
     * 比较两个参数的方法
     *  规则：
     *      当返回值 < 0 时，o1 在前面，o2 在后面
     *      当返回值 > 0 时，o1 在后面，o2 在前面
     *      当返回值 = 0 时，都可以
     *  技巧：
     *      降序：{@code return o1 - o2}
     *      升序：{@code return o2 - o1}
     * @author 23911
     * @date 2022/3/25 16:21
     */
    @Override
    public int compare(Integer o1, Integer o2) {
        return o2 - o1;
    }

}
