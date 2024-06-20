public class Revisor extends Usuario{
    private String especialidad;
    private int articuloRevisado;
    private String userName;
    private String contrasena;

    public Revisor(String nombre, String apellido, String correo, String especialidad, int ArticuloRevisado, String userName, String contrasena){
        super(nombre,apellido,correo);
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

}
