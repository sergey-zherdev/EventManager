package servlets;

import controls.EventControl;
import events.EventList;
import events.Eventable;
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

            EventControl.save(getEvent(req));
            pw.println("<h1>OK</h1>");
        } catch (Exception e) {
            log.error(e);
            pw.println("<h1>ERROR</h1>");
        }
    }

    private Eventable getEvent(HttpServletRequest req) {
        System.err.println(req.getParameter("messageType"));
        System.err.println(req.getParameter("messageType1"));
        boolean repeat = false;
        EventList eventType = null;
        LocalDate startDate = new LocalDate(req.getParameter("startDate"));
        LocalTime startTime = new LocalTime(req.getParameter("startTime"));
        LocalDate endDate = null;
        String desc = null;
        int repeatTime = 0;
        LocalTime endTime = null;
        if ("ALARM".equals(req.getParameter("possible"))) {
            eventType = EventList.ALARM;
            endDate = new LocalDate(req.getParameter("endDate"));
            endTime = new LocalTime(req.getParameter("endTime"));
            desc = "ALARM";
            repeat = true;
            repeatTime = Integer.parseInt(req.getParameter("repeatTime"));
        } else {
            eventType = EventList.REMINDER;
            endDate = startDate;
            endTime = startTime;
            desc = req.getParameter("Description");
        }

        boolean message = false;
        boolean console = false;
        String address = null;
        if (null != req.getParameter("Console")) {
            console = true;
        }
        if (null != req.getParameter("Message")) {
            message = true;
            address = req.getParameter("address");
        }


        return EventsFactory.getEvent(String.valueOf(UUID.randomUUID()), eventType, startDate, startTime, endDate, endTime, desc, repeat, repeatTime, console, message, address);
    }
}
