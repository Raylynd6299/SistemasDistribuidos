public class Main {
    
    static class Worker extends Thread{
        @Override
        public void run() {
        }
    }
    
    public static void main(String[] args) throws Exception {
        Worker worker1 = new Worker();
        Worker worker2 = new Worker();
        worker1.start();
        worker2.start();
        worker1.join(); // Hace que el Hilo Principal espere al Hijo
        worker2.join();
    }
}