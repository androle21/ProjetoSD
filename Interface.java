import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Interface extends Remote{

	int getCliente() throws RemoteException;
	void incrementarCliente() throws RemoteException;

	void setCount(int i) throws RemoteException;
	int getCount() throws RemoteException;

	public String getReplicacao() throws RemoteException;
	public void setTexto(String texto) throws RemoteException;
	public String getTexto() throws RemoteException;	

	public void setResElet(float ResElet) throws RemoteException;
	public float getResElet() throws RemoteException;
	public void setAreaSe(float AreaSe) throws RemoteException;
	public float getAreaSe() throws RemoteException;
	public void setCompCond(float CompCond) throws RemoteException;
	public float getCompCond() throws RemoteException;	

	public void setPodever(float x)throws RemoteException;
	public float getPodever() throws RemoteException;
	public float getResistencia() throws RemoteException;	

	public void resetar() throws RemoteException;	

}