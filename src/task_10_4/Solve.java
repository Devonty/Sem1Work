package task_10_4;

import java.util.ArrayList;
import java.util.List;

public class Solve {
    public static List<Segment> findMaxPair(List<Segment> segments) {
        if (segments.size() <= 1) return null;
        // work
        // default
        List<Segment> result = new ArrayList<>(List.of(segments.get(0), segments.get(1)));
        float maxLen = result.get(0).lengthTogether(result.get(1));
        // max recount
        for (int i = 0; i < segments.size(); i++) {
            for (int j = i + 1; j < segments.size(); j++) {
                float len = segments.get(i).lengthTogether(segments.get(j));
                if (len > maxLen) {
                    maxLen = len;
                    result.set(0, segments.get(i));
                    result.set(1, segments.get(j));
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
    }
}
