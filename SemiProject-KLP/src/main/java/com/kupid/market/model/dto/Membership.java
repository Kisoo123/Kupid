package com.kupid.market.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Membership {
	private int membershipNo;
	private String serialkey;
	private int memberNo;
	private int groupNo;
	private Date startday;
	private Date endday;
}
