package com.counterit.component;

import com.counterit.model.Champ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    @Autowired
    ChampRepository champRepository;

    @GetMapping("/admin")
    public String admin() {
        return "/admin";
    }

    @PostMapping("/insertchamp")
    public String insertChamp(Champ champ) {
        champRepository.save(champ);
        return "/admin";
    }
}
