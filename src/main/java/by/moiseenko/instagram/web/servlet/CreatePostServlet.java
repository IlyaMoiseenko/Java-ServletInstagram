package by.moiseenko.instagram.web.servlet;

/*
    @author Ilya Moiseenko on 23.09.23
*/

import by.moiseenko.instagram.model.Post;
import by.moiseenko.instagram.model.User;
import by.moiseenko.instagram.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

@WebServlet("/create-post")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5
)
public class CreatePostServlet extends HttpServlet {

    private final PostService postService = PostService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/createPost.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part photoPart = req.getPart("photo");
        InputStream photoPartInputStream = photoPart.getInputStream();

        String description = req.getParameter("description");
        User user = (User) req.getSession().getAttribute("user");

        Post post = Post.builder().user(user).description(description).photo(Base64.getEncoder().encodeToString(photoPartInputStream.readAllBytes())).build();

        postService.add(post);

        resp.sendRedirect("/");
    }
}
