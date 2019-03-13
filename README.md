Usage:

java -jar target/expenses-management-0.0.1-SNAPSHOT.jar

Add expenses:
================

HTTP POST to http://127.0.0.1:8080/addExpenses


RequestBody Json Example:
Usage: [{"descricao": <String>, "valor": <american double> , "codigousuario": <number>, "data": <UTC Date Time>}]

E.g. (List):

[{ "descricao": "Em Março", "valor": "1", "codigousuario": "1","data":"2019-03-13T00:27:59.752+0000"},
 { "descricao": "Em Agosto", "valor": "2", "codigousuario": "1","data":"2019-05-13T00:27:59.752+0000"}]
 
List expenses by RANGE:
=========================

HTTP GET to http://127.0.0.1:8080/getExpensesByRange/<USER_ID>/<DATE RANGE START <UTC Date Time>>/<DATE RANGE FINISH <UTC Date Time>>

E.g. (Search by a range Date):
http://127.0.0.1:8080/getExpensesByRange/1/2019-03-12T00:27:59.752+0000/2019-03-14T00:27:59.752+0000

E.g. (Search by a specific Date):
http://127.0.0.1:8080/getExpensesByRange/1/2019-03-12T00:27:59.752+0000/2019-03-12T00:27:59.752+0000



Add a category:
=================

HTTP PATCH to http://127.0.0.1:8080/updateExpenseCategory/<EXPENSE_ID>

E.g.: http://127.0.0.1:8080/updateExpenseCategory/5c873caaa167ad1fe5ec9315

RequestBody Json Example:

Usage: {"categoria":"UMA NOVA CAT"}
