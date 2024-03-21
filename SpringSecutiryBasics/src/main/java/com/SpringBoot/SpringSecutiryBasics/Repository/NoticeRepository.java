package com.SpringBoot.SpringSecutiryBasics.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.SpringBoot.SpringSecutiryBasics.Models.Notice;

@Repository
public interface NoticeRepository extends CrudRepository<Notice, Integer> {
    
    @Query("SELECT n FROM Notice n WHERE CURRENT_DATE BETWEEN n.noticBegDt AND n.noticEndDt")
    List<Notice> findAllActiveNotices();

}

