package bcit.cst.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JobEnum {
    TEACHER(1, "班主任"),
    LECTURER(2, "讲师"),
    MANAGER1(3, "学工主管"),
    MANAGER2(4, "教研主管"),
    CONSULTANT(5, "咨询师"),
    OTHER(99, "其他");

    private final int code;
    private final String label;

    // 根据 code 查找 label
    public static String fromCode(Object code) {
        if (code == null) {
            return "未分类";
        }
        int intCode;
        if (code instanceof Number) {
            intCode = ((Number) code).intValue();
        } else {
            try {
                intCode = Integer.parseInt(code.toString());
            } catch (NumberFormatException e) {
                return "未知";
            }
        }
        for (JobEnum job : values()) {
            if (job.code == intCode) {
                return job.label;
            }
        }
        return "未知";
    }
}
