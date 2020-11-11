import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.io.IOException;
import java.nio.ByteBuffer;


class Chat {
    static byte[] recibe_mensaje(MulticastSocket socket,int longitud) throws IOException{

        byte[] buffer = new byte[longitud];
        DatagramPacket paquete = new DatagramPacket(buffer,buffer.length);
        socket.receive(paquete);
        return buffer;
      }

    static void envia_mensaje(byte[] buffer,String ip,int puerto) throws IOException{
        
        DatagramSocket socket = new DatagramSocket();
        InetAddress grupo = InetAddress.getByName(ip);
        DatagramPacket paquete = new DatagramPacket(buffer,buffer.length,grupo,puerto);
        socket.send(paquete);
        socket.close();
    }

    static class Worker extends Thread {

        public void run() {
            InetAddress grupo ;
            MulticastSocket socket = null;              
            try {
                grupo = InetAddress.getByName("230.0.0.0");
                socket = new MulticastSocket(50000);
                socket.joinGroup(grupo);
            } catch (Exception e) {
                System.out.println("Error creando socket Cliente");
            }
            while(true){       
                try {
                    //El tamañp del proximo mensaje
                    byte[] buffer = recibe_mensaje(socket,1*4);
                    ByteBuffer b = ByteBuffer.wrap(buffer);


                    byte[] a = recibe_mensaje(socket,b.getInt());
                    System.out.println(new String(a,"UTF-8"));
                } catch (Exception e) {
                    System.out.println("Error reciviendo Datos");
                }
            }
            
            
            // En un ciclo infinito se recibirán los mensajes enviados al grupo 
            // 230.0.0.0 a través del puerto 50000 y se desplegarán en la pantalla.
        }
    }
    public static void main(String[] args) throws Exception {

        Worker w = new Worker();
        w.start();
        String nombre = args[0];
        String Mensaje = new String();
        String Completo;
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        ByteBuffer num = ByteBuffer.allocate(1*4);
        
        while(true){
            System.out.println("Escribe el Mensaje("+nombre+"):");
            Mensaje = b.readLine();
            Completo = nombre+":"+Mensaje;

            //enviamos el tamaño del mensaje
            num = ByteBuffer.allocate(1*4);
            num.putInt(Completo.getBytes().length);
            envia_mensaje(num.array(),"230.0.0.0",50000);

            envia_mensaje(Completo.getBytes(),"230.0.0.0",50000);
        }
        // En un ciclo infinito se leerá los mensajes del teclado y se enviarán
        // al grupo 230.0.0.0 a través del puerto 50000.
    }
}