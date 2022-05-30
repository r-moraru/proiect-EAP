package repositories;

import entities.Chitara;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChitaraRepo {
    public ChitaraRepo(boolean initDB) {
        // constructor care creeaza tabelul chitara si il populeaza
        if (initDB) {
            try (Connection con = DriverManager
                    .getConnection(DBConnection.getUrl(),
                            DBConnection.getUser(), DBConnection.getPass())) {

                String createTable = "CREATE TABLE IF NOT EXISTS chitara(" +
                        "   id int primary key auto_increment," +
                        "   pret double not null," +
                        "   cantitate int not null," +
                        "   nume_producator varchar(30) not null," +
                        "   nume_model varchar(30) not null," +
                        "   numar_corzi int not null," +
                        "   tip varchar(30) not null," +
                        "   lemn_fretboard varchar(30) not null," +
                        "   lemn_body varchar(30) not null," +
                        "   nr_freturi int not null," +
                        "   pentru_stangaci boolean not null" +
                        "   );";
                con.createStatement().executeUpdate(createTable);

                String deleteAll = "delete from chitara;";
                con.createStatement().executeUpdate(deleteAll);

                String insertStmt = "insert into chitara(pret,cantitate,nume_producator," +
                        "nume_model,numar_corzi,tip,lemn_fretboard," +
                        "lemn_body,nr_freturi,pentru_stangaci) values" +
                        "(4199.99,3,'Fender','Stratocaster',6," +
                    "'ELECTRICA','ROSEWOOD','MAHOGANY',22,false);";
                con.createStatement().executeUpdate(insertStmt);

                insertStmt = "insert into chitara(pret,cantitate,nume_producator," +
                        "nume_model,numar_corzi,tip,lemn_fretboard," +
                        "lemn_body,nr_freturi,pentru_stangaci) values" +
                        "(599.99,34,'Fender','Squire',6," +
                        "'ELECTRICA','ROSEWOOD','MAHOGANY',21,false);";
                con.createStatement().executeUpdate(insertStmt);
            }
            catch (SQLException e) {
                System.out.println("Error running SQL statements");
            }
        }
    }

    public Chitara getById(Integer id) {
        Chitara chitara = null;

        try (Connection con = DriverManager
                .getConnection(DBConnection.getUrl(),
                        DBConnection.getUser(), DBConnection.getPass())) {

            String selectSql = "select * from chitara where id=" + id + ";";
            Statement stmt = con.createStatement();

            ResultSet resultSet = stmt.executeQuery(selectSql);

            if (resultSet.next()) {
                chitara = new Chitara();
                chitara.setId((long) resultSet.getInt("id"));
                chitara.setPret(resultSet.getDouble("pret"));
                chitara.setCantitate(resultSet.getInt("cantitate"));
                chitara.setNumeProducator(resultSet.getString("nume_producator"));
                chitara.setNumeModel(resultSet.getString("nume_model"));
                chitara.setNumarCorzi(resultSet.getInt("numar_corzi"));
                chitara.setTip(Chitara.Tip.valueOf(
                        resultSet.getString("tip")));
                chitara.setLemnFretboard(Chitara.Lemn.valueOf(
                        resultSet.getString("lemn_fretboard")));
                chitara.setLemnBody(Chitara.Lemn.valueOf(
                        resultSet.getString("lemn_body")));
                chitara.setNrFreturi(resultSet.getInt("nr_freturi"));
                chitara.setPentruStangaci(resultSet.getBoolean("pentru_stangaci"));
            }
        }
        catch (SQLException e) {
            System.out.println("Unable to execute SQL statements");
        }

        return chitara;
    }

    public List<Chitara> getAll() {
        List<Chitara> chitari = new ArrayList<>();

        try (Connection con = DriverManager
                .getConnection(DBConnection.getUrl(),
                        DBConnection.getUser(), DBConnection.getPass())) {

            String selectSql = "select * from chitara;";
            ResultSet resultSet = con.createStatement().executeQuery(selectSql);

            while (resultSet.next()) {
                Chitara chitara = new Chitara();
                chitara.setId((long) resultSet.getInt("id"));
                chitara.setPret(resultSet.getDouble("pret"));
                chitara.setCantitate(resultSet.getInt("cantitate"));
                chitara.setNumeProducator(resultSet.getString("nume_producator"));
                chitara.setNumeModel(resultSet.getString("nume_model"));
                chitara.setNumarCorzi(resultSet.getInt("numar_corzi"));
                chitara.setTip(Chitara.Tip.valueOf(
                        resultSet.getString("tip")));
                chitara.setLemnFretboard(Chitara.Lemn.valueOf(
                        resultSet.getString("lemn_fretboard")));
                chitara.setLemnBody(Chitara.Lemn.valueOf(
                        resultSet.getString("lemn_body")));
                chitara.setNrFreturi(resultSet.getInt("nr_freturi"));
                chitara.setPentruStangaci(resultSet.getBoolean("pentru_stangaci"));

                chitari.add(chitara);
            }
        }
        catch (SQLException e) {
            System.out.println("Error running SQL statements");
        }

        return chitari;
    }

    public void insertChitara(Chitara chitara) {
        try (Connection con = DriverManager
                .getConnection(DBConnection.getUrl(),
                        DBConnection.getUser(), DBConnection.getPass())) {

            String insertSql = "insert into chitara(pret,cantitate,nume_producator," +
                    "nume_model,numar_corzi,tip,lemn_fretboard," +
                    "lemn_body,nr_freturi,pentru_stangaci) values" +
                    "(?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement prep = con.prepareStatement(insertSql);

            prep.setDouble(1, chitara.getPret());
            prep.setInt(2, chitara.getCantitate());
            prep.setString(3, chitara.getNumeProducator());
            prep.setString(4, chitara.getNumeModel());
            prep.setInt(5, chitara.getNumarCorzi());
            prep.setString(6, chitara.getTip().toString());
            prep.setString(7, chitara.getLemnFretboard().toString());
            prep.setString(8, chitara.getLemnBody().toString());
            prep.setInt(9, chitara.getNrFreturi());
            prep.setBoolean(10, chitara.getPentruStangaci());

            prep.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println("Error running SQL statements");
        }
    }

    public void updateChitara(Chitara chitara) {
        try (Connection con = DriverManager
                .getConnection(DBConnection.getUrl(),
                        DBConnection.getUser(), DBConnection.getPass())) {

            String updateSql = "update chitara set" +
                    "   id=?," +
                    "   pret=?," +
                    "   cantitate=?," +
                    "   nume_producator=?," +
                    "   nume_model=?," +
                    "   numar_corzi=?," +
                    "   tip=?," +
                    "   lemn_fretboard=?," +
                    "   lemn_body=?," +
                    "   nr_freturi=?," +
                    "   pentru_stangaci=?" +
                    "   where id=?;";

            PreparedStatement prep = con.prepareStatement(updateSql);

            prep.setInt(1, Math.toIntExact(chitara.getId()));
            prep.setDouble(2, chitara.getPret());
            prep.setInt(3, chitara.getCantitate());
            prep.setString(4, chitara.getNumeProducator());
            prep.setString(5, chitara.getNumeModel());
            prep.setInt(6, chitara.getNumarCorzi());
            prep.setString(7, chitara.getTip().toString());
            prep.setString(8, chitara.getLemnFretboard().toString());
            prep.setString(9, chitara.getLemnBody().toString());
            prep.setInt(10, chitara.getNrFreturi());
            prep.setBoolean(11, chitara.getPentruStangaci());
            prep.setInt(12, Math.toIntExact(chitara.getId()));

            prep.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println("Unable to execute SQL statements");
        }
    }

    public void deleteById(Integer id) {
        try (Connection con = DriverManager
                .getConnection(DBConnection.getUrl(),
                        DBConnection.getUser(),DBConnection.getPass())) {

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
