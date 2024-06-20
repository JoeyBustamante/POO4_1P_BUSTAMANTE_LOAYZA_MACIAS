public class Editor extends Usuario {
    private String especialidad;
    private String nombreJournal;
    private String userName;
    private String contrasenia;

    //Constructor
    public Editor(String nombre, String apellido, String correo, String especialidad, String nombreJournal, String userName, String contrasenia){
        super(nombre, apellido, correo);
        this.especialidad=especialidad;
        this.nombreJournal=nombreJournal;
        this.userName=userName;
        this.contrasenia=contrasenia;
    }

    //Getters
    public String getEspecialidad(){ return this.especialidad;}
    public String getNombreJournal(){ return this.nombreJournal;}
    public String getUserName(){ return this.userName;}
    public String getContrasenia(){ return this.contrasenia;}

    //Setters
    public void setEspecialidad(String especialidad){this.especialidad=especialidad;}
    public void setNombreJournal(String nombreJournal){this.nombreJournal=nombreJournal;}
    public void setUserName(String userName){this.userName=userName;}
    public void setContrasenia(String contrasenia){this.contrasenia=contrasenia;}
    
}
