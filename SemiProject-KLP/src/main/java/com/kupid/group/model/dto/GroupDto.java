package com.kupid.group.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GroupDto {
	private int groupNo;
	private String groupName;
	private int memberCount;
	private Date groupDebutday;
	private String groupCompany;
	private String groupImg;
}
