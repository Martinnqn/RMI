package servidorcentral;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiciosCentralAbstract extends Remote {

    public String consultarClimayHorosc(String s) throws RemoteException;
}
