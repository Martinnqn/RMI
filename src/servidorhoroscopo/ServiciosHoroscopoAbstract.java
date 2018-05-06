package servidorhoroscopo;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiciosHoroscopoAbstract extends Remote {

    public String askHoroscopo(String sign) throws RemoteException;

}
