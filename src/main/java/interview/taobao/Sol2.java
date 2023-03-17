package interview.taobao;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Sol2 {
    public static void main(String[] args) {
        Set<Object> objects = Collections.synchronizedSet(new HashSet<Object>() {
        });
        for (int i = 0; i < 10; i++)
            new Thread(() -> {
                System.out.println(objects.add(Instance.getInstance()));
            }).start();
    }
}
