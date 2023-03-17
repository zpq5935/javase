package book.fengkuang.unit08_collection;

public enum BookEnum {
    MATH("数学书"), CHINESE("语文书"), ENGLISH("英文书");
    String name;

    private BookEnum(String name) {
        this.name = name;
    }

}
