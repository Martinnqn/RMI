/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorclima;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

/**
 *
 * @author Martin
 */
public class ServidorClima {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Uso: ipServidor PuertoServ");
            return;
        }
        try {
            ServiciosClimaAbstract srvClima = new ServiciosClima();
            Naming.rebind("rmi://" + args[0] + ":" + args[1] + "/ServiciosClima", srvClima);
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
