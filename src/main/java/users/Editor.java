package users;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.chrono.ThaiBuddhistChronology;
import java.util.ArrayList;
import java.util.Scanner;

public class Editor extends Usuario {
    protected String especialidad;
    protected String nombreJournal;
    protected String userName;
    protected String contrasena;

    //Constructor
    public Editor(String nombre, String apellido, String correo, Rol rol, String especialidad, String nombreJournal, String userName, String contrasena){
        super(nombre, apellido, correo,rol);
        this.especialidad=especialidad;
        this.nombreJournal=nombreJournal;
        this.userName=userName;
        this.contrasena=contrasena;
    }

    //Getters
    public String getEspecialidad(){ return this.especialidad;}
    public String getNombreJournal(){ return this.nombreJournal;}
    public String getUserName(){ return this.userName;}
    public String getContrasena(){ return this.contrasena;}

    //Setters
    public void setEspecialidad(String especialidad){this.especialidad=especialidad;}
    public void setNombreJournal(String nombreJournal){this.nombreJournal=nombreJournal;}
    public void setUserName(String userName){this.userName=userName;}
    public void setContrasena(String contrasenia){this.contrasena=contrasenia;}

    //InicioSesion
    public static boolean InicioSesion(String usuario, String contrasena){
        boolean seEncuentra=false;
        try {
            BufferedReader lector=new BufferedReader(new FileReader("src\\main\\java\\Archivos\\Usuarios.txt"));
            String linea;
            while ((linea=lector.readLine())!=null) {
                String[] lista=linea.split("_");


                if( lista[3].equals("E")){
                    if (usuario.equals(lista[6]) && contrasena.equals(lista[7])){
                        seEncuentra= true;
                        return seEncuentra;
                        
                    }

                }
                
            }
            
        } catch (IOException e) {
            System.out.println("Error de archivo");
        }
        return seEncuentra;
    }  
    //Mostrar decisiones
    public static ArrayList<String> mostrarCodArticulos(String usuario){
        ArrayList<String> codigos=new ArrayList<String>();
        try (BufferedReader br= new BufferedReader(new FileReader("src\\main\\java\\Archivos\\Revisiones.txt"))){
            String linea;
            
            while ((linea=br.readLine())!=null) {
                String[] lista=linea.split("_");
                if(lista[0].equals(usuario)){
                    System.out.println(lista[4]);
                    codigos.add(lista[4]);
                }
                
            }
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        return codigos;
        
    }  

    //Decision
    public static void decisionFinal(int codigoArticulo,String usuario){
        ArrayList<String> lineas=new ArrayList<String>();
        Decision decision;
        int posicion=0;
        String lin="";
        try (BufferedReader br=new BufferedReader(new FileReader("src\\main\\java\\Archivos\\Revisiones.txt"))){
            String linea;
            Scanner sc=new Scanner(System.in);
            while ((linea=br.readLine())!=null) {
                lineas.add(linea);
                String[] lista=linea.split("_");
                if(codigoArticulo==Integer.parseInt(lista[4])){
                    posicion=lineas.indexOf(linea);
                    try (BufferedReader br1=new BufferedReader(new FileReader("src\\main\\java\\Archivos\\RevicionesP.txt"))){
                        String linea1;
                        while ((linea1=br1.readLine())!=null) {
                            String[] lista1=linea1.split("_");
                            if(Integer.parseInt(lista1[3])==codigoArticulo){
                                System.out.println(lista1[0]+" "+lista1[1]+" "+ lista1[2]);
                            }
                        }
                    } catch (IOException e) {
                        // TODO: handle exception
                    }
                    System.out.println("Aprueba el articulo ACEPTADO/RECHAZADO");
                    String deci=sc.nextLine().toUpperCase();
                    boolean incorrecto1=deci.equals(Decision.RECHAZADO.toString());
                    boolean incorrecto2=deci.equals(Decision.ACEPTADO.toString());
                    while(!incorrecto1 && !incorrecto2){
                        System.out.println("Decision no precisa");
                        System.out.println("Ingrese su decision(ACEPTADO/RECHAZADO):");
                        deci=sc.nextLine().toUpperCase();
                        incorrecto1=deci.equals(Decision.RECHAZADO.toString());
                        incorrecto2=deci.equals(Decision.ACEPTADO.toString());
                    }
                    decision=Decision.valueOf(deci);
                    int codAutor=0;
                    try (BufferedReader br1=new BufferedReader(new FileReader("src\\main\\java\\Archivos\\Articulos.txt"));){
                        while((linea=br1.readLine())!=null){
                            String[] lista1=linea.split("-");
                            if(codigoArticulo==Integer.parseInt(lista1[1])){
                                codAutor=Integer.parseInt(lista1[0]);
                            }
                        }
                        
                    } catch (IOException e) {
                        // TODO: handle exception
                    }
                    Autor autor=new Autor(null, null, null, null, codAutor,null, null, null);
                    try (BufferedReader br2=new BufferedReader(new FileReader("src\\main\\java\\Archivos\\Investigadores.txt"))){
                        while ((linea=br2.readLine())!=null) {
                            String[] lista2=linea.split("_");
                            if(codAutor==Integer.parseInt(lista2[3])){
                                autor.setApellido(lista2[1]);
                                autor.setNombre(lista2[0]);
                                autor.setCorreo(lista2[2]);
                                autor.setRol(Rol.valueOf(lista2[3]));
                                autor.setInstitucion(lista2[5]);
                                autor.setCampoDeInvestigacion(lista2[6]);


                            }
                        }
                        
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                    lin=lista[0]+"_"+lista[1]+"_"+lista[2]+"_"+lista[3]+"_"+lista[4]+"_"+decision.toString();
                    // AQUI VA LA MODIFICACION (ELIMINAR ESTE COMENTARIO LUEGO DE REALIZARLA)
                    Revision.enviarCorreo(autor , "El editor" + decision+ "su articulo",""+ "Su articulo fue" + decision + "por el editor "+ usuario +" y su articulo sera publicado en caso de haber sido aceptado.");

                }
                
            }
        } catch (IOException e) {
            // TODO: handle exception
        }
        lineas.set(posicion, lin);
        try(BufferedWriter bw= new BufferedWriter(new FileWriter("src\\main\\java\\Archivos\\Revisiones.txt",false))) {
            for(String linea:lineas){
                bw.write(linea+"\n");
            }
        } catch (IOException e) {
            // TODO: handle exception
        }
        
    }

    //Comentario
    public static void agregarComentario(int codigoArticulo){
        ArrayList<String> lineas=new ArrayList<String>();
        String comentario;
        int posicion=0;
        String lin="";
        try (BufferedReader br=new BufferedReader(new FileReader("src\\main\\java\\Archivos\\Revisiones.txt"))){
            String linea;
            Scanner sc=new Scanner(System.in);
            while ((linea=br.readLine())!=null) {
                lineas.add(linea);
                String[] lista=linea.split("_");
                if(codigoArticulo==Integer.parseInt(lista[4])){
                    posicion=lineas.indexOf(linea);
                    System.out.println("Ingrese su comentario:");
                    comentario=sc.nextLine();
                    lin=lista[0]+"_"+lista[1]+"_"+lista[2]+"_"+comentario+"_"+lista[4]+"_"+lista[5];

                }
                
            }
        } catch (IOException e) {
            // TODO: handle exception
        }
        lineas.set(posicion, lin);
        try(BufferedWriter bw= new BufferedWriter(new FileWriter("src\\main\\java\\Archivos\\Revisiones.txt",false))) {
            for(String linea:lineas){
                bw.write(linea+"\n");
            }
        } catch (IOException e) {
            // TODO: handle exception
        }        
    }

    //Mostrar Articulos
    public ArrayList<String> mostraArticulosEditor(){
        ArrayList<String> codigos=new ArrayList<String>();
        int contador=1;
        try(BufferedReader br=new BufferedReader(new FileReader("src\\main\\java\\Archivos\\Revisiones.txt"))) {
            String linea;
            while((linea=br.readLine())!=null){
                String[] lista=linea.split("_");
                if (lista[0].equals(userName)){
                    System.out.println(contador+lista[4]);
                    contador+=1;
                    codigos.add(lista[4]);
                }
            }
            
        } catch (IOException e) {
            // TODO: handle exception
        }
        return codigos;
    }

    
    
}
