package interest.japan;

import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

public class WordTest {
    public static void main(String[] args) throws NoSuchFieldException, IOException, IllegalAccessException {
    }

    Character[] charArr = new Character[]{
            'あ', 'い', 'う', 'え', 'お'
            , 'か', 'き', 'く', 'け', 'こ'
            , 'さ', 'し', 'す', 'せ', 'そ'
            , 'た', 'ち', 'つ', 'て', 'と'
            , 'な', 'に', 'ぬ', 'ね', 'の'
            , 'は', 'ひ', 'ふ', 'へ', 'ほ'
            , 'ま', 'み', 'む', 'め', 'も'
            , 'や', 'い', 'よ', 'え', 'よ'
            , 'ら', 'り', 'る', 'れ', 'ろ'
            , 'わ', 'い', 'う', 'え', 'を'
            , 'ん'
    };

    /**
     * 显示字，打发音；显示发音，打字
     * 貌似 junit不支持 io?
     */
    @Test
    public void testAll() throws NoSuchFieldException, IllegalAccessException, InterruptedException {
        List<WordDataBase> beginList = new ArrayList<>(Arrays.asList(WordDataBase.values()));
        Collections.shuffle(beginList);
        Set<WordDataBase> errSet = new HashSet<>();// 记录哪些出错
        Map<WordDataBase, Integer> errCount = new HashMap();// 记录出错的次数
        int mode = 0;// 0-显示发音打印字；1-显示字打印发音
        Class<WordDataBase> baseClass = WordDataBase.class;
        Field pronounce = baseClass.getDeclaredField("pronounce");
        pronounce.setAccessible(true);
        Field chr = baseClass.getDeclaredField("chr");
        chr.setAccessible(true);
        Field[] fields = new Field[]{chr, pronounce};
        System.out.println("*********Begin*************");
        thisIterator(beginList.iterator(), errSet, errCount, fields, mode, true);
        if (errSet.isEmpty()) return;
        System.err.println("has error");
        thisIterator(errSet.iterator(), errSet, errCount, fields, mode, false);
        System.err.println(" error count");
        System.out.println(errCount);
        System.out.println("*********End*************");

    }


    private void thisIterator(Iterator<WordDataBase> iterator, Set errSet, Map<WordDataBase, Integer> errCount, Field[] fields, int mode, boolean add2Set) throws IllegalAccessException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        while (iterator.hasNext()) {
            WordDataBase next = iterator.next();
            Object show = fields[mode].get(next);
            Object check = fields[1 - mode].get(next);
            System.out.println(">>" + show);
            String nextLine = scanner.nextLine();
            if (nextLine.equals(check)) System.out.println("true");
            else {
                System.err.println("false and right is " + check);
                Thread.sleep(1);
                if (add2Set) errSet.add(next);
                errCount.put(next, errCount.get(next) != null ? errCount.get(next) + 1 : errCount.get(next));
            }
            iterator.remove();
        }
    }

    /**
     * 打乱片假名再输出，测试认多少
     */
    @Test
    public void test() {
        List list = new ArrayList<>();
        list.addAll(Arrays.asList(charArr));
        Collections.shuffle(list);
        Character[] copyArr = new Character[list.size()];
        list.toArray(copyArr);
        for (int i = 0; i < copyArr.length; i++) {
            System.out.print(copyArr[i] + "\t");
            if (i > 1 && (i + 1) % 10 == 0) System.out.println();
        }
    }


    @Test
    public void generate() {
        final String CHR = "CHR";
        final String PRONOUNCE = "PRONOUNCE";
        char[] x = new char[]{'a', 'i', 'u', 'e', 'o'};
        char[] y = new char[]{' ', 'k', 's', 't', 'n', 'h', 'm', 'y', 'r', 'w'};
        String template = PRONOUNCE + "('" + CHR + "',\"" + PRONOUNCE + "\")";
        for (int i = 0; i < charArr.length; i++) {
            String curPro = new StringBuilder().append(y[i / 5]).append(x[i % 5]).toString();
            System.out.print(template.replaceAll(PRONOUNCE, curPro).replaceAll(CHR, String.valueOf(charArr[i])));
            System.out.print(",");
            if (i > 1 && (i + 1) % 5 == 0) System.out.println();
        }
    }
}
