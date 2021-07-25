package parominsky.evgeny.belbanka.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import parominsky.evgeny.belbanka.dto.RoleDTO;
import parominsky.evgeny.belbanka.dto.UserDTO;
import parominsky.evgeny.belbanka.entity.User;
import parominsky.evgeny.belbanka.exception.UserAlreadyExistException;
import parominsky.evgeny.belbanka.service.RoleService;
import parominsky.evgeny.belbanka.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    private static final String ADD_MODAL = "adm/modal/addUser";


    private UserService userService;
    private RoleService roleService;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @ModelAttribute("roles")
    public List<RoleDTO> initializeProfiles() {
        List<RoleDTO> roles = roleService.getAllRoles().stream().collect(toList());
        return roles;
    }

    @GetMapping("/addUser")
    public String createUser(Model model) {
        try {
            model.addAttribute("userDTO", new UserDTO());
            return ADD_MODAL;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ADD_MODAL;
        }
    }

    @GetMapping("/allUsers")
    public String showUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "adm/userTable";
    }

    @GetMapping(value = "/deleteUser")
    public String deleteUser(Integer id, Model model) {
        try{
            userService.deleteUser(id);
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("message", "Пользователь успешно удален");
            model.addAttribute("alertClass", "alert-success");
            return"adm/userTable";
        }catch(Exception e){
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("message", "Ошибка удаления пользователя");
            model.addAttribute("alertClass", "alert-danger");
            return "adm/userTable";
        }
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserDTO userDTO, BindingResult bindingResult,
                               @RequestParam Optional<List<RoleDTO>> roles, Model model) {
        if (bindingResult.hasErrors()) {
            return "adm/modal/addUser";
        } else {
            try {
                userService.addUser(userDTO);
                model.addAttribute("users", userService.getAllUsers());
                model.addAttribute("message", "Пользователь успешно добавлен");
                model.addAttribute("alertClass", "alert-success");
                return "adm/userTable";
            } catch (UserAlreadyExistException e) {
                bindingResult.rejectValue("name", e.getMessage());
                return "adm/modal/addUser";
            }
        }
    }

    @GetMapping("/editUser")
    public String editUser(Integer id, Model model) {
        UserDTO userDTO = userService.getUserById(id);

        model.addAttribute("userDTO", userDTO);
        return "adm/modal/editUser";
    }

    // @PatchMapping("/update")
    @PostMapping("/update")
    public String updateUser(UserDTO userDTO, Model model) {
        try {
            User user = userService.getById(userDTO.getId());
            userService.editUser(userDTO);
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("message", "Пользователь успешно изменен");
            model.addAttribute("alertClass", "alert-success");
            return "adm/userTable";
        } catch (Exception e) {
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("message", "Ошибка редактирования пользователя");
            model.addAttribute("alertClass", "alert-danger");
            return "adm/userTable";
        }
    }
}