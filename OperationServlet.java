package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Fornitore;
import dao.FornitoreDao;

public class OperationServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 123L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("type") != null && request.getParameter("type").equals("ricerca")) {
			ricerca(request, response);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("type") != null && request.getParameter("type").equals("inserimento")) {
			try {
				inserimento(request, response);
			} catch (ClassCastException e) {

			}
		}
		if (request.getParameter("type") != null && request.getParameter("type").equals("delete")) {
			cancellazione(request, response);
		}

		if (request.getParameter("type") != null && request.getParameter("type").equals("update")) {
			update(request, response);
		}
	}

	protected static void ricerca(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		FornitoreDao fornitoreDao = new FornitoreDao();
		Fornitore fornitore = fornitoreDao.getFornitore(nome, cognome);
		String spPage = "";

		if (fornitore != null) {
			spPage = "pages/dati.jsp";
			request.setAttribute("fornitore", fornitore);
		} else
			spPage = "pages/error.jsp";
		request.getRequestDispatcher(spPage).forward(request, response);

	}

	protected static void inserimento(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nome = null;
		String cognome = null;
		String citta = null;
		String indirizzo = null;
		Integer eta = null;
		String numero = null;
		boolean success = false;
		try {
			nome = request.getParameter("nome");
			cognome = request.getParameter("cognome");
			citta = request.getParameter("citta");
			indirizzo = request.getParameter("indirizzo");
			eta = Integer.parseInt(request.getParameter("eta"));
			numero = request.getParameter("numero");
			FornitoreDao fDao = new FornitoreDao();
			success = fDao.createFornitore(nome, cognome, numero, indirizzo, citta, eta);
		} catch (NumberFormatException e) {
			success = false;
		} catch (NullPointerException e) {
			success = false;
		}

		String message = "";
		if (success)
			message = "<p style=\"color:green\">Fornitore inserito!</p>";
		else
			message = "<p style=\"color:red\">Errore durante l'inserimento</p>";
		request.setAttribute("success", message);
		request.getRequestDispatcher("pages/inserimento.jsp").forward(request, response);

	}

	protected static void cancellazione(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		FornitoreDao fDao = new FornitoreDao();
		boolean success = fDao.deleteFornitore(id);
		String message = "";
		if (success)
			message = "<p style=\"color:green\">Fornitore eliminato!</p>";
		else
			message = "<p style=\"color:red\">Fornitore non trovato!</p>";
		request.setAttribute("success", message);
		request.getRequestDispatcher("pages/delete.jsp").forward(request, response);
	}

	protected static void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nome = null;
		String cognome = null;
		String citta = null;
		String indirizzo = null;
		Integer eta = null;
		String numero = null;
		Integer id = Integer.parseInt(request.getParameter("ID"));
		if (request.getParameter("nome") != "")
			nome = request.getParameter("nome");
		if (request.getParameter("cognome") != "")
			cognome = request.getParameter("cognome");
		if (request.getParameter("citta") != "")
			citta = request.getParameter("citta");
		if (request.getParameter("indirizzo") != "")
			indirizzo = request.getParameter("indirizzo");
		if (request.getParameter("eta") != "")
			eta = Integer.parseInt(request.getParameter("eta"));
		if (request.getParameter("numero") != "")
			numero = request.getParameter("numero");
		FornitoreDao fDao = new FornitoreDao();
		boolean success = fDao.updateFornitore(id, nome, cognome, citta, indirizzo, eta, numero);
		String message = "";
		if (success)
			message = "<p style=\"color:green\">Fornitore aggiornato!</p>";
		else
			message = "<p style=\"color:red\">Fornitore non trovato!</p>";
		request.setAttribute("success", message);
		request.setAttribute("ID", request.getParameter("ID"));
		request.getRequestDispatcher("pages/update.jsp").forward(request, response);
	}

}
