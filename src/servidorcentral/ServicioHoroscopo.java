/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorcentral;
import java.rmi.Remote;

/**
 *
 * @author Martin
 */
public interface ServicioHoroscopo extends Remote {

    public String consultarHoroscopo(String sign);
}
