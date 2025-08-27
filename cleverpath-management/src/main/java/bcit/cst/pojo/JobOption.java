package bcit.cst.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用于 ECharts 的岗位统计返回对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOption {
    private List<String> jobList;  // 岗位名称
    private List<Long> dataList;   // 对应数量
}
