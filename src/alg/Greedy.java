package alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author QFJiang on 2018/01/05 14:53
 */
public class Greedy {

    public static void main(String[] args) {
        Interval[] intervals = new Interval[]{
                new Interval(1, 3),
                new Interval(2, 5),
                new Interval(4, 7),
                new Interval(6, 9),
                new Interval(8, 10)
        };
        System.out.println(intervalScheduling(intervals));

//        Good[] goods = new Good[]{
//                new Good(2, 6),
//                new Good(2, 3),
//                new Good(6, 5),
//                new Good(5, 4),
//                new Good(4, 6),
//        };
        Good[] goods = new Good[]{
                new Good(4, 3),
                new Good(5, 4),
                new Good(10, 7),
                new Good(11, 8),
                new Good(13, 9),
        };
        System.out.println(fractionKnapsack(17, goods));
    }

    /**
     * 贪婪算法 - 区间调度问题
     * <p>
     * 按区间右端点排序，对每一个区间，若左端点在上一个选择区间的右端点之后，
     * 则选择该区间，否则不选择该区间
     *
     * @param intervals 区间数组
     * @return 最优解区间列表
     */
    public static List<Interval> intervalScheduling(Interval[] intervals) {
        Arrays.sort(intervals);
        int terminate = 0;
        List<Interval> list = new ArrayList<>();
        for (Interval interval : intervals) {
            if (terminate < interval.getStart()) {
                terminate = interval.getTerminate();
                list.add(interval);
            }
        }
        return list;
    }

    /**
     * 区间类，包含起始结束区间点
     */
    public static class Interval implements Comparable<Interval> {
        private int start;
        private int terminate;

        public Interval(int start, int terminate) {
            this.start = start;
            this.terminate = terminate;
        }

        public int getStart() {
            return start;
        }

        public int getTerminate() {
            return terminate;
        }

        @Override
        public String toString() {
            return "(" + start + ", " + terminate + ")";
        }

        @Override
        public int compareTo(Interval o) {
            return Integer.compare(this.terminate, o.terminate);
        }
    }

    /**
     * 分数背包问题，优先选取单位价值大的物品
     */
    public static int fractionKnapsack(int W, Good[] goods) {
        Arrays.sort(goods);
        int totalValues = 0;
        int totalWeights = 0;
        List<Good> list = new ArrayList<>();
        for (int i = goods.length - 1; i >= 0; i--) {
            if (totalWeights + goods[i].getWeight() <= W) {
                totalWeights += goods[i].getWeight();
                totalValues += goods[i].getValue();
                list.add(goods[i]);
            }
        }
        System.out.println(list);
        return totalValues;
    }

    /**
     * 背包问题物品类，包含价值、重量（体积）、单位价值等属性
     */
    public static class Good implements Comparable<Good> {
        private int value;
        private int weight;
        private double unitValue;

        public Good(int value, int weight) {
            this.value = value;
            this.weight = weight;
            this.unitValue = (double) value / weight;
        }

        public int getValue() {
            return value;
        }

        public int getWeight() {
            return weight;
        }

        public double getUnitValue() {
            return unitValue;
        }

        @Override
        public String toString() {
            return "(" + value + ", " + weight + ")";
        }

        @Override
        public int compareTo(Good o) {
            return Double.compare(this.unitValue, o.unitValue);
        }
    }
}
