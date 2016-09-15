import java.io.IOException;

import javax.swing.JButton;

/**
 * @author klauder
 */
public class Arduino {
  private ControlePorta arduino;
  
  /**
   * Construtor da classe Arduino
   */
  public Arduino(){
      arduino = new ControlePorta("COM4",9600);//Windows - porta e taxa de transmissão
      //arduino = new ControlePorta("/dev/ttyUSB0",9600);//Linux - porta e taxa de transmissão
  }    

  /**
   * Envia o comando para a porta serial
   * @param button - Botão que é clicado na interface Java
   */
  public void comunicacaoArduino(int opcao) {        
    if(opcao == 0){
      arduino.enviaDados('0');
      System.out.println("Ligar arduino");//Imprime o nome do botão pressionado
    }
    else if(opcao == 1){
      arduino.enviaDados('1');
      System.out.println("Desligar Arduino");
    }
    else{
      arduino.close();
      System.out.println("ne");//Imprime o nome do botão pressionado
    }
  }
  
  public int lerDados(){
	try {
		return arduino.getInputStream().read();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return 0;
  }
  
  public void fecharConexao(){
	  arduino.fechar();
  }
}