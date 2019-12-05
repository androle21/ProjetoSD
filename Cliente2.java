import java.awt.HeadlessException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import javax.swing.JOptionPane;



/*https://www.todamateria.com.br/progressao-aritmetica/
 * 
 * As progressões aritméticas podem apresentar um número determinado de termos (P.A. finita) ou um número infinito de termos (P.A. infinita).

Para indicar que uma sequência continua indefinidamente utilizamos reticências, por exemplo:

a sequência (4, 7, 10, 13, 16, ...) é uma P.A. infinita.
a sequência (70, 60, 50, 40, 30, 20, 10) é uma P.A. finita.
Cada termo de uma P.A. é identificado pela posição que ocupa na sequência e para representar cada termo utilizamos uma letra (normalmente a letra a) seguida de um número que indica sua posição na sequência.

Por exemplo, o termo a4 na P.A (2, 4, 6, 8, 10) é o número 8, pois é o número que ocupa a 4ª posição na sequência.

Classificação de uma P.A.
De acordo com o valor da razão, as progressões aritméticas são classificadas em:

Constante: quando a razão for igual a zero. Por exemplo: (4, 4, 4, 4, 4...), sendo r = 0.
Crescente: quando a razão for maior que zero. Por exemplo: (2, 4, 6, 8,10...), sendo r = 2.
Decrescente: quando a razão for menor que zero (15, 10, 5, 0, - 5,...), sendo r = - 5
 * 
 */

public class Cliente2 {

	private static Interface controlador = null;
	private static int usuario = 0;


	public static void main(String[] args) throws HeadlessException, NumberFormatException, RemoteException {

		menu();
	}


	// ================================================= ESCOLHA DOS SERVIDORES  ==============================================================



	public  static void menu(){

	

		try {
			String selecao = JOptionPane.showInputDialog(
					null,
					"OPÇÕES:\n\n1) Acessar o SERVIDOR 1?\n\n2) Acessar o SERVIDOR 2?\n\n3) Digite 3 para SAIR! ",
					"ESCOLHA DE SERVIDORES",
					JOptionPane.PLAIN_MESSAGE
					);

			switch(selecao)
			{
			case "1":
				controlador = (Interface)Naming.lookup("//10.0.218.21/rmi");
				controlador.setCount(1);
				usuario = controlador.getCount();
				calculo();
				System.out.println("Cliente"+ controlador.getCount() + " Entrou");
				break;

			case "2":
				controlador = (Interface)Naming.lookup("//10.0.218.24/srv");
				controlador.setCount(1);
				usuario = controlador.getCount();
				calculo();
				System.out.println("Cliente"+ controlador.getCount() + " Entrou");
				break;

			case "3":
				break;


			default:
				menu();
				break;

			}

		}catch(Exception e) {
			menu();
		}

	}//fim do metodo menu


	// =========================================== SERVIDOR ESCOLHIDO PELO CLIENTE  =======================================================



	public static void calculo()
	{
		try 
		{
			// se for o primeiro cliente mostra mensagem de espera
			if(controlador.getCount()==1)
			{
				JOptionPane.showMessageDialog(null, "Aguardando o segundo cliente", "Informação", JOptionPane.WARNING_MESSAGE);
			}
			
			//tenho que ter dois clientes logados no servidor
			while (controlador.getCount() < 2) {}

			//distribui as responsabilidades de cada usuario
			if(usuario == 1 )
			{
				usuario1();
			}

			if(usuario == 2){
				usuario2();
			}

		}catch(Exception e) {

		}

	}


	// ================================================= PRIMEIRO CLIENTE  ===============================================================



