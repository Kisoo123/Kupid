package com.kupid.member.model.dto;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class MemberDto {
	private int memberNo;
	private String memberId;
	private String memberPw;
	private String memberName;
	private String gender;
	private String phone;
	private String address;
	private String addressDetail;
	private String email;
	private Date birth;
	private String nickname;
	private String introduce;
	private String profileImgOriname;
	//private String profileImgRenamed; //재정의 이름 일단 만들어둠(수업에서 배운 내용 활용)
	private String memberGrade;
	private Date enrollDate; //sql date 사용
	private int groupNo;//그룹 foreignkey
	private String groupName;
	private int memberCount;
	private Date groupDebutday;
	private String groupCompany;
	private String groupImg;
	private String serialkey;//membership 시리얼키
	private Date startday;//membership 시작일
	private Date endday;//membership 종료일
	private int subscribeNo; //구독테이블 pk번호(메인 인기순 아티스트 출력에 사용)
	private int replyNumber;
	private int feedNo;
	private Date replyDate;
	private int likes;
	private String replyContent;
	private String penaltyDay;
	private String kakaoId;
	private String kakaoPw;
	private String penaltyDate;
	private String penaltyCate;
}