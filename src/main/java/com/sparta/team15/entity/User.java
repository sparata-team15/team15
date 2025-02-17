package com.sparta.team15.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
@Table(name = "user")
public class User extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String username;

    private String name;

    private String password;

    private String refreshToken;

    @Enumerated(value = EnumType.STRING)
    private UserStatusEnum status;

    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    private String createdAt;

    private String modifiedAt;

    private String statusUpdate;

    @OneToMany(mappedBy = "createdBy", fetch = FetchType.LAZY)
    private List<Board> boardList = new ArrayList<>();


    public User(String username, String password, String name, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.status = UserStatusEnum.USER;
        this.role = role;
        this.createdAt = getCreatedAt();
        this.modifiedAt = getModifiedAt();
    }

    public void withDraw() {
        this.status = UserStatusEnum.NON_USER;
        this.statusUpdate = this.getModifiedAt();
        this.refreshToken = null;
    }

    public boolean logout() {
        refreshToken = null;
        return refreshToken == null ? true : false;
    }


    public void saveRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
