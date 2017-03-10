package weather;

import weather.entity.Weather;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "WeatherServlet",
        urlPatterns = {"/weather"}
)
public class WeatherServlet extends HttpServlet {
    WeatherService weatherService = new WeatherService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String action = req.getParameter("searchAction");

        if (action!=null && action.equals("searchByCity")) {
            searchWeatherByCity(req, resp);
        }
        else {

            forwardWeatherData(req, resp, null);
        }
    }

    private void searchWeatherByCity(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String cityName = req.getParameter("cityName");
        try {
            Weather[] weather = weatherService.getCompleteWeatherForecast(cityName);
            forwardWeatherData(req, resp, weather);
        }catch (IOException e){
            forwardWeatherData(req, resp, null);
        }


    }

    private void forwardWeatherData(HttpServletRequest req, HttpServletResponse resp, Weather[] weatherData)
            throws ServletException, IOException {


        String nextJSP = "/jsp/weatherForecast.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        req.setAttribute("weatherData", weatherData);
        dispatcher.forward(req, resp);
    }
}
