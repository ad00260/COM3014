package com.com3018.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Anisha on 08/05/2016.
 */

@Controller
public class LoginController {

    // spring intercepts admin url request

    @RequestMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout, Model model) {

        if (error != null) {
            model.addAttribute("error", "invalid username and password");
        }

        if (logout != null) {
            model.addAttribute("msg", "you have been logged out successfully");
        }

        return "login";
    }
}
