package bcit.cst.controller;

import bcit.cst.pojo.Dept;
import bcit.cst.pojo.Result;
import bcit.cst.service.DeptService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/depts")   // 所有接口路径前缀
public class DeptController {

    private final DeptService deptService;

    public DeptController(DeptService deptService)
    {
        this.deptService = deptService;
    }

    // 1. 查询所有部门
    @GetMapping
    public Result<List<Dept>> list() {
        List<Dept> depts = deptService.list();
        return Result.success(depts);
    }

    // 2. 根据 id 查询部门
    @GetMapping("/{id}")
    public Result<Dept> getById(@PathVariable Long id) {
        Dept dept = deptService.getById(id);
        return dept != null ? Result.success(dept) : Result.error("Department not found");
    }

    // 3. 添加部门
    @PostMapping
    public Result<Void> add(@RequestBody Dept dept) {
        deptService.add(dept);
        return Result.success();
    }

    // 4. 更新部门
    @PutMapping
    public Result<Void> update(@RequestBody Dept dept) {
        deptService.update(dept);
        return Result.success();
    }

    // 5. 删除部门
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        deptService.delete(id);
        return Result.success();
    }

    @DeleteMapping
    public Result<Void> deleteDeptByParam(Long id)
    {
        deptService.delete(id);
        return Result.success();
    }
}
