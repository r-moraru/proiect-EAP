package repositories;

import entities.Diverse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiverseRepo {
    public DiverseRepo(boolean initDB) {
        if (initDB) {
            try (Connection con = DriverManager
                    .getConnection(DBConnection.getUrl(),
                            DBConnection.getUser(), DBConnection.getPass())) {

                String createTable = "create table if not exists diverse(" +
                        "   id int primary key auto_increment," +
                        "   pret double not null," +
                        "   cantitate int not null," +
                        "   nume_producator varchar(30) not null," +
                        "   nume_model varchar(30) not null," +
                        "   compatibil_cu_chitara boolean not null," +
                        "   compatibil_cu_claviatura boolean not null," +
                        "   contine_software boolean not null," +
                        "   contine_hardware boolean not null" +
                        ");";
                con.createStatement().executeUpdate(createTable);

                String deleteAll = "delete from diverse;";
                con.createStatement().executeUpdate(deleteAll);

                String insertStmt = "insert into diverse(pret,cantitate,nume_producator," +
                        "nume_model,compatibil_cu_chitara,compatibil_cu_claviatura," +
                        "contine_software,contine_hardware) values" +
                        "(54.99,123,'DAddario','Pro Arte',true,false,false,true);";
                con.createStatement().executeUpdate(insertStmt);
            }
            catch (SQLException e) {
                System.out.println("Unable to execute SQL statements");
            }
        }
    }

    public Diverse getById(Integer id) {
        Diverse diverse = null;
        try (Connection con = DriverManager
                .getConnection(DBConnection.getUrl(),
                        DBConnection.getUser(), DBConnection.getPass())) {

            String selectSql = "select * from claviatura where id=" + id + ";";
            Statement stmt = con.createStatement();

            ResultSet resultSet = stmt.executeQuery(selectSql);

            if (resultSet.next()) {
                diverse = new Diverse();
                diverse.setId((long) resultSet.getInt("id"));
                diverse.setPret(resultSet.getDouble("pret"));
                diverse.setCantitate(resultSet.getInt("cantitate"));
                diverse.setNumeProducator(resultSet.getString("nume_producator"));
                diverse.setNumeModel(resultSet.getString("nume_model"));
                diverse.setCompatibilCuChitara(resultSet.getBoolean("compatibil_cu_chitara"));
                diverse.setCompatibilCuClaviatura(resultSet.getBoolean("compatibil_cu_claviatura"));
                diverse.setContineSoftware(resultSet.getBoolean("contine_software"));
                diverse.setContineHardware(resultSet.getBoolean("contine_hardware"));
            }
        }
        catch (SQLException e) {
            System.out.println("Unable to execute SQL statements");
        }

        return diverse;
    }

    public List<Diverse> getAll() {
        List<Diverse> diverseList = new ArrayList<>();

        try (Connection con = DriverManager
                .getConnection(DBConnection.getUrl(),
                        DBConnection.getUser(), DBConnection.getPass())) {

            String selectSql = "select * from diverse;";
            ResultSet resultSet = con.createStatement().executeQuery(selectSql);

            while (resultSet.next()) {
                Diverse diverse = new Diverse();
                diverse.setId((long) resultSet.getInt("id"));
                diverse.setPret(resultSet.getDouble("pret"));
                diverse.setCantitate(resultSet.getInt("cantitate"));
                diverse.setNumeProducator(resultSet.getString("nume_producator"));
                diverse.setNumeModel(resultSet.getString("nume_model"));
                diverse.setCompatibilCuChitara(resultSet.getBoolean("compatibil_cu_chitara"));
                diverse.setCompatibilCuClaviatura(resultSet.getBoolean("compatibil_cu_claviatura"));
                diverse.setContineSoftware(resultSet.getBoolean("contine_software"));
                diverse.setContineHardware(resultSet.getBoolean("contine_hardware"));

                diverseList.add(diverse);
            }

        }
        catch (SQLException e) {
            System.out.println("Unable to execute SQL statements");
        }

        return diverseList;
    }

    public void insertDiverse(Diverse diverse) {
        try (Connection con = DriverManager
                .getConnection(DBConnection.getUrl(),
                        DBConnection.getUser(), DBConnection.getPass())) {

            String insertSql = "insert into diverse(pret,cantitate,nume_producator," +
                    "nume_model,compatibil_cu_chitara,compatibil_cu_claviatura," +
                    "contine_software,contine_hardware) values" +
                    "(?,?,?,?,?,?,?,?);";
            PreparedStatement prep = con.prepareStatement(insertSql);

            prep.setDouble(1, diverse.getPret());
            prep.setInt(2, diverse.getCantitate());
            prep.setString(3, diverse.getNumeProducator());
            prep.setString(4, diverse.getNumeModel());
            prep.setBoolean(5, diverse.getCompatibilCuChitara());
            prep.setBoolean(6, diverse.getCompatibilCuClaviatura());
            prep.setBoolean(7, diverse.getContineSoftware());
            prep.setBoolean(8, diverse.getContineSoftware());

            prep.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println("Unable to execute SQL statements");
        }
    }

    public void updateDiverse(Diverse diverse) {
        try (Connection con = DriverManager
                .getConnection(DBConnection.getUrl(),
                        DBConnection.getUser(), DBConnection.getPass())) {

            String updateSql = "update claviatura set" +
                    "   id=?," +
                    "   pret=?," +
                    "   cantitate=?," +
                    "   nume_producator=?," +
                    "   nume_model=?," +
                    "   compatibil_cu_chitara=?," +
                    "   compatibil_cu_claviatura=?," +
                    "   contine_software=?," +
                    "   contine_hardware=?" +
                    "   where id=?;";
            PreparedStatement prep = con.prepareStatement(updateSql);

            prep.setInt(1, Math.toIntExact(diverse.getId()));
            prep.setDouble(2, diverse.getPret());
            prep.setInt(3, diverse.getCantitate());
            prep.setString(4, diverse.getNumeProducator());
            prep.setString(5, diverse.getNumeModel());
            prep.setBoolean(6, diverse.getCompatibilCuChitara());
            prep.setBoolean(7, diverse.getCompatibilCuClaviatura());
            prep.setBoolean(8, diverse.getContineSoftware());
            prep.setBoolean(9, diverse.getContineSoftware());
            prep.setInt(10, Math.toIntExact(diverse.getId()));

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
