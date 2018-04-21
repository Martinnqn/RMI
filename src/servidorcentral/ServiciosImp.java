/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorcentral;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin
 */
public class ServiciosImp extends UnicastRemoteObject implements Servicios {

    private String ipClima;
    private String portClima;
    private String ipHorosc;
    private String portHorosc;

    private HashMap<String, String> cacheWeather;
    private HashMap<String, String> cacheHoroscope;

    public ServiciosImp(String ipClima, String portClima, String ipHorosc, String portHorosc,
            HashMap cacheHorosc, HashMap cacheClima) throws RemoteException {
        this.ipClima = ipClima;
        this.portClima = portClima;
        this.ipHorosc = ipHorosc;
        this.portHorosc = portHorosc;
        this.cacheHoroscope = cacheHorosc;
        this.cacheWeather = cacheClima;
    }

    @Override
    public String consultarClimayHorosc(String query) throws RemoteException {
        String answer;
        /*extraer fecha y horoscopo de query*/
        String date = query.split(",")[0].substring(1);
        String hAux = query.split(",")[1];
        String sign = hAux.substring(0, hAux.length() - 1);
        if (query.equals("salir")) {
            answer = "Hasta luego.";
        } else {
            String answerW = consultarClima(date);
            String answerH = consultarHoroscopo(sign);
            answer = "Horóscopo: " + answerH + " \n Clima: " + answerW;
        }
        return answer;
    }

    private String consultarHoroscopo(String sign) throws RemoteException {
        String answerH;
        boolean isValidSign = isValidSign(sign);

        if (isValidSign) {
            //busca la consulta en la cache.
            answerH = cacheHoroscope.get(sign);
            //si no tiene dato entonces se lo pregunta al servidor
            if (answerH == null) {
                System.out.println("Servidor> Consultando a servidor de horoscopo...");
                //consultar el horoscopo
                ServicioHoroscopo srv;
                try {
                    srv = (ServicioHoroscopo) Naming.lookup("//" + ipHorosc + ":" + portHorosc + "/ServicioHoroscopo");
                    answerH = srv.consultarHoroscopo(sign);
                    if (answerH.equals("ERROR")) {
                        answerH = "El servidor de horoscopo no esta disponible, consulte mas tarde.";
                    } else {
                        cacheHoroscope.put(sign, answerH);
                    }
                } catch (NotBoundException | MalformedURLException ex) {
                    Logger.getLogger(ServiciosImp.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("Servidor> Cache hit.");
            }
        } else {
            answerH = "Signo no válido";
        }

        return answerH;
    }

    private String consultarClima(String date) throws RemoteException {
        String answerW;
        boolean isValidDate = isValidDate(date);

        //consultar el clima
        //chequea si es un dato valido para consultar el clima o el horoscopo
        if (isValidDate) {
            //busca la consulta en la cache.
            answerW = cacheWeather.get(date);
            //si no tiene dato entonces se lo pregunta al servidor
            if (answerW == null) {
                System.out.println("Servidor> Consultando a servidor de clima...");
                ServicioClima srv;
                try {
                    srv = (ServicioClima) Naming.lookup("//" + ipClima + ":" + portClima + "/ServicioClima");
                    answerW = srv.consultarCLima(date);
                    if (answerW.equals("ERROR")) {
                        answerW = "El servidor de clima no esta disponible, consulte mas tarde.";
                    } else {
                        cacheWeather.put(date, answerW);
                    }
                } catch (NotBoundException | MalformedURLException ex) {
                    Logger.getLogger(ServiciosImp.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("Servidor> Cache hit.");
            }
        } else {
            answerW = "Fecha no válida";
        }

        return answerW;
    }

    public boolean isValidDate(String text) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(text.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    public boolean isValidSign(String text) {
        String[] signs = {"aries", "tauro", "geminis", "cancer", "leo", "virgo",
            "libra", "escorpio", "sagitario", "capricornio", "acuario", "piscis"};
        for (int i = 0; i < signs.length; i++) {
            if (text.equals(signs[i])) {
                return true;
            }
        }
        return false;
    }
}
