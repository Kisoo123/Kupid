package com.kupid.inquiry.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InquiryDto {
	private String inqTitle;
	private String inqContent;
	private String inqFile;
	private int inq_Member;
	private Date inq_Date;
	private int inq_No;

}
