package com.my.vipzal.controllers;

import com.my.vipzal.forms.*;
import com.my.vipzal.services.*;
import com.my.vipzal.table.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProfileController {


    @Resource(name = "userService")
    public UserService users;
    @Resource(name = "tokenCookies")
    public TokenCookies tokenCookies;
    @Resource(name = "trainerForUser")
    public TrainerForUserService trainerForUserService;
    @Resource(name = "trainerService")
    public TrainerService trainerService;
    @Resource(name = "trainingsService")
    public TrainingsService trainingsService;
    @Resource(name = "diseasesService")
    public DiseasesService diseasesService;

    @RequestMapping(path = "/profile", method = RequestMethod.GET)
    public String mainProfile(Model model,
                              @CookieValue(name = "tokenUser", required = false) Cookie cookieUser,
                              @RequestParam(name = "mode", required = false) String mode,
                              @RequestParam(name = "win", required = false) String win){
        if(mode == null)
            mode = "null";

        if(cookieUser != null && cookieUser.getValue() != null) {
            UserTokenCookies token = tokenCookies.get(cookieUser.getValue());
            if (token != null) {
                User user = users.get(token.idUser);

                switch (mode){
                    case "null":
                    case "main": modeMain(model, user); break;
                    case "schedule": modeSchedule(model, user); break;
                    case "setting": modeSetting(model, user); break;
                }

                if(win != null) {
                    switch (win) {
                        case "addDisease": winAddDisease(model); break;
                    }
                }
            }
        }else{
            return "redirect:/index";
        }

        return "profile";
    }

    @RequestMapping(path = "/profile/setting/save", method = RequestMethod.POST)
    public String settingSave(Model model,
                              @CookieValue(name = "tokenUser", required = false) Cookie cookie,
                              @ModelAttribute("userForm") UserFormReg form){

        if(cookie != null){
            UserTokenCookies token = tokenCookies.get(cookie.getValue());
            if(token != null){
                User user = users.get(token.idUser);
                if(user != null){
                    user.fio = form.fio;
                    user.email = form.email;
                    user.growth = form.growth;
                    user.mass = form.mass;
                    user.password = form.password;
                    user.phoneNumber = form.phone;
                    user.age = form.age;
                    user.addressResidence = form.address;

                    users.update(user);
                }
            }
        }

        return "redirect:/profile?mode=setting";
    }

    private void winAddDisease(Model model){
        model.addAttribute("isAddDisease", true);
        model.addAttribute("diseaseForm", new DiseaseForm());
    }

    private void modeMain(Model model, User user){
        model.addAttribute("modeDraw", "main");
        UserProfileForm form = new UserProfileForm(user);
        model.addAttribute("userData", form);

        List<Disease> listDisease = diseasesService.getAllForUser(user.id);
        List<DiseaseData> listDataDisease = new ArrayList<>();
        for(Disease d : listDisease){
            listDataDisease.add(new DiseaseData(d));
        }
        model.addAttribute("listDiseases", listDataDisease);
    }

    private void modeSchedule(Model model, User user){
        model.addAttribute("modeDraw", "schedule");

        List<Trainings> list = trainingsService.getForUser(user.id);
        List<TrainingsData> listData = new ArrayList<>();
        HashMap<Long, Trainer> mapTrainer = new HashMap<>();
        for(Trainings trainings : list){
            TrainingsData data = new TrainingsData(trainings);
            Trainer trainer = trainerService.get(trainings.idTrainer);
            data.trainer = trainer;
            if(mapTrainer.get(trainer.id) == null){
                mapTrainer.put(trainer.id, trainer);
            }
            listData.add(data);
        }

        List<TrainerData> listTrainer = new ArrayList<>();
        for(Map.Entry<Long, Trainer> entry : mapTrainer.entrySet()){
            listTrainer.add(new TrainerData(entry.getValue()));
        }

        model.addAttribute("listTrainer", listTrainer);
        model.addAttribute("listTrainings", listData);
    }

    private void modeSetting(Model model, User user){
        model.addAttribute("modeDraw", "setting");

        UserFormReg form = new UserFormReg();
        form.set(user);

        model.addAttribute("userForm", form);
    }
}
