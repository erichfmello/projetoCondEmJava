package Controler;

import java.sql.SQLException;

import Model.ModelAllResidential;

public class ControlerLogin extends ControlerConection {
	private String cnpj;
	private int totalResidential;
	private String tipo;
	
	private String residentialName;
	
	// variaveis Model
	ModelAllResidential[] modelAllResidential;
	
	// Construtores
	public ControlerLogin() {
	}

	public ControlerLogin(String cnpj, String tipo) {
		super();
		this.cnpj = cnpj;
		this.tipo = tipo;
	}

	// getter and setter
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public int getTotalResidential() {
		return totalResidential;
	}

	public void setTotalResidential(int totalResidential) {
		this.totalResidential = totalResidential;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public ModelAllResidential[] getModelAllResidential() {
		return modelAllResidential;
	}

	public void setModelAllResidential(ModelAllResidential[] modelAllResidential) {
		this.modelAllResidential = modelAllResidential;
	}

	public String getResidentialName() {
		return residentialName;
	}

	public void setResidentialName(String residentialName) {
		this.residentialName = residentialName;
	}

	// Selects
	public void selectAllResidential(String login, String password) {
		try {
			conecting();
			
			conn = conecting();
			comandSql = conn.createStatement();
			rs = comandSql.executeQuery("select count(*) from login where nomeUsuario = '" + login + "' and senhaUsuario = '" + password + "'");
			
			if(rs.next()) {
				totalResidential = rs.getInt("count(*)");				
				rs = comandSql.executeQuery("select * from login where nomeUsuario = '" + login + "' and senhaUsuario = '" + password + "'");
				while(rs.next()) {
					cnpj = rs.getString("cnpj");
					tipo = rs.getString("tipo");
				}
			}
			
			
			closeConection(conn);
			
		} catch (SQLException e) {
			System.out.println("Erro no select: " + e);
		}
	}
	
	public void selectResidentialNames(String name, String  password) {
		int i = 0;
		
		try {
			conecting();
			
			conn = conecting();
			comandSql = conn.createStatement();
			
			rs = comandSql.executeQuery("select count(*) from login l inner join condominio c on l.cnpj = c.cnpj where nomeUsuario = '" + name + "' and senhaUsuario = '" + password + "'");
			if(rs.next()) {
				totalResidential = rs.getInt("count(*)");
			}
			
			modelAllResidential = new ModelAllResidential[totalResidential];
			
			rs = comandSql.executeQuery("select * from login l inner join condominio c on l.cnpj = c.cnpj where nomeUsuario = '" + name + "' and senhaUsuario = '" + password + "' order by c.nome");
			while(rs.next()) {
				residentialName = rs.getString("c.nome");
				cnpj = rs.getString("c.cnpj");
				
				modelAllResidential[i] = new ModelAllResidential(residentialName, cnpj);
				i++;
			}
			
			closeConection(conn);
			
		} catch (SQLException e) {
			System.out.println("Erro no select: " + e);
		}
	}
	
	public void insertNewUser(String userName, String userPassword, String cnpj) {
		try {
			conecting();
			
			conn = conecting();
			comandSql = conn.createStatement();
			comandSql.executeUpdate("insert into login (nomeUsuario, senhaUsuario, cnpj, tipo) value('" + userName + "', '" + userPassword + "', '" + cnpj + "', 'a')");
			
			closeConection(conn);
			
		} catch (SQLException e) {
			System.out.println("Erro no insert: " + e);
		}
	}
}
