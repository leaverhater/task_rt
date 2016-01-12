package ru.rt.task;

import java.sql.*;

public class Main {
    public static final String CSV_PATH = "dictionary_content.csv";

    public static void main(String[] args) {
        BranchCSVReader branchCSVReader = new BranchCSVReader(CSV_PATH);
        for (Branch branch : branchCSVReader.getBranchesList()) {
//            System.out.println(branch.getId() + "\t" + branch.getParentId() + "\t" + branch.getName() + "\t" + branch.getCode());
        }

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:49161:xe", "vadya", "enjoybmstu");
            String query = "INSERT INTO branch_ref (id, parent_id, name, code) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            for (Branch branch : branchCSVReader.getBranchesList()) {
                ps.setString(1, branch.getId());

                ps.setString(2, branch.getParentId());
                ps.setString(3, branch.getName());
                ps.setString(4, branch.getCode());
                System.out.println("Setting code" + branch.getCode());

                ps.addBatch();
            }
            ps.executeBatch();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
