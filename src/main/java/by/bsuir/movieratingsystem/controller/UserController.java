package by.bsuir.movieratingsystem.controller;

import by.bsuir.movieratingsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @SneakyThrows
    @GetMapping
    public String getUsersPage(Model model) {
        model.addAttribute("users", userService.getUserList());
        return "admin";
    }

    @PostMapping("/{id}")
    public String updateUser(
            @PathVariable Long id,
            @RequestParam int status,
            @RequestParam boolean isBanned,
            Model model
    ) {
        userService.updateUser(id, status, isBanned);
        return "redirect:/users";
    }
}
