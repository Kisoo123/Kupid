package com.kupid.manager.notice.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Notice {
	private int noticeNo;
	private String noticeCategory;
	private String noticeTitle;
	private String noticeContent;
	private Date noticeDate;
}
