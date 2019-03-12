package br.com.rsoukef.expensesmanagement.controller;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import br.com.rsoukef.expensesmanagement.model.Expense;
import br.com.rsoukef.expensesmanagement.model.InsertResponse;

public class ExpensesControllerTest {

	@Autowired
	private ExpensesController controller;
	
	private List<Expense> expenses = new ArrayList<>();
	
	private static final String USER_1="1";
	private static final String USER_2="2";
	
	@Before
	public void prepareMock() {
		Expense mockExpenseA = new Expense();
		
		mockExpenseA.setCodigousuario("1");
		mockExpenseA.setData(new Date());
		mockExpenseA.setDescricao("Gasto 1 - Usuario 1");
		mockExpenseA.setValor(1D);
		
		Expense mockExpenseB = new Expense();
		
		mockExpenseB.setCodigousuario("1");
		mockExpenseB.setData(new Date());
		mockExpenseB.setDescricao("Gasto 2 - Usuario 1");
		mockExpenseB.setValor(2D);
		
		Expense mockExpenseC = new Expense();
		
		mockExpenseC.setCodigousuario("2");
		mockExpenseC.setData(new Date());
		mockExpenseC.setDescricao("Gasto 1 - Usuario 2");
		mockExpenseC.setValor(3D);
		
		expenses.add(mockExpenseA);
		expenses.add(mockExpenseB);
		expenses.add(mockExpenseC);
	}
	
	@Test
	public void addExpensesSuccessTest() {
		InsertResponse response = controller.addExpenses(expenses);
		assertTrue(response.getInsertedItems()==3);
	}
	
	@Test
	public void getExpensesByDateRange() throws ParseException {
		
		Date start = new Date();
		start = start.from((LocalDateTime.from(start.toInstant()).minusDays(1)).toInstant(ZoneOffset.UTC));
		
		Date end = new Date();
		end = end.from((LocalDateTime.from(start.toInstant()).plusDays(1)).toInstant(ZoneOffset.UTC));
		
		expenses = new ArrayList<>();
		
		expenses.addAll(controller.getExpensesByRange(USER_1, start.toString(), end.toString()));
		
		expenses.addAll(controller.getExpensesByRange(USER_2, start.toString(), end.toString()));
		
		assertTrue(expenses.size()==3);
	}
	
	@Test
	public void updateCategorySuccessTest() {
		List< ResponseEntity<String> > responses = new ArrayList<>();
		expenses.forEach(expense->{
			expense.setCategoria("Mock_Categoria");
			responses.add(controller.updateCategory(expense.getId(),expense));	
		});
		
		assertTrue(responses.size()==3);
	}

}
