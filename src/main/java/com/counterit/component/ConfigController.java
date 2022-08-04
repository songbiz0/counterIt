package com.counterit.component;

import com.counterit.model.MyChampEntity;
import com.counterit.repository.ChampRepository;
import com.counterit.repository.MyChampRepository;
import com.counterit.security.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ConfigController {

    @Autowired
    MyChampRepository myChampRepository;
    @Autowired
    AuthenticationFacade authenticationFacade;
    @Autowired
    ChampRepository champRepository;

    @GetMapping("/config")
    public String config(Model model) {
        if(authenticationFacade.getLoginUser() == null) {
            return "/main";
        }

        List<String> lanes = List.of("top", "jungle", "middle", "bottom", "support");
        for(String lane : lanes) {
            List<MyChampEntity> list = myChampRepository.findByUserEntity_IdxAndLane(authenticationFacade.getLoginUserPk(), lane);
            if(list.size() > 0) {
                model.addAttribute(lane, list);
            }
        }
        model.addAttribute("champs", champRepository.findAll());
        return "/config";
    }
}
