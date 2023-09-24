package by.moiseenko.instagram.web.servlet;

/*
    @author Ilya Moiseenko on 24.09.23
*/

import by.moiseenko.instagram.model.Comment;
import by.moiseenko.instagram.model.Post;
import by.moiseenko.instagram.model.User;
import by.moiseenko.instagram.service.CommentService;
import by.moiseenko.instagram.service.PostService;
import by.moiseenko.instagram.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/user/create-comment")
public class CreateCommentServlet extends HttpServlet {

    private final CommentService commentService = CommentService.getInstance();
    private final PostService postService = PostService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int postId = Integer.valueOf(req.getParameter("post_id"));
        String commentText = req.getParameter("commentMessage");
        User user = (User) req.getSession().getAttribute("user");

        Optional<Post> postById = postService.findById(postId);
        if (postById.isPresent()) {
            Post post = postById.get();

            Comment comment = new Comment();
            comment.setPost(post);
            comment.setUser(user);
            comment.setText(commentText);

            commentService.add(comment);
        }

        resp.sendRedirect("/user/viewpost?id=" + postId);
    }
}
