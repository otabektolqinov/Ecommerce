package com.company.ecommerce.mvc.admin;

import com.company.ecommerce.dto.request.UserRequestDto;
import com.company.ecommerce.dto.response.UserResponseDto;
import com.company.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class UserMvcController {

    private final UserService userService;

    @GetMapping("/get-all")
    public String users(Model model) {
        List<UserResponseDto> content = userService.getAll().getContent();
        model.addAttribute("users", content);
        return "admin/users/users :: content";
    }

    @GetMapping("/create")
    public String createUserForm() {
        return "admin/users/user-form :: form";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute UserRequestDto dto,
                             Model model) {
        userService.createUser(dto);
        model.addAttribute("users", userService.getAll().getContent());
        return "admin/users/users :: content";
    }

    @GetMapping("/edit/{id}")
    public String updateUserForm(@PathVariable String id, Model model){
        System.out.println(id);
        UserResponseDto content = userService.getUsersById(Long.parseLong(id)).getContent();
        model.addAttribute("user", content);
        model.addAttribute("id", id);
        return "admin/users/user-edit :: form";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute UserRequestDto dto, Model model){
        userService.updateUserById(id, dto);
        model.addAttribute("users", userService.getAll().getContent());
        return "admin/users/users :: content";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }
}
