/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorhoroscopo;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Martin
 */
public interface ServiciosHoroscopoAbstract extends Remote {

    public String consultarHoroscopo(String sign) throws RemoteException;
}
