package org.osiam.example.hystrix;

import javax.servlet.http.HttpSession;

import org.osiam.client.oauth.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

    @Autowired
    private HttpSession httpSession;

    @RequestMapping("/")
    public String dashboard(final Model model) {
        final AccessToken accessToken = (AccessToken) httpSession
                .getAttribute(OsiamAccessDecisionManager.ACCESS_TOKEN_SESSION_KEY);
        model.addAttribute("isLoggedIn", accessToken != null);
        model.addAttribute("email", accessToken != null ? accessToken.getUserName() : "");
        return "dashboard";
    }
}
