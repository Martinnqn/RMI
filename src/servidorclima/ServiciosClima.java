package servidorclima;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class ServiciosClima extends UnicastRemoteObject implements ServiciosClimaAbstract {

    public ServiciosClima() throws RemoteException {
    }

    @Override
    public String askClima(String date) throws RemoteException {
        System.out.println("Cliente> Consulta: " + date);
        
        String[] weathers = {"Soleado", "Lluvioso", "Ventoso", "Nublado"};
        String[] dmy = date.split("/");
        int seed = Integer.parseInt(dmy[0])
                + Integer.parseInt(dmy[1])
                + Integer.parseInt(dmy[2]) / 1000;
        Random random = new Random(seed);
        return weathers[random.nextInt(3)];
    }
}
