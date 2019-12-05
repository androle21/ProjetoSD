import java.rmi.Remote;
import java.rmi.RemoteException;


import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Interface extends Remote {
	
	int getCliente() throws RemoteException;
	void incrementarCliente() throws RemoteException;
	
	void setCount(int i) throws RemoteException;
	int getCount() throws RemoteException;
		
	public String getReplicacao() throws RemoteException;
	public void setTexto(String texto) throws RemoteException;
	public String getTexto() throws RemoteException;	
	
	
	public void setPodever(float x)throws RemoteException;
	public float getPodever() throws RemoteException;
	public float getSomaPa() throws RemoteException;	
	
	public void resetar() throws RemoteException;	
		
		
	
	public int getPtermo() throws RemoteException;
	public void setPtermo(int termo) throws RemoteException;

	public int getRazao() throws RemoteException;
	public void setRazao(int razao) throws RemoteException;
	
	public int getQtermo() throws RemoteException;
	public void setQtermo(int qtermo) throws RemoteException;

	
	public String Criptografia (String aux) throws RemoteException;
	public String Descriptografia (String aux) throws RemoteException;

}
