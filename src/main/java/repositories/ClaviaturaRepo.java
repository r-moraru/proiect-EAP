package repositories;

import entities.Claviatura;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClaviaturaRepo {
    public ClaviaturaRepo(boolean initDB) {
        if (initDB) {
            try (Connection con = DriverManager
                    .getConnection(DBConnection.getUrl(),
                            DBConnection.getUser(), DBConnection.getPass())) {

                String createTable = "create table if not exists claviatura(" +
                        "   id int primary key auto_increment," +
                        "   pret double not null," +
                        "   cantitate int not null," +
                        "   nume_producator varchar(30) not null," +
                        "   nume_model varchar(30) not null," +
                        "   numar_clape int not null," +
                        "   tip varchar(30) not null" +
                        ");";
                con.createStatement().executeUpdate(createTable);

                String deleteAll = "delete from claviatura;";
                con.createStatement().executeUpdate(deleteAll);

                // 3,2499.99,7,Arturia,KeyLab3,88,MIDI_CONTROLLER
                String insertStmt = "insert into claviatura(pret,cantitate,nume_producator," +
                        "nume_model,numar_clape,tip) values" +
                        "(2499.99,7,'Arturia','KeyLab3',88,'MIDI_CONTROLLER');";
                con.createStatement().executeUpdate(insertStmt);
            }
            catch (SQLException e) {
                System.out.println("Unable to execute SQL statement");
                e.printStackTrace();
            }
        }
    }

    public Claviatura getById(Integer id) {
        Claviatura claviatura = null;

        try (Connection con = DriverManager
                .getConnection(DBConnection.getUrl(),
                        DBConnection.getUser(), DBConnection.getPass())) {

            String selectSql = "select * from claviatura where id=" + id + ";";
            Statement stmt = con.createStatement();

            ResultSet resultSet = stmt.executeQuery(selectSql);

            if (resultSet.next()) {
                claviatura = new Claviatura();
                claviatura.setId((long) resultSet.getInt("id"));
                claviatura.setPret(resultSet.getDouble("pret"));
                claviatura.setCantitate(resultSet.getInt("cantitate"));
                claviatura.setNumeProducator(resultSet.getString("nume_producator"));
                claviatura.setNumeModel(resultSet.getString("nume_model"));
                claviatura.setNumarClape(resultSet.getInt("numar_clape"));
                claviatura.setTip(Claviatura.Tip.valueOf(resultSet.getString("tip")));
            }
        }
        catch (SQLException e) {
            System.out.println("Unable to execute SQL statements");
        }

        return claviatura;
    }

    public List<Claviatura> getAll() {
        List<Claviatura> claviaturi = new ArrayList<>();

        try (Connection con = DriverManager
                .getConnection(DBConnection.getUrl(), DBConnection.getUser(),
                        DBConnection.getPass())) {

            String selectSql = "select * from claviatura;";
            ResultSet resultSet = con.createStatement().executeQuery(selectSql);

            while (resultSet.next()) {
                Claviatura claviatura = new Claviatura();
                claviatura.setId((long) resultSet.getInt("id"));
                claviatura.setPret(resultSet.getDouble("pret"));
                claviatura.setCantitate(resultSet.getInt("cantitate"));
                claviatura.setNumeProducator(resultSet.getString("nume_producator"));
                claviatura.setNumeModel(resultSet.getString("nume_model"));
                claviatura.setNumarClape(resultSet.getInt("numar_clape"));
                claviatura.setTip(Claviatura.Tip.valueOf(resultSet.getString("tip")));

                claviaturi.add(claviatura);
            }
        }
        catch (SQLException e) {
            System.out.println("Unable to execute SQL statements");
        }

        return claviaturi;
    }

    public void insertClaviatura(Claviatura claviatura) {
        try (Connection con = DriverManager
                .getConnection(DBConnection.getUrl(),
                        DBConnection.getUser(), DBConnection.getPass())) {

            String insertSql = "insert into claviatura(pret,cantitate,nume_producator," +
                    "nume_model,numar_clape,tip) values" +
                    "(?,?,?,?,?,?);";
            PreparedStatement prep = con.prepareStatement(insertSql);

            prep.setDouble(1, claviatura.getPret());
            prep.setInt(2, claviatura.getCantitate());
            prep.setString(3, claviatura.getNumeProducator());
            prep.setString(4,claviatura.getNumeModel());
            prep.setInt(5, claviatura.getNumarClape());
            prep.setString(6, claviatura.getTip().toString());

            prep.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println("Unable to run SQL statements");
        }
    }

    public void updateClaviatura(Claviatura claviatura) {
        try (Connection con = DriverManager
                .getConnection(DBConnection.getUrl(),
                        DBConnection.getUser(), DBConnection.getPass())) {

            String updateSql = "update claviatura set" +
                    "   id=?," +
                    "   pret=?," +
                    "   cantitate=?," +
                    "   nume_producator=?," +
                    "   nume_model=?," +
                    "   numar_clape=?," +
                    "   tip=?" +
                    "   where id=?;";

            PreparedStatement prep = con.prepareStatement(updateSql);

            prep.setInt(1, Math.toIntExact(claviatura.getId()));
            prep.setDouble(2, claviatura.getPret());
            prep.setInt(3, claviatura.getCantitate());
            prep.setString(4, claviatura.getNumeProducator());
            prep.setString(5, claviatura.getNumeModel());
            prep.setInt(6, claviatura.getNumarClape());
            prep.setString(7, claviatura.getTip().toString());
            prep.setInt(8, Math.toIntExact(claviatura.getId()));

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

            String deleteSql = "delete from chitara where id=?;";
            PreparedStatement prep = con.prepareStatement(deleteSql);

            prep.setInt(1, id);

            prep.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println("Unable to execute SQL statements");
        }
    }
}
