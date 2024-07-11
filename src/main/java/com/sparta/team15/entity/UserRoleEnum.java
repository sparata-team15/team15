package com.sparta.team15.entity;

public enum UserRoleEnum {
  ADMIN(Authority.ADMIN),
  USER(Authority.USER);

  private final String authority;

  UserRoleEnum(String authority) {
    this.authority = authority;
  }

  public String getAuthority(){
    return this.authority;
  }


  public static class Authority{
    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";
  }
}
