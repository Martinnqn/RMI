/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorcentral;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Martin
 */
public interface ServicioClima extends Remote {

    public String consultarClima(String date) throws RemoteException;

}
