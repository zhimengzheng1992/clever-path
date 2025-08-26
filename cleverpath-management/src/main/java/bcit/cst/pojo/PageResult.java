package bcit.cst.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页结果封装类
 * @author Zhimeng Zheng
 * @version 2025-08-21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T>
{
    private Long total; // 总记录数
    private List<T> rows;   // 当前页的数据
}
