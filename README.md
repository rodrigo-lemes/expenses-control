Usage:

java -jar target/expenses-management-0.0.1-SNAPSHOT.jar

Add expenses:
================

HTTP POST to http://127.0.0.1:8080/addExpenses


RequestBody Json Example:
Usage: {"descricao": <String>, "valor": <american double> , "codigousuario": <number>, "data": <UTC Date Time>}
[{ "descricao": "Em Março", "valor": "1", "codigousuario": "1","data":"2012-04-23T18:25:43.511Z"},
 { "descricao": "Em Agosto", "valor": "2", "codigousuario": "1","data":"2018-09-23T18:25:43.511Z"}]

List expenses by RANGE:
=========================

HTTP GET to http://127.0.0.1:8080/getExpensesByRange/<USER_ID>/<DATE RANGE START - dd-MM-yyyy HH:mm:ss>/<DATE RANGE FINISH - dd-MM-yyyy HH:mm:ss>

E.g. (Search by a specific Date):
http://127.0.0.1:8080/getExpensesByRange/1/01-03-2019 00:00:00/21-03-2019 23:59:59

E.g.2 (Search by Date + 5 seconds:
http://127.0.0.1:8080/getExpensesByRange/1/01-03-2019 00:01:01/21-03-2019 01:01:06

Add a category:
=================

HTTP PATCH to http://127.0.0.1:8080/updateExpenseCategory/<EXPENSE_ID>

E.g.: http://127.0.0.1:8080/updateExpenseCategory/5c873caaa167ad1fe5ec9315

RequestBody Json Example:

{"categoria":"UMA NOVA CAT"}
