package br.com.rsoukef.expensesmanagement.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.rsoukef.expensesmanagement.model.Expense;

public interface ExpensesRepository extends MongoRepository<Expense, Long> {

	public List<Expense> findByCodigousuarioAndDataBetween(String idUser,Date from, Date to);
	public List<Expense> findByCodigousuario(String codigousuario);
	public Expense findById(String id);
	
}
