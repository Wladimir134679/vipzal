package com.my.vipzal.controllers;

import com.my.vipzal.services.TrainerService;
import com.my.vipzal.table.Trainer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(path = "/server")
public class ServerController {

    @Resource(name = "trainerService")
    public TrainerService trainerService;

    @RequestMapping(path = "com1", method = RequestMethod.GET)
    public String com1(Model model, HttpServletRequest request){

        Trainer trainer = new Trainer();
        trainer.email = "trainer@vipzal.com";
        trainer.fio = "Путин Вова";
        trainer.numberEndClasses = 0;
        trainer.password = "w1";
        trainer.phoneNumber = "8920 000 11 22";

        trainerService.save(trainer);

        return "redirect:" + request.getHeader("referer");
    }
}
