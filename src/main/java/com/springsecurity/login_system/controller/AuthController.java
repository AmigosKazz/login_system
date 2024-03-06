package com.springsecurity.login_system.controller;

import com.springsecurity.login_system.dto.UserDto;
import com.springsecurity.login_system.entity.User;
import com.springsecurity.login_system.service.UserService;
import jakarta.validation.Valid;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.naming.Binding;
import java.util.List;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // handler methode to handler home page request
    @GetMapping("/index")
    public String home() {
        return "index";
    }

    //handler methode to handler user registration form request
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        //create model object to store form data
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "register";
    }

    //handler methode to handler user registration form submit request
    @PostMapping("/register/save")
    public  String registration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue(
                    "email",
                    null,
                    "There is already an account registered with that email");
        }

        if(result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    //handler methode to handler list of user
    @GetMapping("/user")
    public String listUser(Model model) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
