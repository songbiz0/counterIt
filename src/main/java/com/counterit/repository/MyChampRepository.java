package com.counterit.repository;

import com.counterit.model.MyChampEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyChampRepository extends JpaRepository<MyChampEntity, Long> {

    List<MyChampEntity> findByUserEntity_IdxAndLane(Long idx, String lane);
    List<MyChampEntity> findByUserEntity_IdxAndLaneAndChampEntity_Ennm(Long idx, String lane, String ennm);
    void deleteByUserEntity_idx(Long idx);
}
