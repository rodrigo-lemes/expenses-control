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
	private String descricao;
	@JsonFormat(shape=JsonFormat.Shape.NUMBER_FLOAT, pattern="0.00")
	private Double valor;
	private String codigousuario;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone="UTC")
	private Date data;
	private String categoria;

}
