package users;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import mail.Mail;

/**
 * Representa una revisión de un artículo, asignando editores y revisores,
 * gestionando comentarios y notificaciones, y almacenando los datos de la revisión.
 */
public class Revision {
    private Editor editor;
    private ArrayList<Revisor> revisores;
    private Articulo articulo;
    private String comentario;
    private int artID;

    /**
     * Constructor para crear una revisión con los atributos especificados.
     *
     * @param editor     el editor asignado a la revisión
     * @param revisores  la lista de revisores asignados a la revisión
     * @param articulo   el artículo que se está revisando
     * @param comentario el comentario asociado a la revisión
     * @param artID      el ID del artículo
     */
    public Revision(Editor editor, ArrayList<Revisor> revisores, Articulo articulo, String comentario, int artID) {
        this.editor = editor;
        this.revisores = revisores;
        this.articulo = articulo;
        this.comentario = comentario;
        this.artID = artID;
    }

    // Getters
    public Editor getEditor() { return this.editor; }
    public ArrayList<Revisor> getRevisores() { return this.revisores; }
    public Articulo getArticulo() { return this.articulo; }
    public String getComentario() { return this.comentario; }
    public int getArtID() { return this.artID; }
    
    // Setters
    public void setEditor(Editor editor) { this.editor = editor; }
    public void setRevisores(ArrayList<Revisor> revisores) { this.revisores = revisores; }
    public void setArticulo(Articulo articulo) { this.articulo = articulo; }
    public void setComentario(String comentario) { this.comentario = comentario; }
    public void setArtID(int artID) { this.artID = artID; }

    Scanner sc = new Scanner(System.in);

