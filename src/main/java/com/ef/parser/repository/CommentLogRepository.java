package com.ef.parser.repository;

import com.ef.parser.domain.entity.CommentLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentLogRepository extends JpaRepository<CommentLog, Long> {}
