package bcit.cst.service.impl;

import bcit.cst.pojo.Dept;
import bcit.cst.repository.DeptRepository;
import bcit.cst.service.DeptService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zhimeng Zheng
 * @version 2025-08-20
 */
@Service
public class DeptServiceImpl implements DeptService
{

    private final DeptRepository deptRepository;

    public DeptServiceImpl(DeptRepository deptRepository)
    {
        this.deptRepository = deptRepository;
    }

    @Override
    public List<Dept> list() {
        return deptRepository.findAllByOrderByUpdateTimeDesc();
    }

    @Override
    public void add(Dept dept) {
        deptRepository.save(dept);
    }

    @Override
    public void delete(Long id) {
        deptRepository.deleteById(id);
    }

    @Override
    public Dept getById(Long id) {
        return deptRepository.findById(id).orElse(null);
    }

    @Override
    public void update(Dept dept) {
        deptRepository.save(dept);
    }
}
