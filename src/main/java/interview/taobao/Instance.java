package interview.taobao;

public class Instance {
    static Object object;

    public static Object getInstance() {
        if (null == object) {
            synchronized (Instance.class) {
                if (null == object) {
                    object = new Object();
                }
            }
        }
//        object = new Object();
        return object;
    }
}
