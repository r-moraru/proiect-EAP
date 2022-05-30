package repositories;

import entities.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// id nume prenume adresaMail
public class ClientRepo {
    public ClientRepo(boolean initDB) {
        if (initDB) {
            try (Connection con = DriverManager
                    .getConnection(DBConnection.getUrl(),
                            DBConnection.getUser(),DBConnection.getPass())) {

                String createTable = "create table if not exists client(" +
                        "   id int primary key auto_increment," +
                        "   nume varchar(30) not null," +
                        "   prenume varchar(30) not null," +
                        "   adresa_mail varchar(64) not null" +
                        ");";
                con.createStatement().executeUpdate(createTable);

                String deleteAll = "delete from client;";
                con.createStatement().executeUpdate(deleteAll);

                String insertStmt = "insert into client(nume,prenume,adresa_mail)" +
                        " values ('Dorian','Grey','prince.charming@swagmail.com');";
                con.createStatement().executeUpdate(insertStmt);

                insertStmt = "insert into client(nume,prenume,adresa_mail)" +
                        " values ('Wes','Anderson','wes.anderson@oldmail.com');";
                con.createStatement().executeUpdate(insertStmt);
            }
            catch (SQLException e) {
                System.out.println("Unable to execute SQL statements");
            }
        }
    }

    public List<Client> getAll() {
        List<Client> clienti = new ArrayList<>();

        try (Connection con = DriverManager
                .getConnection(DBConnection.getUrl(),
                        DBConnection.getUser(),DBConnection.getPass())) {

            String selectSql = "select * from client;";
            ResultSet resultSet = con.createStatement().executeQuery(selectSql);

            while (resultSet.next()) {
                Client client = new Client();
                client.setId((long) resultSet.getInt("id"));
                client.setNume(resultSet.getString("nume"));
                client.setPrenume(resultSet.getString("prenume"));
                client.setAdresaMail(resultSet.getString("adresa_mail"));

                clienti.add(client);
            }
        }
        catch (SQLException e) {
            System.out.println("Unable to execute SQL statements");
        }

        return clienti;
    }

    public Client getById(Integer id) {
        Client client = null;

        try (Connection con = DriverManager
                .getConnection(DBConnection.getUrl(),
                        DBConnection.getUser(),DBConnection.getPass())) {

            String selectSql = "select * from client where id=" + id + ";";
            ResultSet resultSet = con.createStatement().executeQuery(selectSql);

            if (resultSet.next()) {
                client = new Client();
                client.setId((long) resultSet.getInt("id"));
                client.setNume(resultSet.getString("nume"));
                client.setPrenume(resultSet.getString("prenume"));
                client.setAdresaMail(resultSet.getString("adresa_mail"));
            }
        }
        catch (SQLException e) {
            System.out.println("Unable to execute SQL statements");
        }

        return client;
    }

    public void insertClient(Client client) {
        try (Connection con = DriverManager
                .getConnection(DBConnection.getUrl(),
                        DBConnection.getUser(), DBConnection.getPass())) {

            String insertSql = "insert into client(nume,prenume,adresa_mail) " +
                    " values(?,?,?);";
            PreparedStatement prep = con.prepareStatement(insertSql);
            prep.setString(1,client.getNume());
            prep.setString(2,client.getPrenume());
            prep.setString(3,client.getPrenume());

            prep.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println("Unable to execute SQL statements");
        }
    }

    public void updateClient(Client client) {
        try (Connection con = DriverManager
                .getConnection(DBConnection.getUrl(),
                        DBConnection.getUser(), DBConnection.getPass())) {

            String updateSql = "update client set" +
                    "   id=?," +
                    "   nume=?," +
                    "   prenume=?," +
                    "   adresaMail=?" +
                    "   where id=?;";

            PreparedStatement prep = con.prepareStatement(updateSql);

            prep.setInt(1, Math.toIntExact(client.getId()));
            prep.setString(2,client.getNume());
            prep.setString(3,client.getPrenume());
            prep.setString(4,client.getPrenume());
            prep.setInt(5, Math.toIntExact(client.getId()));

            prep.executeUpdate();

        }
        catch (SQLException e) {
            System.out.println("Unable to execute SQL statements");
        }
    }

    public void deleteById(Integer id) {
        try (Connection con = DriverManager
                .getConnection(DBConnection.getUrl(),
                        DBConnection.getUser(), DBConnection.getPass())) {

            String deleteSql = "delete from client where id=?;";
            PreparedStatement prep = con.prepareStatement(deleteSql);

            prep.setInt(1, id);

            prep.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println("Unable to execute SQL statements");
        }
    }
}
