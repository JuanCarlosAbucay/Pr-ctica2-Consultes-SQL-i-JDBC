import java.sql.*;

public class SQL {
    public static void consultas(){
        Interaction interaction = new Interaction();
        int num = interaction.menu();
        switch (num) {
            case 1:
                estrens();
                break;
            case 2:
                directors();
                break;
            case 3:
                insert();
                break;
        }
    }

    public static void estrens(){
        String query;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/cinema?&serverTimezone=UTC";
        String user = "root";
        String password = "admin";

        try {
            Class.forName(driver);
            query = "SELECT * FROM Films WHERE DataEstrena BETWEEN '1978-1-1' AND '2006-1-1'";
            try(Connection con = DriverManager.getConnection(url, user, password);
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query)) {
                int colNum = getColumnNames(rs);
                if (colNum > 0) {
                    while (rs.next()) {
                        for (int i = 0; i < colNum; i++) {
                            if (i + 1 == colNum) {
                                System.out.println(rs.getString(i + 1));
                            } else {
                                System.out.print(rs.getString(i + 1) + ", ");
                            } //endif
                        } //endfor
                    } //endwhile
                } //endif
            }//endif
        } catch (SQLException e) {
            System.out.println(e);
        }//
        catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void directors(){
        Interaction interaction = new Interaction();

        String query;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/cinema?&serverTimezone=UTC";
        String user = "root";
        String password = "admin";

        String director = interaction.director();
        try {
            Class.forName(driver);
            query = "SELECT * FROM Films " +
                    "INNER JOIN film_peli ON Films.idFilm = film_peli.idFilm " +
                    "INNER JOIN director ON film_peli.idDirector = director.idDirector " +
                    "WHERE Nom LIKE '" + director + "' ;";
            try(Connection con = DriverManager.getConnection(url, user, password);
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query)) {
                int colNum = getColumnNames(rs);
                if (colNum > 0) {
                    while (rs.next()) {
                        for (int i = 0; i < colNum; i++) {
                            if (i + 1 == colNum) {
                                System.out.println(rs.getString(i + 1));
                            } else {
                                System.out.print(rs.getString(i + 1) + ", ");
                            } //endif
                        } //endfor
                    } //endwhile
                } //endif
            }//endif
        } catch (SQLException e) {
            System.out.println(e);
        }//
        catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void insert(){
        String query;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/cinema?&serverTimezone=UTC";
        String user = "root";
        String password = "admin";

        Interaction interaction = new Interaction();

        interaction.film();
        try {
            Class.forName(driver);
            query = "INSERT INTO films(Titol, DataEstrena, Pais) values " +
                    "('" + interaction.getTitol() + "', '" + interaction.getData() + "', '" + interaction.getPais() + "');";
            try (Connection con = DriverManager.getConnection(url, user, password);
                 Statement st = con.createStatement();
            ){
                st.executeUpdate(query);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        consultas();
    }

    public static int getColumnNames(ResultSet rs) throws SQLException {
        int numberOfColumns = 0;
        if (rs != null) {   //create an object based on the Metadata of the result set
            ResultSetMetaData rsMetaData = rs.getMetaData();
            numberOfColumns = rsMetaData.getColumnCount();   //Use the getColumn method to get the number of columns returned
            for (int i = 1; i < numberOfColumns + 1; i++) {  //get and print the column names, column indexes start from 1
                String columnName = rsMetaData.getColumnName(i);
                System.out.print(columnName + ", ");
            }
        }
        System.out.println();
        return numberOfColumns;
    }
}