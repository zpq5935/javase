package interview.feizhu;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// 第3题
public class Sol3 {
    public static void main(String[] args) {
        LinkedList<String> handPoker = new LinkedList<>();
        List<String> deskPoker = Arrays.asList("A", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K");

        for (int index = 0; index < deskPoker.size(); index++) {
            deskPoker(deskPoker.get(index), handPoker);
            if (index < deskPoker.size() - 1) {
                handsBottomToTop(handPoker);
            }
        }
        System.out.println(handPoker);
    }

    private static void deskPoker(String tmpPoker, LinkedList<String> handPoker) {
        handPoker.offerFirst(tmpPoker);
    }


    public static void handsBottomToTop(LinkedList<String> handPoker) {
        String last = handPoker.pollLast();
        handPoker.offerFirst(last);
    }
}
