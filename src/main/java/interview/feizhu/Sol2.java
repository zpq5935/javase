package interview.feizhu;

import java.math.BigDecimal;
import java.util.*;

// 第2题
public class Sol2 {
    public static void main(String[] args) {
        Shop observable = new Shop("Gtx1060", BigDecimal.valueOf(1200));
        for (Observer member : loadMembers("小张", "小丽", "王明")) {
            observable.addObserver(member);
        }
        observable.changePrice();
    }

    private static List<Observer> loadMembers(String... names) {
        return Arrays.asList(new Consumer("小王"), new Provider("xx供货商"));
    }

    // 消费者
    static class Consumer implements Observer {
        public Consumer(String name) {
            this.name = name;
        }

        private String name;

        @Override
        public void update(Observable o, Object arg) {
            System.out.println(name + "太好了，又降了，赶紧入手");
        }
    }

    // 供货商
    static class Provider implements Observer {
        public Provider(String name) {
            this.name = name;
        }

        private String name;

        @Override
        public void update(Observable o, Object arg) {
            System.out.println(name + " 呀，咋价格又降了");
        }
    }

    // 商品
    static class Shop extends Observable {
        public void changePrice() {
            super.setChanged();
            super.notifyObservers();
        }

        private String name;
        private BigDecimal price;

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public Shop(String name, BigDecimal price) {
            this.name = name;
            this.price = price;
        }
    }


}
