import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Cliente {
    static void read(DataInputStream f,byte[] b,int posicion,int longitud) throws Exception{
        while (longitud > 0){
            int n = f.read(b,posicion,longitud);
            posicion += n;
            longitud -= n;
        }
    }
    public static void main(String[] args) throws Exception {
        try {

            long Tini;
            Tini = System.currentTimeMillis();
            Socket conexion = new Socket("localhost",50000);

            //Stream de salida de datos
            DataOutputStream salida = new DataOutputStream(conexion.getOutputStream());

            //Buffer Stream de Lectura de Datos
            DataInputStream entrada = new DataInputStream(conexion.getInputStream());
            
            salida.writeInt(123);

            salida.writeDouble(1234567890.1234567890);

            salida.write("hola".getBytes()); // el metodo write envia bytes, por lo que para enviar la cadena debemos convertirla a bytes

            byte[] buffer = new byte[4];
            read(entrada,buffer,0,4);
            System.out.println(new String(buffer,"UTF-8"));

            ByteBuffer b = ByteBuffer.allocate(10000*8);
            
            Double nu = 1.0;
            for (int l= 0; l < 10000 ;l++){
                b.putDouble(nu);
                nu += 1;
            }
            
            byte[] a = b.array();

            salida.write(a);

            
            salida.close();
            entrada.close();
            conexion.close();
        
            System.out.println(System.currentTimeMillis() -Tini);
            
            
        } catch (Exception e) {
            System.out.println("Error al crear el Socket, compruebe el estado del servidor");
        }

    }
}