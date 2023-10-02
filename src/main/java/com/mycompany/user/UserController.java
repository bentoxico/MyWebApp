package com.mycompany.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String showUserList(Model model) {
        List<User> list = userService.listAll();
        model.addAttribute("listUsers", list);
        return "users";
    }

    @GetMapping("/users/new")
    public String createUser(Model model) {

        model.addAttribute("user", new User());

        return "form";

    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes redirectAttributes) {
        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("message", "The user has been saved successfully");
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        try {
            User user = userService.getUser(id);
            model.addAttribute("user", user);
            return "form";
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/users/delete/{id}")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            User user = userService.getUser(id);
            userService.deleteUser(user);
            redirectAttributes.addFlashAttribute("message", "The user has been deleted successfully");
            return "redirect:/users";
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
