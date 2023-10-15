package by.moiseenko.instagram.web.servlet;

/*
    @author Ilya Moiseenko on 15.10.23
*/

import by.moiseenko.instagram.service.CommentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/deletecomment")
public class RemoveCommentServlet extends HttpServlet {

    private final CommentService commentService = CommentService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int commentId = Integer.parseInt(req.getParameter("comment_id"));
        int postId = Integer.parseInt(req.getParameter("post_id"));

        commentService.removeById(commentId);

        resp.sendRedirect("/user/viewpost?id=" + postId);
    }
}
