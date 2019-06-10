package com.ef.parser.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "comment_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentLog {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  public String ip;

  public String comment;
}
