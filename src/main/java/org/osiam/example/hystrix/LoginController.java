package org.osiam.example.hystrix;

import javax.servlet.http.HttpSession;

import org.osiam.client.oauth.AccessToken;
import org.osiam.client.oauth.Scope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private ConnectorBuilder connectorBuilder;

    @Autowired
    private HttpSession httpSession;

    @RequestMapping("/login")
    public String login() {
        final AccessToken accessToken = (AccessToken) httpSession
                .getAttribute(OsiamAccessDecisionManager.ACCESS_TOKEN_SESSION_KEY);
        if (accessToken == null) {
            return "redirect:" + connectorBuilder.createConnector().getAuthorizationUri(Scope.ADMIN, Scope.ME);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/login/oauth", params = "code")
    public String handleOAuthFlow(
            @RequestParam(value = "code", required = true) String code) {

        httpSession.setAttribute(OsiamAccessDecisionManager.ACCESS_TOKEN_SESSION_KEY, connectorBuilder
                .createConnector().retrieveAccessToken(code));
        return "redirect:/";
    }

    @RequestMapping("/logout")
    public String logout() {
        httpSession.invalidate();
        SecurityContextHolder.clearContext();
        return "redirect:/";
    }
}
