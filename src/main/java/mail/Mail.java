/*package mail;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import io.github.cdismacio.dotenv.Dotenv;
import jdk.javadoc.doclet.DocletEnvironment;


public class Mail {
    private static String correo;
    private static String clave;
    private static String host;
    private static String puerto;
    private static Properties properties;
    private static Session session;

    public static void inicializarSistemaCorreo(){
        cargarDatos();
        cargarPropiedades();
        crearSesion();
    }

    public static String getCorreo(){return correo;}

    private static void cargarDatos(){
        Dotenv dotenv = Dotenv.configure().directory("/tmp").filename("credenciales.properties").load();
        correo = dotenv.get("CORREO");
        clave = dotenv.get("CLAVE");
        host = dotenv.get("HOST");
        puerto = dotenv.get("PUERTO");
    }
    private static void cargarPropiedades() {
        properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.ssl.trust", host);
        properties.put("mail.smtp.port", puerto);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", true);
        properties.put("mail.smtp.socketFactory.port", puerto);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    }

    private static void crearSesion() {
        session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(correo, clave);
            }
        });
    }

    public static String sendMail(String destinatario, String asunto, String mensaje) {
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correo));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setText(mensaje);
            Transport.send(message);
            return "Correo enviado correctamente a " + destinatario;
        } catch (MessagingException e) {
            return "Error al enviar correo a " + destinatario + ": " + e.getMessage();
        }
    }
}
*/