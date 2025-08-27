package bcit.cst.service.impl;

import bcit.cst.dto.LoginRequestDTO;
import bcit.cst.dto.LoginResponseDTO;
import bcit.cst.exception.BusinessException;
import bcit.cst.pojo.Emp;
import bcit.cst.repository.EmpRepository;
import bcit.cst.service.AuthService;
import bcit.cst.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final EmpRepository empRepository;

    @Override
    public LoginResponseDTO login(LoginRequestDTO requestDTO) {
        // 1. 根据用户名查找
        Emp emp = empRepository.findByUsername(requestDTO.getUsername())
                .orElseThrow(() -> new BusinessException(0, "用户名不存在"));

        // 2. 校验密码（这里演示明文，真实项目中用 BCrypt）
        if (!emp.getPassword().equals(requestDTO.getPassword())) {
            throw new BusinessException(0, "密码错误");
        }

        // 3. 生成 Token（这里可以用 JWT）
        String token = JwtUtil.generateToken(emp.getId(), emp.getUsername());

        return new LoginResponseDTO(emp.getId(), emp.getUsername(), emp.getName(), token);
    }
}
