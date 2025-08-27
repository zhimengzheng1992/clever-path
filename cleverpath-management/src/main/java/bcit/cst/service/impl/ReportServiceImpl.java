package bcit.cst.service.impl;

import bcit.cst.enums.GenderEnum;
import bcit.cst.enums.JobEnum;
import bcit.cst.pojo.GenderOption;
import bcit.cst.pojo.JobOption;
import bcit.cst.repository.EmpRepository;
import bcit.cst.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final EmpRepository empRepository;

    @Override
    public JobOption getJobReport() {
        List<Object[]> rows = empRepository.countByJob();

        List<String> jobList = new ArrayList<>();
        List<Long> dataList = new ArrayList<>();

        for (Object[] row : rows) {
            // row[0] 是 job 值（数字），用枚举映射成中文
            String jobName = JobEnum.fromCode(row[0]);
            jobList.add(jobName);

            // row[1] 是数量
            dataList.add(((Number) row[1]).longValue());
        }

        return new JobOption(jobList, dataList);
    }

    @Override
    public List<GenderOption> getGenderReport() {
        List<Object[]> rows = empRepository.countByGender();

        List<GenderOption> result = new ArrayList<>();
        for (Object[] row : rows) {
            Integer genderCode = (Integer) row[0];
            Long count = ((Number) row[1]).longValue();

            String name;
            if (genderCode != null && genderCode == 1) {
                name = "男性员工";
            } else if (genderCode != null && genderCode == 2) {
                name = "女性员工";
            } else {
                name = "未分类";
            }

            result.add(new GenderOption(name, count));
        }
        return result;
    }
}
