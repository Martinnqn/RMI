package servidorclima;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiciosClimaAbstract extends Remote {

    public String askClima(String date) throws RemoteException;

}
