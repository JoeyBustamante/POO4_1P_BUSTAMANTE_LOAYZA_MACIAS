import java.util.ArrayList;
import users.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
public class Sistema {
    public static ArrayList<Usuario> listaUsuario=new ArrayList<Usuario>();
    private static ArrayList<Articulo> listaArticulo=new ArrayList<Articulo>();

    //Metodos
    public static void AgergarListaUsuario(Usuario user){
       listaUsuario.add(user);
    }
    public static void AgergarListaArticulo(Articulo articulo){
        listaArticulo.add(articulo);
    }
    public static void registrarDatoUsuario(){
        try{
            BufferedWriter escrito=new BufferedWriter(new FileWriter("src\\main\\java\\Archivos\\Usuarios.txt",false));
            for (Usuario usuario : listaUsuario) {
                escrito.write(usuario+"\n");
            }
            escrito.close();
        }catch(IOException e){
            System.out.println("Error al escribir en el archivo");
        }

    }
}
