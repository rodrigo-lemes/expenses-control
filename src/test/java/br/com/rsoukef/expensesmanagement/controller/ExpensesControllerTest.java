package br.com.rsoukef.expensesmanagement.controller;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.rsoukef.expensesmanagement.model.Expense;
import br.com.rsoukef.expensesmanagement.model.InsertResponse;
import lombok.Getter;
import lombok.Setter;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExpensesControllerTest {
	
	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

	@Autowired
	private ExpensesController controller;
	@Getter
	@Setter
	private List<Expense> expenses = new ArrayList<>();
	
	
	private static final String USER_1="1";
	private static final String USER_2="2";
	
	@Before
	public void prepareMock() throws ParseException {
		Expense mockExpenseA = new Expense();
		fmt.setTimeZone(TimeZone.getTimeZone("UTC"));
		
		mockExpenseA.setCodigousuario("1");
		mockExpenseA.setData(fmt.parse(fmt.format(new Date())));
		mockExpenseA.setDescricao("Gasto 1 - Usuario 1");
		mockExpenseA.setValor(1D);
		
		Expense mockExpenseB = new Expense();
		
		mockExpenseB.setCodigousuario("1");
		mockExpenseB.setData(fmt.parse(fmt.format(new Date())));
		mockExpenseB.setDescricao("Gasto 2 - Usuario 1");
		mockExpenseB.setValor(2D);
		
		Expense mockExpenseC = new Expense();
		
		mockExpenseC.setCodigousuario("2");
		mockExpenseC.setData(fmt.parse(fmt.format(new Date())));
		mockExpenseC.setDescricao("Gasto 1 - Usuario 2");
		mockExpenseC.setValor(3D);
		
		expenses.add(mockExpenseA);
		expenses.add(mockExpenseB);
		expenses.add(mockExpenseC);
	}
	
	@Test
	public void stage1_addExpensesSuccessTest() {
		InsertResponse response = controller.addExpenses(expenses);
		assertTrue(response.getInsertedItems()==3);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void stage2_getExpensesByDateRange() throws ParseException {
		fmt.setTimeZone(TimeZone.getTimeZone("UTC"));
		
		Date start = fmt.parse(fmt.format(new Date()));
		start.setDate(start.getDate()-1);
		
		Date end = fmt.parse(fmt.format(new Date()));
		end.setDate(end.getDate()+1);
		
		setExpenses(new ArrayList<>());
		
		getExpenses().addAll(controller.getExpensesByRange(USER_1, fmt.format(start), fmt.format(end)));
		assertTrue(expenses.size()==2);
		
		getExpenses().addAll(controller.getExpensesByRange(USER_2, fmt.format(start), fmt.format(end)));
		
		assertTrue(expenses.size()==3);
	}
	
	@Test
	public void stage3_updateCategorySuccessTest() throws ParseException {
		stage2_getExpensesByDateRange();
		List< ResponseEntity<String> > responses = new ArrayList<>();
		getExpenses().forEach(expense->{
			expense.setCategoria("Mock_Categoria");
			responses.add(controller.updateCategory(expense.getId(),expense));	
		});
		
		assertTrue(responses.size()==3);
	}

}
