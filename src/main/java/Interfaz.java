import java.util.Scanner;

import users.Rol;
import users.Usuario;


public class Interfaz {
    
    public void inicio(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese A si es Autor, R si es Revisor o E si es editor");
        String tipo = sc.nextLine();
        System.out.println("Ingrese su nombre: ");
        String nombre=sc.nextLine();
        System.out.println("Ingrese su apellido: ");
        String apellido=sc.nextLine();
        System.out.println("Ingrese su correo: ");
        String correo=sc.nextLine();
        switch (Rol.valueOf(tipo)) {
            case A:
                System.out.println();
                
                break;
            
            case R:
            
                break;

            case E:

                break;
        
            default:
                break;
        }
        
    }
    
    
}
