package br.com.johni.pessoa;


import javacard.framework.APDU;
import javacard.framework.Applet;
import javacard.framework.ISO7816;
import javacard.framework.ISOException;
import javacard.framework.Util;

public class Identificacao extends Applet {
	
	final static byte CLA_IDENTIFICACAO = (byte) 0xB0;
	
	final static byte INS_SET_NOME = (byte) 0x10;
	final static byte INS_GET_NOME = (byte) 0x20;
	final static byte INS_SET_IDADE = (byte) 0x30;
	final static byte INS_GET_IDADE = (byte) 0x40;
	final static byte INS_SET_TELEFONE  = (byte) 0x50;
	final static byte INS_GET_TELEFONE  = (byte) 0x60;
	final static byte INS_SET_OBSERVACAO = (byte) 0x70;
	final static byte INS_GET_OBSERVACAO = (byte) 0x80; 
	
	private Pessoa pessoa = null; 
	
	private Identificacao( byte[] bArray, short bOffset, byte bLength ) {
			
		this.pessoa = new Pessoa();
			
		register(bArray, (short) (bOffset + 1), bArray[bOffset]);
		
	}		
	public static void install(byte[] bArray, short bOffset, byte bLength) {
		
		new Identificacao(bArray, bOffset, bLength);
	}

	public void process(APDU apdu) {
		
		if (selectingApplet()) {
			return;
		}

		byte[] buf = apdu.getBuffer();
		switch (buf[ISO7816.OFFSET_INS]) {
		case INS_SET_NOME:
			pessoa.setNome(receberDados(apdu));			
			break;
		case INS_GET_NOME:
			enviarDados(apdu, pessoa.getNome());			
			break;
			
		case INS_SET_IDADE:
			pessoa.setIdade(receberDados(apdu));
			break;
			
		case INS_GET_IDADE:
			enviarDados(apdu, pessoa.getIdade());
			break;
			
		case INS_SET_TELEFONE:
			pessoa.setTelefone(receberDados(apdu));
			break;
			
		case INS_GET_TELEFONE:
			enviarDados(apdu, pessoa.getTelefone());
			break;
			
		case INS_SET_OBSERVACAO:
			pessoa.setObservacao(receberDados(apdu));
			break;
		
		case INS_GET_OBSERVACAO:
			enviarDados(apdu, pessoa.getObservacao());
			break;
		default:
			
			ISOException.throwIt(ISO7816.SW_INS_NOT_SUPPORTED);
		}
	}

	private byte[] receberDados(APDU apdu) {		
		byte[] dados = new byte[127]; 
		byte[] buffer = apdu.getBuffer();		
		short lengthDados = (short) (buffer[ISO7816.OFFSET_CDATA] & 0x00FF);
		
		if (lengthDados == 0)
			ISOException.throwIt(ISO7816.SW_WRONG_LENGTH);
					
		Util.arrayCopy(buffer, (short)((ISO7816.OFFSET_CDATA) & 0x00FF),
			dados, (short) 0, (short) 100);
	
		return dados; 
	}
	
	private void enviarDados(APDU apdu, byte[] dados) {
		
		short lengthDados = (short) dados.length;
		
		if (lengthDados == 0) 
			ISOException.throwIt(ISO7816.SW_WRONG_LENGTH);
		
		apdu.setOutgoing();
		apdu.setOutgoingLength(lengthDados);
		apdu.sendBytesLong(dados, (short) 0 , lengthDados);
		
	}
	
}
