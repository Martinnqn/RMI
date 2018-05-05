package servidorcentral;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.HashMap;

public class ServidorCentral {

    public static void main(String[] args) {
        if (args.length != 6) {
            System.err.println("Uso: ipServidor PuertoServ ipClima portClima "
                    + "ipHoroscopo portHoroscopo");
            return;
        }
        //caches
        HashMap<String, String> cacheWeather;
        HashMap<String, String> cacheHoroscope;
        //inicializar las caches
        cacheWeather = new HashMap<>();
        cacheHoroscope = new HashMap<>();
        try {
            ServiciosCentral servicios = new ServiciosCentral(args[2], args[3], args[4], args[5],
                    cacheHoroscope, cacheWeather);
            Naming.rebind("rmi://" + args[0] + ":" + args[1] + "/ServiciosCentral", servicios);
            System.out.println("Servidor> Online.");
            System.out.println("Servidor> Esperando consultas...");
        } catch (RemoteException e) {
            System.err.println("Servidor> Error de comunicacion: " + e.toString());
            System.exit(1);
        } catch (MalformedURLException e) {
            System.err.println("Servidor> Excepcion:");
            e.printStackTrace();
            System.exit(1);
        }
    }

}
