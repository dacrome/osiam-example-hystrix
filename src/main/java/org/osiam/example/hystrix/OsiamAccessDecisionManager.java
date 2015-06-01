package org.osiam.example.hystrix;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.osiam.client.exception.UnauthorizedException;
import org.osiam.client.oauth.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * This class is responsible for decide admin-access.
 */
@Component
public class OsiamAccessDecisionManager implements AccessDecisionManager {

    public final static String ACCESS_TOKEN_SESSION_KEY = "ACCESS_TOKEN";

    @Autowired
    private HttpSession session;

    @Autowired
    private ConnectorBuilder connectorBuilder;

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) {

        final AccessToken accessToken = (AccessToken) session.getAttribute(ACCESS_TOKEN_SESSION_KEY);
        if (accessToken == null) {
            throw new AccessDeniedException("There is no access token in session!");
        }

        try {
            connectorBuilder.createConnector().validateAccessToken(accessToken);
        } catch (UnauthorizedException e) {
            throw new AccessDeniedException("The access token is not valid!", e);
        }
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
