package com.neohack.backend.controller;

import com.neohack.backend.exception.NoElementException;
import com.neohack.backend.exception.SesCodeException;
import com.neohack.backend.service.UserService;
import com.neohack.backend.service.impl.MailService;
import com.neohack.backend.service.impl.SesCodeService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RestorePassController {

    private final UserService userService;
    private final SesCodeService sesCodeService;

    @ApiOperation(value = "Страница восстановления доступа к аккаунту № 1")
    @GetMapping("/restore/{email}")
    public void sendSesCodeToEmail(@PathVariable String email) throws NoElementException {
        sesCodeService.sendSesToEmail(email);
    }

    @ApiOperation(value = "Страница восстановления доступа к аккаунту № 2")
    @PostMapping("/restore/{email}/ses")
    public void resetPassBySesCode(@RequestBody Integer sesCode, @PathVariable String email) throws SesCodeException {
        sesCodeService.checkSesCode(sesCode, email);
    }

    @ApiOperation(value = "Страница для создания нового пароля")
    @PostMapping("/restore/{email}/new_pass")
    public void setNewPass(@RequestBody String newPassword, @PathVariable String email) throws NoElementException {
        userService.setNewPassByEmail(email, newPassword);
    }

}
