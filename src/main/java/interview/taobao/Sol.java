package interview.taobao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sol {
    static List<String> strings = Arrays.asList("万", "千", "百", "十", "");
    static Map<Integer, String> numMap = new HashMap<Integer, String>();

    static {
        numMap.put(0, "零");
        numMap.put(1, "一");
        numMap.put(2, "二");
        numMap.put(3, "三");
        numMap.put(4, "四");
        numMap.put(5, "五");
        numMap.put(6, "六");
        numMap.put(7, "七");
        numMap.put(8, "八");
        numMap.put(9, "九");
    }

    public static void main(String[] args) {
        System.out.println(parse(3));
    }

    public static String parse(int number) {
        List<Integer> integers = Arrays.asList(0, 0, 0, 0, 0);
        int nowN, yiliu = number;
        int index = 0;
        while (yiliu > 0) {
            nowN = yiliu % 10;
            yiliu = yiliu / 10;
            integers.set(4 - index, nowN);
            index++;
        }

        StringBuffer ret = new StringBuffer();
        boolean hasZero = false;
        boolean hasFirst = false;
        for (int i = 0; i < integers.size(); i++) {
            int curN = integers.get(i);
            if (curN != 0) {
                hasFirst = true;
                ret.append(numMap.get(curN)).append(strings.get(i));
            } else if (!hasZero && hasFirst) {
                hasZero = true;
                ret.append(numMap.get(curN));
            }
        }
        return ret.toString();
    }

}
