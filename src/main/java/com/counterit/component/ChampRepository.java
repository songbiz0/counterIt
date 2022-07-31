package com.counterit.component;

import com.counterit.model.Champ;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChampRepository extends JpaRepository<Champ, Long> {

}
