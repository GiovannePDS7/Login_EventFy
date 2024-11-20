package com.eventfy.login.controller;

import com.eventfy.login.dto.LoginRequestDTO;
import com.eventfy.login.dto.LoginResponseDTO;
import com.eventfy.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/authenticate")
    public ResponseEntity<LoginResponseDTO> authenticate(@RequestParam String usuario, @RequestParam String senha){//@RequestBody LoginRequestDTO loginReques) {
      LoginRequestDTO loginReques = new LoginRequestDTO(usuario, senha);
        LoginResponseDTO response = loginService.autenticar(loginReques);

        return ResponseEntity.ok(response);
    }

}
