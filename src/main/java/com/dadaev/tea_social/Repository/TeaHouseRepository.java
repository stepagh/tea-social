package com.dadaev.tea_social.Repository;

import com.dadaev.tea_social.model.TeaHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeaHouseRepository extends JpaRepository<TeaHouse, Long> {

}
