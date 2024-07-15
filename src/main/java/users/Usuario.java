package users;

/**
 * Clase que representa a un usuario genérico en el sistema.
 */
public class Usuario {
    protected String nombre;
    protected String apellido;
    protected String correo;
    protected Rol rol;

    /**
     * Constructor de la clase Usuario.
     * 
     * @param nombre   Nombre del usuario.
     * @param apellido Apellido del usuario.
     * @param correo   Correo electrónico del usuario.
     * @param rol      Rol del usuario (Editor, Revisor, Autor).
     */
    public Usuario(String nombre, String apellido, String correo, Rol rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.rol = rol;
    }

    /**
     * Método getter para obtener el nombre del usuario.
     * 
     * @return Nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método getter para obtener el apellido del usuario.
     * 
     * @return Apellido del usuario.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Método getter para obtener el correo electrónico del usuario.
     * 
     * @return Correo electrónico del usuario.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Método getter para obtener el rol del usuario.
     * 
     * @return Rol del usuario.
     */
    public Rol getRol() {
        return rol;
    }

    /**
     * Método setter para establecer el nombre del usuario.
     * 
     * @param nombre Nuevo nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método setter para establecer el apellido del usuario.
     * 
     * @param apellido Nuevo apellido del usuario.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Método setter para establecer el correo electrónico del usuario.
     * 
     * @param correo Nuevo correo electrónico del usuario.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Método setter para establecer el rol del usuario.
     * 
     * @param rol Nuevo rol del usuario.
     */
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    /**
     * Método que compara dos objetos Usuario para determinar si son iguales.
     * 
     * @param usuario Usuario con el que se compara.
     * @return true si los usuarios son iguales (misma información), false en caso contrario.
     */
    public boolean equals(Usuario usuario) {
        if (this.nombre.equals(usuario.getNombre()) && this.apellido.equals(usuario.getApellido())
                && this.correo.equals(usuario.getCorreo()) && this.rol.equals(usuario.getRol())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método que devuelve una representación en cadena del objeto Usuario.
     * 
     * @return Representación en cadena del objeto Usuario.
     */
    @Override
    public String toString() {
        return this.nombre + "_" + this.apellido + "_" + this.correo + "_" + this.rol;
    }
}
