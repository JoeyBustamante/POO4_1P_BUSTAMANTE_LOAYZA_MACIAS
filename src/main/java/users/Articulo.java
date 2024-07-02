package users;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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


    public void guardarArticulo(){

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src\\main\\java\\Archivos\\Articulos.txt",true))) {
            writer.write(toString());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    //toString
    public String toString() {
        // TODO Auto-generated method stub
        return autor.toString()+" "+this.codigo+" "+" "+this.datos;
    }


}
