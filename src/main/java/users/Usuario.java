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
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }
    
    //Setters

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    
}
