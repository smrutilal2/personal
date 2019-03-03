package home.algo;

import java.util.ArrayList;
import java.util.List;

public class WeightedString {


    private static List<Character> chars = new ArrayList<>(26);
    private static List<Long> weights = new ArrayList<>(26);

    static {
        chars.add(new Character('A'));
        weights.add(1l);
        for (int i = 1; i < 26; i++) {
            chars.add(new Character((char) ('A' + i)));
            weights.add((i + 1l) * weights.get(i - 1) + (i + 1l));
        }

        System.out.println(chars);
        System.out.println(weights);
    }

    private static String findSmallest(long weight) {

        int index = findStopIndex(weight);

        StringBuilder sb = new StringBuilder();

        findCombination(weight, index, sb);

        return reverse(sb.toString());
    }

    private static String reverse(String s) {
        StringBuilder rsBuilder = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            rsBuilder.append(s.charAt(i));
        }
        return rsBuilder.toString();
    }

    private static int findStopIndex(long weight) {
        char stopAt;
        int index = 0;
        for (int i = 0; i < weights.size() - 1; i++) {
            long w = weights.get(i);
            if (w == weight) {
                stopAt = chars.get(i);
                index = i;
                break;
            }

            if (weights.get(i) > weight) {
                stopAt = chars.get(i - 1);
                index = i - 1;
                break;
            }
        }
        return index;
    }

    private static void findCombination(long target, int stopIndex, StringBuilder sb) {
        if (weights.contains(target)) {
            sb.append(chars.get(stopIndex));
            return;
        } else if (target <= 0) {
            return;
        } else {
            sb.append(chars.get(stopIndex));
            long weight = weights.get(stopIndex);
            target -= weight;
            findCombination(target, findStopIndex(target), sb);
        }
    }

    public static void main(String[] args) {
        System.out.println(findSmallest(80));
        System.out.println(findSmallest(328));
        System.out.println(findSmallest(330));
    }

}
