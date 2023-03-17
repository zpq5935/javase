package util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;

public class RandomUtil {
    public static List random(int length, Class baseEle, Map<Field, List> fieldRepo) throws Exception {
        Random random = new Random();
        List list = new ArrayList();
        Constructor constructor = baseEle.getConstructor();
        for (int i = 0; i < length; i++) {
//            Object o = baseEle.newInstance();?
            constructor.setAccessible(true);
            Object o = constructor.newInstance(null);
            //
            Iterator<Map.Entry<Field, List>> iterator = fieldRepo.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Field, List> next = iterator.next();
                Field field = next.getKey();
                List fieldList = next.getValue();
                int r = random.nextInt(fieldList.size());
                field.setAccessible(true);
                field.set(o, fieldList.get(r));
            }
            list.add(o);
        }
        return list;
    }

}
