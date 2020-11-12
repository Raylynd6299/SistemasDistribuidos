import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClaseRMI extends UnicastRemoteObject implements InterfaceRMI{

    public int N;

    public ClaseRMI() throws RemoteException  {
        super( );
    }
    public void SetN(int num) throws RemoteException{
        this.N = num;
    }

    public int[][] multiplica_matrices(int[][] A,int[][] B) throws RemoteException{
        int[][] C = new int[N/2][N/2];

        for (int i = 0; i < N/2; i++)
            for (int j = 0; j < N/2; j++)
                for (int k = 0; k < N; k++)
                    C[i][j] += A[i][k] * B[j][k];
        return C;
    }

    
    public long checksum(int[][] m) throws RemoteException  {
        long s = 0;
        for (int i = 0; i < m.length; i++)
        for (int j = 0; j < m[0].length; j++)
            s += m[i][j];
        return s;
    }
}