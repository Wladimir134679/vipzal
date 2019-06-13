package com.my.vipzal.controllers;

import com.my.vipzal.forms.TrainerData;
import com.my.vipzal.forms.TrainerHeader;
import com.my.vipzal.forms.UserHeader;
import com.my.vipzal.services.*;
import com.my.vipzal.table.Trainer;
import com.my.vipzal.table.TrainerTokenCookies;
import com.my.vipzal.table.User;
import com.my.vipzal.table.UserTokenCookies;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;

@ControllerAdvice
public class MyControllerAdvice {

    @Resource(name = "trainerService")
    public TrainerService trainerService;
    @Resource(name = "trainerTokenCookies")
    public TrainerTokenCookiesService trainerTokenCookiesService;
    @Resource(name = "trainingsService")
    public TrainingsService trainingsService;
    @Resource(name = "userService")
    public UserService userService;
    @Resource(name = "tokenCookies")
    public TokenCookies tokenCookies;
    @Resource(name = "diseasesService")
    public DiseasesService diseasesService;

    @ModelAttribute("header")
    public void modelHeader(Model model,
                            @CookieValue(name = "tokenTrainer", required = false) Cookie cookieTrainer,
                            @CookieValue(name = "tokenUser", required = false) Cookie cookieUser){
        if(cookieTrainer != null){
            TrainerTokenCookies token = trainerTokenCookiesService.get(cookieTrainer.getValue());
            if(token != null){
                Trainer trainer = trainerService.get(token.idTrainer);
                if(trainer != null){
                    TrainerHeader data = new TrainerHeader();
                    data.trainer = trainer;
                    model.addAttribute("isConnectionTrainer", true);
                    model.addAttribute("trainerHeader", data);
                }
            }
        }
        if(cookieUser != null){
            UserTokenCookies token = tokenCookies.get(cookieUser.getValue());
            if(token != null){
                User user = userService.get(token.idUser);
                if(user != null){
                    UserHeader data = new UserHeader();
                    data.user = user;
                    model.addAttribute("isConnectionUser", true);
                    model.addAttribute("userHeader", data);
                }
            }
        }
    }
}
