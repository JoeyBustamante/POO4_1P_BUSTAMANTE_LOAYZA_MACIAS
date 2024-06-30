import java.util.Scanner;

import users.Autor;
import users.Editor;
import users.Revisor;
import users.Rol;
import users.Usuario;


public class Interfaz {
    
    public  void inicio(){
        Scanner sc = new Scanner(System.in);
        System.out.println(" 1: Someter Articulo \n "+"2: Iniciar Secion");
        String tipo = sc.nextLine().toUpperCase();
        if(tipo.equals("2")){
            boolean acceso = false;
            do{
                System.out.println("Ingrese su usuario ");
                String usuario= sc.nextLine();
                System.out.println("Ingrese la contrasena: ");
                String contrasena  = sc.nextLine();
                String editorb = Editor.InicioSesion(usuario, contrasena);
                String revisorb = Revisor.InicioSesion(usuario, contrasena);
                if(editorb.equals("E") ){
                    acceso=true;



                }else if(revisorb.equals("R")){
                    acceso= true;
                }
                
                else{
                    System.out.println("Credenciales invalidas, vuelva a intentarlo");
                }

            }while(acceso == false);

            

        }else{
            Autor.someterArticulo();

        }
        
    }
    
    
}
