package tech.costa.luiz.reactive.resource;

import io.r2dbc.client.R2dbc;
import io.r2dbc.h2.CloseableConnectionFactory;
import io.r2dbc.h2.H2ConnectionFactory;
import reactor.core.publisher.Flux;

public class Users {


    public static void main(String[] args) {
//        Old school
//        Connection conn = null;
//        try {
//            conn = DriverManager.getConnection("jdbc:h2:mem:reactivedb", "sa", "");
//            conn.createStatement().execute("create table person(id varchar(50), first_name varchar(50), last_name varchar(50))");
//            conn.createStatement().execute("INSERT INTO person (id, first_name, last_name) VALUES ('1', 'A', 'B')");
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } finally {
//            try {
//                conn.close();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }

        CloseableConnectionFactory connectionFactory = H2ConnectionFactory.inMemory("reactivedb");

        R2dbc r2dbc = new R2dbc(connectionFactory);
        final Flux<String> database = r2dbc.inTransaction(handle ->
                handle.execute("create table person(id varchar(50), first_name varchar(50), last_name varchar(50));" +
                        "INSERT INTO person (id, first_name, last_name) VALUES ('1', 'A', 'B')"))
                .thenMany(r2dbc.inTransaction(handle ->
                        handle.select("SELECT * FROM person")
                                .mapResult(result ->
                                        // FIXME convert to Record
                                        result.map((row, rowMetadata) -> row.get("id", String.class) + "  - " +
                                                row.get("first_name", String.class) + "  - " +
                                                row.get("last_name", String.class)))));

        database.subscribe(System.out::println);


    }




}
