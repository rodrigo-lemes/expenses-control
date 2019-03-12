package br.com.rsoukef.expensesmanagement.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Document(collection="expenses")
public class Expense {

	@Id 
	private String id;
	private String description;
	private Double value;
	private String codigousuario;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss", timezone="America/Sao_Paulo")
	private Date data;
	private String categoria;

}
