package com.my.vipzal.controllers;

import com.my.vipzal.forms.*;
import com.my.vipzal.services.*;
import com.my.vipzal.table.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Controller
public class TrainerController {

    @Resource(name = "trainerService")
    public TrainerService trainerService;
    @Resource(name = "trainerTokenCookies")
    public TrainerTokenCookiesService trainerTokenCookiesService;
    @Resource(name = "trainingsService")
    public TrainingsService trainingsService;
    @Resource(name = "userService")
    public UserService userService;
    @Resource(name = "diseasesService")
    public DiseasesService diseasesService;

    @RequestMapping(path = {"trainer/auth"}, method = RequestMethod.GET)
    public String getMain(Model model,
                          @CookieValue(name = "tokenTrainer", required = false)Cookie cookie){
        if(cookie != null){
            TrainerTokenCookies token = trainerTokenCookiesService.get(cookie.getValue());
            if(token != null)
                return "redirect:/trainer/profile";
        }
        TrainerAuthForm authForm = new TrainerAuthForm();
        model.addAttribute("trainerForm", authForm);
        return "trainer/trainerAuth";
    }

    @RequestMapping(path = {"trainer/auth"}, method = RequestMethod.POST)
    public String auth(Model model,
                       HttpServletResponse response,
                       @ModelAttribute(name = "trainerForm") TrainerAuthForm auth){

        Trainer trainer = trainerService.get(auth.password, auth.email);
        if(trainer == null){
            model.addAttribute("error", "Неверный логин или пароль");
            return "redirect:/trainer/auth";
        }else{
            TrainerTokenCookies token = new TrainerTokenCookies();
            token.age = 60 * 1000;
            token.time = System.currentTimeMillis();
            token.token = UUID.randomUUID().toString();
            token.idTrainer = trainer.id;
            trainerTokenCookiesService.save(token);

            Cookie cookie = new Cookie("tokenTrainer", token.token);
            cookie.setMaxAge(token.age);
            cookie.setPath("/");
            response.addCookie(cookie);
            return "redirect:/trainer/profile";
        }
    }


    @RequestMapping(path = "/trainer/exit", method = RequestMethod.GET)
    public String exit(@CookieValue(name = "tokenTrainer", required = false) Cookie cookie,
                       HttpServletResponse response){
        if(cookie == null)
            return "redirect:/index";

        TrainerTokenCookies token = trainerTokenCookiesService.get(cookie.getValue());
        if(token == null)
            return "redirect:/index";
        trainerTokenCookiesService.delete(token);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return "redirect:/index";
    }

    @RequestMapping(path = {"trainer/profile"}, method = RequestMethod.GET)
    public String profile(Model model,
                          @CookieValue(name = "tokenTrainer", required = false)Cookie cookie,
                          @RequestParam(name = "mode", required = false) String mode,
                          @RequestParam(name = "win", required = false) String win,
                          @RequestParam(name = "show", required = false) String show,
                          @RequestParam(name = "idUser", required = false) Long idUser){
        if(mode == null)
            mode = "main";
        if(cookie == null)
            return "redirect:/index";

        TrainerTokenCookies token = trainerTokenCookiesService.get(cookie.getValue());
        if(token != null){
            Trainer trainer = trainerService.get(token.idTrainer);
            if(trainer == null) return "redirect:/index";
            switch (mode){
                case "main": main(model, trainer); break;
                case "schedule": schedule(model, trainer); break;
                case "setting": setting(model, trainer); break;
            }

            if(win != null){
                switch (win){
                    case "newSchedule": winNewTrainingsSchedule(model, trainer); break;
                }
            }

            if (show != null) {
                switch(show){
                    case "disease": showDisease(model, idUser); break;
                }
            }

            return "trainer/profile";
        }else{
            return "redirect:/index";
        }
    }

