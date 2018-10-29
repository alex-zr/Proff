package task_Questionare;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet( urlPatterns = {"/answer"})
public class Quest extends HttpServlet {
    static final String TEMPLATE = "<html><head><title>Statistic</title></head><body><h2 align=\"center\">%s</h1></body></html>";
    private AtomicInteger count = new AtomicInteger(0);
    private AtomicInteger vegetariansCount = new AtomicInteger(0);
    private AtomicInteger notVegetarianCount = new AtomicInteger(0);

    public Quest() {
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String gender = req.getParameter("gender");
        String age = req.getParameter("age");
        String meat = req.getParameter("meat");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name + " ");
        stringBuilder.append(surname + ". ");
        stringBuilder.append("You`re " + age + " ");
        stringBuilder.append(gender + " ");
        if (meat.contentEquals("yes")) {
            stringBuilder.append(" and you aren`t vegetarian.");
            this.notVegetarianCount.getAndAdd(1);
        } else {
            stringBuilder.append(" and you are vegetarian");
            this.vegetariansCount.getAndAdd(1);
        }

        resp.getWriter().write(String.format("<html><head><title>Statistic</title></head><body><h2 align=\"center\">%s</h1></body></html>", stringBuilder.toString()));
        resp.getWriter().write(String.format("<html><head><title>Statistic</title></head><body><h2 align=\"center\">%s</h1></body></html>", "<br><a href=\"index.jsp\"> <input type=\"button\" value=\"Try again?\"> </a>"));
        this.count.getAndAdd(1);
        resp.getWriter().write(String.format("<html><head><title>Statistic</title></head><body><h2 align=\"center\">%s</h1></body></html>", "Today was " + this.count + " people"));
        if (this.vegetariansCount.get() > this.notVegetarianCount.get()) {
            resp.getWriter().write(String.format("<html><head><title>Statistic</title></head><body><h2 align=\"center\">%s</h1></body></html>", "Vegetarians were more)))"));
        } else if (this.vegetariansCount.get() < this.notVegetarianCount.get()) {
            resp.getWriter().write(String.format("<html><head><title>Statistic</title></head><body><h2 align=\"center\">%s</h1></body></html>", "Non vegetarians were more)))"));
        } else {
            resp.getWriter().write(String.format("<html><head><title>Statistic</title></head><body><h2 align=\"center\">%s</h1></body></html>", "Vegetarians were as much as normal people)))"));
        }

        resp.getWriter().write(String.format("<html><head><title>Statistic</title></head><body><h2 align=\"center\">%s</h1></body></html>", "<img src=\"foto.png\">"));
    }
}
