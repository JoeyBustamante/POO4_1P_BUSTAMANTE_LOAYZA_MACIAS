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
        notificar(editorElegido);
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
        notificar(r1);
        notificar(r2);

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


    //Metodos para notificar
    public void notificarAutor(){
        Mail.inicializarSistemaCorreo();
        Mail.sendMail(this.articulo.getAutor().getCorreo() , "Su articulo entro a revision", "Se le informa que su articulo"
        +" sera revisado por "+this.revisores.get(0).getNombre()+" "+this.revisores.get(0).getApellido()+" y por "
        + this.revisores.get(1).getNombre()+" "+this.revisores.get(1).getApellido()+" y se le asigno al editor "
        + this.editor.getNombre()+" "+this.editor.getApellido()+". Se le informara sobre su proceso conforme el mismo avance.");
    }

    public void notificar(Usuario user){
        Mail.inicializarSistemaCorreo();
        if(user instanceof Editor){
            Editor editor=(Editor)user;
            enviarCorreo(editor, "Se le asigno un articulo como editor", "Estimado "+editor.getNombre()+" "
        + editor.getApellido()+" se le envio este articulo para que de su aprobacion luego de que fue revisado por los revisores");
        } else if(user instanceof Revisor){
            Revisor revisor=(Revisor)user;
            enviarCorreo(revisor, "Se le asgino una articulo para revision", "Estimado "+revisor.getNombre()+" "+revisor.getApellido()
            + "se le asigno un articulo adjunto a este correo para su revision y se solicita que lo visualice y emita su juicio y comentarios"
            +" con respectos al articulo");
        }
    }

    //Metodos de enviar correo
    public static void enviarCorreo(Usuario user, String asunto, String cuerpo){
        Mail.inicializarSistemaCorreo();
        Mail.sendMail(user.correo, asunto, cuerpo);
    }

    @Override
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
