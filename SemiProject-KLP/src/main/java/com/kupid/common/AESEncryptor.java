package com.kupid.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;



public class AESEncryptor {
	private static SecretKey key;
	private String path;//key저장하는 파일의 위치저장하느 변수
	
	public AESEncryptor() {
		this.path=AESEncryptor.class.getResource("/").getPath();
		this.path=this.path.substring(0,this.path.indexOf("classes"));
		File keyFile=new File(this.path+"bslove.bs");
		if(keyFile.exists()) {
			try(ObjectInputStream ois
					=new ObjectInputStream(new FileInputStream(keyFile));){
				
				AESEncryptor.key=(SecretKey)ois.readObject();
				
			}catch(IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}else {
			generateKey();
		}
	}
	private void generateKey() {
		SecureRandom rnd=new SecureRandom();
		KeyGenerator keygen=null;
		
		try(ObjectOutputStream oos
				=new ObjectOutputStream(
						new FileOutputStream(this.path+"bslove.bs"))) {
			keygen=KeyGenerator.getInstance("AES");
			keygen.init(128,rnd);
			AESEncryptor.key=keygen.generateKey();
			oos.writeObject(AESEncryptor.key);
			
		} catch (IOException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	//Cipher클래스를 이용해서 암호화 복호화처리를 함.
	
	//암호화메소드 설정하기
	public static String encryptData(String oriVal) 
			throws Exception{
		//
		Cipher cipher=Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE,AESEncryptor.key);
		
		byte[] oribyte=oriVal.getBytes(Charset.forName("UTF-8"));
		byte[] encryptByte=cipher.doFinal(oribyte);
		
		return Base64.getEncoder().encodeToString(encryptByte);
	}
	//복호화메소드 설정하기
	public static String decryptData(String encryptVal) 
		throws Exception{
		Cipher cipher=Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, AESEncryptor.key);
		byte[] encrypByte=Base64.getDecoder()
				.decode(encryptVal.getBytes(Charset.forName("UTF-8")));
		byte[] decryptByte=cipher.doFinal(encrypByte);
		
		return new String(decryptByte);
		
	}

}
