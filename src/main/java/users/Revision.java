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

    public Editor asignarEditor(){
        Random random=new Random();
        Editor editorElegido;
        ArrayList<Editor> editores=new ArrayList<Editor>();
        try(BufferedReader br=new BufferedReader(new FileReader("src\\main\\java\\Archivos\\Usuarios.txt"))) {
            String linea;
            while ((linea=br.readLine())!=null) {
                String[] lista=linea.split("_");
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
        return this.editor;

    }
    public void asignarRevisor(Articulo art){
        Random random=new Random();
        Random random2=new Random();
        ArrayList<Revisor> Listarevisores= new ArrayList<Revisor>();
        try(BufferedReader br=new BufferedReader(new FileReader("src\\main\\java\\Archivos\\Revisores.txt"))) {
            String linea;
            while ((linea=br.readLine())!=null) {
                String[] lista=linea.split("_");
                System.out.println(lista[3]);
                if(Rol.valueOf(lista[3]).equals(Rol.R)){
                    Revisor rev=new Revisor(lista[0], lista[1], lista[2], Rol.valueOf(lista[3]), lista[4], Integer.parseInt(lista[5]), lista[6], lista[7]);
                    Listarevisores.add(rev);
                }
            }            
        } catch (IOException e) {
            // TODO: handle exception
        }
        int posicion1=random.nextInt(Listarevisores.size());
        int posicion2=random2.nextInt(Listarevisores.size());
        while (posicion1==posicion2) {
            posicion2=random2.nextInt(Listarevisores.size());
        }

        ArrayList<Revisor> rev= new ArrayList<Revisor>();

        Revisor r1 =Listarevisores.get(posicion1);
        Revisor r2 =Listarevisores.get(posicion2);
        rev.add(r1);
        rev.add(r2);
        try{
            Adicionar1("src\\main\\java\\Archivos\\Revisores.txt", r1);
            Adicionar1("src\\main\\java\\Archivos\\Revisores.txt", r2);
        }catch(IOException e){
            e.printStackTrace();
        }
        this.revisores=rev;

        String contenido = "\n"+r1.userName+"_N/A N/A_"+art.getCodigo();

        try (FileWriter escritor = new FileWriter("src\\main\\java\\Archivos\\RevicionesP.txt", true)) {
            escritor.write(contenido);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }





    }

    //Guardar en Revisiones.txt
    public void guardarRevision(){
        try (BufferedWriter bw= new BufferedWriter(new FileWriter("src\\main\\java\\Archivos\\Revisiones.txt",true))){
            
            bw.write(toString()+"\n");;

            
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    // Metodo para agregar comentarios
    public void agregarComentario(){
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
        return editor.getUserName()+"_["+revisores.get(0).getUserName()+","+revisores.get(1).getUserName()+"]_"+articulo.getDatos()+"_"+comentario+"_"+artID+"_"+"null";
    }


    //Leer archivo
    public static ArrayList<String> leerArchivo(String rutaArchivo) throws IOException {
        ArrayList<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        }
        return lineas;
    }

    //Escribir en el Archivo

    public static void escribirArchivo(String rutaArchivo, ArrayList<String> lineas) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (String linea : lineas) {
                bw.write(linea);
                bw.newLine();
            }
        }
    }

    //modificar Archivo 


    public static void Adicionar1(String rutaArchivo,Revisor r1) throws IOException {
        ArrayList<String> lineas = leerArchivo(rutaArchivo);
        //int numRevisado1  = r1.articuloRevisado;
        


        for (int i = 0; i < lineas.size(); i++) {

            String linea = lineas.get(i);
            String [] sep = linea.split("_");
            if(r1.userName.equals(sep[6])){
                int num= Integer.parseInt(sep[5]);
                num+=1;
                sep[5]=String.valueOf(num);

                lineas.set(i,String.join("_", sep));

            }else{
               // System.out.println(sep[6]);
            }

            ;


        }

        escribirArchivo(rutaArchivo, lineas);
    }


}
