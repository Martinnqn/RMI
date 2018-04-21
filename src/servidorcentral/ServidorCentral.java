/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorcentral;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.HashMap;

/**
 *
 * @author Martin
 */
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
            ServiciosImp servicios = new ServiciosImp(args[2], args[3], args[4], args[5],
                    cacheHoroscope, cacheWeather);
            Naming.rebind("rmi://" + args[0] + ":" + args[1] + "/ServiciosImp", servicios);
        } catch (RemoteException e) {
            System.err.println("Error de comunicacion: " + e.toString());
            System.exit(1);
        } catch (MalformedURLException e) {
            System.err.println("Excepcion en ServidorCentral:");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
