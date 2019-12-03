import java.rmi.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Implementacao extends UnicastRemoteObject implements Interface {


	protected Implementacao() throws RemoteException {
		super();
	}

	int cliente = 1;
	String Brasil="Brasil";
	String Portugal="Portugal";
	float podever = 0;



	public float getPodever() throws RemoteException{
		return podever;
	}

	public void setPodever(float podever) throws RemoteException {
		this.podever = podever;
	}





	public String getReplicacao()
	{
		Portugal=Brasil;
		return "replicou";
	}




	//Criado um SetCount para poder esperar que os dois cliente estejam logados antes de iniciar o Cauculo
	int count = 0;

	public void setCount(int i) throws RemoteException{
		count+= i;
	}	

	public int getCount() throws RemoteException{
		return count;
	}

	/*AQUI*/

	public  void setTexto(String texto){
		texto = texto;
	}

	public String getTexto(){
		return Brasil;
	}

	public int getCliente() throws RemoteException {
		return cliente;
	}

	//toda vez que ele executa essa funcao muda  valor da variavel de 0 para 1 e de um para 0 onde 0 representa o cliente 1 e 1 representa o cliente 2
	public void incrementarCliente() throws RemoteException {
		if (cliente > 1) {
			cliente = 1;
		} else {
			cliente = 2;
		}
	}

	private  float ResElet= 0;
	private  float CompCond= 0;
	private  float AreaSe = 0;
	public static float Resistencia = 0;

	public void resetar() throws RemoteException {
		ResElet = 0;
		CompCond = 0;
		AreaSe = 0;
		Resistencia = 0;
		count=0;
	}

	public void calculaArea() throws Exception {
		Resistencia = (ResElet * CompCond) / AreaSe ;
		System.out.println("Valor da Resistência "+ Resistencia);
	}

	public void setResElet(float Variavel) throws RemoteException {
		System.out.println("Valor da Resistividade Elétrica definido por: "+ Variavel);
		ResElet = Variavel;	
	}

	public void setCompCond(float Variavel) throws RemoteException {
		System.out.println("Valor do Comprimento do Condutor definido: "+ Variavel);
		CompCond = Variavel;	
	}

	public void setAreaSe(float Variavel) throws RemoteException {
		System.out.println("Valor Resistência é: "+ Variavel);
		AreaSe = Variavel;	
	}

	public  float getResElet() throws RemoteException {
		return ResElet;
	}

	public   float getCompCond() throws RemoteException {
		return this.CompCond;
	}

	public   float getAreaSe() throws RemoteException {
		return AreaSe;
	}

	public   float getResistencia() throws RemoteException {
		return Resistencia;
	}

}