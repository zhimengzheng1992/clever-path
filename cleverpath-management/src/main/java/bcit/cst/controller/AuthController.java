package bcit.cst.controller;

import bcit.cst.dto.LoginRequestDTO;
import bcit.cst.dto.LoginResponseDTO;
import bcit.cst.pojo.Result;
import bcit.cst.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {

    private final AuthService loginService;

    /**
     * 登录接口
     */
    @PostMapping("/login")
    public Result<LoginResponseDTO> login(@RequestBody LoginRequestDTO requestDTO) {
        log.info("用户登录请求: {}", requestDTO.getUsername());

        LoginResponseDTO responseDTO = loginService.login(requestDTO);

        return Result.success(responseDTO);
    }
}
