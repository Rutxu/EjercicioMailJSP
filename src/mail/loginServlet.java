package mail;

import java.io.IOException;
import java.io.PrintWriter;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet(description = "Servlet que recoge los datos del formulario", urlPatterns = { "/login" })
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String username = request.getParameter("uname");
		String password = request.getParameter("psw");

		try {
			String encryptedPass = EncryptDecryptionAES.encrypt(password, EncryptDecryptionAES.main());

			if (AuthHelpers.checkUser(username, encryptedPass)) {
				HttpSession session = request.getSession();
				session.setAttribute("user", username);

				out.println("<html><head><title>Menú Principal</title></head><body><h1 style='text-align:center;color:blue; margin:0; padding:5px'>Menú Principal</h1>"
						+ "<p style='text-align:center;'><b>Usuario:</b> " + session.getAttribute("user") +"</p><p><a href='index.jsp'>"
								+ "Desconectar</a></p></body></html>");
			} else {
				out.println("Usuario o contraseña incorrecta o su email no ha sido validado");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
