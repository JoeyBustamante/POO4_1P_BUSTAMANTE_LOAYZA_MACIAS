package users;
import java.util.ArrayList;
import java.util.Scanner;

import mail.Mail;
public class Revision {
    private Editor editor;
    private ArrayList<Revisor> revisores;
    private Articulo articulo;
    private String comentario;
    private int artID;

    //Constructor
    public Revision(Editor editor,ArrayList<Revisor> revisores, Articulo articulo, String comentario, int artID){
        this.editor=editor;
        this.revisores=revisores;
        this.articulo=articulo;
        this.comentario=comentario;
        this.artID=artID;
    }

    //Getters
    public Editor getEditor(){return this.editor;}
    public ArrayList<Revisor> getRevisores(){return this.revisores;}
    public Articulo getArticulo(){return this.articulo;}
    public String getComentario(){return this.comentario;}
    public int getArtID(){return this.artID;}
    
    //Setters
    public void setEditor(Editor editor){this.editor=editor;}
    public void setRevisores(ArrayList<Revisor> revisores){this.revisores=revisores;}
    public void setArticulo(Articulo articulo){this.articulo=articulo;}
    public void setComentario(String comentario){this.comentario=comentario;}
    public void setArtID(int artID){this.artID=artID;}


    Scanner sc= new Scanner(System.in);

    private void agregarComentario(){
        System.out.println("Ingrese su comentario: ");
        String comentario = sc.nextLine();
        this.comentario=comentario;
    }

    public void decidir(Editor e){
        System.out.println("Ingrese su decision: (Y/N)");
        String respuesta = sc.nextLine();
        respuesta = respuesta.toUpperCase();
        boolean a=false;
        while(!a){
            switch(respuesta){
                case "Y":
                    System.out.println("El Articulo fue aceptado");
                    enviarCorreo(articulo.getAutor() , articulo.getAutor().getCorreo() , "El editor aprobo su articulo", 
                    "Su articulo fue aprobado por el editor "+e.getNombre()+" "+e.getApellido()+" y su articulo sera publicado.");
                    a=true;
                    break;
                case "N":
                    System.out.println("Articulo rechazado");
                    enviarCorreo(articulo.getAutor() , articulo.getAutor().getCorreo() , "El editor rechazo su articulo", 
                    "Su articulo fue rechazado por el editor "+e.getNombre()+" "+e.getApellido()+" y su articulo no sera publicado.");
                    a=true;
                    break;
                default:
                    System.out.println("Respuesta invalida ingrese Y para si y N para no");
                    break;

            }
        }
    }

    public void decidir(Revisor r){
        agregarComentario();
        System.out.println("Ingrese su decision, aprueba el articulo?: (Y/N)");
        String respuesta = sc.nextLine();
        respuesta = respuesta.toUpperCase();
        boolean a=false;
        while(!a){
            switch(respuesta){
                case "Y":
                    System.out.println("El Articulo fue aceptado");
                    enviarCorreo(articulo.getAutor() , articulo.getAutor().getCorreo() , "Un revisor aprobo su articulo", 
                    "Su articulo fue aprobado por el revisor "+r.getNombre()+" "+r.getApellido()+" y a emitido el siguiente comentario: "
                    + '"'+comentario+'"');
                    a=true;
                    break;
                case "N":
                    System.out.println("Articulo rechazado");
                    enviarCorreo(articulo.getAutor() , articulo.getAutor().getCorreo() , "Un revisor rechazo su articulo", 
                    "Su articulo fue rechazado por el revisor "+r.getNombre()+" "+r.getApellido()+" y a emitido el siguiente comentario: "
                    + '"'+comentario+'"');
                    a=true;
                    break;
                default:
                    System.out.println("Respuesta invalida ingrese Y para si y N para no");
                    break;

            }
        }
    }

    public void notificar(Editor editor){
        Mail.inicializarSistemaCorreo();
        Mail.sendMail(editor.getCorreo(), "Se le asigno un articulo como editor", "Estimado "+editor.getNombre()+" "
        + editor.getApellido()+" se le envio este articulo para que de su aprobacion luego de que fue revisado por los revisores "
        + this.revisores.get(0).getNombre()+" "+this.revisores.get(0).getApellido()+ " y "+this.revisores.get(1).getNombre()
        + this.revisores.get(1).getApellido()+ "se le solicita que emita su respuesta de si el articulo se publicara o no.");
    }

    public void notificar(ArrayList<Revisor> revisores){
        Mail.inicializarSistemaCorreo();
        for(Revisor r: revisores){
            Mail.sendMail(r.getCorreo(), "Se le asgino una articulo para revision", "Estimado "+r.getNombre()+" "+r.getApellido()
            + "se le asigno un articulo adjunto a este correo para su revision y se solicita que lo visualice y emita su juicio y comentarios"
            +" con respectos al articulo");
        }
    }

    public void notificar(Autor autor){
        Mail.inicializarSistemaCorreo();
        Mail.sendMail(this.articulo.getAutor().getCorreo() , "Su articulo entro a revision", "Se le informa que su articulo"
        +" sera revisado por "+this.revisores.get(0).getNombre()+" "+this.revisores.get(0).getApellido()+" y por "
        + this.revisores.get(1).getNombre()+" "+this.revisores.get(1).getApellido()+" y se le asigno al editor "
        + this.editor.getNombre()+" "+this.editor.getApellido()+". Se le informara sobre su proceso conforme el mismo avance.");
    }

    private void enviarCorreo(Autor autor, String correo, String asunto, String cuerpo){
        Mail.inicializarSistemaCorreo();
        Mail.sendMail(correo, asunto, cuerpo);
    }


    private void enviarCorreo(Revisor revisor, String correo, String asunto, String cuerpo){
        Mail.inicializarSistemaCorreo();
        Mail.sendMail(correo, asunto, cuerpo);
    }

    private void enviarCorreo(Editor editor, String correo, String asunto, String cuerpo){
        Mail.inicializarSistemaCorreo();
        Mail.sendMail(correo, asunto, cuerpo);
    }
}
