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

				out.println("Inicio de sesion completado");
			} else {
				out.println("Usuario o contraseña incorrecta o su email no ha sido validado");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
