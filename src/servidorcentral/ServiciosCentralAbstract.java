package servidorcentral;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiciosCentralAbstract extends Remote {

    public String askClimaHoroscopo(String s) throws RemoteException;
}
