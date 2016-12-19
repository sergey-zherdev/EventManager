package servlets;

import controls.EventControl;
import events.EventList;
import events.EventsFactory;
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

/**
 * Created by Сергей on 19.12.2016.
 */
@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw = resp.getWriter();
        try {
            EventList eventType;
            LocalDate date =  new LocalDate(req.getParameter("Date")).minusMonths(1);
            LocalTime time =new LocalTime(req.getParameter("Time"));
            String desc = req.getParameter("Description");
            EventControl.setStorage(StorageList.BASE);
            if("Alarm".equals(req.getParameter("possible-result")))
                eventType = EventList.ALARM;
            else
                eventType = EventList.REMINDER;
            EventControl.save(EventsFactory.getEvent(eventType, date, time, desc, false));
            pw.println("<h1>OK</h1>");
        }
        catch (Exception e)
        {
            pw.println();
        }
    }
}
