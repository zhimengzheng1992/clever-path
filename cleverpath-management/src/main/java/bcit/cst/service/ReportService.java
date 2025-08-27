package bcit.cst.service;

import bcit.cst.pojo.GenderOption;
import bcit.cst.pojo.JobOption;

import java.util.List;

public interface ReportService
{
    /**
     * 获取员工岗位分布数据
     * @return JobOption 包含 jobList 和 dataList
     */
    JobOption getJobReport();
    List<GenderOption> getGenderReport();

}