	public static void usuario1()
	{
		try {
			while(usuario == 1){
			//espera a vez
			while(usuario != controlador.getCliente()){}
			//efetua a pergunta
			String Ptermo = JOptionPane.showInputDialog(null, "Digite o primeiro termo da PA:", "CLIENTE (1)", JOptionPane.PLAIN_MESSAGE);
			int resp = Integer.parseInt(Ptermo);

			//enquanto a pergunta não atender a necessidade repete a pergunta
			while (resp < 0 || resp > 10)
			{
				Ptermo = JOptionPane.showInputDialog(null, "Digite o primeiro termo da PA:", "CLIENTE (1)", JOptionPane.PLAIN_MESSAGE);
				resp = Integer.parseInt(Ptermo);
			}
			//salva o valor 
			controlador.setPtermo(resp);

			//passa a vez para o usuario 2
			controlador.incrementarCliente();

			//mostra a mensagem de espera
			System.out.println("Aguardando parametros do segundo cliente.");
			JOptionPane.showMessageDialog(null, "Aguardando parametros do segundo cliente.", "CLIENTE (1)", JOptionPane.WARNING_MESSAGE);

			//espera sua vez
			while(usuario != controlador.getCliente()){}

			String Qtermo = JOptionPane.showInputDialog(null, "Digite o ultimo termo da PA", "CLIENTE (1)", JOptionPane.PLAIN_MESSAGE);
			//salva o valor 
			controlador.setQtermo(Integer.parseInt(Qtermo));
			//passa a vez para o usuario 2
			controlador.incrementarCliente();


			System.out.println("Aguardando parametros do segundo cliente.");
			JOptionPane.showMessageDialog(null, "Aguardando parametros do segundo cliente.", "CLIENTE (1)", JOptionPane.WARNING_MESSAGE);

			//espera sua vez
			while(usuario != controlador.getCliente()){}

			
			//pergunta ao usuario1:			 


			int resposta  =  JOptionPane.showConfirmDialog(null, "Deseja exibir o Resultado ?" );
			System.out.println(resposta);
			controlador.setPodever(resposta);
			controlador.incrementarCliente();

			if(resposta==0) {

				JOptionPane.showMessageDialog(null,"Você informou o primeiro termo da PA e o ultimo termo da PA."
						+ "\nFÓRMULA PA = An=A+(n-1)*R"
						+ "\nO Calculo da PA é : "
						
						+(controlador.getPtermo()+(controlador.getQtermo()-1)*controlador.getRazao())
						);
			}





			controlador.resetar();
			// Retornar ao MENU		
			while(usuario != controlador.getCliente()){}
			int continuar  =  JOptionPane.showConfirmDialog(null, "Deseja voltar ao menu ?" );

			if(continuar==0)
			{
				
				controlador.setCount(1);
				usuario1(); 
			}



		}
		}catch(Exception e) {
			System.err.println(" Saindo do Sistema " + e);
			e.printStackTrace();
			System.exit(3);

		}


	}


	// ================================================= SEGUNDO CLIENTE  ===============================================================



	public static void usuario2()
	{
		try {
			while(usuario == 2){
			while(usuario != controlador.getCliente()){}
			System.out.println("Aguardando parametros do primeiro cliente.");
			JOptionPane.showMessageDialog(null, "Aguardando parametros do primeiro cliente.", "CLIENTE (2)", JOptionPane.WARNING_MESSAGE);
			//espera a vez
			while(usuario != controlador.getCliente()){}

			String Razao = JOptionPane.showInputDialog(null, "Digite a Razao da PA", "CLIENTE (2)", JOptionPane.PLAIN_MESSAGE);
			controlador.setRazao(Integer.parseInt(Razao));
			controlador.incrementarCliente();
			
			//espera a mensagem
			JOptionPane.showMessageDialog(null, "Aguardando parametros do primeiro cliente.", "CLIENTE (2)", JOptionPane.WARNING_MESSAGE);
			while(usuario != controlador.getCliente()){}

			//efetua a pergunta
			
			/*
			String DistCarga = JOptionPane.showInputDialog(null, "Digite a Distancia das Cargas(d) : ", "CLIENTE (2)", JOptionPane.PLAIN_MESSAGE);
			//controlador.setDistCarga(Float.parseFloat(DistCarga));
			float resp = Float.parseFloat(DistCarga);

			while (resp < controlador.getConstElet())	{

				DistCarga = JOptionPane.showInputDialog(null, "Digite a Digite a Distancia das Cargas(d) : ", "CLIENTE (2)", JOptionPane.PLAIN_MESSAGE);
				resp = Float.parseFloat(DistCarga);

			}

			controlador.setDistCarga(resp);
			*/
			//passa a vez para o usuario 1
			controlador.incrementarCliente();

			//espera o primeiro usuario
			while(usuario != controlador.getCliente()){}

			// resposta do servidor			 
			if(controlador.getPodever()==0)
			{
				JOptionPane.showMessageDialog(null,"Você informou a Razao da PA. \n "
						+ "FÓRMULA PA = An=A+(n-1)*R"
						
						+(controlador.getPtermo()+(controlador.getQtermo()-1)*controlador.getRazao())
						);
			}else {

				JOptionPane.showMessageDialog(null,"Usuário 1 não permitiu mostrar o resultado. \n");
			}
			
			controlador.resetar();
			// Retornar ao MENU	
			controlador.resetar();
			controlador.incrementarCliente();
			while(usuario != controlador.getCliente()){}
			int continuar  =  JOptionPane.showConfirmDialog(null, "Deseja voltar ao menu ?" );
			if(continuar==0)
			{
				while(usuario != controlador.getCliente()){}
				
				controlador.setCount(1);
				usuario2(); 
			}


			controlador.incrementarCliente();
		}

		}catch(Exception e) {
			System.err.println(" Saindo do Sistema " + e);
			e.printStackTrace();
			System.exit(0);
		}
	}
}


