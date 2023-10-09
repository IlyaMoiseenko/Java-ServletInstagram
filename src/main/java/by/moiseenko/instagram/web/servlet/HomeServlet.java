package by.moiseenko.instagram.web.servlet;

/*
    @author Ilya Moiseenko on 23.09.23
*/

import by.moiseenko.instagram.entity.Post;
import by.moiseenko.instagram.entity.User;
import by.moiseenko.instagram.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/")
public class HomeServlet extends HttpServlet {

    private final PostService postService = PostService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

        if (user != null) {
            Iterable<Post> allByFollowing = postService.findAllByFollowing(user);
            Iterable<Post> allUserPosts = postService.findAll();

            req.setAttribute("posts", allByFollowing);
            req.setAttribute("allPosts", allUserPosts);
        } else {
            req.setAttribute("posts", postService.findAll());
        }

        getServletContext().getRequestDispatcher("/pages/home.jsp").forward(req, resp);
    }
}
