package by.moiseenko.instagram.web.servlet;

/*
    @author Ilya Moiseenko on 23.09.23
*/

import by.moiseenko.instagram.entity.Post;
import by.moiseenko.instagram.entity.User;
import by.moiseenko.instagram.service.FollowersService;
import by.moiseenko.instagram.service.PostService;
import by.moiseenko.instagram.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/user/profile")
public class ProfileServlet extends HttpServlet {

    private final PostService postService = PostService.getInstance();
    private final UserService userService = UserService.getInstance();
    private final FollowersService followersService = FollowersService.getInstance();

    private boolean isAlreadyFollowed = false;
    private int followersCount = 0;
    private int followingCount = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        Optional<User> byUsername = userService.getByUsername(username);

        if (byUsername.isPresent()) {
            User user = byUsername.get();

            Iterable<Post> allByUser = postService.findAllByUser(user);
            req.setAttribute("userPosts", allByUser);
            req.setAttribute("viewedUser", user);

            followersCount = followersService.getFollowersByUser(user);
            req.setAttribute("followersCount", followersCount);

            followingCount = followersService.getFollowingByUser(user);
            req.setAttribute("followingCount", followingCount);

            User parent = (User) req.getSession().getAttribute("user");

            isAlreadyFollowed = followersService.isFollow(parent, user);
            req.setAttribute("isAlreadyFollowed", isAlreadyFollowed);
        }

        getServletContext().getRequestDispatcher("/pages/profile.jsp").forward(req, resp);
    }
}
