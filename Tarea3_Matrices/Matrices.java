import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Matrices {
    static Object lock = new Object();
    static int N = 1000;
    static int[][] A = new int[N][N];
    static int[][] B = new int[N][N];
    static int[][] C = new int[N][N];

    static void read(DataInputStream f,byte[] b,int posicion,int longitud) throws Exception{
        while (longitud > 0){
        int n = f.read(b,posicion,longitud);
        posicion += n;
        longitud -= n;
        }
    }
    static class Worker extends Thread {
        Socket conexion;
        Worker(Socket canal){
            this.conexion = canal;
        }
        public void run () {
           try {
               DataOutputStream salida = new DataOutputStream(conexion.getOutputStream());
               DataInputStream entrada = new DataInputStream(conexion.getInputStream());
               
               int nodoConec = 0;
               ByteBuffer row ;
               byte[] rowC ;
               
               nodoConec = entrada.readInt();
               salida.writeInt(N);

               if (nodoConec == 1) {
                   //A1
                   for (int fila = 0;fila < N/2; fila++){
                        row = ByteBuffer.allocate(N*8);
                        for (int columna = 0; columna < N ; columna++){
                            row.putInt(A[fila][columna]);
                        }
                        rowC = row.array();
                        salida.write(rowC);
                   }

                   //B1
                    for (int fila = 0;fila < N/2; fila++){
                        row = ByteBuffer.allocate(N*8);
                        for (int columna = 0; columna < N ; columna++){
                            row.putInt(B[fila][columna]);
                        }
                        rowC = row.array();
                        salida.write(rowC);
                    }

                    //Recibimos C1
                    
                    for (int fila = 0; fila < N/2 ; fila++){
                        rowC = new byte[8*(N/2)];
                        read(entrada,rowC,0,(N/2)*8);
                        row = ByteBuffer.wrap(rowC);
                        for(int columna = 0; columna < N/2 ; columna++){
                            synchronized(lock) {
                                C[fila][columna] = row.getInt();
                            } 
                        }
                    }

               }else if (nodoConec == 2) {
                   //A1
                    for (int fila = 0;fila < N/2; fila++){
                        row = ByteBuffer.allocate(N*8);
                        for (int columna = 0; columna < N ; columna++){
                            row.putInt(A[fila][columna]);
                        }
                        rowC = row.array();
                        salida.write(rowC);
                    }

                    //B2
                    for (int fila = N/2;fila < N; fila++){
                        row = ByteBuffer.allocate(N*8);
                        for (int columna = 0; columna < N ; columna++){
                            row.putInt(B[fila][columna]);
                        }
                        rowC = row.array();
                        salida.write(rowC);
                    }

                    //Recibimos C2
                    
                    for (int fila = 0; fila < N/2 ; fila++){
                        rowC = new byte[8*(N/2)];
                        read(entrada,rowC,0,(N/2)*8);
                        row = ByteBuffer.wrap(rowC);
                        for(int columna = N/2; columna < N ; columna++){
                            synchronized(lock) {
                                C[fila][columna] = row.getInt();
                            } 
                        }
                    }

               }else if (nodoConec == 3) {
                    //A2
                    for (int fila = N/2;fila < N; fila++){
                        row = ByteBuffer.allocate(N*8);
                        for (int columna = 0; columna < N ; columna++){
                            row.putInt(A[fila][columna]);
                        }
                        rowC = row.array();
                        salida.write(rowC);
                    }

                    //B1
                    for (int fila = 0;fila < N/2; fila++){
                        row = ByteBuffer.allocate(N*8);
                        for (int columna = 0; columna < N ; columna++){
                            row.putInt(B[fila][columna]);
                        }
                        rowC = row.array();
                        salida.write(rowC);
                    }
                    
                    //Recibimos C3
                    
                    for (int fila = N/2; fila < N ; fila++){
                        rowC = new byte[8*(N/2)];
                        read(entrada,rowC,0,(N/2)*8);
                        row = ByteBuffer.wrap(rowC);
                        for(int columna = 0; columna < N/2 ; columna++){
                            synchronized(lock) {
                                C[fila][columna] = row.getInt();
                            } 
                        }
                    }

               }else if (nodoConec == 4) {
                    //A2
                    for (int fila = N/2;fila < N; fila++){
                        row = ByteBuffer.allocate(N*8);
                        for (int columna = 0; columna < N ; columna++){
                            row.putInt(A[fila][columna]);
                        }
                        rowC = row.array();
                        salida.write(rowC);
                    }

                    //B2
                    for (int fila = N/2;fila < N; fila++){
                        row = ByteBuffer.allocate(N*8);
                        for (int columna = 0; columna < N ; columna++){
                            row.putInt(B[fila][columna]);
                        }
                        rowC = row.array();
                        salida.write(rowC);
                    }

                    //Recibimos C4
                    
                    for (int fila = N/2; fila < N ; fila++){
                        rowC = new byte[8*(N/2)];
                        read(entrada,rowC,0,(N/2)*8);
                        row = ByteBuffer.wrap(rowC);
                        for(int columna = N/2; columna < N ; columna++){
                            synchronized(lock) {
                                C[fila][columna] = row.getInt();
                            }                            
                        }
                    }
               }

               salida.close();
               entrada.close();
               conexion.close();


           } catch (Exception e) {
               System.out.println("Ocurrio un error: "+e.getMessage());
           }
        }
    }
    static void MostrarMatriz(int[][] mat,int nm){
        for (int fila = 0; fila < nm ; fila ++){
            for(int columna = 0; columna < nm ; columna++){
                System.out.printf("%d ",mat[fila][columna]);
            }
            System.out.printf("\n");
        }
    }

    static int ChecksumMatriz(int[][] mat,int nm){
        int check = 0;
        for (int fila = 0; fila < nm ; fila ++){
            for(int columna = 0; columna < nm ; columna++){
                check += mat[fila][columna];
            }
        }
        return check;
    }

    public static void main(String[] args) throws Exception{
        if(args.length != 1){
            System.err.println(("Uso:"));
            System.err.println("Java Matrices <nodo>");
            System.exit(0);
        }
        int nodo = Integer.valueOf(args[0]);
        
        if (nodo == 0){ 
            //Lenando Matrices
            for (int i = 0; i < N; i++ ){
                for (int j = 0; j < N ; j++ ){
                    A[i][j] = 2*i+j;
                    B[j][i] = 2 *i - j;
                    C[i][j] = 0;
                }
            } 
            
            //Trasponer B

            ServerSocket servidor = new ServerSocket(50000);
            Worker w[] = new Worker[4];

            int i = 0;
            while(i < 4){
                Socket conexion = servidor.accept();
                w[i] = new Worker(conexion);
                w[i].start();
                i++;
            }

            
            while(i < 4){
                w[i].join();
                i++;
            }
            
            if(N == 4){
                System.out.printf(("Matriz A: \n"));
                MostrarMatriz(A,N);
                System.out.printf(("Matriz B: \n"));
                MostrarMatriz(B,N);
                System.out.printf(("Matriz C: \n"));
                MostrarMatriz(C,N);
            }
            System.out.println("El checksum de la matriz C es: "+String.valueOf(ChecksumMatriz(C,N)));

        }else {
            Socket conexion = null;
            for(;;)
                try {
                    conexion = new Socket("localhost",50000);
                    break;                    
                } catch (Exception e) {
                    Thread.sleep(100);
                }
            
            DataOutputStream salida = new DataOutputStream(conexion.getOutputStream());
            DataInputStream entrada = new DataInputStream(conexion.getInputStream());

            ByteBuffer row ;
            byte[] rowC ;
            int[][] An;
            int[][] Bn;
            int[][] Cn;//
            //Envio el nodo en el que estoy
            salida.writeInt(nodo);
            int numCol = entrada.readInt();
            
            An = new int[numCol/2][numCol];
            Bn = new int[numCol/2][numCol];//
            Cn = new int[numCol/2][numCol/2];//
            //Recibo mi parte de A
            for (int fila = 0; fila < numCol/2 ; fila++){
                rowC = new byte[8*N];
                read(entrada,rowC,0,N*8);
                row = ByteBuffer.wrap(rowC);
                for(int columna = 0; columna < N ; columna++){
                    
                    An[fila][columna] = row.getInt();
                
                }
            }


            //Recibo mi parte de B
            for (int fila = 0; fila < numCol/2 ; fila++){
                rowC = new byte[8*N];
                read(entrada,rowC,0,N*8);
                row = ByteBuffer.wrap(rowC);
                for(int columna = 0; columna < N ; columna++){
                    Bn[fila][columna] = row.getInt();
                }
            }

            //Obtengo mi parte de C
            for (int i = 0; i < numCol/2; i++)
                for (int j = 0; j < numCol/2; j++)
                    for (int k = 0; k < numCol; k++)
                        C[i][j] += A[i][k] * B[j][k];

            //Envio mi parte de C
            for (int fila = 0;fila < numCol/2; fila++){
                row = ByteBuffer.allocate((numCol/2)*8);
                for (int columna = 0; columna < numCol/2 ; columna++){
                    row.putInt(B[fila][columna]);
                }
                rowC = row.array();
                salida.write(rowC);
            }

            salida.close();
            entrada.close();
            conexion.close();
        }
    }

}