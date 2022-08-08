package com.counterit.component;

import com.counterit.model.ConfigDTO;
import com.counterit.model.MyChampEntity;
import com.counterit.repository.ChampRepository;
import com.counterit.repository.MyChampRepository;
import com.counterit.security.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConfigService {

    @Autowired
    MyChampRepository myChampRepository;
    @Autowired
    AuthenticationFacade authenticationFacade;
    @Autowired
    ChampRepository champRepository;

    @Transactional
    public void updateConfig(ConfigDTO dto) {
        Long pk = authenticationFacade.getLoginUserPk();
        myChampRepository.deleteByUserEntity_idx(pk);

        List<MyChampEntity> list = new ArrayList<>();

        for(Long idx : dto.getTop()) {
            MyChampEntity entity = new MyChampEntity();
            entity.setLane("top");
            entity.setUserEntity(authenticationFacade.getLoginUser());
            entity.setChampEntity(champRepository.findById(idx).get());
            list.add(entity);
        }
        for(Long idx : dto.getJungle()) {
            MyChampEntity entity = new MyChampEntity();
            entity.setLane("jungle");
            entity.setUserEntity(authenticationFacade.getLoginUser());
            entity.setChampEntity(champRepository.findById(idx).get());
            list.add(entity);
        }
        for(Long idx : dto.getMiddle()) {
            MyChampEntity entity = new MyChampEntity();
            entity.setLane("middle");
            entity.setUserEntity(authenticationFacade.getLoginUser());
            entity.setChampEntity(champRepository.findById(idx).get());
            list.add(entity);
        }
        for(Long idx : dto.getBottom()) {
            MyChampEntity entity = new MyChampEntity();
            entity.setLane("bottom");
            entity.setUserEntity(authenticationFacade.getLoginUser());
            entity.setChampEntity(champRepository.findById(idx).get());
            list.add(entity);
        }
        for(Long idx : dto.getSupport()) {
            MyChampEntity entity = new MyChampEntity();
            entity.setLane("support");
            entity.setUserEntity(authenticationFacade.getLoginUser());
            entity.setChampEntity(champRepository.findById(idx).get());
            list.add(entity);
        }
        myChampRepository.saveAll(list);
    }
}
