package com.kupid.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class PasswordEncoder extends HttpServletRequestWrapper {
	public PasswordEncoder(HttpServletRequest request) {
		super(request);
	}
	@Override
	public String getParameter(String name) {
		String oriVal=super.getParameter(name);
		try {
		if(name.equals("password")) {
			//비밀번호에만 암호화처리
			System.out.println(oriVal);
			String encode=getSHA512(oriVal);
			System.out.println(oriVal); //원본
			System.out.println(encode); //암호화된 값
			return encode;
		}else {
			return super.getParameter(name);
		} 
		} catch(NullPointerException e) {
			return super.getParameter(name);
		}
	}
	private String getSHA512(String oriVal) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-512");
		} catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		//byte로 변환
		byte[] oriValByte = oriVal.getBytes();
		md.update(oriValByte); //변경할 값 전달
		byte[] encryptVal = md.digest();
		//다시 문자열로 변환
		String encryptStr = Base64.getEncoder().encodeToString(encryptVal);
		return encryptStr;
	}
}
