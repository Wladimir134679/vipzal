package com.my.vipzal.controllers;

import com.my.vipzal.forms.TrainerData;
import com.my.vipzal.forms.UserHeaderForm;
import com.my.vipzal.services.TokenCookies;
import com.my.vipzal.services.TrainerService;
import com.my.vipzal.services.UserService;
import com.my.vipzal.table.Trainer;
import com.my.vipzal.table.User;
import com.my.vipzal.table.UserTokenCookies;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Resource(name = "userService")
    public UserService users;
    @Resource(name = "tokenCookies")
    public TokenCookies tokenCookies;
    @Resource(name = "trainerService")
    public TrainerService trainerService;

    @RequestMapping(path = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model){

        List<Trainer> trainerList = trainerService.getAll();
        List<TrainerData> listData = new ArrayList<>();
        for(Trainer t : trainerList){
            listData.add(new TrainerData(t));
        }
        model.addAttribute("trainersList", listData);

        return "index";
    }

    @RequestMapping(path = "/fragmentsData")
    public String fragments(Model model){
        System.out.println("FRAGMENTS");
        return "fragments";
    }

    @RequestMapping(path = "/trainers", method = RequestMethod.GET)
    public String trainers(Model model){
        List<Trainer> trainerList = trainerService.getAll();
        List<TrainerData> listData = new ArrayList<>();
        for(Trainer t : trainerList){
            TrainerData data = new TrainerData(t);
            listData.add(data);
        }
        model.addAttribute("trainersList", listData);
        return "trainers";
    }

    @RequestMapping(path = "/about", method = RequestMethod.GET)
    public String about(Model model){
        return "about";
    }
}
