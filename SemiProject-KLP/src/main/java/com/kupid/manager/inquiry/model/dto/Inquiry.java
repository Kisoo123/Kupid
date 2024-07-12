package com.kupid.manager.inquiry.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Inquiry {
	private int inqNo;
	private int inqMember;//참조 memberNo 문의한 회원번호
	private String inqTitle;
	private String inqContent;
	private Date inqDate;
	private String writer;
	private boolean answer;//답변유무
	private String inqFile;
}
