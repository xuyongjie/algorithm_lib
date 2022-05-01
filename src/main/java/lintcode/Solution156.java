package lintcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solution156 {

    public static void main(String[] args) {
    }

    public static class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    /**
     * @param intervals: interval list.
     * @return: A new interval list.
     */
    public List<Interval> merge(List<Interval> intervals) {
        // write your code here
        List<Interval> result = new ArrayList<>();
        if (intervals.size() == 0) {
            return result;
        }
        intervals.sort(Comparator.comparingInt(o -> o.start));
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;
        for (int i = 1; i < intervals.size(); i++) {
            Interval cur = intervals.get(i);
            if (end >= cur.start) {
                end = Math.max(end, cur.end);
            } else {
                result.add(new Interval(start, end));
                start = cur.start;
                end = cur.end;
            }
        }
        result.add(new Interval(start, end));
        return result;
    }
}
