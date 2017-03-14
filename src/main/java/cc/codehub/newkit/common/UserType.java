package cc.codehub.newkit.common;


public enum UserType {

    /**
     * 普通员工
     */
    NORMAL("普通员工", 0),

    /**
     * 管理员
     */
    ADMIN("管理员", 1);


    // 成员变量
    private final String key;
    private final Integer value;

    UserType(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public Integer getValue()
    {
        return this.value;
    }
}
