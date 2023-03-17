package interview.date20220602;

import java.util.ArrayList;
import java.util.List;
//2、给定一个只包含数字的字符串，用以表示一个IP地址，返回所有可能从s获得的有效IP地址。
//         你可以按任何顺序返回答案。
//         有效IP地址正好由四个整数（每个整数位于0到255之间组成，且不能含有前导0），整数之间用
//         '.'分隔。
//         例如："0.1.2.201"和"192.168.1.1"是有效IP地址，但是"0.011.255.245"、"192.168.
//         1.312"和"192.168@1.1"是无效IP地址。
//        示例1：
//        输入：s="25525511135"
//        输出：["255.255.11.135","255.255.111.35"]
//        示例2：
//        输入：s="0000"
//        输出：["0.0.0.0"]
//        示例3：
//        输入：s="1111"
//        输出：["1.1.1.1"]
//        示例4：
//        输入：s="010010"
//        输出：["0.10.0.10","0.100.1.0"]
//        示例5：
//        输入：s="101023"
//        输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
//        提示：
//        0<=s.length<=3000
//        s仅由数字组成


public class classSolution {

    public static void main(String[] args) {
        System.out.println(restoreIpAddresses("010010"));
        System.out.println(restoreIpAddresses("25525511135"));
    }

    public static List<String> restoreIpAddresses(String s) {
        List<String> ret = new ArrayList<>();
        if (s.length() < 4 || s.length() > 12) return ret;
        for (int endIndex = 1; endIndex <= 3; endIndex++) {
            String curIp = s.substring(0, endIndex);
            if (endIndex > 1 && curIp.charAt(0) == '0') continue;

            if (Integer.valueOf(curIp) <= 255 && Integer.valueOf(curIp) >= 0) {
                ret.addAll(restoreIpAddresses(curIp + ".", s.substring(endIndex), 3));
            }
        }
        return ret;
    }

    public static List<String> restoreIpAddresses(String existIP, String s, int number) {
        List<String> ret = new ArrayList<>();
        if (s.length() < number || s.length() > 3 * number) return ret;
        for (int endIndex = 1; endIndex <= 3 && endIndex <= s.length(); endIndex++) {
            String curIp = s.substring(0, endIndex);
            if (endIndex > 1 && curIp.charAt(0) == '0') continue;
            if (Integer.valueOf(curIp) <= 255 && Integer.valueOf(curIp) >= 0) {
                if (number == 1) {
                    if (endIndex != s.length()) {
                        continue;
                    } else {
                        ret.add(existIP + curIp);
                    }
                } else {
                    ret.addAll(restoreIpAddresses(existIP + curIp + ".", s.substring(endIndex), number - 1));
                }
            }
        }
        return ret;
    }

}
