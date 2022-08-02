package com.counterit.component;

import com.counterit.model.ChampEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    @Autowired
    ChampRepository champRepository;
    @Autowired
    CrawlingService crawlingService;
    @Autowired ChampService champService;

    @GetMapping("/admin")
    public String admin() {
        return "/admin";
    }

    @PostMapping("/insertchamp")
    public String insertChamp(ChampEntity champEntity) {
        champRepository.save(champEntity);
        return "/admin";
    }

    @GetMapping("/crawling")
    public String crawling(@RequestParam String version) {
        crawlingService.crawling(version);
        return "/admin";
    }

    @GetMapping("/crawlingrange")
    public String crawlingRange(@RequestParam String from, @RequestParam String to) {
        int fromVersion = Integer.parseInt(from.split("\\.")[1]);
        int toVersion = Integer.parseInt(to.split("\\.")[1]);

        for(int i=fromVersion; i<=toVersion; i++) {
            String season = from.split("\\.")[0];
            crawlingService.crawling(season + "." + i);
        }
        return "/admin";
    }
}
