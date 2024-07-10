package com.sparta.team15.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
@Table(name = "user")
public class User extends Timestamped{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String username;

  private String name;

  private String password;

  @Enumerated(value = EnumType.STRING)
  private UserStatusEnum status;

  @Enumerated(value = EnumType.STRING)
  private UserRoleEnum role;

  private String createdAt;

  private String modifiedAt;

  public User(String username, String password, String name, UserRoleEnum role) {
    this.username = username;
    this.password = password;
    this.name = name;
    this.status = UserStatusEnum.USER;
    this.role = role;
  }

}
