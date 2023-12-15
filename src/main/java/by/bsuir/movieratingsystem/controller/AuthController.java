package by.bsuir.movieratingsystem.controller;

import by.bsuir.movieratingsystem.entity.User;
import by.bsuir.movieratingsystem.exception.ServiceException;
import by.bsuir.movieratingsystem.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/signup")
    public String getSignUpPage() {
        return "registration";
    }

    @PostMapping("/signup")
    public String signUp(@RequestParam String username, @RequestParam String password, @RequestParam String rePassword, Model model) {
        if (!Objects.equals(password, rePassword)) {
            model.addAttribute("message", "Passwords should match");
            return "registration";
        }
        if (password.length() < 8) {
            model.addAttribute("message", "Password should be at least 8 characters");
            return "registration";
        }
        String salt = BCrypt.gensalt(12);
        try {
            userService.register(username, BCrypt.hashpw(password, salt));
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "registration";
        }
        model.addAttribute("message", "Successful registration");
        return "redirect:/auth/signin";
    }

    @GetMapping("/signin")
    public String getSignInPage() {
        return "login";
    }

    @PostMapping("/signin")
    public String signIn(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        try {
            User user = userService.login(username, password);
            if (!user.getIsBanned()) {
                session.setAttribute("user", user);
                return "redirect:/movies";
            } else {
                model.addAttribute("message", "This user is banned");
                return "login";
            }
        } catch (ServiceException e) {
            model.addAttribute("message", e.getMessage());
            return "login";
        }
    }

    @PostMapping("/logout")
    public String doLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/signin";
    }
}
