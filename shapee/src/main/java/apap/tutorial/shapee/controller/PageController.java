package apap.tutorial.shapee.controller;

import apap.tutorial.shapee.model.PasswordModel;
import apap.tutorial.shapee.model.UserModel;
import apap.tutorial.shapee.service.RoleService;
import apap.tutorial.shapee.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PageController {
    @Autowired
    RoleService roleService;
    @Autowired
    UserRoleService userRoleService;

    @RequestMapping("/")
    public String home(Model model) {
        UserModel user = userRoleService.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("role", user.getRole().getRole());
        model.addAttribute("listRole", roleService.findAll());
        return "home";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/update-password")
    private String updatePasswordForm(Model model) {
        model.addAttribute("page", "Update Password");
        return "update-password";
    }

    @PostMapping("/update-password")
    private String updatePasswordSubmit(@ModelAttribute PasswordModel password, RedirectAttributes redir, Model model) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        UserModel user = userRoleService.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        if (password.getNewPassword().equals(password.getConfirmPassword())) {
            if (passwordEncoder.matches(password.getOldPassword(), user.getPassword())) {

                if (userRoleService.validatePassword(password.getNewPassword())) {
                    String newPassword = password.getNewPassword();
                    userRoleService.updatePassword(user, newPassword);
                    model.addAttribute("errorState", "Password Anda berhasil diperbarui");

                } else {
                    model.addAttribute("errorState","Password harus terdiri atas angka dan huruf serta minimal memiliki 8 karakter");
                }

            } else {
                model.addAttribute("errorState","Password yang dimasukkan tidak sesuai dengan password lama Anda");
            }

        } else {
            model.addAttribute("errorState","Password baru tidak sesuai dengan yang dikonfirmasi");
        }
        model.addAttribute("page", "Update Password");
        return "update-password";
    }

}
