import java.util.Scanner;

import users.Autor;
import users.Editor;
import users.Rol;
import users.Usuario;

import java.io.BufferedReader;
import java.io.FileReader;
public class Main {
    Sistema sistema=new Sistema();
    public static void main(String[] args) {
        Usuario autor=new Autor("Francis", "Loayza", "loayzafrancis@gmail.com", Rol.A, null, 0, null, null, null);
        Usuario editor=new Editor("Joey", "Bustamante", "ejemplo", Rol.E, null, null, "Dios", "1234");
        Sistema.AgergarListaUsuario(autor);
        System.out.println(Sistema.listaUsuario.toString());
        Sistema.registrarDatoUsuario();

        System.out.println("Ingrese su Usuario:");
        Scanner sc=new Scanner(System.in);
        String usuario=sc.nextLine();
        System.out.println("Ingrese su Contrasena:");
        String contra=sc.nextLine();
        
    }
}
