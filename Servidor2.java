import java.rmi.Naming;

public class Servidor2 {

	public static void main (String [] args){

		try{
			Implementacao Servidor = new Implementacao(); // Criação do objeto para o servidor
			Naming.rebind("//10.0.218.16/srv", Servidor);

			System.out.println("Servidor 2 no ar : " + Servidor.Portugal );
			//Servidor.getReplicacao();
			//System.out.println("Servidor 2 no ar REPLICADO : " + Servidor.Portugal );
		}

		catch (Exception e){
			System.err.println("Houve algum problema" + e);
			e.printStackTrace();
			System.exit(2);
		}
		System.out.println("Aguardando inicialização do objeto...");
	}

}
