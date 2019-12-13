package net.banck.servicec;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.AbstractOAuth2Token;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

public class FeignConfigurationJwt {

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor(OAuth2AuthorizedClientService clientService) {

        return requestTemplate -> {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) auth;
            OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
                oauthToken.getAuthorizedClientRegistrationId(),
                oauthToken.getName());
            OAuth2AccessToken accessToken = client.getAccessToken();
            requestTemplate.header("Authorization", "Bearer " + accessToken.getTokenValue());
        };
    }
}
