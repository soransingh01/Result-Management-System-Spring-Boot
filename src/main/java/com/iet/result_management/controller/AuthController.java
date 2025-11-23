package com.iet.result_management.controller;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String confirmPassword,
                          HttpSession session) {

        if (!password.equals(confirmPassword)) {
            return "redirect:/login?mismatch=true";
        }

        if (username.equals("admin") && password.equals("admin")) {
            session.setAttribute("admin", true);
            return "redirect:index";
        }

        return "redirect:/login?error=true";
    }

    @GetMapping("/forgot-password")
    public String forgotPasswordPage(){
        return "forgot-password";
    }

     @GetMapping("/index")
    public String indexPage(){
        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}


