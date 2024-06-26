import mail.Mail;
public class Main {
    Sistema sistema=new Sistema();
    public static void main(String[] args) {
       /* 
        Usuario autor=new Autor("Francis", "Loayza", "loayzafrancis@gmail.com", Rol.A, null, 0, null, null, null);
        Usuario editor=new Editor("Joey", "Bustamante", "ejemplo", Rol.E, null, null, "Dios", "1234");
        Sistema.AgergarListaUsuario(autor);
        System.out.println(Sistema.listaUsuario.toString());
        Sistema.registrarDatoUsuario();

        System.out.println("Ingrese su Usuario:");
        Scanner sc=new Scanner(System.in);
        String usuario=sc.nextLine();
        System.out.println("Ingrese su Contrasena:");
        String contra=sc.nextLine();
        */

        Mail.inicializarSistemaCorreo();
        Mail.sendMail("chrismacmen9192@gmail.com", "prueba", "fuciona?");

    }
}
