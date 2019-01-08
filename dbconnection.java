package main;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		//Inizializza la classe passata come parametro, ovvero il driver JDBC per postgres
		Class.forName("org.postgresql.Driver");
		
		/*
		 Crea una connessione al database tramite il metodo statico getConnection della classe DriverManager
		 Servono tre parametri:
		 - URL del database così definita "jdbc:postgresql://host:port/database" dove:
		 	-host è il nome o indirizzo ip dell'host con il database (di solito localhost)
		 	-port è la porta del servizio postgresql (di default è la porta 5432)
		 	-database è il nome del database
		 -user è il nome utente (di solito postgres)
		 -password è la password (inserita in installazione)
		 */
		Connection dataBase = DriverManager.getConnection("jdbc:postgresql://host:port/database", "user", "password");
	
		/*
		 Si crea un oggetto di tipo statement collegato al database per eseguire le query
		 Per eseguire query parametriche si usa un oggetto di tipo PreparedStatement, passando una stringa
		 	con la query sql e mettendo dei ? al posto dei parametri
		 
		 */
		Statement state = dataBase.createStatement();
		PreparedStatement prepState = dataBase.prepareStatement("QUERY");
		
		/*
		 PER GLI OGGETTI DI TIPO STATEMENT
		 Per eseguire le query ci sono due metodi: executeQuery per istruzioni SELECT (che danno risultati)
		 e executeUpdate per INSERT, UPDATE e DELETE (che non danno risultati.
		 Entrambi i metodi prendono come paametro la query sql come stringa
		 Nel caso di executeQuery i risultati vanno memorizzati su un oggetto di tipo ResultSet
		 */
		ResultSet rs = state.executeQuery("QUERY"); //se select
		state.execute("QUERY"); //se update, delete o insert
		
		/*
		 PER GLI OGGETTI PREPAREDSTATEMENT 
		 Si devono impostare i parametri tramite il metodo setXXX(index, value) dove:
		 -index è il punto della query dove mettere il valore (se index=1 si mette value al posto del primo ? e così via)
		 -value è il valore da sostituire ed è di tipo XXX
		 -XXX è il tipo da sostituire al parametro (int, string, date, etc)
		 
		 ad esempio per mettere un int al posto del secondo ? si usa preparedStatement.setInt(2, int)
		 
		 Poi si usano executeQuery o executeUpdate come prima
		 */
		prepState.setXXX(index, value);
		ResultSet prepRs = prepState.executeQuery();
		prepState.executeUpdate();
		
		/*
		Per scorrere i risultati di un ResultSet si può usare il seguente codice.
		Per recuperare i dati si usa il metodo getXXX che prende come parametro una stringa 
		con il nome della colonna (attributo) del dato
		 */
		
		while(rs.next()){
			String stringa = rs.getString("NomeColonna"); //Si può usare anche getInt getBoolean etc
		}
		
	}
}
