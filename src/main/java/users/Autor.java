package users;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ALIENWARE
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
public class Autor extends Usuario {
    protected int id;
    protected String institucion;
    protected String campoDeInvestgacion;
    protected ArrayList<Articulo> articulo=new ArrayList<Articulo>();
    
    //Constructor
    public Autor(String nombre, String apellido, String correo, Rol rol, int id, String institucion, String campoDeInvestigacion, ArrayList<Articulo> articulo){
        super(nombre,apellido,correo,rol);
        this.id=id;
        this.institucion=institucion;
        this.campoDeInvestgacion=campoDeInvestigacion;
        this.articulo=articulo;
    }
    
    //Getters
    public int getId(){return this.id;}
    public String getInstitucion(){return this.institucion;}
    public String getCampoDeInvestigacion(){return this.campoDeInvestgacion;}
    public ArrayList<Articulo> getArticulo(){return this.articulo;}

    //Setters
    public void setId(int id){this.id=id;}
    public void setInstitucion(String institucion){this.institucion=institucion;}
    public void setCampoDeInvestigacion(String campoDeInvestgacion){this.campoDeInvestgacion=campoDeInvestgacion;}
    public void setArticulo(ArrayList<Articulo> articulo){this.articulo=articulo;}


    public static void someterArticulo(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese su nombre: ");
        String nombre= sc.nextLine();
        System.out.println("Ingrese su apellido");
        String apellido = sc.nextLine();
        System.out.println("Ingrese su correo: ");
        String correo = sc.nextLine();
        System.out.println("Ingrese su especialidad: ");
        String especialidad = sc.nextLine();
        System.out.println("Ingrese su institucion: ");
        String institucion = sc.nextLine();
        System.out.println("Escriba su campo de invesigacion: ");
        String campoDeIvestigacion = sc.nextLine();
        System.out.println("Escriba el contenido de su articulo: ");
        String contenido = sc.nextLine();
        Random rd = new Random();
        int codigoAutor =  100 + rd.nextInt(9000);
        int codigoArticulo = 100+ rd.nextInt(9000);

        ArrayList<Articulo> articulos = new ArrayList<>();

        Autor aut = new Autor(nombre, apellido, correo, Rol.A, codigoAutor, institucion, campoDeIvestigacion, articulos);
        Articulo art = new Articulo(aut, codigoArticulo, contenido);
        articulos.add(art);

        art.guardarArticulo();
        aut.guardarAutor();
        





        
    }


    public void guardarAutor(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src\\main\\java\\Archivos\\Investigadores.txt",true))) {
            writer.write(nombre+ " "+ apellido + " " + correo + " "+ "A"+" "+id+ " "+institucion+" "+campoDeInvestgacion+" "+articulo.toString());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }





    

}
