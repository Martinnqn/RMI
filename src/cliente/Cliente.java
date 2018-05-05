package cliente;

import java.rmi.*;

import servidorcentral.ServiciosCentralAbstract;

public class Cliente {

    public static void main(String args[]) {
        if (args.length != 3) {
            System.err.println("Uso Cliente: IPServidor PuertoServidor (fecha,signo).");
            return;
        }
        try {
            ServiciosCentralAbstract srv = (ServiciosCentralAbstract) Naming.lookup("//" + args[0] + ":" + args[1] + "/ServiciosCentral");
            System.out.println(srv.consultarClimayHorosc(args[2]));
        } catch (RemoteException e) {
            System.err.println("Error de comunicacion: " + e.toString());
        } catch (Exception e) {
            System.err.println("Excepcion en ClienteEco:");
            e.printStackTrace();
        }
    }

}
