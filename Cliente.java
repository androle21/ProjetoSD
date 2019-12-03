import java.awt.HeadlessException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import javax.swing.JOptionPane;

public class Cliente {

	private static Interface controlador = null;
	private static int usuario = 0;

	public static void main(String[] args) throws HeadlessException, NumberFormatException, RemoteException {
		menu();
	}

	// ========================================================================================================================================
	// ================================================= ESCOLHA DOS SERVIDORES  ==============================================================
	// ========================================================================================================================================

	public  static void menu(){
		String selecao = JOptionPane.showInputDialog(null,
				"OPÇÕES:\n\n1) Para acessar o SERVIDOR 1\n\n2) Para acessar o SERVIDOR 2\n\n3) Digite 3 para SAIR! ",
				"ESCOLHA DE SERVIDORES",
				JOptionPane.PLAIN_MESSAGE
				);

		try {
			switch(selecao){
			case "1":
				controlador = (Interface)Naming.lookup("//10.0.218.8/rmi");
				controlador.setCount(1);
				usuario = controlador.getCount();
				calculo();
				System.out.println("Cliente"+ controlador.getCount() + " Entrou");
				break;

			case "2":
				controlador = (Interface)Naming.lookup("//10.0.218.16/srv");
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
		}

		catch(Exception e){
			menu();
		}
	}

	// ========================================================================================================================================
	// =========================================== FIM SERVIDOR ESCOLHIDO PELO CLIENTE  =======================================================
	// ========================================================================================================================================


	public static void calculo()
	{
		try 
		{
			// se for o primeiro cliente mostra mensagem de espera
			if(controlador.getCount()==1){
				JOptionPane.showMessageDialog(null, "Aguardando o Segundo Cliente", "Informação", JOptionPane.WARNING_MESSAGE);
			}

			//tenho que dois clientes logados no servidor
			while (controlador.getCount() < 2) {}

			//distribui as responsabilidades de cada usuario
			if(usuario ==1 ){
				usuario1();
			}

			if(usuario==2){
				usuario2();
			}
		}

		catch(Exception e){

		}
	}

	// ========================================================================================================================================
	// =============================================== Interação do primeiro usuário  =========================================================
	// ========================================================================================================================================

	public static void usuario1(){
		try {

			//espera a vez
			while(usuario != controlador.getCliente()){}
			//efetua a pergunta
			String ResElet = JOptionPane.showInputDialog(null, "Digite a Resistividade Elétrica do Condutor (p):", "CLIENTE (1)", JOptionPane.PLAIN_MESSAGE);
			Float resp = Float.parseFloat(ResElet);

			//enquanto a pergunta não atender a necessidade repete a pergunta
			while (resp < 0 || resp > 10){
				ResElet = JOptionPane.showInputDialog(null, "Digite a Resistividade Elétrica do Condutor (p):", "CLIENTE (1)", JOptionPane.PLAIN_MESSAGE);
				resp = Float.parseFloat(ResElet);
			}
			//salva o valor 
			controlador.setResElet(resp);

			//passa a vez para o usuario 2
			controlador.incrementarCliente();

			//mostra a mensagem de espera
			System.out.println("Aguardando parametros do segundo cliente.");
			JOptionPane.showMessageDialog(null, "Aguardando parametros do segundo cliente.", "CLIENTE (1)", JOptionPane.WARNING_MESSAGE);



			//espera sua vez
			while(usuario != controlador.getCliente()){}

			//efetua a pergunta
			String AreaSe = JOptionPane.showInputDialog(null, "Digite a Área da Seção do Condutor(A) : ", "CLIENTE (1)", JOptionPane.PLAIN_MESSAGE);
			//controlador.setAreaSe(Float.parseFloat(AreaSe));
			resp = Float.parseFloat(AreaSe);

			while (resp < controlador.getResElet())	{

				AreaSe = JOptionPane.showInputDialog(null, "Digite a Área da Seção do Condutor(A) : ", "CLIENTE (1)", JOptionPane.PLAIN_MESSAGE);
				resp = Float.parseFloat(AreaSe);
			}


			controlador.setAreaSe(resp);

			//passa a vez para o usuario 2
			controlador.incrementarCliente();

			//espera o segundo usuario
			while(usuario != controlador.getCliente()){}

			//SEGUNDA ALTERAÇÃO:			 
			if(controlador.getPodever()==0){
				JOptionPane.showMessageDialog(null,"Você informou a Resistividade Elétrica e a Área da Seção do Condutor. \n "
						+ "FÓRMULA R = p* l / A \nA Resistência Elétrica é: "
						+ ((controlador.getResElet() * controlador.getCompCond()) / controlador.getAreaSe()));

			}
			else{
				JOptionPane.showMessageDialog(null,"Usuário 2 não permitiu mostrar o resultado. \n");
			}

			controlador.resetar();

			// Retornar ao MENU		

			int continuar  =  JOptionPane.showConfirmDialog(null, "Deseja voltar ao menu ?" );

			if(continuar==0){
				menu(); 
			}
		}
		catch(Exception e) {
			System.err.println(" Saindo do Sistema " + e);
			e.printStackTrace();
			System.exit(3);
		}
	}

	// ========================================================================================================================================
	// ================================================= Interação do segundo usuário =========================================================
	// ========================================================================================================================================

	public static void usuario2(){
		try {
			System.out.println("Aguardando parametros do primeiro cliente.");
			JOptionPane.showMessageDialog(null, "Aguardando parametros do primeiro cliente.", "CLIENTE (2)", JOptionPane.WARNING_MESSAGE);
			//espera a vez
			while(usuario != controlador.getCliente()){}

			String CompCond = JOptionPane.showInputDialog(null, "Digite o Comprimento do Condutor: (l)", "CLIENTE (2)", JOptionPane.PLAIN_MESSAGE);
			controlador.setCompCond(Float.parseFloat(CompCond));
			controlador.incrementarCliente();
			//espera a mensagem
			JOptionPane.showMessageDialog(null, "Aguardando parametros do primeiro cliente.", "CLIENTE (2)", JOptionPane.WARNING_MESSAGE);
			while(usuario != controlador.getCliente()){}

			// SEGUNDA ALTERAÇÃO			 
			int resp  =  JOptionPane.showConfirmDialog(null, "Deseja exibir o Resultado ?" );
			System.out.println(resp);
			controlador.setPodever(resp);
			controlador.incrementarCliente();

			if(resp==0) {

				JOptionPane.showMessageDialog(null,"Você informou o Comprimento do Condutor."
						+ "\nFÓRMULA R = p * l / A "
						+ "\nA Resistência Elétrica é: "
						+ ((controlador.getResElet() * controlador.getCompCond()) / controlador.getAreaSe()));
			}

			// Retornar ao MENU	


			int continuar  =  JOptionPane.showConfirmDialog(null, "Deseja voltar ao menu ?" );
			if(continuar==0)
			{
				menu(); 
			}
		}
		catch(Exception e) {
			System.err.println(" Saindo do Sistema " + e);
			e.printStackTrace();
			System.exit(0);
		}
	}

}