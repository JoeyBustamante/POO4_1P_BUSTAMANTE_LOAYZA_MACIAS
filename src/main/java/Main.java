<<<<<<< HEAD
import java.util.Scanner;

import users.Autor;
import users.Rol;
import users.Usuario;

import java.io.BufferedReader;
import java.io.FileReader;
=======
>>>>>>> 83109245b84f417a49fef01380332c549371e1a9
public class Main {
    Sistema sistema=new Sistema();
    public static void main(String[] args) {
<<<<<<< HEAD
        Usuario autor=new Autor("Francis", "Loayza", "loayzafrancis@gmail.com", Rol.A, null, 0, null, null, null);
        Sistema.AgergarListaUsuario(autor);
        System.out.println(Sistema.listaUsuario.toString());
        Sistema.registrarDatoUsuario();

        System.out.println("Ingrese su Usuario:");
        Scanner sc=new Scanner(System.in);
        String usuario=sc.nextLine();
        System.out.println("Ingrese su Contrasena:");
        String contra=sc.nextLine();
=======
        Interfaz in1 = new Interfaz();
        in1.inicio();
        //Mail.inicializarSistemaCorreo();
        //Mail.sendMail("chrismacmen9192@gmail.com","prueba","funciona?");
>>>>>>> 83109245b84f417a49fef01380332c549371e1a9
        
    }
}