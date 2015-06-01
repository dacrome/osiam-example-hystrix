package org.osiam.example.hystrix;

import javax.servlet.http.HttpSession;

import org.osiam.client.oauth.AccessToken;
import org.osiam.resources.scim.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProfileController {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private ConnectorBuilder connectorBuilder;

    @RequestMapping("/profile")
    public String profile(final Model model) {
        final AccessToken accessToken = (AccessToken) httpSession
                .getAttribute(OsiamAccessDecisionManager.ACCESS_TOKEN_SESSION_KEY);
        final User currentUser = connectorBuilder.createConnector().getCurrentUser(accessToken);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("isLoggedIn", accessToken != null);
        model.addAttribute("email", accessToken != null ? accessToken.getUserName() : "");
        return "profile";
    }
}
