package com.jfsd.travel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jfsd.travel.model.Booking;
import com.jfsd.travel.model.User;
import com.jfsd.travel.service.BookingService;
import com.jfsd.travel.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private BookingService bookingService;

    @GetMapping("register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("register")
    public String registerUser(@ModelAttribute User user) {
        userService.registerUser(user);
        return "redirect:/login";
    }

    @GetMapping("login")
    public String loginForm(@RequestParam(value = "redirect", required = false) String redirect, Model model) {
        model.addAttribute("redirect", redirect);
        return "login";
    }

    @PostMapping("login")
    public String login(@RequestParam String username, @RequestParam String password,
                        @RequestParam(value = "redirect", required = false) String redirect,
                        HttpSession session) {
        User user = userService.authenticate(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            if (redirect != null && !redirect.isEmpty()) {
                return "redirect:" + redirect;
            }
            return "redirect:/profile";
        }
        return "login?error=true";
    }

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login?redirect=/profile";
        }
        List<Booking> bookings = bookingService.getBookingsByUser(user);
        model.addAttribute("user", user);
        model.addAttribute("bookings", bookings);
        return "profile";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
