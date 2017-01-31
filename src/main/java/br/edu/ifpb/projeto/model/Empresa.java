package br.edu.ifpb.projeto.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Empresa extends Usuario {
	private Integer cnpj;
	private String endereco;
	private Integer numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String estado;
	private String cep;
	private String pontoDeReferencia;
	private String responsavel;
	private String cargoResponsavel;
	private String nomeDoContato;
	private String telefone;
	private String fax;
	private Boolean habilitada;

	@OneToMany(mappedBy="empresa", cascade=CascadeType.REMOVE, orphanRemoval=true)
	private List<Estagio> estagios = new ArrayList<Estagio>();

    @OneToMany(mappedBy="empresa", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Vaga> vagas = new ArrayList<Vaga>();

	public Empresa() {}

	public Empresa(String nome, String email) {
		super(nome, email);
	}

	public void addEstagio(Estagio estagio) {
		this.estagios.add(estagio);
	}

	public List<Estagio> getEstagios() {
		return this.estagios;
	}

	public void addVaga(Vaga vaga) {
		this.vagas.add(vaga);
	}

	public List<Vaga> getVagas() {
		return this.vagas;
	}

	public Integer getCnpj() {
		return cnpj;
	}
	public void setCnpj(Integer cnpj) {
		this.cnpj = cnpj;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getPontoDeReferencia() {
		return pontoDeReferencia;
	}
	public void setPontoDeReferencia(String pontoDeReferencia) {
		this.pontoDeReferencia = pontoDeReferencia;
	}
	public String getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}
	public String getCargoResponsavel() {
		return cargoResponsavel;
	}

	public void setCargoResponsavel(String cargoResponsavel) {
		this.cargoResponsavel = cargoResponsavel;
	}

	public String getNomeDoContato() {
		return nomeDoContato;
	}
	public void setNomeDoContato(String nomeDoContato) {
		this.nomeDoContato = nomeDoContato;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public Boolean getHabilitada() {
		return habilitada;
	}
	public void setHabilitada(Boolean habilitada) {
		this.habilitada = habilitada;
	}
}
