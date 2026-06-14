package com.dadaev.tea_social.Repository;

import com.dadaev.tea_social.model.Tea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeaRepository extends JpaRepository<Tea, Long> {

}
