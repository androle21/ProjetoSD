
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Implementacao extends UnicastRemoteObject implements Interface  {
		
	
	protected Implementacao() throws RemoteException {	
		super(); // chamando o construtor da classe UnicastRemoteObject - usado para exportar um
					// objeto remoto
	}
	
	
	
	int cliente = 1;
	String Laranja="Laranja";
	String Abobora="Abobora";
	float podever = 0;
	
	
	
	public float getPodever() throws RemoteException{
		return podever;
	}
	
	public void setPodever(float podever) throws RemoteException {
		this.podever = podever;
	}
	
	
	
	
	
    public String getReplicacao()
    {
    	Abobora=Laranja;
        return "replicou";
    }
	
	
    
    
//Criado um SetCount para poder esperar que os dois cliente estejam logados antes de iniciar o Cauculo
int count = 0;
	
	public void setCount(int i) throws RemoteException
	{
		count+= i;
	}	
	
	public int getCount() throws RemoteException
	{
		return count;
	}
	
/*AQUI*/
	
    public  void setTexto(String texto)
    {
    	 texto = texto;
    	
    	
    }
    
    public String getTexto()
    {
    	return Laranja;
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
	
	
	
	
	
	private int ptermo=0;
	private int razao=0;
	private int qtermo=0;
	public static int SomaPa =0;
	
	public void resetar() throws RemoteException{
		ptermo = 0;
		razao = 0;
		qtermo = 0;
		SomaPa = 0;
	}
	
	public void CalculaPa() throws Exception{
		SomaPa = (ptermo+(qtermo-1)*razao);
		System.out.println("Valor do Somatorio da PA "+ SomaPa);
	}
	
	public void setPtermo(int Variavel) throws RemoteException {
	System.out.println("Valor do Primeiro Termo: "+ Variavel);
	ptermo = Variavel;
	}
	
	public void setRazao(int Variavel) throws RemoteException{
		System.out.println("Valor da Razao: "+ Variavel);
		razao=Variavel;
	}
	
	public void setQtermo(int Variavel) throws RemoteException{
		System.out.println("Valor do Ultimo Termo: "+ Variavel);
		qtermo=Variavel;
	}
	
	public int getPtermo() throws RemoteException {
		
		return this.ptermo;
		}
	
	public int getRazao() throws RemoteException {
	
	return razao;
	}
		
	public int getQtermo() throws RemoteException {
		
		return qtermo;
		}
	
	public   float getSomaPa() throws RemoteException {
		return SomaPa;
	}
	
		
		   
		
			
	
		

/*
	public int pa(int a, int r, int n, int an) throws RemoteException {
		for (int i = 1; i <= n; i++) {
			an = a + (i -1)*r; //Fórmula geral da PA

			//AN=A1+(n-1)*r
			//A1 primeiro termo
			//R Razaão
			//N Quantidade de Termos
			//An=A1+(n-1)*r;

			}
		System.out.println(an);
		return an;
	}
*/

		public String Criptografia(String aux) throws RemoteException {
			char charArray[];
			char charAux = ' ';
			String output = "";
			int tam = aux.length();

			charArray = new char[tam];
			output += "\nCriptografado: ";

			aux.getChars(0, tam, charArray, 0);
			

			for (int count = 0; count < charArray.length; count++) {
				if (charArray[count] == ' ')
					charAux = ' ';
				if (charArray[count] == '1')
					charAux = 'A';
				if (charArray[count] == '2')
					charAux = 'B';
				if (charArray[count] == '3')
					charAux = 'C';
				if (charArray[count] == '4')
					charAux = 'D';
				if (charArray[count] == '5')
					charAux = 'E';
				if (charArray[count] == '6')
					charAux = 'F';
				if (charArray[count] == '7')
					charAux = 'G';
				if (charArray[count] == '8')
					charAux = 'H';
				if (charArray[count] == '9')
					charAux = 'I';
				if (charArray[count] == '0')
					charAux = 'j';

				charArray[count] = charAux;
				output += charArray[count];

			}
			return output;

		}
		
		
	   //Criptografia
		
		public String Descriptografia(String aux) throws RemoteException {
			char charArray[];
			char charAux = ' ';
			String output2 = "Descriptografado: " + aux;
			int tam = aux.length();

			charArray = new char[tam];
			output2 += "";

			aux.getChars(0, tam, charArray, 0);

			for (int count = 0; count < charArray.length; count++) {
				if (charArray[count] == ' ')
					charAux = ' ';
				if (charArray[count] == 'A')
					charAux = '1';
				if (charArray[count] == 'B')
					charAux = '2';
				if (charArray[count] == 'C')
					charAux = '3';
				if (charArray[count] == 'D')
					charAux = '4';
				if (charArray[count] == 'E')
					charAux = '5';
				if (charArray[count] == 'F')
					charAux = '6';
				if (charArray[count] == 'G')
					charAux = '7';
				if (charArray[count] == 'H')
					charAux = '8';
				if (charArray[count] == 'I')
					charAux = '9';
				if (charArray[count] == 'J')
					charAux = '0';

				charArray[count] = charAux;
				output2 += charArray[count];
			}
			return output2;
		}
	
		
		
	}



