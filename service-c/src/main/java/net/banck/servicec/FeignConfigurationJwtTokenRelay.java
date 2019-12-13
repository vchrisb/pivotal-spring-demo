package net.banck.servicec;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.AbstractOAuth2Token;


public class FeignConfigurationJwtTokenRelay {

    @Bean
    public RequestInterceptor JwtTokenRelay ( ) {
        return new RequestInterceptor() {
            @Override
            public void apply (RequestTemplate requestTemplate) {
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                if(auth == null) return;
                AbstractOAuth2Token token = (AbstractOAuth2Token) auth.getCredentials();
                //System.out.println(token.getTokenValue());
                requestTemplate.header("Authorization", "Bearer " + token.getTokenValue());

            }
        };
    }
}

