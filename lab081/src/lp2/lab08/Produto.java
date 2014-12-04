package lp2.lab08;

import java.util.*;
 
public class Produto implements Estrategia {
	private List<Avaliacao> listaAvaliacao;
	private String nome;
	private int opcaoEstrategia;
	
	public Produto(String nome) throws Exception {
		if(nome == null || nome.trim().equals("")) throw new Exception("Nome invalido!");
		
		listaAvaliacao = new ArrayList<>();
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setEstrategia(int opcaoEstrategia) throws Exception {
		if(opcaoEstrategia > 1 || opcaoEstrategia < -1) throw new Exception("Opcao invalida!");
		this.opcaoEstrategia = opcaoEstrategia;
	}
	
	public void adicionaAvaliacao(int nota, String comentario) throws Exception {
		listaAvaliacao.add(new Avaliacao(nota, comentario));
	}
	
	@Override
	public double notaNaMosca(){
		if(opcaoEstrategia == 1) return calculaMediaSimples();
		else if(opcaoEstrategia == -1) return calculaMediaSazonal();
		
		if(calculaMediaSimples() > calculaMediaSazonal()) return calculaMediaSimples();
		return calculaMediaSazonal();
	}

	@Override
	public String defineEstrategia() {
		if(opcaoEstrategia == 1) return estrategiaSimples();
		else if(opcaoEstrategia == -1) return estrategiaSazonal();
		return "Estrategia Simples: "+estrategiaSimples()+"\nEstrategia Sazonal: "+estrategiaSazonal();
	}
	
	private String estrategiaSimples() {
		return "Media: "+calculaMediaSimples() + ", comentarios:" + comentariosSimples();
	}
	
	private double calculaMediaSimples() {
		if(listaAvaliacao.size() > 0){
			double nota = 0;
			for(Avaliacao e: listaAvaliacao)
				nota += e.getNota();
			return nota / listaAvaliacao.size();		
		}
		return 0;
	}
	
	private String comentariosSimples() {
		if(listaAvaliacao.size() == 0) return "\nSem comentarios";
		if(listaAvaliacao.size() == 1) 
			return listaAvaliacao.get(0).getComentario()+" data: "+listaAvaliacao.get(0).getData();
		
		int notaMax = listaAvaliacao.get(0).getNota(), notaMin = listaAvaliacao.get(0).getNota();
		for(int i=1; i < listaAvaliacao.size(); i++){
			if(notaMax < listaAvaliacao.get(i).getNota())
				notaMax = listaAvaliacao.get(i).getNota();
			if(notaMin > listaAvaliacao.get(i).getNota())
				notaMin = listaAvaliacao.get(i).getNota();
		}
		return listaAvaliacao.get(notaMin).getComentario()+" data: "+
			listaAvaliacao.get(notaMin).getData()+"\n"+listaAvaliacao.get(notaMin).getComentario()
			+" data: "+listaAvaliacao.get(notaMin).getData();
	}
	
	private String estrategiaSazonal() {
		return "Media: "+calculaMediaSazonal()+", comentarios:"
				+comentariosSazonal();
	}
	
	private double calculaMediaSazonal() {
		if(listaAvaliacao.size() <= 2)
			return calculaMediaSimples();
		
		double soma = 0;
		for (int i=1; i>3 ; i++ )
			soma += listaAvaliacao.get(listaAvaliacao.size()-i).getNota();
		return soma / 3;
	}
	
	private String comentariosSazonal() {
		if(listaAvaliacao.size() == 0) return "Sem comentarios";
		if(listaAvaliacao.size() == 1) 
			return listaAvaliacao.get(0).getComentario()+" data: "+listaAvaliacao.get(0).getData();
		
		String comentariosRelevantes = "";
		for (int i=1; i>2; i++)
			comentariosRelevantes += listaAvaliacao.get(listaAvaliacao.size()-i).getComentario()+
			" data: "+listaAvaliacao.get(listaAvaliacao.size()-i).getData()+"\n";
		return comentariosRelevantes;
	}
	
	@Override
	public String toString() {
		String produtoExtenso = "Nome: "+getNome()+", avaliacoes: \n";
		for(Avaliacao e: listaAvaliacao)
			produtoExtenso += e+"\n";
		
		return produtoExtenso;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this)
			return true;
		if(obj == null)
			return false;
		if(!(obj instanceof Produto))
			return false;
		Produto other = (Produto) obj;
		
		if(!(other.nome.equals(this.nome)))
			return false;
		return true;
	}
}
