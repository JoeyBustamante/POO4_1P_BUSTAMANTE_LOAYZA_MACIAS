package users;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Revisor extends Usuario{
    protected String especialidad;
    protected int articuloRevisado;
    protected String userName;
    protected String contrasena;

    public Revisor(String nombre, String apellido, String correo,Rol rol, String especialidad, int articuloRevisado, String userName, String contrasena){
        super(nombre,apellido,correo,rol);
        this.especialidad=especialidad;
        this.articuloRevisado=articuloRevisado;
        this.userName=userName;
        this.contrasena=contrasena;
    }


    //Getters
    public String getEspecialidad(){return this.especialidad;}
    public int getArticuloRevisado(){return this.articuloRevisado;}
    public String getUserName(){return this.userName;}
    public String getContrasena(){return this.contrasena;}

    //Setters
    public void setEspecialidad(String especialidad){this.especialidad=especialidad;}
    public void setArticuloRevisado(int articuloRevisado){this.articuloRevisado=articuloRevisado;}
    public void setUserName(String userName){this.userName=userName;}
    public void setContrasena(String contrasena){this.contrasena=contrasena;}

    //InicioSesion
    public static boolean InicioSesion(String usuario, String contrasena){
        boolean seEncuentra=false;
        try {
            BufferedReader lector=new BufferedReader(new FileReader("src\\main\\java\\Archivos\\Usuarios.txt"));
            String linea;
            while ((linea=lector.readLine())!=null) {
                String[] lista=linea.split("_");

                if( lista[3].equals("R")){
                    if (usuario.equals(lista[6]) && contrasena.equals(lista[7])){
                        seEncuentra= true;
                        return seEncuentra;
                        
                    }

                }
                
                
            }
            
        } catch (IOException e) {
            System.out.println("Error de archivo");
        }
        return seEncuentra;
    }        


    //Agregar comentario Articulo
    public void AgergarComentario(){
        ArrayList<String> listaLeida = new ArrayList<>();
        try (BufferedReader br=new BufferedReader(new FileReader("src\\main\\java\\Archivos\\Revisiones.txt"))){
            
        } catch (IOException e) {
            // TODO: handle exception
        }
        for(String linea:listaLeida){
            String[] lista=linea.split("_");
            
        }
        System.out.println("Ingerese el comentario: ");
        
        try {
            BufferedWriter bw= new BufferedWriter(new FileWriter("src\\main\\java\\Archivos\\Revisiones.txt",true));
            

        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }

    public static void estadoReviciones(String usuario){
        String comentariop= "";

        ArrayList<String> opciones = new ArrayList<>() ;
        try {
            BufferedReader lector=new BufferedReader(new FileReader("src\\main\\java\\Archivos\\RevicionesP.txt"));
            String linea;
            int opcion =0;
            boolean encontrado = false;
            while ((linea=lector.readLine())!=null) {
                String[] lista=linea.split("_");
                String dato= lista[0];
                if(dato.equals(usuario)){
                    if(!encontrado){
                        System.out.println("Tiene Articulos pendientes:");
                        encontrado=true;
                    }else{
                        opcion+=1;
                        opciones.add(lista[3]);
                        System.out.println(opcion+ ": "+lista[3] );
                        
                    }
                    
                    //for(int i=0;lector.readLine()!=null;i++){
                        
                   // }
                    
                }else{
                    //System.out.println("No se le an asignado nada aun");
                }
                

                
            }
            
            
        } catch (IOException e) {
            System.out.println("Error de archivo");
        }
        System.out.println("Seleccione el articulo que desea revisar");
        Scanner sc = new Scanner(System.in);
        int opcion2 = sc.nextInt();
        String codigo = opciones.get(opcion2-1);
        int opcion3=0;
        ArrayList<String> lineas=new ArrayList<String>();
        try {
            BufferedReader lector=new BufferedReader(new FileReader("src\\main\\java\\Archivos\\RevicionesP.txt"));
            String linea;
            while ((linea=lector.readLine())!=null) {
                String[] lista=linea.split("_");   
                lineas.add(linea);
                if(lista[3].equals(codigo)){
                    System.out.println("Comentarios: "+lista[1]);
                    System.out.println("Decision de aceptar o rechazar: "+lista[2]);
                    System.out.println(" ");
                    System.out.println("Presione 1 para comentar o 2 para tomar una decicion ");
                    opcion3=sc.nextInt();

                }  
            }
        } catch (IOException e) {
            System.out.println("Error de archivo");
        }
        while (opcion3!= 1 && opcion3!=2) {
            System.out.println("Error al seleccionar la opcion!");
            System.out.println("Presione 1 para comentar o 2 para tomar una decicion ");
            opcion3=sc.nextInt();
            
        }
        int posicion=0;
        String cambio="";
        if(opcion3==1){
            System.out.println("Ingrese el comentario: ");
            sc.nextLine();
            String comentario=sc.nextLine();
            comentariop=comentario;
            for(String linea: lineas){
                String[] lista= linea.split("_");
                if(lista[3].equals(codigo)){
                    posicion=lineas.indexOf(linea);
                    lista[1]=comentario;
                    cambio=lista[0]+"_"+lista[1]+"_"+lista[2]+"_"+lista[3];
                }
                
            }
        }
        else if(opcion3==2){
            System.out.println("Ingrese su decision(ACEPTADO/RECHAZADO):");
            sc.nextLine();
            String decision=sc.nextLine().toUpperCase();
            boolean incorrecto1=decision.equals(Decision.RECHAZADO.toString());
            boolean incorrecto2=decision.equals(Decision.ACEPTADO.toString());
            while(!incorrecto1 && !incorrecto2){
                System.out.println("Decision no precisa");
                System.out.println("Ingrese su decision(ACEPTADO/RECHAZADO):");
                decision=sc.nextLine().toUpperCase();
                incorrecto1=decision.equals(Decision.RECHAZADO.toString());
                incorrecto2=decision.equals(Decision.ACEPTADO.toString());
            }
            for(String linea:lineas){
                String[] lista=linea.split("_");
                if(lista[3].equals(codigo)){
                    lista[2]=Decision.valueOf(decision).toString();
                    posicion=lineas.indexOf(linea);
                    cambio=lista[0]+"_"+lista[1]+"_"+lista[2]+"_"+lista[3];
                    System.out.println(cambio);
                }
            }
            // --------------------- AQUI -------------------------------//
            String nombre=null;
            String apellido=null;
            try (BufferedReader br1=new BufferedReader(new FileReader("src\\main\\java\\Archivos\\Revisores.txt"))){
                String linea;
                while ((linea=br1.readLine())!=null) {
                    String[] lista=linea.split("_");
                    if(lista[6].equals(usuario)){
                        nombre=lista[0];
                        apellido=lista[1];
                    }

                    
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
            int codAutor=0;
            try (BufferedReader br1=new BufferedReader(new FileReader("src\\main\\java\\Archivos\\Articulos.txt"));){
                String linea;
                while((linea=br1.readLine())!=null){
                    String[] lista1=linea.split("-");
                    if(Integer.parseInt(codigo)==Integer.parseInt(lista1[1])){
                        codAutor=Integer.parseInt(lista1[0]);
                    }
                }
                
            } catch (IOException e) {
                // TODO: handle exception
            }
            Autor autor=new Autor(null, null, null, null, codAutor,null, null, null);
            try (BufferedReader br2=new BufferedReader(new FileReader("src\\main\\java\\Archivos\\Investigadores.txt"))){
                String linea;
                while ((linea=br2.readLine())!=null) {
                    String[] lista2=linea.split("_");
                    if(codAutor==Integer.parseInt(lista2[3])){
                        autor.setApellido(lista2[1]);
                        autor.setNombre(lista2[0]);
                        autor.setCorreo(lista2[2]);
                        autor.setRol(Rol.valueOf(lista2[3]));
                        autor.setInstitucion(lista2[5]);
                        autor.setCampoDeInvestigacion(lista2[6]);


                    }
                }
            Revision.enviarCorreo(autor , "Un revisor"+ decision +"su articulo",
            "Su articulo fue"+decision+" por el revisor "+nombre+" "+apellido+" y a emitido el siguiente comentario: "+ '"'+ comentariop +'"');
        }catch(IOException e){

        }
        }
        lineas.set(posicion, cambio);
        try (BufferedWriter bw=new BufferedWriter(new FileWriter("src\\main\\java\\Archivos\\RevicionesP.txt",false))){
            for(String linea: lineas){
                bw.write(linea+"\n");
            }
            
        } catch (IOException e) {
            // TODO: handle exception
        }
        
        
    }

    @Override
    public String toString(){
        return super.toString()+"_"+ especialidad+" "+articuloRevisado+" "+ userName+" "+contrasena;
    }
}
