package com.my.vipzal.controllers;

import com.my.vipzal.forms.UserFormAuth;
import com.my.vipzal.forms.UserFormReg;
import com.my.vipzal.services.TokenCookies;
import com.my.vipzal.services.UserService;
import com.my.vipzal.table.User;
import com.my.vipzal.table.UserTokenCookies;
import com.sun.org.apache.bcel.internal.generic.GETFIELD;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class LogIO {

    @Resource(name = "userService")
    public UserService users;
    @Resource(name = "tokenCookies")
    public TokenCookies tokenCookies;

    @RequestMapping(path = "/exit", method = RequestMethod.GET)
    public String exit(Model model, HttpServletResponse response, HttpServletRequest request){
        Cookie remove = new Cookie("tokenUser", "");
        remove.setMaxAge(0);
        response.addCookie(remove);
        String referer = request.getHeader("referer");
        if(referer == null)
            referer = "/index";
        return "redirect:" + referer;
    }

    @RequestMapping(path = "/auth", method = RequestMethod.POST)
    public String auth(@ModelAttribute("userForm") UserFormAuth userForm, Model model, HttpServletResponse response){

        User user = users.get(userForm.email, userForm.password);
        if(user == null){
            model.addAttribute("error", "Не верные данные");
            return "/authorization";
        }else{
            UserTokenCookies token = new UserTokenCookies();
            token.token = UUID.randomUUID().toString();
            token.idUser = user.id;
            token.timeCreate = System.currentTimeMillis();
            token.time = 1000*60;

            tokenCookies.save(token);

            Cookie cookie = new Cookie("tokenUser", token.token);
            cookie.setMaxAge((int)token.time);
            response.addCookie(cookie);
            return "redirect:/index";
        }
    }

    @RequestMapping(path = "/authorization", method = RequestMethod.GET)
    public String authorization(Model model){
        UserFormAuth auth = new UserFormAuth();
        model.addAttribute("userForm", auth);
        return "authorization";
    }

    @RequestMapping(path = "/reg", method = RequestMethod.POST)
    public String registration(Model model, @ModelAttribute("userForm") UserFormReg reg, HttpServletResponse response){

        if(!reg.password.equals(reg.password1)) {
            model.addAttribute("errorMessage", "Пароли не совпали");
            return "registration";
        }

        User user = new User();
        user.fio = reg.fio;
        user.addressResidence = reg.address;
        user.age = reg.age;
        user.email = reg.email;
        user.growth = reg.growth;
        user.mass = reg.mass;
        user.password = reg.password;
        user.phoneNumber = reg.phone;

        users.save(user);

        UserTokenCookies cookies = new UserTokenCookies();
        cookies.idUser = user.id;
        cookies.time = 1000*60;
        cookies.token = UUID.randomUUID().toString();
        cookies.timeCreate = System.currentTimeMillis();
        System.out.println("length: " + cookies.token.length());
        tokenCookies.save(cookies);

        Cookie cookie = new Cookie("tokenUser", cookies.token);
        cookie.setMaxAge((int)cookies.time);
        response.addCookie(cookie);

        return "redirect:/index";
    }

    @RequestMapping(path = "/registration", method = RequestMethod.GET)
    public String registrationForm(Model model){
        UserFormReg userFormReg = new UserFormReg();
        model.addAttribute("userForm", userFormReg);
        return "registration";
    }
}
