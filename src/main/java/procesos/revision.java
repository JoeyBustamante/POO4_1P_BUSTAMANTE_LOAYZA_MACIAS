package procesos;
import users.*;
import java.util.ArrayList;
import java.util.Scanner;

public class revision {
    private Editor editor;
    private ArrayList<Revisor> revisores= new ArrayList<Revisor>();
    private Articulo articulo;
    private String comentario;
    private int artID;

    Scanner sc= new Scanner(System.in);

    public void agregarComentario(){
        System.out.println("Ingrese su comentario: ");
        String comentario = sc.nextLine();
        this.comentario=comentario;
    }

    public void decidir(){
        System.out.println("Ingrese su decision: (Y/N)");
        String respuesta = sc.nextLine();
        respuesta = respuesta.toUpperCase();
        boolean a=false;
        while(!a){
            switch(respuesta){
                case "Y":
                    System.out.println("El Articulo fue aceptado");
                    // se deberia activar el metodo de enviar correo al autor notificandole que el articulo fue aceptado
                    a=true;
                    break;
                case "N":
                    System.out.println("Articulo rechazado");
                    // se deberia activar el metodo enviarcorreo al autor notificandole que fue rechazado
                    a=true;
                    break;
                default:
                    System.out.println("Respuesta invalida ingrese Y para si y N para no");
                    break;

            }
        }
    }

    public void enviarCorreo(Editor editor){
        //Falta el proceso de enviar el correo y la forma del correo
    }

    public void enviarCorreo(Revisor revisor){
        //Falta el proceso de enviar el correo y la forma del correo
    }

    public void enviarCorreo(Autor autor){
        //Falta el proceso de enviar el correo y la forma del correo
    }

    public void enviarCorreo(String comentario, Autor autor){
        agregarComentario();
        //Falta el proceso de enviar el correo y la forma del correo
    }
}
