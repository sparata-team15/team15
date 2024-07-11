package com.sparta.team15.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Team {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String teamName;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_name", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "board_id", nullable = false)
  private Board board;


  public Team(String teamName, User user, Board board) {
    this.teamName = teamName;
    this.user = user;
    this.board = board;
  }
}
