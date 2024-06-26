package users;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
                String[] lista=linea.split(" ");


                if( lista[3].equals("E")){
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

    
    
}
