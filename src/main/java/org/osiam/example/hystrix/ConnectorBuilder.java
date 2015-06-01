package org.osiam.example.hystrix;

import org.osiam.client.OsiamConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConnectorBuilder {

    @Autowired
    private Config config;

    public OsiamConnector createConnector() {
        OsiamConnector.Builder oConBuilder = new OsiamConnector.Builder()
                .setAuthServerEndpoint(config.getAuthServerHome())
                .setResourceServerEndpoint(config.getResourceServerHome())
                .setClientId(config.getClientId())
                .setClientSecret(config.getClientSecret())
                .setClientRedirectUri("http://localhost:50000/login/oauth");
        return oConBuilder.build();
    }
}
