package by.moiseenko.instagram.web.servlet;

/*
    @author Ilya Moiseenko on 23.09.23
*/

import by.moiseenko.instagram.model.Post;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int postId = Integer.valueOf(req.getParameter("id"));
        Optional<Post> postById = postService.findById(postId);

        if (postById.isPresent()) {
            Post post = postById.get();

            req.setAttribute("post", post);
        }

        getServletContext().getRequestDispatcher("/pages/viewpost.jsp").forward(req, resp);
    }
}
