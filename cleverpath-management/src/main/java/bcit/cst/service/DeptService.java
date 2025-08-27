package bcit.cst.service;

import bcit.cst.pojo.Dept;

import java.util.List;

public interface DeptService {

    // 查询所有部门
    List<Dept> list();

    // 新增部门
    void add(Dept dept);

    // 根据id删除部门
    void delete(Long id);

    // 根据id查询
    Dept getById(Long id);

    // 更新部门
    void update(Dept dept);
}
