import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(urlPatterns = "/ind")
public class Index extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> strings = Arrays.asList("Audi", "Opel", "BMW", "Porshe");
        request.setAttribute("models", strings);
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }
}
