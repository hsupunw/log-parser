package com.ef.parser.repository;

import com.ef.parser.domain.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

  @Query(
      value =
          "SELECT l FROM log l \n"
              + " WHERE l.date BETWEEN :startDate AND :endDate \n"
              + "GROUP BY l.ip HAVING count(l.ip) >= :threshold")
  List<Log> findForThreshold(@Param("startDate") Date startDate, @Param("endDate")Date endDate, @Param("threshold")Long threshold);
}