    /**
     * Asigna un editor aleatorio a la revisión y notifica al editor asignado.
     *
     * @return el editor asignado
     */
    public Editor asignarEditor() {
        Random random = new Random();
        Editor editorElegido;
        ArrayList<Editor> editores = new ArrayList<Editor>();
        try (BufferedReader br = new BufferedReader(new FileReader("src\\main\\java\\Archivos\\Usuarios.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] lista = linea.split("_");
                if (Rol.valueOf(lista[3]) == Rol.E) {
                    Editor ed = new Editor(lista[0], lista[1], lista[2], Rol.valueOf(lista[3]), lista[4], lista[5], lista[6], lista[7]);
                    editores.add(ed);
                }
            }
        } catch (IOException e) {
            // Manejar excepción
        }
        int posicion = random.nextInt(editores.size());
        editorElegido = editores.get(posicion);
        this.editor = editorElegido;
        notificar(editorElegido);
        return this.editor;
    }

    /**
     * Asigna dos revisores aleatorios al artículo y los notifica.
     *
     * @param art el artículo al que se asignan los revisores
     */
    public void asignarRevisor(Articulo art) {
        Random random = new Random();
        Random random2 = new Random();
        ArrayList<Revisor> Listarevisores = new ArrayList<Revisor>();
        try (BufferedReader br = new BufferedReader(new FileReader("src\\main\\java\\Archivos\\Revisores.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] lista = linea.split("_");
                if (Rol.valueOf(lista[3]).equals(Rol.R)) {
                    Revisor rev = new Revisor(lista[0], lista[1], lista[2], Rol.valueOf(lista[3]), lista[4], Integer.parseInt(lista[5]), lista[6], lista[7]);
                    Listarevisores.add(rev);
                }
            }            
        } catch (IOException e) {
            // Manejar excepción
        }
        int posicion1 = random.nextInt(Listarevisores.size());
        int posicion2 = random2.nextInt(Listarevisores.size());
        while (posicion1 == posicion2) {
            posicion2 = random2.nextInt(Listarevisores.size());
        }

        ArrayList<Revisor> rev = new ArrayList<Revisor>();

        Revisor r1 = Listarevisores.get(posicion1);
        Revisor r2 = Listarevisores.get(posicion2);
        rev.add(r1);
        rev.add(r2);
        notificar(r1);
        notificar(r2);

        try {
            Adicionar1("src\\main\\java\\Archivos\\Revisores.txt", r1);
            Adicionar1("src\\main\\java\\Archivos\\Revisores.txt", r2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.revisores = rev;

        String contenido = "\n" + r1.userName + "_N/A N/A_" + art.getCodigo();

        try (FileWriter escritor = new FileWriter("src\\main\\java\\Archivos\\RevicionesP.txt", true)) {
            escritor.write(contenido);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    /**
     * Guarda los datos de la revisión en el archivo "Revisiones.txt".
     */
    public void guardarRevision() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src\\main\\java\\Archivos\\Revisiones.txt", true))) {
            bw.write(toString() + "\n");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    /**
     * Agrega un comentario a la revisión.
     */
    public void agregarComentario() {
        System.out.println("Ingrese su comentario: ");
        String comentario = sc.nextLine();
        this.comentario = comentario;
    }

    /**
     * Notifica al autor del artículo sobre el estado de la revisión.
     */
    public void notificar() {
        Mail.inicializarSistemaCorreo();
        Mail.sendMail(this.articulo.getAutor().getCorreo(), "Su articulo entro a revision", "Se le informa que su articulo"
            + " sera revisado por " + this.revisores.get(0).getNombre() + " " + this.revisores.get(0).getApellido() + " y por "
            + this.revisores.get(1).getNombre() + " " + this.revisores.get(1).getApellido() + " y se le asigno al editor "
            + this.editor.getNombre() + " " + this.editor.getApellido() + ". Se le informara sobre su proceso conforme el mismo avance.");
    }

    /**
     * Notifica a un usuario específico sobre su asignación en la revisión.
     *
     * @param user el usuario a notificar
     */
    public void notificar(Usuario user) {
        Mail.inicializarSistemaCorreo();
        if (user instanceof Editor) {
            Editor editor = (Editor) user;
            enviarCorreo(editor, "Se le asigno un articulo como editor", "Estimado " + editor.getNombre() + " "
                + editor.getApellido() + " se le envio este articulo para que de su aprobacion luego de que fue revisado por los revisores");
        } else if (user instanceof Revisor) {
            Revisor revisor = (Revisor) user;
            enviarCorreo(revisor, "Se le asgino una articulo para revision", "Estimado " + revisor.getNombre() + " " + revisor.getApellido()
                + " se le asigno un articulo adjunto a este correo para su revision y se solicita que lo visualice y emita su juicio y comentarios"
                + " con respectos al articulo");
        }
    }

    /**
     * Envía un correo electrónico a un usuario.
     *
     * @param user   el usuario que recibirá el correo
     * @param asunto el asunto del correo
     * @param cuerpo el cuerpo del correo
     */
    public static void enviarCorreo(Usuario user, String asunto, String cuerpo) {
        Mail.inicializarSistemaCorreo();
        Mail.sendMail(user.correo, asunto, cuerpo);
    }

    @Override
    public String toString() {
        return editor.getUserName() + "_[" + revisores.get(0).getUserName() + "," + revisores.get(1).getUserName() + "]_" + articulo.getDatos() + "_" + comentario + "_" + artID + "_" + "null";
    }

    /**
     * Lee las líneas de un archivo y las devuelve como una lista de cadenas.
     *
     * @param rutaArchivo la ruta del archivo a leer
     * @return una lista de cadenas con las líneas del archivo
     * @throws IOException si ocurre un error al leer el archivo
     */
    public static ArrayList<String> leerArchivo(String rutaArchivo) throws IOException {
        ArrayList<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        }
        return lineas;
    }

    /**
     * Escribe las líneas en un archivo.
     *
     * @param rutaArchivo la ruta del archivo a escribir
     * @param lineas      la lista de líneas a escribir
     * @throws IOException si ocurre un error al escribir en el archivo
     */
    public static void escribirArchivo(String rutaArchivo, ArrayList<String> lineas) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (String linea : lineas) {
                bw.write(linea);
                bw.newLine();
            }
        }
    }

    /**
     * Modifica el archivo "Revisores.txt" para actualizar el número de artículos revisados por un revisor.
     *
     * @param rutaArchivo la ruta del archivo a modificar
     * @param r1          el revisor cuyo número de artículos revisados se actualizará
     * @throws IOException si ocurre un error al escribir en el archivo
     */
    public static void Adicionar1(String rutaArchivo, Revisor r1) throws IOException {
        ArrayList<String> lineas = leerArchivo(rutaArchivo);
        for (int i = 0; i < lineas.size(); i++) {
            String linea = lineas.get(i);
            String[] sep = linea.split("_");
            if (r1.userName.equals(sep[6])) {
                int num = Integer.parseInt(sep[5]);
                num += 1;
                sep[5] = String.valueOf(num);
                lineas.set(i, String.join("_", sep));
            }
        }
        escribirArchivo(rutaArchivo, lineas);
    }
}
