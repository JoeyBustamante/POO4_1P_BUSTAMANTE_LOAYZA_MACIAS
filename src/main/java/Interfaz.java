import java.util.Scanner;

import users.Editor;
import users.Revisor;
import users.Rol;
import users.Usuario;


public class Interfaz {
    
    public void inicio(){
        Scanner sc = new Scanner(System.in);
        System.out.println(" 1: Someter Articulo \n "+"2: Iniciar Secion");
        String tipo = sc.nextLine().toUpperCase();
        switch (tipo) {
            case "1":
                
                break;
            case "2":
                boolean ingreso=false;
                do {
                    System.out.println("--------------------------");
                    System.out.println("Ingrese su usuario: ");
                    String usuario= sc.nextLine();
                    System.out.println("Ingrese la contrasena: ");
                    String contrasena  = sc.nextLine();
                    boolean editorb = Editor.InicioSesion(usuario, contrasena);
                    boolean revisorb = Revisor.InicioSesion(usuario, contrasena);
                    if(editorb || revisorb){
                        System.out.println("Bienvenido!");
                        ingreso=true;
                    }
                    else{
                        System.out.println("Usuario o Contrasenia incorrecta");
                    }
                } while (!ingreso);
                break;
            default:
                System.out.println("Error al Seleccionar");
                break;
        }
        
    }
    
    
}
