package br.com.rsoukef.expensesmanagement.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.rsoukef.expensesmanagement.model.Expense;
import br.com.rsoukef.expensesmanagement.model.InsertResponse;
import br.com.rsoukef.expensesmanagement.repository.ExpensesRepository;

@RestController
public class ExpensesController {

	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

	@Autowired
	private ExpensesRepository repository;

	@PostMapping(value = "/addExpenses", consumes = "application/json")
	public InsertResponse addExpenses(@RequestBody List<Expense> expenses) {

		repository.saveAll(expenses);
		InsertResponse response = new InsertResponse();
		response.setInsertedItems(expenses.size());
		return response;
	}

	@ResponseBody
	@GetMapping(value = "/getExpensesByRange/{codigoUsuario}/{dateBegin}/{dateFinish}")
	public List<Expense> getExpensesByRange(@PathVariable String codigoUsuario, @PathVariable String dateBegin,
			@PathVariable String dateFinish) throws ParseException {
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		return repository.findByCodigousuarioAndDataBetween(codigoUsuario, formatter.parse(dateBegin),
				formatter.parse(dateFinish));
	}

	@PatchMapping(value = "/updateExpenseCategory/{id}", consumes = "application/json")
	public ResponseEntity<String> updateCategory(@PathVariable String id, @RequestBody Expense categoryData) {

		Expense expenseToPatch = repository.findById(id);

		expenseToPatch.setCategoria(categoryData.getCategoria());

		repository.save(expenseToPatch);

		return new ResponseEntity<>("Added Category to Expense with ID: " + id, HttpStatus.OK);

	}

}
