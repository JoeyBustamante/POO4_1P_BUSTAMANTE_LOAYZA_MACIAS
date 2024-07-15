package users;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;

/**
 * Clase que gestiona el sistema de usuarios y artículos.
 */
public class Sistema {
    public static ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
    private static ArrayList<Articulo> listaArticulo = new ArrayList<Articulo>();

    /**
     * Agrega un usuario a la lista de usuarios del sistema.
     * 
     * @param user Usuario a agregar.
     */
    public static void AgergarListaUsuario(Usuario user) {
        listaUsuario.add(user);
    }

    /**
     * Agrega un artículo a la lista de artículos del sistema.
     * 
     * @param articulo Artículo a agregar.
     */
    public static void AgergarListaArticulo(Articulo articulo) {
        listaArticulo.add(articulo);
    }

    /**
     * Registra los datos de los usuarios en un archivo de texto.
     */
    public static void registrarDatoUsuario() {
        try {
            BufferedWriter escrito = new BufferedWriter(new FileWriter("src\\main\\java\\Archivos\\Usuarios.txt", true));
            for (Usuario usuario : listaUsuario) {
                if (usuario instanceof Editor) {
                    Editor ed = (Editor) usuario;
                    escrito.write(ed.toString() + "\n");
                } else if (usuario instanceof Revisor) {
                    Revisor re = (Revisor) usuario;
                    escrito.write(re.getNombre() + "_" + re.getApellido() + "_" + re.getCorreo() + "_" + re.getRol()
                            + "_" + re.getEspecialidad() + "_" + re.getArticuloRevisado() + "_" + re.getUserName()
                            + "_" + re.getContrasena() + "\n");
                } else if (usuario instanceof Autor) {
                    Autor autor = (Autor) usuario;
                    escrito.write(autor.toString() + "\n");
                }
            }
            escrito.close();
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo");
        }

    }

    /**
     * Carga los usuarios desde un archivo de texto y los devuelve en una lista.
     * 
     * @return Lista de usuarios cargados.
     */
    public ArrayList<Usuario> cargarUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src\\main\\java\\Archivos\\Usuarios.txt"))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String datos[] = linea.split(" ");
                String nombre = datos[0];
                String apellido = datos[1];
                String correo = datos[2];
                String rolr = datos[3];

                Rol rol = Rol.valueOf(rolr);

                Usuario usuario = new Usuario(nombre, apellido, correo, rol);
                usuarios.add(usuario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

}
