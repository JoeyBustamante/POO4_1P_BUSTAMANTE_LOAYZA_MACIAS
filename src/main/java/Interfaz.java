import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

import users.Articulo;
import users.Autor;
import users.Editor;
import users.Revisor;
import users.Rol;
import users.Usuario;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;


public class Interfaz {
    
    public void inicio(){
        Scanner sc = new Scanner(System.in);
        System.out.println(" 1: Someter Articulo \n "+"2: Iniciar Secion");
        String tipo = sc.nextLine().toUpperCase();
        switch (tipo) {
            case "1":
                System.out.println("--------------------------");
                Autor.someterArticulo();


                

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
                        if (editorb){
                            
                        }else{
                            Revisor.estadoReviciones(usuario);
                        }
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
