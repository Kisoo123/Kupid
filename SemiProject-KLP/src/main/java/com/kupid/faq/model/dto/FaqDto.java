package com.kupid.faq.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FaqDto {

	private int faqNo;
	private String faqTitle;
	private String faqContent;
	private String faqCategory;
	private Date faqDate;
}
