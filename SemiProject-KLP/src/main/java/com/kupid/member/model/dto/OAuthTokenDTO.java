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
public class OAuthTokenDTO {
	private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    private String scope;
    private int refresh_token_expires_in;
}
