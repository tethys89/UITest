/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.UITest;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Madaxe
 */
public class Client {

    //<editor-fold defaultstate="collapsed" desc="variables">
    private String server;
    private String nick;
    private String login;
    private String channel;
    private Socket socket;
    private int serverPort;
    private BufferedWriter out;
    private BufferedReader in;
    //    private int host;
    //</editor-fold>

    public Client(String s, int h, String n, String l, String c, int SP) {
	server = s;
//	host = h;
	nick = n;
	login = l;
	channel = c;
	serverPort = SP;
	try {
	    socket = new Socket(s, SP);
	} catch (UnknownHostException ex) {
	    System.out.print("Error: Couldn't connect to host");
	} catch (IOException ex) {
	    System.out.print("Error: Couldn't connect socket");
	}
    }

    //<editor-fold defaultstate="collapsed" desc="getters and setters">
    public String getServer() {
	return server;
    }

//    public int getHost() {
//	return host;
//    }

    public String getNick() {
	return nick;
    }

    public String getLogin() {
	return login;
    }

    public String getChannel() {
	return channel;
    }

    public Socket getSocket() {
	return socket;
    }

    public int getSP() {
	return serverPort;
    }

    public BufferedWriter getBW() {
	return out;
    }

    public BufferedReader getBR() {
	return in;
    }

    public void setServer(String s) {
	server = s;
    }

//    public void setHost(int h) {
//	host = h;
//    }

    public void setNick(String n) {
	nick = n;
    }

    public void setLogin(String l) {
	login = l;
    }

    public void setChannel(String c) {
	channel = c;
    }

    public void setSocket(Socket s) {
	socket = s;
    }

    public void setSP(int SP) {
	serverPort = SP;
    }

    public void setBW(BufferedWriter BW) {
	out = BW;
    }

    public void setBR(BufferedReader BR) {
	in = BR;
    }
    //</editor-fold>
       
    public BufferedReader inStream(){
	try {
	    in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
	} catch (IOException ex) {
	    System.out.print("Error: Couldn't connect to input stream");
	}
	return in;
    }
    
    public BufferedWriter outStream(){
	try {
	    out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	} catch (IOException ex) {
	    System.out.print("Error: Couldn't connect to output stream");
	}
	return out;
    }
    
    public void connectClient() {
	try {
	    out.write("NICK " + nick + "\r\n");
	    out.write("USER " + login + " 8 * :" + nick + "\r\n");
	    out.flush();
	} catch (IOException ex) {
	    System.out.print("Error: Couldn't connect to server");
	}
    }
    
    public void joinChan(){
	try {
	    out.write("JOIN " + channel + "\r\n");
	    out.flush();
	} catch (IOException ex) {
	    System.out.print("Error: Couldn't join channel");
	}
    }

    public void sendMessage(String s) {
	try {
	    out.write("PRIVMSG " + channel + " : " + s + "\r\n");
	    out.flush();
	} catch (IOException ex) {
	    System.out.print("couldn't send to server");
	}

//These lines need to go outside of the client sendMessgaes function
//	jTextArea1.append(dateFormat.format(date) + " " + nick + ": " + s + "\r\n");
//	jTextArea3.setText("");
    }





}