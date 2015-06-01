package org.osiam.example.hystrix;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.osiam.client.oauth.AccessToken;
import org.osiam.client.query.QueryBuilder;
import org.osiam.resources.scim.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private ConnectorBuilder connectorBuilder;

    @RequestMapping("/search")
    public String search(@RequestParam(value = "query") final String query, final Model model) {
        final AccessToken accessToken = (AccessToken) httpSession
                .getAttribute(OsiamAccessDecisionManager.ACCESS_TOKEN_SESSION_KEY);
        QueryBuilder qb = new QueryBuilder();
        qb.filter(query);
        final List<User> users = connectorBuilder.createConnector().searchUsers(qb.build(), accessToken).getResources();
        model.addAttribute("users", users);
        model.addAttribute("isLoggedIn", accessToken != null);
        model.addAttribute("email", accessToken != null ? accessToken.getUserName() : "");
        return "search";
    }
}
