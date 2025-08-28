package bcit.cst.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用于返回 ECharts 饼图的数据项
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenderOption {
    private String name;   // 饼图的标签
    private Long value;    // 饼图的值
}
