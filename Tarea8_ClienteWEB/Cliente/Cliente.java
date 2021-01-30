import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
import java.io.IOException ;
import java.net.URL;
import java.net.URLEncoder;
import java.net.HttpURLConnection;
import java.io.OutputStream;


public class Cliente{
    public static void Peticion_alta(String url){
        //Proceso para realizar la alta al servicio web
        URL url_endpoint;
        HttpURLConnection conexionServicio =null;
        OutputStream salida;
        String email,nombre,apellidoPa,apellidoMa,fechaNa,tel,gen,payload="";
        Gson content = null;
        Usuario user = null;
        BufferedReader lector=null;
        try{
            url_endpoint = new URL(url);
            conexionServicio = (HttpURLConnection) url_endpoint.openConnection();
            conexionServicio.setDoOutput ( true );
            conexionServicio.setRequestMethod( "POST" );
            conexionServicio.setRequestProperty( "Content-Type" , "application/x-www-form-urlencoded" );
            lector = new BufferedReader(new InputStreamReader(System.in));
        }catch(Exception e){
            System.out.println("Ocurrio un Error al crear la conexion: "+e.getMessage());
            System.exit(1);
        }
        try{
            System.out.println("Ingresa el nombre: ");
            nombre = URLEncoder.encode(lector.readLine(),"UTF-8");
            System.out.println("Ingresa el apellido paterno: ");
            apellidoPa = URLEncoder.encode(lector.readLine(),"UTF-8");
            System.out.println("Ingresa el apellido materno: ");
            apellidoMa = URLEncoder.encode(lector.readLine(),"UTF-8");
            System.out.println("Ingresa el email: ");
            email = URLEncoder.encode(lector.readLine(),"UTF-8");
            System.out.println("Ingresa tu fecha de nacimiento (aaaa/mm/dd): ");
            fechaNa = URLEncoder.encode(lector.readLine(),"UTF-8");
            System.out.println("Ingresa el telefono: ");
            tel = URLEncoder.encode(lector.readLine(),"UTF-8");
            System.out.println("Ingresa el genero ( M / F ): ");
            gen = lector.readLine();
            
            user = new Usuario(email,nombre,apellidoPa,apellidoMa,fechaNa,tel,gen);
            
            content = new GsonBuilder().create();
            payload = "usuario="+content.toJson(user).replace("{",URLEncoder.encode("{", "UTF-8")).replace("}",URLEncoder.encode("}", "UTF-8")).replace("\"",URLEncoder.encode("\"","UTF-8"));
        }catch(Exception e){
            System.out.println("Error al crear el payload: "+e.getMessage());
            System.exit(1);
        } 
        try{
            salida = conexionServicio.getOutputStream();
            salida.write(payload.getBytes());
            salida.flush();
        }catch(Exception e){
            System.out.println("Ocurrio un error al enviar la peticion: "+e.getMessage());
            System.exit(1);
        }
        try{
            if (conexionServicio.getResponseCode() != HttpURLConnection.HTTP_OK){
                throw new RuntimeException("Codigo del Servidor HTTP: " + conexionServicio.getResponseCode());
            }else{
                System.out.println("OK\n");
                try {
                    conexionServicio.disconnect();
                }catch(Exception e){
                    System.out.println("Error al cerrar la conexion");
                }
            }
        }catch(Exception e){
            System.out.println("Ocurrio un error al recibir la respuesta");
        }
        
    }

    public static void Peticion_consulta(String url){
        URL url_endpoint;
        HttpURLConnection conexionServicio=null;
        OutputStream salida;
        String payload="",email,response;
        Gson content = null;
        BufferedReader lector=null;
        Usuario user = null;
        try{
            url_endpoint = new URL(url);
            conexionServicio = (HttpURLConnection) url_endpoint.openConnection();
            conexionServicio.setDoOutput ( true );
            conexionServicio.setRequestMethod( "POST" );
            conexionServicio.setRequestProperty( "Content-Type" , "application/x-www-form-urlencoded" );
            lector = new BufferedReader(new InputStreamReader(System.in));
            payload="";
        }catch(Exception e){
            System.out.println("Ocurrio un Error al crear la conexion: "+e.getMessage());
            System.exit(1);
        }
        try{
            System.out.println("Ingresa el email: ");
            email = URLEncoder.encode(lector.readLine(),"UTF-8");
            payload = "email="+email;
        }catch(Exception e){
            System.out.println("Error creando el payload: "+e.getMessage());
            System.exit(1);
        }
        try{
            salida = conexionServicio.getOutputStream();
            salida.write(payload.getBytes());
            salida.flush();
        }catch(Exception e){
            System.out.println("Error al realizar la peticion: "+e.getMessage());
            System.exit(1);
        }
        try{
            if (conexionServicio.getResponseCode() != HttpURLConnection.HTTP_OK){
                throw new RuntimeException("Codigo de error en el Servidor HTTP: \n\n" + conexionServicio.getResponseCode()+"\nUsuario no encontrado");
            }else{
                BufferedReader datos = new BufferedReader(new InputStreamReader(conexionServicio.getInputStream()));
                while((response=datos.readLine()) != null){
                    content = new Gson();
                    user = content.fromJson(response,Usuario.class);
                    System.out.println("Datos obtenidos: "+user.toString()+"\n\n");
                } 
            }
            conexionServicio.disconnect();
        }catch(Exception e){
            System.out.println("Error al recibr la informacion: \n"+e.getMessage());
        }
        
    }
    
