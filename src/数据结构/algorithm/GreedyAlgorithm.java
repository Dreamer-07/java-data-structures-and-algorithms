package algorithm;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 贪心算法练习
 * @author 小丶木曾义仲丶哈牛柚子露丶蛋卷
 * @version 1.0
 * @date 2022/4/29 9:49
 */
public class GreedyAlgorithm {

    /**
     * 表示一个项目宣讲的开始和结束时间
     */
    public static class Project {
        // 开始时间
        public int start;
        // 结束时间
        public int end;

        public Project(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    /**
     * 比较器
     */
    public static class ProjectComparators implements Comparator<Project> {
        /**
         * 结束时间早的排在前面
         * @param o1
         * @param o2
         * @return
         */
        @Override
        public int compare(Project o1, Project o2) {
            return o1.end - o2.end;
        }
    }

    /**
     *
     * @param projects 所有项目
     * @param startTime 会议室开始使用的时间
     * @param endTime 会议室结束使用的时间
     * @return
     */
    public int exec01(Project[] projects, int startTime, int endTime) {
        // 讲数组按照指定规则排序
        Arrays.sort(projects, new ProjectComparators());
        int count = 0;
        // 遍历数组
        for (int i = 0; i < projects.length; i++) {
            if (startTime < projects[i].start) {
                startTime = projects[i].end;
                count++;
                // 如果时间到了就直接退出即可
                if (startTime == endTime) {
                    break;
                }
                if (startTime > endTime) {
                    count--;
                    break;
                }
            }
        }
        return count;
    }

}
