package bcit.cst.service;

import bcit.cst.dto.LoginRequestDTO;
import bcit.cst.dto.LoginResponseDTO;

public interface AuthService {
    LoginResponseDTO login(LoginRequestDTO requestDTO);
}
