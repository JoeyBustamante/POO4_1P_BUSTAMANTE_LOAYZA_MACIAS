package users;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Representa a un Editor, un tipo de usuario que tiene la capacidad de gestionar artículos y tomar decisiones sobre ellos.
 * Esta clase permite gestionar los atributos del editor, iniciar sesión, mostrar decisiones sobre artículos, tomar decisiones finales,
 * agregar comentarios y mostrar artículos asignados al editor.
 * Extiende la clase Usuario.
 * 
 * @see Usuario
 * @see Articulo
 * @see Decision
 */
public class Editor extends Usuario {
    protected String especialidad;
    protected String nombreJournal;
    protected String userName;
    protected String contrasena;

    /**
     * Constructor para crear un editor con los atributos especificados.
     *
     * @param nombre        el nombre del editor
     * @param apellido      el apellido del editor
     * @param correo        el correo electrónico del editor
     * @param rol           el rol del editor
     * @param especialidad  la especialidad del editor
     * @param nombreJournal el nombre del journal del editor
     * @param userName      el nombre de usuario del editor
     * @param contrasena    la contraseña del editor
     */
    public Editor(String nombre, String apellido, String correo, Rol rol, String especialidad, String nombreJournal, String userName, String contrasena) {
        super(nombre, apellido, correo, rol);
        this.especialidad = especialidad;
        this.nombreJournal = nombreJournal;
        this.userName = userName;
        this.contrasena = contrasena;
    }

    /**
     * Obtiene la especialidad del editor.
     *
     * @return la especialidad del editor
     */
    public String getEspecialidad() {
        return this.especialidad;
    }

    /**
     * Establece una nueva especialidad para el editor.
     *
     * @param especialidad la nueva especialidad del editor
     */
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * Obtiene el nombre del journal del editor.
     *
     * @return el nombre del journal del editor
     */
    public String getNombreJournal() {
        return this.nombreJournal;
    }

    /**
     * Establece un nuevo nombre de journal para el editor.
     *
     * @param nombreJournal el nuevo nombre de journal del editor
     */
    public void setNombreJournal(String nombreJournal) {
        this.nombreJournal = nombreJournal;
    }

    /**
     * Obtiene el nombre de usuario del editor.
     *
     * @return el nombre de usuario del editor
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Establece un nuevo nombre de usuario para el editor.
     *
     * @param userName el nuevo nombre de usuario del editor
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Obtiene la contraseña del editor.
     *
     * @return la contraseña del editor
     */
    public String getContrasena() {
        return this.contrasena;
    }

