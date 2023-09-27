package by.moiseenko.instagram.web.servlet;

/*
    @author Ilya Moiseenko on 27.09.23
*/

import by.moiseenko.instagram.model.Post;
import by.moiseenko.instagram.model.User;
import by.moiseenko.instagram.service.LikeService;
import by.moiseenko.instagram.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/unlike")
public class UnlikeServlet extends HttpServlet {

    private final LikeService likeService = LikeService.getInstance();
    private final PostService postService = PostService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        int post_id = Integer.parseInt(req.getParameter("post_id"));

        Optional<Post> postById = postService.findById(post_id);
        if (postById.isPresent()) {
            likeService.removeByUserAndPost(user, postById.get());
            resp.sendRedirect("/user/viewpost?id=" + post_id);
        }
    }
}
