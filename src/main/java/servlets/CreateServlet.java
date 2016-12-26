package servlets;

import controls.EventControl;
import events.EventList;
import events.EventsFactory;
import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import storages.StorageList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

/**
 * Created by Сергей on 19.12.2016.
 */
@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(CreateServlet.class);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw = resp.getWriter();
        try {
            boolean repeat = false;
            EventList eventType;
            LocalDate startDate = new LocalDate(req.getParameter("startDate"));
            LocalTime startTime = new LocalTime(req.getParameter("startTime"));
            LocalDate endDate = new LocalDate(req.getParameter("endDate"));
            LocalTime endTime = new LocalTime(req.getParameter("endTime"));
            String desc = req.getParameter("Description");
            if ("ALARM".equals(req.getParameter("possible"))) {
                eventType = EventList.ALARM;
                repeat = true;
            } else
                eventType = EventList.REMINDER;
            EventControl.save(EventsFactory.getEvent(String.valueOf(UUID.randomUUID()), eventType, startDate, startTime, endDate, endTime, desc, repeat));
            pw.println("<h1>OK</h1>");
        } catch (Exception e) {
            log.error(e);
            pw.println();
        }
    }
}
