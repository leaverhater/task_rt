package ru.rt.task;

import java.io.IOException;
import java.sql.*;

public class Main {
    public static final String CSV_PATH = "dictionary_content.csv";

    public static void main(String[] args) {
        BranchCSVReader branchCSVReader = new BranchCSVReader(CSV_PATH);
        try {
            branchCSVReader.readBranches();
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:49161:xe", "vadya", "password");
            String query = "INSERT INTO branch_ref (id, parent_id, name, code) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            System.out.println("Start inserting");
            for (Branch branch : branchCSVReader.getBranchesList()) {
                ps.setString(1, branch.getId());
                ps.setString(2, branch.getParentId());
                ps.setString(3, branch.getName());
                ps.setString(4, branch.getCode());

                // Catch constraint violations
                try {
                    ps.executeUpdate();
                    System.out.println("Inserted branch with id " + branch.getId());
                } catch (SQLIntegrityConstraintViolationException ex) {
                    System.out.println("Cannot insert branch with id " + branch.getId() + ":\n" + ex.getMessage());
                }
            }
            con.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
