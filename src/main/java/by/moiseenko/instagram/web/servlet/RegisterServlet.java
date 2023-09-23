package by.moiseenko.instagram.web.servlet;

/*
    @author Ilya Moiseenko on 23.09.23
*/

import by.moiseenko.instagram.model.City;
import by.moiseenko.instagram.model.Country;
import by.moiseenko.instagram.model.User;
import by.moiseenko.instagram.service.CityService;
import by.moiseenko.instagram.service.CountryService;
import by.moiseenko.instagram.service.UserService;

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
import java.util.List;
import java.util.Optional;

@WebServlet("/register")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5
)
public class RegisterServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();
    private final CountryService countryService = CountryService.getInstance();
    private final CityService cityService = CityService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Country> countries = countryService.findAll();
        List<City> cities = cityService.findAll();

        req.setAttribute("countries", countries);
        req.setAttribute("cities", cities);

        getServletContext().getRequestDispatcher("/pages/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part photoPart = req.getPart("photo");
        InputStream photoInputStream = photoPart.getInputStream();

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        int countryId = Integer.valueOf(req.getParameter("country"));
        int cityId = Integer.valueOf(req.getParameter("city"));


        User user = new User(
                name,
                surname,
                username,
                Base64.getEncoder().encodeToString(photoInputStream.readAllBytes()),
                email,
                password,
                countryService.findById(countryId).get(),
                cityService.findById(cityId).get()
        );

        userService.add(user);

        resp.sendRedirect("/login");
    }
}
