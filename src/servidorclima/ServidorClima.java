package servidorclima;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class ServidorClima {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Uso: ipServidor PuertoServ");
            return;
        }
        try {
            ServiciosClimaAbstract srvClima = new ServiciosClima();
            Naming.rebind("rmi://" + args[0] + ":" + args[1] + "/ServiciosClima", srvClima);
            System.out.println("Servidor Clima> Online.");
            System.out.println("Servidor Clima> Esperando consultas...");
        } catch (RemoteException e) {
            System.err.println("Error de comunicacion: " + e.toString());
            System.exit(1);
        } catch (MalformedURLException e) {
            System.err.println("Excepcion en ServidorClima:");
            e.printStackTrace();
            System.exit(1);
        }
    }

}
