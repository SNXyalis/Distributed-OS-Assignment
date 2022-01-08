package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.AithshRepository;
import com.example.demo.repository.KateuthinshRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @GetMapping("/")
    public String mainPage() {
        return "redirect:/login";
    }

    //Home--------------------------------------------------------------------------------------->
    @GetMapping("/home")
    @Secured("ROLE_ADMIN") //Pros to paron einai anoixto gia olous gia na API testing
    public String homePage(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }

    //User--------------------------------------------------------------------------------------->
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/show-users")
    @Secured("ROLE_ADMIN")
    public String showUsers(Model model) {
        model.addAttribute("people", userRepository.findAll());
        return "User/index";
    }

    @GetMapping("/new-user")
    @Secured("ROLE_ADMIN")
    public String addUserForm(Model ac) {
        ac.addAttribute("user", new User());
        return "User/NewUser/index";
    }

    @GetMapping("/edit-user")
    @Secured("ROLE_ADMIN")
    public String editUserPage() {
        return "User/EditUser/index";
    }

    @GetMapping("/delete-user")
    @Secured("ROLE_ADMIN")
    public String deleteUserPage() {
        return "User/DeleteUser/index";
    }

    //Form--------------------------------------------------------------------------------------->
    @Autowired
    private AithshRepository aithshRepository;

    @GetMapping("/show-forms")
    @Secured({ "ROLE_ADMIN", "ROLE_TEACHER","ROLE_GRAMMATEIA" })
    public String showForms(Model model) {
        model.addAttribute("forms", aithshRepository.findAll() );
        return "form/index";
    }

    @GetMapping("/new-form")
    @Secured({ "ROLE_ADMIN","ROLE_STUDENT" })
    public String addForm() {
        return "form/NewForm/index";
    }

    @GetMapping("/edit-form")
    @Secured({ "ROLE_ADMIN","ROLE_STUDENT", "ROLE_TEACHER","ROLE_GRAMMATEIA" })
    public String editForm() {
        return "form/EditForm/index";
    }

    @GetMapping("/delete-form")
    @Secured("ROLE_ADMIN")
    public String deleteForm() {
        return "form/DeleteForm/index";
    }

    //Postgraduta Program--------------------------------------------------------------------------------------->
    @Autowired
    private KateuthinshRepository kateuthinshRepository;

    @GetMapping("/show-pgs")
    public String showPgs(Model model) {
        model.addAttribute("programs", kateuthinshRepository.findAll());
        return "pgProgram/index";
    }

    @GetMapping("/new-pg")
    @Secured("ROLE_ADMIN")
    public String addPgForm() {
        return "pgProgram/NewPg/index";
    }

    @GetMapping("/edit-pg")
    @Secured("ROLE_ADMIN")
    public String editPgPage() {
        return "pgProgram/EditPg/index";
    }

    @GetMapping("/delete-pg")
    @Secured("ROLE_ADMIN")
    public String deletePgPage() {
        return "pgProgram/DeletePg/index";
    }

}
