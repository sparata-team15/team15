package com.sparta.team15.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "board_column")
public class Column {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
}
