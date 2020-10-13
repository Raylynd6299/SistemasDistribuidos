class MultiplicacionMatriz_2 {
    static int N = 1000;
    static int[][] A = new int[N][N];
    static int[][] B = new int[N][N];
    static int[][] C = new int[N][N];
    static void MostrarCuartoMatriz(int[][] mat){
        for (int fila = 500; fila < 504 ; fila ++){
            for(int columna = 500; columna < 504 ; columna++){
                System.out.printf("%d ",mat[fila][columna]);
            }
            System.out.printf("\n");
        }
    }
    static long ChecksumMatriz(int[][] mat,int nm){
        long check = 0;
        for (int fila = 0; fila < nm ; fila ++){
            for(int columna = 0; columna < nm ; columna++){
                check += mat[fila][columna];
            }
        }
        return check;
    }
    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        //inicializamos las matrices A y B
        for (int i = 0; i < N; i++ ){
            for (int j = 0; j < N ; j++ ){
                A[i][j] = 2*i+j;
                B[j][i] = 2 *i - j;
                C[i][j] = 0;
            }
        }



        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                for (int k = 0; k < N; k++)
                    C[i][j] += A[i][k] * B[j][k];

        //MostrarMatriz(A,N);
        //MostrarMatriz(B,N);
        //MostrarMatriz(C,N);
        System.out.println(ChecksumMatriz(C,N));
        long t2 = System.currentTimeMillis();
        System.out.println("Tiempo: " + (t2 - t1) + "ms");
    }

}
