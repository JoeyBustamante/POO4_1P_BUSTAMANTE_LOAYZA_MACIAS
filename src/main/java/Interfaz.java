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


public interface Interfaz {
    
    static void inicio(){
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
                            ArrayList<String>codigos=Editor.mostrarCodArticulos(usuario);
                            System.out.println("Ingrese el codigo del articulo que desea revisar:");
                            int codi=sc.nextInt();
                            boolean incorrecto=true;
                            do {
                                System.out.println("Entra");
                                for(String codigo:codigos){
                                    if(codi==Integer.parseInt(codigo)){
                                        incorrecto=false;
                                    }
                                    else{
                                        System.out.println("Ingreso de codigo incorrecto");
                                        for(String codig1: codigos){
                                            System.out.println(codig1);
                                        }
                                        System.out.println("Ingrese el codigo del articulo que desea revisar:");
                                        codi=sc.nextInt();
                                    }
                                }
                            } while (incorrecto);
                            Editor.decisionFinal(codi,usuario);
                            Editor.agregarComentario(codi);
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
