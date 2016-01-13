package ru.rt.task;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for convenient parsing CSV represented branch records
 */
public class BranchCSVReader {
    public static final String CHARSET = "UTF-8";

    private String csvPath;
    private List<Branch> branchesList = new ArrayList<>();

    /**
     * Constructor
     * @param csvPath Path of CSV file to parse into branch objects
     */
    public BranchCSVReader(String csvPath) {
        this.csvPath = csvPath;
    }

    /**
     * Parse dictionary CSV into branchesList
     * @throws IOException
     */
    public void readBranches() throws IOException {
        File csvData = new File(csvPath);
        CSVParser parser = CSVParser.parse(csvData, Charset.forName(CHARSET), CSVFormat.RFC4180);
        for (CSVRecord csvRecord : parser) {
            Branch branch = new Branch();
            branch.setId(csvRecord.get(0));
            branch.setParentId(csvRecord.get(1));
            branch.setName(csvRecord.get(2));
            branch.setCode(csvRecord.get(3));
            branchesList.add(branch);
        }
    }

    public List<Branch> getBranchesList() {
        return branchesList;
    }
}
