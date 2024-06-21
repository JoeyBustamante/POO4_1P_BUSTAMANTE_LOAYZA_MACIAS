package users;
public class Articulo {
    private Autor autor;
    private int codigo;
    private String datos;

    public Articulo(Autor autor, int codigo, String datos){
        this.autor=autor;
        this.codigo=codigo;
        this.datos=datos;
    }

    //getters
    public Autor getAutor(){return autor;}
    public int getCodigo(){return codigo;}
    public String getDatos(){return datos;}

    //setters
    public void setAutor(Autor autor){this.autor=autor;}
    public void setCodigo(int codigo){this.codigo=codigo;}
    public void setDatos(String datos){this.datos=datos;}
    

}
