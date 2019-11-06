package apap.tutorial.shapee.controller;

import apap.tutorial.shapee.model.UserModel;
import apap.tutorial.shapee.service.RoleService;
import apap.tutorial.shapee.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    private String addUserSubmit(@ModelAttribute UserModel user, Model model) {
        userRoleService.addUser(user);
        model.addAttribute("listRole", roleService.findAll());
        return "home";
    }
}
