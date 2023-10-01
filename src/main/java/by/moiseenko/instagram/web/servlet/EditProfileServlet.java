package by.moiseenko.instagram.web.servlet;

/*
    @author Ilya Moiseenko on 29.09.23
*/

import by.moiseenko.instagram.model.User;
import by.moiseenko.instagram.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Base64;

@WebServlet("/edit-profile")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5
)
public class EditProfileServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/editprofile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part photoPart = req.getPart("newImage");
        String photoString = Base64.getEncoder().encodeToString(photoPart.getInputStream().readAllBytes());

        String newName = req.getParameter("newName");
        String newEmail = req.getParameter("newEmail");
        String newPassword = req.getParameter("newPassword");

        User currentUser = (User) req.getSession().getAttribute("user");

        User user = User
                .builder()
                .name(newName)
                .email(newEmail)
                .password(newPassword)
                .photo(photoString)
                .build();

        userService.update(user, currentUser);

        req.getSession().setAttribute("user", userService.getByUsername(currentUser.getUsername()).get());

        resp.sendRedirect("/edit-profile");
    }
}
