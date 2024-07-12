package com.kupid.manager.penalty.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Penalty {
	private int penaltyNo;
	private int reportNO;
	private int MemberNo;
	private String penaltyCategory;
	private Date penaltyStart;
	private Date penaltyEnd;
}
