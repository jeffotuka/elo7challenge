## elo7challenge

An experimental Java application that saves and queries records, as part of a programming challenge. 

The current implementation is console-based and has few dependencies - only HSQLDB is required for basic execution. It is intended to be used as a core for more elaborate implementations.

Requisites of the challenge are as follows (in Portuguese):

```
Desenvolver um sistema de agendamento de transferências financeiras.
 
1) O usuário deve poder agendar uma transferência financeira com as seguintes informações:
 
  - Conta de origem (padrão XXXXX-X)
	- Conta de destino  (padrão XXXXX-X)
	- Valor da transferência
	- Taxa (a ser calculada)
	- Data do agendamento
	- Tipo (A, B, C, D)
		A: Operações do tipo A tem uma taxa de $2 mais 3% do valor da trasferência
		B: Operações do tipo B tem uma taxa de $10 para pedidos com agendamento até 30 dias da data de cadastro e $8 para os demais
		C: Operações do tipo C tem uma taxa regressiva conforme a data de agendamento:
			 	maior que 30 dias da data de cadastro - 1.2%
				até 30 dias da data de cadastro - 2.1%
				até 25 dias da data de cadastro - 4.3%
				até 20 dias da data de cadastro - 5.4%
				até 15 dias da data de cadastro - 6.7%
				até 10 dias da data de cadastro - 7.4%
				até  5 dias da data de cadastro - 8.3%
		D: Operações do tipo D tem a taxa igual a A, B ou C dependendo do valor da transferência.
			Valores até $25.000 seguem a taxação tipo A
			Valores de $25.001 até $120000 seguem a taxação tipo B
			Valores maiores que $120000 seguem a taxação tipo C
 
2) O usuário deve poder ver todos os agendamentos cadastrados.
 
Nota: A persistência não precisa ser em banco de dados.
 
Fique à vontade para criar em cima dessa idéia!
```

### Compiling and running

Java 6 or higher is required.


To compile:
```
javac -cp "src/main/java:lib/*" -d bin src/main/java/elo7challenge/transfersystem/main/TransferSystem.java
```

First time setup to generate internal database:
```
java -cp "bin:lib/*" elo7challenge.transfersystem.main.TransferSystem --setup
```

Running "list" command to see all records:
```
java -cp "bin:lib/*" elo7challenge.transfersystem.main.TransferSystem --list
```

Running "input" command to interactively register a new record:
```
java -cp "bin:lib/*" elo7challenge.transfersystem.main.TransferSystem --input
```

If you prefer, the project is configured for Eclipse, it can be used to compile and run the application.