    public static void Peticion_borrar(String url){
        URL url_endpoint;
        HttpURLConnection conexionServicio=null;
        OutputStream salida;
        String payload="",email;
        BufferedReader lector=null;
        try{
            url_endpoint = new URL(url);
            conexionServicio = (HttpURLConnection) url_endpoint.openConnection();
            conexionServicio.setDoOutput ( true );
            conexionServicio.setRequestMethod( "POST" );
            conexionServicio.setRequestProperty( "Content-Type" , "application/x-www-form-urlencoded" );
            lector = new BufferedReader(new InputStreamReader(System.in));
            payload="";
        }catch(Exception e){
            System.out.println("Ocurrio un Error al crear la conexion: "+e.getMessage());
            System.exit(1);
        }
        try{
            System.out.println("Ingresa el email: ");
            email = URLEncoder.encode(lector.readLine(),"UTF-8");
            payload = "email="+email;
        }catch(Exception e){
            System.out.println("Error creando el payload: "+e.getMessage());
            System.exit(1);
        }
        try{
            salida = conexionServicio.getOutputStream();
            salida.write(payload.getBytes());
            salida.flush();
        }catch(Exception e){
            System.out.println("Ocurrio un error al enviar la peticion: "+e.getMessage());
            System.exit(1);
        }
        try{
            if (conexionServicio.getResponseCode() != HttpURLConnection.HTTP_OK){
                throw new RuntimeException("Codigo de error del Servidor HTTP: " + conexionServicio.getResponseCode());
            }else{
                System.out.println("OK\n\n");
                try {
                    conexionServicio.disconnect();
                }catch(Exception e){
                    System.out.println("Error al cerrar la conexion");
                }
            }
        }catch(Exception e){
            System.out.println("Error al recibr la respuesta"+e.getMessage());
        }    
    }
    
    public static void Peticion_borrarAll(String url){
        URL url_endpoint;
        HttpURLConnection conexionServicio=null;
        OutputStream salida;
        String payload="";
        try{
            url_endpoint = new URL(url);
            conexionServicio = (HttpURLConnection) url_endpoint.openConnection();
            conexionServicio.setDoOutput ( true );
            conexionServicio.setRequestMethod( "POST" );
            conexionServicio.setRequestProperty( "Content-Type" , "application/x-www-form-urlencoded" );
            payload="";
        }catch(Exception e){
            System.out.println("Ocurrio un Error al crear la conexion: "+e.getMessage());
            System.exit(1);
        }
        payload = "borrar_usuarios=all";
        try{
            salida = conexionServicio.getOutputStream();
            salida.write(payload.getBytes());
            salida.flush();
        }catch(Exception e){
            System.out.println("Ocurrio un error al enviar la peticion: "+e.getMessage());
            System.exit(1);
        }
        try{
            if (conexionServicio.getResponseCode() != HttpURLConnection.HTTP_OK){
                throw new RuntimeException("Error en el Servdor\nCodigo del Servidor HTTP: " + conexionServicio.getResponseCode());
            }else{
                System.out.println("OK\n\n");
                try {
                    conexionServicio.disconnect();
                }catch(Exception e){
                    System.out.println("Error al cerrar la conexion: "+e.getMessage());
                }
            }
        }catch(Exception e){
            System.out.println("Error al recibir la respuesta");
        }
    }
    
    public static void main(String[] args){
        if(args.length < 1){
            System.out.println("java Cliente <IP>  | Es necesario");
            System.exit(1);
        }
        String ip = args[0];
        String url = "http://"+ip+":8080/Servicio/rest/ws/";
        boolean flag = true;
        while(flag){
            System.out.println("Cliente para consumir REST\nSeleccione ua Opcion:");
            System.out.println("a.Alta usuario\nb.Consulta usuario\nc.Borra usuario\nd.Borrar todos los usuarios\ne.Salir\n\nOpcion: ");
            String opcion;
            try{
                BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
                opcion = lector.readLine();
            }catch(IOException e){
                opcion = "e";
            }    
                
            if(!opcion.isEmpty()){
                switch(opcion.toLowerCase().charAt(0)){
                    case 'a':Peticion_alta(url+"alta");break;
                    case 'b':Peticion_consulta(url+"consulta");break;
                    case 'c':Peticion_borrar(url+"borra"); ;break;
                    case 'd':Peticion_borrarAll(url+"borrar_usuarios"); ;break;
                    case 'e':flag = false;break;
                    default:{System.out.flush();
                        System.out.println("Ingrese una opcion valida");                    
                    }
                }        
            }else{
                System.out.flush();
                System.out.println("Ingresar una opcion");
            }        
        }        
    }
}
