package com.sergio.oauth2.backend.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PublicController {

    @GetMapping("/public/messages")
    public String publicMessages(@AuthenticationPrincipal OAuth2User user, Model model) {
        model.addAttribute("body", "nobody");
        return "response";
    }
}
