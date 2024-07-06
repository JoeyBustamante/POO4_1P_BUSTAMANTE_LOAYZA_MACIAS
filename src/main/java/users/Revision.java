package users;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Random;

import mail.Mail;
public class Revision {
    private Editor editor;
    private ArrayList<Revisor> revisores;
    private Articulo articulo;
    private String comentario;
    private int artID;

    //Metodo static
    public static void inicializarSistemaRevision(Articulo articulo){
        //Recibe un articulo que se usara para crar una instacnia de revision y el nombre de la variable sera la id del articulo
        //Y luego buscara al azar tanto revisores como un editor para inicializar el objeto de revision.
    }

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

    public void asignarEditor(){
        Random random=new Random();
        Editor editorElegido;
        ArrayList<Editor> editores=new ArrayList<Editor>();
        try(BufferedReader br=new BufferedReader(new FileReader("src\\main\\java\\Archivos\\Usuarios.txt"))) {
            String linea;
            while ((linea=br.readLine())!=null) {
                String[] lista=linea.split(" ");
                if(Rol.valueOf(lista[3])==Rol.E){
                    Editor ed=new Editor(lista[0], lista[1], lista[2], Rol.valueOf(lista[3]), lista[4], lista[5], lista[6], lista[7]);
                    editores.add(ed);
                }
            }
        } catch (IOException e) {
            // TODO: handle exception
        }
        int posicion=random.nextInt(editores.size());
        editorElegido=editores.get(posicion);
        this.editor=editorElegido;

    }
    public void asignarRevisor(){
        Random random=new Random();
        Random random2=new Random();
        ArrayList<Revisor> revisores= new ArrayList<Revisor>();
        try(BufferedReader br=new BufferedReader(new FileReader("src\\main\\java\\Archivos\\Usuarios.txt"))) {
            String linea;
            while ((linea=br.readLine())!=null) {
                String[] lista=linea.split(" ");
                if(Rol.valueOf(lista[3])==Rol.R){
                    Revisor rev=new Revisor(lista[0], lista[1], lista[2], Rol.valueOf(lista[3]), lista[4], Integer.parseInt(lista[5]), lista[6], lista[7]);
                    revisores.add(rev);
                }
            }            
        } catch (IOException e) {
            // TODO: handle exception
        }
        int posicion1=random.nextInt(revisores.size());
        int posicion2=random2.nextInt(revisores.size());
        while (posicion1==posicion2) {
            posicion2=random2.nextInt(revisores.size());
        }
        this.revisores.add(revisores.get(posicion1));
        this.revisores.add(revisores.get(posicion2));

    }

    //Guardar en Revisiones.txt
    public void guardarRevision(){
        try {
            BufferedWriter bw=new BufferedWriter(new FileWriter("src\\main\\java\\Archivos\\Revisiones.txt", false));
            bw.write(toString());

            
        } catch (IOException e) {
            // TODO: handle exception
        }
    }

    // Metodo para agregar comentarios
    private void agregarComentario(){
        System.out.println("Ingrese su comentario: ");
        String comentario = sc.nextLine();
        this.comentario=comentario;
    }

    // Metodos para decidir
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

    //Metodos para notificar
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

    //Metodos de enviar correo
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

    public String toString(){
        return editor.getUserName()+" ["+revisores.get(1).getUserName()+","+revisores.get(2).getUserName()+"] "+articulo.getDatos()+" "+comentario+" "+artID;
    }
}
