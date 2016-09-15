import javax.swing.JOptionPane;

public class Tela{

	public Arduino conn;
	
	public Tela(){
		this.conn = new Arduino();
	}
	
	
	public static void main(String[] args) {
		Tela tela = new Tela();
		while(true){
			int opcao = JOptionPane.showConfirmDialog(null, "Ligar ou desligar");
			if(opcao == 0){
				tela.conn.comunicacaoArduino(0);
			}else{
				tela.conn.comunicacaoArduino(1);
			}
		}
	}
}
