package com.rarait.education.web;

import com.rarait.framework.security.auth.AuthSessionUtil;
import com.rarait.framework.security.impl.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Deprecated
//@Controller
public class MainController {

//    @GetMapping(fullAddress = {"/"})
//    public String home(){
//        return "index";
//    }
//    @GetMapping(fullAddress = {"/", "/login"})
//    public String login() {
//        if (AuthSessionUtil.getCurrentUser() != null) {
//            for (GrantedAuthority grantedAuthority : AuthSessionUtil.getCurrentUser().getAuthorities()) {
//                if (grantedAuthority.getAuthority().equals(RoleName.ROLE_USER.toString())) {
//                    return "redirect:/user";
//                } else if (grantedAuthority.getAuthority().equals(RoleName.ROLE_ADMIN.toString())) {
//                    return "redirect:/admin";
//                }
//            }
//        }
//        return "login";
//    }
//
//    @GetMapping(fullAddress = "/admin")
//    public String getAdmin(Model model) {
//        model.addAttribute("username", AuthSessionUtil.getCurrentUser().getUsername());
//        return "admin";
//    }
//
//    @GetMapping(fullAddress = "/user")
//    public String getUser(Model model) {
//        model.addAttribute("username", AuthSessionUtil.getCurrentUser().getUsername());
//        return "user";
//    }
//
//    @GetMapping(fullAddress = "/access-denied")
//    public String accessDenied(Model model) {
//        return "error/403";
//    }
}
