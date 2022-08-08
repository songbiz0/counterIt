package com.counterit.component;

import com.counterit.model.*;
import com.counterit.repository.ChampRepository;
import com.counterit.repository.MyChampRepository;
import com.counterit.repository.StatsRepository;
import com.counterit.security.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    ChampRepository champRepository;
    @Autowired
    StatsService statsService;
    @Autowired
    StatsRepository statsRepository;
    @Autowired
    AuthenticationFacade authenticationFacade;
    @Autowired
    MyChampRepository myChampRepository;

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/getchamplist")
    @ResponseBody
    public List<ChampEntity> getChampList() {
        return champRepository.findAll();
    }

    @GetMapping("/getstats")
    @ResponseBody
    public List<StatsVO> getStats(StatsDTO statsDTO) {
        statsDTO = statsService.setStdIdx(statsDTO);
        List<StatsVO> list = new ArrayList<>();


        List<StatsInterface> statsList = statsRepository.getStats(statsDTO);
        UserEntity user = authenticationFacade.getLoginUser();

        if(user != null && myChampRepository.findByUserEntity_IdxAndLane(user.getIdx(), statsDTO.getLane()).size() > 0) {
            for(StatsInterface item : statsList) {
                List<MyChampEntity> configList = myChampRepository.findByUserEntity_IdxAndLaneAndChampEntity_Ennm(user.getIdx(), statsDTO.getLane(), item.getVsennm());
                if(configList.size() > 0) {
                    list.add(item.getStats());
                }
            }
        } else {
            for(StatsInterface item : statsList) {
                list.add(item.getStats());
            }
        }

        return list;
    }

    @GetMapping("/getennm")
    @ResponseBody
    public StringVO getEnnm(String name) {
        return new StringVO(ChampService.champNameSimplify(champRepository.findByKrnm(name).getEnnm()));
    }
}