    /**
     * Establece una nueva contraseña para el editor.
     *
     * @param contrasena la nueva contraseña del editor
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Inicia sesión para el editor con el nombre de usuario y la contraseña proporcionados.
     *
     * @param usuario    el nombre de usuario del editor
     * @param contrasena la contraseña del editor
     * @return true si la combinación de nombre de usuario y contraseña es correcta, false en caso contrario
     */
    public static boolean InicioSesion(String usuario, String contrasena) {
        boolean seEncuentra = false;
        try {
            BufferedReader lector = new BufferedReader(new FileReader("src\\main\\java\\Archivos\\Usuarios.txt"));
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] lista = linea.split("_");

                if (lista[3].equals("E")) {
                    if (usuario.equals(lista[6]) && contrasena.equals(lista[7])) {
                        seEncuentra = true;
                        return seEncuentra;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error de archivo");
        }
        return seEncuentra;
    }

    /**
     * Muestra los códigos de los artículos asignados al editor especificado por su nombre de usuario.
     *
     * @param usuario el nombre de usuario del editor
     * @return una lista de códigos de artículos asignados al editor
     */
    public static ArrayList<String> mostrarCodArticulos(String usuario) {
        ArrayList<String> codigos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src\\main\\java\\Archivos\\Revisiones.txt"))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] lista = linea.split("_");
                if (lista[0].equals(usuario)) {
                    System.out.println(lista[4]);
                    codigos.add(lista[4]);
                }
            }
        } catch (Exception e) {
            // Manejar excepción
        }
        return codigos;
    }

    /**
     * Toma una decisión final sobre un artículo especificado por su código.
     * Muestra las revisiones del artículo, permite al editor ingresar una decisión (ACEPTADO o RECHAZADO),
     * y notifica al autor del artículo sobre la decisión.
     *
     * @param codigoArticulo el código del artículo
     * @param usuario        el nombre de usuario del editor
     */
    public static void decisionFinal(int codigoArticulo, String usuario) {
        ArrayList<String> lineas = new ArrayList<>();
        Decision decision;
        int posicion = 0;
        String lin = "";
        try (BufferedReader br = new BufferedReader(new FileReader("src\\main\\java\\Archivos\\Revisiones.txt"))) {
            String linea;
            Scanner sc = new Scanner(System.in);
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
                String[] lista = linea.split("_");
                if (codigoArticulo == Integer.parseInt(lista[4])) {
                    posicion = lineas.indexOf(linea);
                    try (BufferedReader br1 = new BufferedReader(new FileReader("src\\main\\java\\Archivos\\RevicionesP.txt"))) {
                        String linea1;
                        while ((linea1 = br1.readLine()) != null) {
                            String[] lista1 = linea1.split("_");
                            if (Integer.parseInt(lista1[3]) == codigoArticulo) {
                                System.out.println(lista1[0] + " " + lista1[1] + " " + lista1[2]);
                            }
                        }
                    } catch (IOException e) {
                        // Manejar excepción
                    }
                    System.out.println("Aprueba el articulo ACEPTADO/RECHAZADO");
                    String deci = sc.nextLine().toUpperCase();
                    boolean incorrecto1 = deci.equals(Decision.RECHAZADO.toString());
                    boolean incorrecto2 = deci.equals(Decision.ACEPTADO.toString());
                    while (!incorrecto1 && !incorrecto2) {
                        System.out.println("Decision no precisa");
                        System.out.println("Ingrese su decision(ACEPTADO/RECHAZADO):");
                        deci = sc.nextLine().toUpperCase();
                        incorrecto1 = deci.equals(Decision.RECHAZADO.toString());
                        incorrecto2 = deci.equals(Decision.ACEPTADO.toString());
                    }
                    decision = Decision.valueOf(deci);
                    int codAutor = 0;
                    try (BufferedReader br1 = new BufferedReader(new FileReader("src\\main\\java\\Archivos\\Articulos.txt"))) {
                        while ((linea = br1.readLine()) != null) {
                            String[] lista1 = linea.split("-");
                            if (codigoArticulo == Integer.parseInt(lista1[1])) {
                                codAutor = Integer.parseInt(lista1[0]);
                            }
                        }
                    } catch (IOException e) {
                        // Manejar excepción
                    }
                    Autor autor = new Autor(null, null, null, null, codAutor, null, null, null);
                    try (BufferedReader br2 = new BufferedReader(new FileReader("src\\main\\java\\Archivos\\Investigadores.txt"))) {
                        while ((linea = br2.readLine()) != null) {
                            String[] lista2 = linea.split("_");
                            if (codAutor == Integer.parseInt(lista2[3])) {
                                autor.setApellido(lista2[1]);
                                autor.setNombre(lista2[0]);
                                autor.setCorreo(lista2[2]);
                                autor.setRol(Rol.valueOf(lista2[3]));
                                autor.setInstitucion(lista2[5]);
                                autor.setCampoDeInvestigacion(lista2[6]);
                            }
                        }
                    } catch (Exception e) {
                        // Manejar excepción
                    }
                    lin = lista[0] + "_" + lista[1] + "_" + lista[2] + "_" + lista[3] + "_" + lista[4] + "_" + decision.toString();
                    Revision.enviarCorreo(autor, "El editor " + decision + " su articulo", "Su articulo fue " + decision + " por el editor " + usuario + " y su articulo sera publicado en caso de haber sido aceptado.");
                }
            }
        } catch (IOException e) {
            // Manejar excepción
        }
        lineas.set(posicion, lin);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src\\main\\java\\Archivos\\Revisiones.txt", false))) {
            for (String linea : lineas) {
                bw.write(linea + "\n");
            }
        } catch (IOException e) {
            // Manejar excepción
        }
    }

    /**
     * Agrega un comentario a un artículo especificado por su código.
     *
     * @param codigoArticulo el código del artículo
     */
    public static void agregarComentario(int codigoArticulo) {
        ArrayList<String> lineas = new ArrayList<>();
        String comentario;
        int posicion = 0;
        String lin = "";
        try (BufferedReader br = new BufferedReader(new FileReader("src\\main\\java\\Archivos\\Revisiones.txt"))) {
            String linea;
            Scanner sc = new Scanner(System.in);
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
                String[] lista = linea.split("_");
                if (codigoArticulo == Integer.parseInt(lista[4])) {
                    posicion = lineas.indexOf(linea);
                    System.out.println("Ingrese su comentario:");
                    comentario = sc.nextLine();
                    lin = lista[0] + "_" + lista[1] + "_" + lista[2] + "_" + comentario + "_" + lista[4] + "_" + lista[5];
                }
            }
        } catch (IOException e) {
            // Manejar excepción
        }
        lineas.set(posicion, lin);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src\\main\\java\\Archivos\\Revisiones.txt", false))) {
            for (String linea : lineas) {
                bw.write(linea + "\n");
            }
        } catch (IOException e) {
            // Manejar excepción
        }
    }

    /**
     * Muestra los artículos asignados al editor y devuelve una lista de códigos de artículos.
     *
     * @return una lista de códigos de artículos asignados al editor
     */
    public ArrayList<String> mostraArticulosEditor() {
        ArrayList<String> codigos = new ArrayList<>();
        int contador = 1;
        try (BufferedReader br = new BufferedReader(new FileReader("src\\main\\java\\Archivos\\Revisiones.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] lista = linea.split("_");
                if (lista[0].equals(userName)) {
                    System.out.println(contador + lista[4]);
                    contador += 1;
                    codigos.add(lista[4]);
                }
            }
        } catch (IOException e) {
            // Manejar excepción
        }
        return codigos;
    }
}
