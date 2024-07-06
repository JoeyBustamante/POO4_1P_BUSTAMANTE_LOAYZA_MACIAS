package users;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Revisor extends Usuario{
    protected String especialidad;
    protected int articuloRevisado;
    protected String userName;
    protected String contrasena;

    public Revisor(String nombre, String apellido, String correo,Rol rol, String especialidad, int ArticuloRevisado, String userName, String contrasena){
        super(nombre,apellido,correo,rol);
        this.especialidad=especialidad;
        this.articuloRevisado=articuloRevisado;
        this.userName=userName;
        this.contrasena=contrasena;
    }

    //Getters
    public String getEspecialidad(){return this.especialidad;}
    public int getArticuloRevisado(){return this.articuloRevisado;}
    public String getUserName(){return this.userName;}
    public String getContrasena(){return this.contrasena;}

    //Setters
    public void setEspecialidad(String especialidad){this.especialidad=especialidad;}
    public void setArticuloRevisado(int articuloRevisado){this.articuloRevisado=articuloRevisado;}
    public void setUserName(String userName){this.userName=userName;}
    public void setContrasena(String contrasena){this.contrasena=contrasena;}

    //InicioSesion
    public static boolean InicioSesion(String usuario, String contrasena){
        boolean seEncuentra=false;
        try {
            BufferedReader lector=new BufferedReader(new FileReader("src\\main\\java\\Archivos\\Usuarios.txt"));
            String linea;
            while ((linea=lector.readLine())!=null) {
                String[] lista=linea.split(" ");

                if( lista[3].equals("R")){
                    if (usuario.equals(lista[4]) && contrasena.equals(lista[5])){
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


    //Agregar comentario Articulo
    public void AgergarComentario(){
        ArrayList<String> listaLeida = new ArrayList<>();
        try (BufferedReader br=new BufferedReader(new FileReader("src\\main\\java\\Archivos\\Revisiones.txt"))){
            
        } catch (IOException e) {
            // TODO: handle exception
        }
        for(String linea:listaLeida){
            String[] lista=linea.split(" ");
            
        }
        System.out.println("Ingerese el comentario: ");
        
        try {
            BufferedWriter bw= new BufferedWriter(new FileWriter("src\\main\\java\\Archivos\\Revisiones.txt",true));
            

        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }

    

}
