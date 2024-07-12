package com.kupid.feed.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reply {
	private int replyNumber;
	private int feedNo;
	private Date replyDate;
	private int likes;
	private int memberNo;
	private String replyContent;
}
