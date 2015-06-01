package org.osiam.example.hystrix;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Holds all application wide used properties
 */
@Component
public class Config {

    @Value("${org.osiam.client.secret:dcad6ce4-b4e9-4875-bf13-fb6c4c15af4f}")
    private String clientSecret;

    @Value("${org.osiam.client.id:hystrix-example-client}")
    private String clientId;

    @Value("${org.osiam.resource-server.home:http://localhost:8080/osiam-resource-server}")
    private String resourceServerHome;

    @Value("${org.osiam.auth-server.home:http://localhost:8080/osiam-auth-server}")
    private String authServerHome;

    public String getResourceServerHome() {
        return resourceServerHome;
    }

    public String getAuthServerHome() {
        return authServerHome;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }
}
