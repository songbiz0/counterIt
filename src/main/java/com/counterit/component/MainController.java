package com.counterit.component;

import com.counterit.model.ChampEntity;
import com.counterit.model.StatsDTO;
import com.counterit.model.StatsVO;
import com.counterit.repository.ChampRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    ChampRepository champRepository;

    @GetMapping("/")
    public String main() {
        return "/main";
    }

    @GetMapping("/getchamplist")
    @ResponseBody
    public List<ChampEntity> getChampList() {
        return champRepository.findAll();
    }

    @GetMapping("/getstats")
    @ResponseBody
    public List<StatsVO> getStats(StatsDTO statsDTO) {
        System.out.println(statsDTO);
        return null;
    }
}
