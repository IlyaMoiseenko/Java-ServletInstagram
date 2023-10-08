package by.moiseenko.instagram.web.servlet;

/*
    @author Ilya Moiseenko on 23.09.23
*/

import by.moiseenko.instagram.adapter.HashtagAdapter;
import by.moiseenko.instagram.entity.Hashtag;
import by.moiseenko.instagram.entity.Post;
import by.moiseenko.instagram.entity.User;
import by.moiseenko.instagram.service.PostService;
import by.moiseenko.instagram.service.TagService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@WebServlet("/create-post")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5
)
public class CreatePostServlet extends HttpServlet {

    private final HashtagAdapter hashtagAdapter = new HashtagAdapter();

    private final PostService postService = PostService.getInstance();
    private final TagService tagService = TagService.getInstance();

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
        String tags = req.getParameter("tag");

        Post post = Post
                .builder()
                .user(user)
                .description(description)
                .photo(Base64.getEncoder().encodeToString(photoPartInputStream.readAllBytes()))
                .build();

        Optional<Integer> addedPostId = postService.add(post);
        if (addedPostId.isPresent()) {
            Integer postId = addedPostId.get();
            post.setId(postId);
            post.setHashtags(
                    hashtagAdapter.converToList(tags)
            );

            tagService.save(post.getHashtags(), post);
        }

        resp.sendRedirect("/");
    }
}
