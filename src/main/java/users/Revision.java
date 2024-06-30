package users;
import java.util.ArrayList;
public class Revision {
    private Editor editor;
    private ArrayList<Revisor> revisores;
    private Articulo articulo;
    private String comentario;
    private int artID;

    //Constructor
    public Revision(Editor editor,ArrayList<Revisor> revisores, Articulo articulo, String comentario, int artID){
        this.editor=editor;
        this.revisores=revisores;
        this.articulo=articulo;
        this.comentario=comentario;
        this.artID=artID;
    }

    //Getters
    public Editor getEditor(){return this.editor;}
    public ArrayList<Revisor> getRevisores(){return this.revisores;}
    public Articulo getArticulo(){return this.articulo;}
    public String getComentario(){return this.comentario;}
    public int getArtID(){return this.artID;}
    
    //Setters
    public void setEditor(Editor editor){this.editor=editor;}
    public void setRevisores(ArrayList<Revisor> revisores){this.revisores=revisores;}
    public void setArticulo(Articulo articulo){this.articulo=articulo;}
    public void setComentario(String comentario){this.comentario=comentario;}
    public void setArtID(int artID){this.artID=artID;}



}
