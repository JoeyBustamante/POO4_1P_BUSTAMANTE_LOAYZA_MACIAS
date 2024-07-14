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
import java.io.BufferedReader;
import java.io.FileReader;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
public class Autor extends Usuario {
    private int id;
    private String institucion;
    private String campoDeInvestgacion;
    private ArrayList<Articulo> articulo=new ArrayList<Articulo>();
    
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
    public Rol getRol(){return this.rol;}
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
        System.out.println("Ingrese su apellido:");
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
        Revision revision=new Revision(null, null, art, null, codigoArticulo);
        articulos.add(art);
        art.guardarArticulo();
        aut.EncontrarAutor();
        revision.asignarEditor();
        revision.asignarRevisor( art);
        revision.notificarAutor();
        revision.guardarRevision();
        System.out.println(revision.toString());

        
    }


    

    public void EncontrarAutor(){
        ArrayList<String> listaLeida = new ArrayList<>();
        try {
            
            ArrayList<String> listaescrita = new ArrayList<>();
            BufferedReader br=new BufferedReader(new FileReader("src\\main\\java\\Archivos\\Investigadores.txt"));
            String linea;
            while ((linea=br.readLine())!=null) {
                listaLeida.add(linea);
            }

        } catch (IOException e) {
            // TODO: handle exception
        }
        ArrayList<Articulo> listaArticulo=new ArrayList<Articulo>();
        int contador=0;
        boolean noEncuentra=true;
        for (int i=0; i<listaLeida.size();i++) {
            String l1=listaLeida.get(i);
            String lista[]=l1.split("_");
            Usuario user=new Usuario(lista[0], lista[1], lista[2],Rol.valueOf(lista[3]));
            if(equals(user)){
                noEncuentra=false;
                String lin1=lista[7].replace("[", "");
                String lin2=lin1.replace("]","");
                String list[]=lin2.split(",");
                this.setId(Integer.parseInt(lista[4]));

                for (String elementos : list) {
                    String ele[]=elementos.split("-");
                    Articulo art=new Articulo(this, Integer.parseInt(ele[1]),ele[2]);
                    this.articulo.add(art);
                }
                break;
            }
            else{
                contador+=1;
            }
        }
        if(!noEncuentra){
            listaLeida.set(contador,toString());
        }
        else{
            listaLeida.add(toString());
            Sistema.listaUsuario.add(this);
            Sistema.registrarDatoUsuario();
        }

        try (BufferedWriter bw=new BufferedWriter(new FileWriter("src\\main\\java\\Archivos\\Investigadores.txt",false))){
            for(String linea:listaLeida){
                bw.write(linea+"\n");
            }
            
        } catch (IOException e) {
            // TODO: handle exception
        }
        
    }

    

    //toString
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString()+"_"+this.id+"_"+this.institucion+"_"+this.campoDeInvestgacion+"_"+this.articulo.toString();
    }






    

}
