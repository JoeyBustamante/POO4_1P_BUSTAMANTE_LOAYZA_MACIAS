import mail.Mail;
import users.*;
import java.util.Scanner;
public class Main {
    Sistema sistema=new Sistema();
    public static void main(String[] args) {
        
        Usuario autor=new Autor("Francis", "Loayza", "loayzafrancis@gmail.com", Rol.A, null, 0, null, null, null);
        Editor editor=new Editor("Joey", "Bustamante", "ejemplo", Rol.E, null, null, "Dios", "1234");
        Sistema.AgergarListaUsuario(autor);
        Sistema.AgergarListaUsuario(editor);
        System.out.println(Sistema.listaUsuario.toString());
        Sistema.registrarDatoUsuario();

        Interfaz inter = new Interfaz();
        inter.inicio();
        
        /*

        Mail.inicializarSistemaCorreo();
        Mail.sendMail("chrismacmen9192@gmail.com", "prueba", "fuciona?");
        */

    }
}
