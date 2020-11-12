import java.rmi.Naming;

public class ClienteRMI{ 
  static int N = 4;
  static int[][] A = new int[N][N];
  static int[][] B = new int[N][N];
  static int[][] C = new int[N][N];

  static void MostrarMatriz(int[][] mat){
    for (int fila = 0; fila < mat.length ; fila ++){
        for(int columna = 0; columna < mat[0].length ; columna++){
            System.out.printf("%d ",mat[fila][columna]);
        }
        System.out.printf("\n");
    }
  }
  static int[][] parte_matriz(int A[][],int inicio) throws Exception {
    int[][] M = new int[N/2][N];
    for (int i = 0; i < N/2; i++)
      for (int j = 0; j < N; j++)
        M[i][j] = A[i + inicio][j];
    return M;
  }
  static int[][] TranspuestaMatriz(int A[][]) throws Exception {
    int AT [][] =  new int[N][N];
    for (int fila = 0; fila < N ; fila++){
      for (int columna = 0;columna < N; columna++){
        AT[columna][fila] = A[fila][columna];
      }
    }
    return AT;
  }
  static void llenarMatrices() throws Exception {
    //Lenando Matrices
    for (int i = 0; i < N; i++ ){
      for (int j = 0; j < N ; j++ ){
          A[i][j] = 2*i-j;
          B[i][j] = 2 *i + j;
          C[i][j] = 0;
      }
    }
  }
  static void acomoda_matriz(int[][] C,int[][] A,int renglon,int columna){
    for (int i = 0; i < N/2; i++)
      for (int j = 0; j < N/2; j++)
        C[i + renglon][j + columna] = A[i][j];
  }
  public static void main(String args[]) throws Exception  {

    // en este caso el objeto remoto se llama "prueba", notar que se utiliza el puerto default 1099
    String url = "rmi://localhost/prueba";
    // obtiene una referencia que "apunta" al objeto remoto asociado a la URL
    InterfaceRMI r = (InterfaceRMI)Naming.lookup(url);

    llenarMatrices();
    MostrarMatriz(B);
    B = TranspuestaMatriz(B);
  
    int[][] A1 = parte_matriz(A,0);
    int[][] A2 = parte_matriz(A,N/2);
    int[][] B1 = parte_matriz(B,0);
    int[][] B2 = parte_matriz(B,N/2);
    r.SetN(N);
    int[][] C1 = r.multiplica_matrices(A1,B1);
    int[][] C2 = r.multiplica_matrices(A1,B2);
    int[][] C3 = r.multiplica_matrices(A2,B1);
    int[][] C4 = r.multiplica_matrices(A2,B2);

    acomoda_matriz(C,C1,0,0);
    acomoda_matriz(C,C2,0,N/2);
    acomoda_matriz(C,C3,N/2,0);
    acomoda_matriz(C,C4,N/2,N/2);

    if(N == 4){
      System.out.printf(("Matriz A: \n"));
      MostrarMatriz(A);
      System.out.printf(("Matriz B: \n"));
      MostrarMatriz(B);
      System.out.printf(("Matriz C: \n"));
      MostrarMatriz(C);
    }
    System.out.println("checksum=" + r.checksum(C));
  }
}
