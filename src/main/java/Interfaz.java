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
                boolean existenteID=true;
                int artID = (int) (Math.floor(Math.random()*(1000-9999+1)+9999));
                while (existenteID) {
                    try {
                        BufferedReader br=new BufferedReader(new FileReader("src\\main\\java\\Archivos\\Articulos.txt"));
                        String linea;
                        while ((linea=br.readLine())!=null) {
                            String lista[]=linea.split(" ");
                            int cod=Integer.parseInt(lista[1]);
                            if(artID!=cod){
                                existenteID=false;
                            }
                            else{
                                artID=(int) (Math.floor(Math.random()*(1000-9999+1)+9999));
                            }
                        }          
                    } catch (IOException e) {
                        // TODO: handle exception
                    }
                }
                System.out.println("Nombre:");
                String nombre=sc.nextLine();
                System.out.println("Apellido:");
                String apellido=sc.nextLine();
                System.out.println("Datos:");
                String datos=sc.nextLine();
                System.out.println("Correo:");
                String correo=sc.nextLine();
                Autor autor=new Autor(nombre, apellido, correo, Rol.A, 0, null, null, null);
                Articulo articulo=new Articulo(autor, artID , datos);

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
