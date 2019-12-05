import java.rmi.Naming;

public class Servidor2 {
	
	public static void main (String [] args) {
		
		
		try{
			
			Implementacao Servidor = new Implementacao(); // Criação do objeto para o servidor
			//System.out.println("Servidor no ar Abobora : " + Servidor.Abobora );
			Naming.rebind("//localhost/rmi", Servidor);
			System.out.println("Servidor no ar  Abobora REPLICADO : " + Servidor.Abobora );
			//Servidor.getReplicacao();
		
		}
		catch (Exception e){
			System.err.println("Houve algum problema" + e);
			e.printStackTrace();
			System.exit(2);
		}
		System.out.println("Aguardando inicialização do objeto...");
		
	}
	
}
