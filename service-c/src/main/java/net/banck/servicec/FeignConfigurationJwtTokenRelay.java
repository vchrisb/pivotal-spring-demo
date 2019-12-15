package net.banck.servicec;

import feign.RequestInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.AbstractOAuth2Token;


public class FeignConfigurationJwtTokenRelay {

    private static final Logger LOG = LoggerFactory.getLogger(FeignConfigurationJwtTokenRelay.class);

    @Bean
    public RequestInterceptor JwtTokenRelay ( ) {
        return requestTemplate -> {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if(auth == null) return;
            AbstractOAuth2Token token = (AbstractOAuth2Token) auth.getCredentials();
            LOG.debug("Using Token for Relay: {}", token.getTokenValue());
            requestTemplate.header("Authorization", "Bearer " + token.getTokenValue());

        };
    }
}

