package servidorhoroscopo;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class ServidorHoroscopo {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Uso: ipServidor PuertoServ");
            return;
        }
        try {
            ServiciosHoroscopoAbstract srvHorosc = new ServiciosHoroscopo();
            Naming.rebind("rmi://" + args[0] + ":" + args[1] + "/ServiciosHoroscopo", srvHorosc);
            System.out.println("Servidor Horoscopo> Online.");
            System.out.println("Servidor Horoscopo> Esperando consultas...");
        } catch (RemoteException e) {
            System.err.println("Error de comunicacion: " + e.toString());
            System.exit(1);
        } catch (MalformedURLException e) {
            System.err.println("Excepcion en ServidorHoroscopo:");
            e.printStackTrace();
            System.exit(1);
        }
    }

}
