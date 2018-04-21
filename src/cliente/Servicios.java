/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Martin
 */
public interface Servicios extends Remote{
    String consultarClimayHorosc (String s) throws RemoteException;
}
