package com.sangdaero.walab.common.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.sangdaero.walab.common.entity.UserInterest;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends TimeEntity {

	@Id @GeneratedValue
    private Long id;

    private String name;

    private String nickname;

    private String profile;

    private String socialId;

    private String phone;

    private Byte userType;

    private Byte status;

    private Integer volunteerTime;

    @OneToMany(mappedBy = "user")
    private List<UserInterest> userInterestList = new ArrayList<>();

    private String service;

    private String memo;

    private Byte locationAgree;

    private Byte phoneAgree;

    private String community;

    private LocalDateTime lastLogin;

    private Byte isDelete;
	
    @Builder
    public User(Long id, String name, String nickname, String profile, String socialId,
                String phone, Byte userType, Byte status, Integer volunteerTime,
//                Set<InterestCategory> interests,
                String service, String memo, Byte locationAgree, Byte phoneAgree, String community,
                LocalDateTime lastLogin, Byte isDelete) {

        this.id=id;
        this.name=name;
        this.nickname=nickname;
        this.profile=profile;
        this.socialId=socialId;
        this.phone=phone;
        this.userType=0;
        this.status=1;
        this.volunteerTime=0;
        //this.interests=interests;
        this.service=service;
        this.memo=memo;
        this.locationAgree=0;
        this.phoneAgree=0;
        this.community=community;
        this.lastLogin=lastLogin;
        this.isDelete=0;
    }
	
	
}
