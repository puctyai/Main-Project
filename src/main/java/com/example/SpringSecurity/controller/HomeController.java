package com.example.SpringSecurity.controller;



import com.example.SpringSecurity.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.example.SpringSecurity.model.User;
import com.example.SpringSecurity.repository.UserRepository;
import com.example.SpringSecurity.service.ServiceUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequiredArgsConstructor
public class HomeController {
    @Autowired
    private final ServiceUser serviceUser;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private ItemService itemService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/index")
    public String mainPage(){
        System.out.println(serviceUser.getCurrentUser().getEmail());
        return "redirect:/";
    }


    @PreAuthorize("isAnonymous()")
    @GetMapping(value = "/")
    public String main(){
        return "main";
    }

    @GetMapping(value = "/register")
    public String registration() {

        return "register";
    }

    @GetMapping(value = "/sign")
    public String signIn() {
        return "sign-in";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/profil")
    public String getProfil(){
        return "change-password";
    }




    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/forbidden")
    public String forbidden(){
        return "forbidden";
    }


    @GetMapping(value = "/items")
    public String items(Model model){
        model.addAttribute("items",itemService.getAllItems());
        return "items";
    }


    @PreAuthorize("isAnonymous()")
    @PostMapping(value = "/register")
    public String register(User user, @RequestParam String rePassword){
      String result = serviceUser.addUser(user, rePassword);
        return "redirect:/register?" + result;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/change-password")
    public String changePass(@RequestParam String currentPass,
                             @RequestParam String newPass,
                             @RequestParam String reNewPass){
        String result=  serviceUser.changePass(currentPass, newPass, reNewPass);
return "redirect:/change-password?"+result ;
    }


}
