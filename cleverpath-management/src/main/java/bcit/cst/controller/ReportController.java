package bcit.cst.controller;

import bcit.cst.pojo.GenderOption;
import bcit.cst.pojo.JobOption;
import bcit.cst.pojo.Result;
import bcit.cst.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/empJobData")
    public Result<JobOption> empJobData() {
        log.info("查询员工岗位分布数据");
        return Result.success(reportService.getJobReport());
    }

    @GetMapping("/empGenderData")
    public Result<List<GenderOption>> empGenderData() {
        return Result.success(reportService.getGenderReport());
    }
}
