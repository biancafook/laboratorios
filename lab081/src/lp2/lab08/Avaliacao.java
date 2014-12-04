package lp2.lab08;

import java.text.SimpleDateFormat;
import java.util.*;

public class Avaliacao {
	private int nota;
	private String comentario;
	private Date data;
	
	public Avaliacao(int nota, String comentario) throws Exception{
		if(nota < -2 || nota > 2) throw new Exception("Nota invalida!");
		if(comentario == null || comentario.trim().equals("") || comentario.length() > 140)
			throw new Exception("Comentario invalida!");
		
		this.data = new Date();
		this.nota = nota;
		this.comentario = comentario;
	}

	public int getNota() {
		return nota;
	}
	
	public String getComentario() {
		return comentario;
	}
	
	public String getData() {
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");  
		return formatador.format(data);
	}
	
	@Override
	public String toString() {
		return "Nota: "+nota+", comentario: "+comentario;
	}
	
	@Override
	public boolean equals(Object obj){
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(!(obj instanceof Avaliacao))
			return false;
		
		Avaliacao other = (Avaliacao) obj;
		
		if(this.nota != other.nota)
			return false;
		if(!(this.comentario.equals(other.comentario)))
			return false;
		return true;
	}
}
