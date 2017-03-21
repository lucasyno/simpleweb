package servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/raty")
public class RatyServlet extends HttpServlet {
    private int SKALA = 12;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        float kwota = Float.parseFloat(request.getParameter("kwota"));
        int ilosc_rat = Integer.parseInt(request.getParameter("ilosc_rat"));
        int oprocentowanie = Integer.parseInt(request.getParameter("oprocentowanie"));
        float oplata_stala = Float.parseFloat(request.getParameter("oplata_stala"));
        String rodzaj = request.getParameter("rodzaj");

        response.setContentType("text/html");
        if (rodzaj.equals("stala")) {
            ratyStala(kwota, ilosc_rat, oprocentowanie, oplata_stala, response);
        }
        else if (rodzaj.equals("malejaca")) {
            ratyMalejaca(kwota, ilosc_rat, oprocentowanie, oplata_stala, response);
        }
    }

    private void ratyStala(float kwota, int ilosc_rat, int oprocentowanie, float oplata_stala,
                           HttpServletResponse response) throws IOException {
        double wspolczynnik = 1 + (oprocentowanie / (100.0 * SKALA));
        double wysokosc_raty;
        double pozostalo;

        response.getWriter().println("<table>" +
                "<tr>" +
                    "<th>nr raty</th>" +
                    "<th>do splaty</th>" +
                    "<th>całkowita kwota raty</th>" +
                "</tr>"
        );

        wysokosc_raty = kwota * Math.pow(wspolczynnik, (double)ilosc_rat) * (wspolczynnik - 1) /
                (Math.pow(wspolczynnik, (double)ilosc_rat) - 1);

        for(int rata = 1; rata <= ilosc_rat; rata++){
            pozostalo = kwota - wysokosc_raty * rata;
            response.getWriter().println(
                    "<tr>" +
                            "<td>" + rata + "</td>" +
                            "<td>" + String.format("%.2f", wysokosc_raty) + "</td>" +
                            "</tr>"
            );
        }
        response.getWriter().println("</table>");
    }

    private void ratyMalejaca(float kwota, int ilosc_rat, int oprocentowanie, float oplata_stala,
                              HttpServletResponse response) throws IOException {
        double czkapitalowa = kwota / ilosc_rat;
        double czodsetkowa;
        double pozostalo = kwota;

        response.getWriter().println("<table>" +
                "<tr>" +
                "<th>nr raty</th>" +
                "<th>kwota kapitalu</th>" +
                "<th>czesc kapitalowa raty</th>" +
                "<th>czesc odsetkowa raty</th>" +
                "<th>wysokosc raty</th>" +
                "</tr>"
        );

        for(int rata = 1; rata <= ilosc_rat; rata++) {
            czodsetkowa = pozostalo * oprocentowanie / 100.0 / SKALA;
            response.getWriter().println(
                    "<tr>" +
                            "<td>" + rata + "</td>" +
                            "<td>" + String.format("%.2f", pozostalo) + "</td>" +
                            "<td>" + String.format("%.2f", czkapitalowa) + "</td>" +
                            "<td>" + String.format("%.2f", czodsetkowa) + "</td>" +
                            "<td>" + String.format("%.2f", czkapitalowa + czodsetkowa) + "</td>" +
                    "</tr>"
            );
            pozostalo -= czkapitalowa;
        }
    }
}

