import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Servidor {
    static void read(DataInputStream f,byte[] b,int posicion,int longitud) throws Exception{
        while (longitud > 0){
            int n = f.read(b,posicion,longitud);
            posicion += n;
            longitud -= n;
        }
    }
    public static void main(String[] args) throws Exception{

        long Tini;
        Tini = System.currentTimeMillis();

        ServerSocket servidor = new ServerSocket(50000);

        Socket conexion = servidor.accept();

        DataOutputStream salida = new DataOutputStream(conexion.getOutputStream());

        DataInputStream entrada = new DataInputStream(conexion.getInputStream());

        int n = entrada.readInt();
        System.out.println(n);

        double x = entrada.readDouble();
        System.out.println(x);

        byte[] buffer = new byte[4];
        read(entrada,buffer,0,4);
        System.out.println(new String(buffer,"UTF-8"));

        salida.write("HOLA".getBytes());

        byte[] a = new byte[10000*8];

        read(entrada,a,0,10000*8);

        ByteBuffer b = ByteBuffer.wrap(a);

        for(int i = 0; i < 10000; i++) System.out.println(b.getDouble());

        

        salida.close();
        entrada.close();
        conexion.close();
        
        System.out.println(System.currentTimeMillis()-Tini);
    }
}