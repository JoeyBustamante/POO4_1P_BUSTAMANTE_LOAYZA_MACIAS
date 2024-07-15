package users;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Representa un artículo escrito por un autor.
 * Esta clase permite crear, obtener y modificar artículos,
 * así como guardar su información en un archivo de texto.
 */
public class Articulo {
    private Autor autor;
    private int codigo;
    private String datos;

    /**
     * Constructor para crear un artículo con un autor, código y datos específicos.
     *
     * @param autor  el autor del artículo
     * @param codigo el código del artículo
     * @param datos  los datos del artículo
     */
    public Articulo(Autor autor, int codigo, String datos){
        this.autor = autor;
        this.codigo = codigo;
        this.datos = datos;
    }

    /**
     * Obtiene el autor del artículo.
     *
     * @return el autor del artículo
     */
    public Autor getAutor() {
        return autor;
    }

    /**
     * Establece un nuevo autor para el artículo.
     *
     * @param autor el nuevo autor del artículo
     */
    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    /**
     * Obtiene el código del artículo.
     *
     * @return el código del artículo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Establece un nuevo código para el artículo.
     *
     * @param codigo el nuevo código del artículo
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene los datos del artículo.
     *
     * @return los datos del artículo
     */
    public String getDatos() {
        return datos;
    }

    /**
     * Establece nuevos datos para el artículo.
     *
     * @param datos los nuevos datos del artículo
     */
    public void setDatos(String datos) {
        this.datos = datos;
    }

    /**
     * Guarda la información del artículo en un archivo de texto.
     * El archivo se encuentra en la ruta "src\main\java\Archivos\Articulos.txt".
     * Si ocurre un error durante la escritura en el archivo, se imprime un mensaje de error.
     */
    public void guardarArticulo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src\\main\\java\\Archivos\\Articulos.txt", true))) {
            writer.write(toString());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    /**
     * Devuelve una representación en forma de cadena del artículo.
     * El formato es "idAutor-codigo-datos".
     *
     * @return una cadena que representa el artículo
     */
    @Override
    public String toString() {
        return autor.getId() + "-" + this.codigo + "-" + this.datos;
    }
}
