package users;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Representa un autor, que es un tipo de usuario con la capacidad de escribir y someter artículos.
 * Esta clase permite gestionar los atributos del autor, someter nuevos artículos y realizar diversas operaciones relacionadas.
 * Extiende la clase Usuario.
 * 
 * @see Usuario
 * @see Articulo
 * @see Revision
 */
public class Autor extends Usuario {
    private int id;
    private String institucion;
    private String campoDeInvestgacion;
    private ArrayList<Articulo> articulo = new ArrayList<Articulo>();

    /**
     * Constructor para crear un autor con los atributos especificados.
     *
     * @param nombre             el nombre del autor
     * @param apellido           el apellido del autor
     * @param correo             el correo electrónico del autor
     * @param rol                el rol del autor
     * @param id                 el identificador del autor
     * @param institucion        la institución del autor
     * @param campoDeInvestigacion el campo de investigación del autor
     * @param articulo           la lista de artículos del autor
     */
    public Autor(String nombre, String apellido, String correo, Rol rol, int id, String institucion, String campoDeInvestigacion, ArrayList<Articulo> articulo) {
        super(nombre, apellido, correo, rol);
        this.id = id;
        this.institucion = institucion;
        this.campoDeInvestgacion = campoDeInvestigacion;
        this.articulo = articulo;
    }

    /**
     * Obtiene el identificador del autor.
     *
     * @return el identificador del autor
     */
    public int getId() {
        return this.id;
    }

    /**
     * Establece un nuevo identificador para el autor.
     *
     * @param id el nuevo identificador del autor
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene la institución del autor.
     *
     * @return la institución del autor
     */
    public String getInstitucion() {
        return this.institucion;
    }

    /**
     * Establece una nueva institución para el autor.
     *
     * @param institucion la nueva institución del autor
     */
    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    /**
     * Obtiene el campo de investigación del autor.
     *
     * @return el campo de investigación del autor
     */
    public String getCampoDeInvestigacion() {
        return this.campoDeInvestgacion;
    }

    /**
     * Establece un nuevo campo de investigación para el autor.
     *
     * @param campoDeInvestigacion el nuevo campo de investigación del autor
     */
    public void setCampoDeInvestigacion(String campoDeInvestigacion) {
        this.campoDeInvestgacion = campoDeInvestigacion;
    }

    /**
     * Obtiene la lista de artículos del autor.
     *
     * @return la lista de artículos del autor
     */
    public ArrayList<Articulo> getArticulo() {
        return this.articulo;
    }

    /**
     * Establece una nueva lista de artículos para el autor.
     *
     * @param articulo la nueva lista de artículos del autor
     */
    public void setArticulo(ArrayList<Articulo> articulo) {
        this.articulo = articulo;
    }

    /**
     * Método estático para someter un nuevo artículo.
     * Solicita al usuario ingresar varios detalles, crea un nuevo autor y artículo,
     * y realiza diversas operaciones relacionadas con la revisión del artículo.
     */
    public static void someterArticulo() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese su nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Ingrese su apellido:");
        String apellido = sc.nextLine();
        System.out.println("Ingrese su correo: ");
        String correo = sc.nextLine();
        System.out.println("Ingrese su especialidad: ");
        String especialidad = sc.nextLine();
        System.out.println("Ingrese su institucion: ");
        String institucion = sc.nextLine();
        System.out.println("Escriba su campo de investigacion: ");
        String campoDeInvestigacion = sc.nextLine();
        System.out.println("Escriba el contenido de su articulo: ");
        String contenido = sc.nextLine();
        Random rd = new Random();
        int codigoAutor = 100 + rd.nextInt(9000);
        int codigoArticulo = 100 + rd.nextInt(9000);

        ArrayList<Articulo> articulos = new ArrayList<>();

        Autor aut = new Autor(nombre, apellido, correo, Rol.A, codigoAutor, institucion, campoDeInvestigacion, articulos);
        Articulo art = new Articulo(aut, codigoArticulo, contenido);
        Revision revision = new Revision(null, null, art, null, codigoArticulo);
        articulos.add(art);
        art.guardarArticulo();
        aut.EncontrarAutor();
        revision.asignarEditor();
        revision.asignarRevisor(art);
        revision.notificar();
        revision.guardarRevision();
    }

    /**
     * Encuentra y actualiza la información del autor en el archivo de texto "Investigadores.txt".
     * Si el autor no se encuentra en el archivo, se añade una nueva entrada.
     * También actualiza la lista de usuarios del sistema.
     */
    public void EncontrarAutor() {
        ArrayList<String> listaLeida = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("src\\main\\java\\Archivos\\Investigadores.txt"));
            String linea;
            while ((linea = br.readLine()) != null) {
                listaLeida.add(linea);
            }
        } catch (IOException e) {
            // Manejar excepción
        }

        int contador = 0;
        boolean noEncuentra = true;
        for (int i = 0; i < listaLeida.size(); i++) {
            String l1 = listaLeida.get(i);
            String lista[] = l1.split("_");
            Usuario user = new Usuario(lista[0], lista[1], lista[2], Rol.valueOf(lista[3]));
            if (equals(user)) {
                noEncuentra = false;
                String lin1 = lista[7].replace("[", "");
                String lin2 = lin1.replace("]", "");
                String list[] = lin2.split(",");
                this.setId(Integer.parseInt(lista[4]));

                for (String elementos : list) {
                    String ele[] = elementos.split("-");
                    Articulo art = new Articulo(this, Integer.parseInt(ele[1]), ele[2]);
                    this.articulo.add(art);
                }
                break;
            } else {
                contador += 1;
            }
        }
        if (!noEncuentra) {
            listaLeida.set(contador, toString());
        } else {
            listaLeida.add(toString());
            Sistema.listaUsuario.add(this);
            Sistema.registrarDatoUsuario();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src\\main\\java\\Archivos\\Investigadores.txt", false))) {
            for (String linea : listaLeida) {
                bw.write(linea + "\n");
            }
        } catch (IOException e) {
            // Manejar excepción
        }
    }

    /**
     * Devuelve una representación en forma de cadena del autor.
     * El formato es "nombre_apellido_correo_rol_id_institucion_campoDeInvestigacion_articulos".
     *
     * @return una cadena que representa al autor
     */
    @Override
    public String toString() {
        return super.toString() + "_" + this.id + "_" + this.institucion + "_" + this.campoDeInvestgacion + "_" + this.articulo.toString();
    }
}
