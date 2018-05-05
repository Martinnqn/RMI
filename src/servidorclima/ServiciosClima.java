/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorclima;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

/**
 *
 * @author Martin
 */
public class ServiciosClima extends UnicastRemoteObject implements ServiciosClimaAbstract {

    public ServiciosClima() throws RemoteException {
    }

    @Override
    public String consultarClima(String date) throws RemoteException {
        String[] weathers = {"Soleado", "Lluvioso", "Ventoso", "Nublado"};
        String[] dmy = date.split("/");
        int seed = Integer.parseInt(dmy[0])
                + Integer.parseInt(dmy[1])
                + Integer.parseInt(dmy[2]) / 1000;
        Random random = new Random(seed);
        return weathers[random.nextInt(3)];
    }
}
