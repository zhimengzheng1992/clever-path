package bcit.cst.enums;

public enum GenderEnum {
    MALE(1, "男"),
    FEMALE(2, "女");

    private final int code;
    private final String label;

    GenderEnum(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public static String fromCode(Object code) {
        if (code == null) return "未知";
        int c = (code instanceof Number) ? ((Number) code).intValue() : Integer.parseInt(code.toString());
        for (GenderEnum g : values()) {
            if (g.code == c) {
                return g.label;
            }
        }
        return "未知";
    }
}

