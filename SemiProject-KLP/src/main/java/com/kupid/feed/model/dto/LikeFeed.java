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
	public class LikeFeed {
		private int memberNo;
		private int feedNo;
		private int likes;
		private int likesSwitch;
	}


