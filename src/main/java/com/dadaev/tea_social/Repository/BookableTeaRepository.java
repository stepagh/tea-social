package com.dadaev.tea_social.Repository;

import com.dadaev.tea_social.model.BookableTea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookableTeaRepository extends JpaRepository<BookableTea, Long> {
    List<BookableTea> findAllByTeaHouse_id(Long teaHouseId);
}
