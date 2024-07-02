package users;
public class Usuario {
    protected String nombre;
    protected String apellido;
    protected String correo;
    protected Rol rol;

    //Constructor
    public Usuario(String nombre, String apellido, String correo, Rol rol){
        this.nombre= nombre;
        this.apellido= apellido;
        this.correo= correo;
        this.rol = rol;
    }

    //Getters
    public String getNombre() {return nombre;}
    public String getApellido() {return apellido;}
    public String getCorreo() {return correo;}
    public Rol getRol(){return rol;}
    
    //Setters
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setApellido(String apellido) {this.apellido = apellido;}
    public void setCorreo(String correo) {this.correo = correo;}
    public void setRol(Rol rol){this.rol=rol;}

    public boolean equals(Usuario usuario){
        if(this.nombre.toUpperCase().equals(nombre.toUpperCase()) && this.apellido.toUpperCase().equals(apellido.toUpperCase()) && this.correo.toUpperCase().equals(correo.toUpperCase()) && this.rol.equals(rol)){
            return true;
        }
        else{
            return false;
        }
    }
    
    //Metodo toString
    @Override
    public String toString(){
        return this.nombre+" "+this.apellido+" "+this.correo+" "+this.rol;
    }    
    
}