    private void showDisease(Model model, long idUser){
        User user = userService.get(idUser);
        if(user == null)
            return;
        List<Disease> listDisease = diseasesService.getAllForUser(user.id);
        List<DiseaseData> listData = new ArrayList<>();
        for(Disease d : listDisease){
            listData.add(new DiseaseData(d));
        }

        model.addAttribute("isShowDiseases", true);
        model.addAttribute("listDiseasesUser", listData);
        model.addAttribute("userData", new UserData(user));
    }

    @RequestMapping(path = "/trainer/profile/setting/save", method = RequestMethod.POST)
    public String saveSetting(Model model,
                              @CookieValue(name = "tokenTrainer", required = false) Cookie cookie,
                              @ModelAttribute("trainerForm") TrainerForm form){

        if(cookie != null){
            TrainerTokenCookies token = trainerTokenCookiesService.get(cookie.getValue());
            if(token != null){
                Trainer trainer = trainerService.get(token.idTrainer);
                if(trainer != null){
                    form.get(trainer);
                    trainerService.update(trainer);
                }
            }
        }

        return "redirect:/trainer/profile?mode=setting";
    }

    @RequestMapping(path = "/trainer/addTrainings", method = RequestMethod.POST)
    public String addSchedule(Model model,
                              @CookieValue(name = "tokenTrainer", required = false)Cookie cookie,
                              @ModelAttribute(name = "formUser") TrainingsUserForm form){

        TrainerTokenCookies token = trainerTokenCookiesService.get(cookie.getValue());
        if(token != null) {
            Trainer trainer = trainerService.get(token.idTrainer);
            if (trainer == null) return "redirect:/index";

            Trainings trainings = new Trainings();
            trainings.day = form.day;
            trainings.hour = form.hour;
            trainings.month = form.month;
            trainings.year = form.year;
            trainings.idUser = form.id;
            trainings.idTrainer = trainer.id;
            trainingsService.save(trainings);
        }
        return "redirect:/trainer/profile?mode=schedule";
    }


    @RequestMapping(path = "/trainer/deleteSchedule", method = RequestMethod.GET)
    public String deleteSchedule(Model model,
                                 @RequestParam(name = "id") Long id){
        Disease disease = diseasesService.get(id);
        if(disease != null){
            diseasesService.delete(disease);
        }
        return "redirect:/trainer/profile?mode=schedule";
    }

    private void winNewTrainingsSchedule(Model model, Trainer trainer){
        TrainingsUserForm form = new TrainingsUserForm();
        model.addAttribute("formUser", form);

        List<User> userAll = userService.getAll();
        List<UserData> userDataList = new ArrayList<>();
        for(User user : userAll){
            userDataList.add(new UserData(user));
        }
        model.addAttribute("userListNewSchedule", userDataList);
    }

    private void main(Model model, Trainer trainer){
        model.addAttribute("modeDraw", "main");

        TrainerData data = new TrainerData(trainer);
        model.addAttribute("data", data);
    }

    private void schedule(Model model, Trainer trainer){
        model.addAttribute("modeDraw", "schedule");

        List<Trainings> trainingsList = trainingsService.getForTrainer(trainer.id);
        List<TrainingsData> trainingsDataList = new ArrayList<>();
        HashMap<Long, User> mapUserId = new HashMap<>();
        for(Trainings t : trainingsList){
            TrainingsData data = new TrainingsData(t);
            User user = userService.get(t.idUser);
            mapUserId.put(user.id, user);
            data.user = user;
            trainingsDataList.add(data);
        }
        model.addAttribute("trainingsList", trainingsDataList);

        List<UserProfileForm> listUserForm = new ArrayList<>();
        for(HashMap.Entry<Long, User> u : mapUserId.entrySet()){
            UserProfileForm form = new UserProfileForm(u.getValue());
            listUserForm.add(form);
        }
        model.addAttribute("userList", listUserForm);
    }

    private void setting(Model model, Trainer trainer){
        TrainerForm form = new TrainerForm();
        form.set(trainer);
        model.addAttribute("trainerForm", form);
        model.addAttribute("modeDraw", "setting");
    }
}
