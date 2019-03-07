package mail;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.Properties;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet(description = "Servlet que recoge los datos del formulario", urlPatterns = { "/signup" })
public class signupServlet extends HttpServlet {
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
		String email = request.getParameter("email");

		try {
			String encryptedPass = EncryptDecryptionAES.encrypt(password, EncryptDecryptionAES.main());

			if (AuthHelpers.insertUser(username, email, encryptedPass)) {
				out.println("Resgistrado correctamente");
				String to = email;
				String from = "fpdespliegue10@gmail.com";
				String host = "localhost";

				Properties properties = System.getProperties();
				properties.setProperty("mail.smtp.host", host);
				Session session = Session.getDefaultInstance(properties);
				response.setContentType("text/html");

				try {
					MimeMessage message = new MimeMessage(session);

					message.setFrom(new InternetAddress(from));
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
					message.setSubject("Confirmación de registro");

					StringBuilder sb = new StringBuilder();
					sb.append("<h1>Confirma tu direccion de correo</h1>");
					sb.append("<a href=\"http://localhost:8080/AuthProject/confirmar?id=");
					MessageDigest md = MessageDigest.getInstance("SHA-512");
					byte[] a = md.digest((username + email).getBytes());
					String validation = DatatypeConverter.printHexBinary(a);
					sb.append(validation);
					sb.append("\">Confirmar</a>");

					message.setContent(sb.toString(), "text/html");

					Transport.send(message);
					String title = "Codigo enviado";
					String res = "Comprueba tu email";
					String docType = "<!doctype html>\n";

					out.println(docType + "<html>\n" + "<head><title>" + title + "</title></head>\n" + "<body>\n"
							+ "<h1 align = \"center\">" + title + "</h1>\n" + "<p align = \"center\">" + res + "</p>\n"
							+ "</body>" + "</html>");
					AuthHelpers.insertValidation(username, validation);
				} catch (MessagingException mex) {
					mex.printStackTrace();
				}

			} else {
				out.println("No ha sido posible completar el registro");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
