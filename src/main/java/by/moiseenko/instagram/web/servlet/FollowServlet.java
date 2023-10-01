package by.moiseenko.instagram.web.servlet;

/*
    @author Ilya Moiseenko on 1.10.23
*/

import by.moiseenko.instagram.model.User;
import by.moiseenko.instagram.service.FollowersService;
import by.moiseenko.instagram.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/follow")
public class FollowServlet extends HttpServlet {

    private final FollowersService followersService = FollowersService.getInstance();
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User parent = (User) req.getSession().getAttribute("user");
        Optional<User> child = userService.getByUsername(req.getParameter("child"));

        followersService.save(parent, child.get());

        resp.sendRedirect("/user/profile?username=" + child.get().getUsername());
    }
}
