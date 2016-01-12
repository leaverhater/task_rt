package ru.rt.task;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BranchCSVReader {
    public static final String SEP = ",";

    private String csvPath;
    private List<Branch> branchesList = new ArrayList<>();

    public BranchCSVReader(String csvPath) {
        this.csvPath = csvPath;
        readBranches();
    }

    private void readBranches() {
        BufferedReader br = null;

        try {
            String line;
            br = new BufferedReader(new FileReader(csvPath));
            while ((line = br.readLine()) != null) {
                String[] values = line.split(SEP);
                Branch branch = new Branch();
                branch.setId(values[0]);
                branch.setParentId(values[1]);
                branch.setName(cutQuotes(values[2]));
                branch.setCode(cutQuotes(values[3]));
                branchesList.add(branch);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String cutQuotes(String quotedString) {
        if ((quotedString.charAt(0) == '"') && (quotedString.charAt(quotedString.length() - 1) == '"')) {
            return quotedString.substring(1, quotedString.length() - 1);
        }
        return quotedString;
    }

    public List<Branch> getBranchesList() {
        return branchesList;
    }
}
