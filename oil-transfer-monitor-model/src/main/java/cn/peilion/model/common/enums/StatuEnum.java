package cn.peilion.model.common.enums;

public enum StatuEnum {
    EXCELLENT(0, "健康"),
    GOOD(1, "良好"),
    MODERATE(2, "一般"),
    POOR(3, "劣化"),
    OFFLINE(4, "离线");

    int code;
    String statu;

    StatuEnum(int code, String statu) {
        this.code = code;
        this.statu = statu;
    }

    public int getCode() {
        return code;
    }

    public String getStatu() {
        return statu;
    }

}
