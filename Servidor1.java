import java.rmi.Naming;

public class Servidor1 {

	public static void main (String [] args){

		try{
			Implementacao Servidor = new Implementacao(); // Criação do objeto para o servidor
			Naming.rebind("//10.0.218.8/rmi", Servidor);

			System.out.println("Servidor 1 no ar: " + Servidor.Brasil );
		}

		catch (Exception e){
			System.err.println("Houve algum problema" + e);
			e.printStackTrace();
			System.exit(2);
		}
		System.out.println("Aguardando inicialização do objeto...");
	}

}
