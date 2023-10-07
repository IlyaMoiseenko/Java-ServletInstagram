package by.moiseenko.instagram.web.servlet;

/*
    @author Ilya Moiseenko on 23.09.23
*/

import by.moiseenko.instagram.entity.Post;
import by.moiseenko.instagram.entity.User;
import by.moiseenko.instagram.service.LikeService;
import by.moiseenko.instagram.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/user/viewpost")
public class ViewPostServlet extends HttpServlet {

    private final PostService postService = PostService.getInstance();
    private final LikeService likeService = LikeService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer postId = Integer.valueOf(req.getParameter("id"));
        Optional<Post> postById = postService.findById(postId);

        if (postById.isPresent()) {
            Post post = postById.get();
            int likes = likeService.findAllByPost(post);

            User user = (User) req.getSession().getAttribute("user");

            boolean userLikeStatus = likeService.findByUserAndPost(user, post);
            if (userLikeStatus)
                req.setAttribute("like", true);
            else req.setAttribute("like", false);

            req.setAttribute("post", post);
            req.setAttribute("likes", likes);

            getServletContext().getRequestDispatcher("/pages/viewpost.jsp").forward(req, resp);
        }
    }
}
