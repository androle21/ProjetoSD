import java.rmi.Naming;

public class Servidor1 {
	
		public static void main (String [] args) {

		try{
			Implementacao Servidor = new Implementacao(); // Criação do objeto para o servidor
			System.out.println("Servidor no ar" + " Laranja: " + Servidor.Laranja );
			Naming.rebind("//localhost/rmi", Servidor);
						
			
		}
		catch (Exception e){
			System.err.println("Houve algum problema" + e);
			e.printStackTrace();
			System.exit(2);
		}
		System.out.println("Aguardando inicialização do objeto...");
		}
	
}
