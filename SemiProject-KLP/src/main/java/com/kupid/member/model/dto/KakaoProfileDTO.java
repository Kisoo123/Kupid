package com.kupid.member.model.dto;

import java.util.Properties;

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
public class KakaoProfileDTO {
    private String id;
    private Properties properties;
    private KakaoAccount kakao_account;
    
    public static class Properties {
        private String nickname;
        private String profile_image;
        private String thumbnail_image;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    public static class KakaoAccount {
    	
        private boolean profile_needs_agreement;
        private Profile profile;
        String email;
        
        public static class Profile {
            private String nickname;
            private String thumbnail_image_url;
            private String profile_image_url;
            private boolean is_default_image;
        }
    }

}


