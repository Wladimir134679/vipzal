package com.my.vipzal.controllers;

import com.my.vipzal.forms.DiseaseForm;
import com.my.vipzal.services.*;
import com.my.vipzal.table.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class DiseasesController {


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
    @Resource(name = "trainerTokenCookies")
    public TrainerTokenCookiesService trainerTokenCookiesService;

    @RequestMapping(path = "/profile/addDisease", method = RequestMethod.POST)
    public String addDiseases(Model model,
                              HttpServletResponse response,
                              HttpServletRequest request,
                              @CookieValue(name = "tokenUser", required = false)Cookie cookie,
                              @ModelAttribute(name = "diseaseForm") DiseaseForm form){
        if(cookie != null){
            UserTokenCookies token = tokenCookies.get(cookie.getValue());
            if(token != null){
                User user = users.get(token.idUser);
                if(user != null){
                    if(form != null){
                        Disease disease = new Disease();
                        disease.name = form.name;
                        disease.description = form.description;
                        disease.idUser = user.id;
                        diseasesService.save(disease);
                    }
                }
            }
        }
        return "redirect:/profile?mode=main";
    }

    @RequestMapping(path = "/profile/deleteDiseases", method = RequestMethod.GET)
    public String delete(Model model,
                         HttpServletResponse response,
                         HttpServletRequest request,
                         @CookieValue(name = "tokenUser", required = false)Cookie cookie,
                         @RequestParam(name = "id") Long idDisease){
        if(cookie != null){
            UserTokenCookies token = tokenCookies.get(cookie.getValue());
            if(token != null){
                User user = users.get(token.idUser);
                if(user != null){
                    Disease disease = diseasesService.get(idDisease);
                    if(disease != null)
                        diseasesService.delete(disease);
                }
            }
        }
        return "redirect:/profile?mode=main";
    }


//    @RequestMapping(path = "/diseases/get-all", method = RequestMethod.POST)
//    public String getDiseases(Model model,
//                              HttpServletResponse response,
//                              HttpServletRequest request,
//                              @CookieValue(name = "tokenTrainer", required = false)Cookie cookie,
//                              @RequestParam(name = "id", required = true) Long idUser){
//        if(cookie != null){
//            TrainerTokenCookies token = trainerTokenCookiesService.get(cookie.getValue());
//            if(token != null){
//                Trainer trainer = trainerService.get(token.idTrainer);
//                if(trainer != null){
//
//                }
//            }
//        }
//        return "redirect:/profile?mode=main";
//    }

}
