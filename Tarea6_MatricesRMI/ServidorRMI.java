import java.rmi.Naming;


public class ServidorRMI{
    public static void main(String[] args) throws Exception{
      //0.0.0.0 nos da la ip de la interfaz
      String url = "rmi://0.0.0.0/nodo";
      ClaseRMI obj = new ClaseRMI();
  
      // registra la instancia en el rmiregistry
      Naming.rebind(url,obj);
    }
}