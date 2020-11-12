import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceRMI extends Remote{
    public int[][] multiplica_matrices(int[][] A,int[][] B) throws RemoteException;
    public void SetN(int num) throws RemoteException;
    public long checksum(int[][] m) throws RemoteException;
}