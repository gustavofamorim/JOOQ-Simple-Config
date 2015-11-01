package com.company;

import org.jooq.util.GenerationTool;
import org.jooq.util.jaxb.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Gustavo Freitas on 30/10/2015.
 */
public class DataBaseCodeGenerator {

    private String CONTROLLER;
    private String URL;
    private String USER;
    private String PASSWORD;
    private String SCHEMA_NAME;

    private String PACKAGE_NAME;
    private String DIRECTORY;

    public void generateCode() throws Exception {
        this.loadDataBaseInfo();

        Configuration configuration = new Configuration()
                .withJdbc(new Jdbc()
                        .withDriver(this.CONTROLLER)
                        .withUrl(this.URL)
                        .withUser(this.USER)
                        .withPassword(this.PASSWORD))
                .withGenerator(new Generator()
                        .withName("org.jooq.util.DefaultGenerator")
                        .withDatabase(new Database()
                                .withName("org.jooq.util.mysql.MySQLDatabase")
                                .withIncludes(".*")
                                .withExcludes("")
                                .withInputSchema(this.SCHEMA_NAME))
                        .withTarget(new Target()
                                .withPackageName(this.PACKAGE_NAME)
                                .withDirectory(this.DIRECTORY)));
        GenerationTool.main(configuration);
    }

    public void loadDataBaseInfo() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("DataBaseInfo.txt"));

        this.CONTROLLER = br.readLine();
        this.URL = br.readLine();
        this.USER = br.readLine();
        this.PASSWORD = br.readLine();
        this.SCHEMA_NAME = br.readLine();
        this.PACKAGE_NAME = br.readLine();
        this.DIRECTORY = br.readLine();

        br.close();
    }

    public static DataBaseCodeGenerator getInstance(){
        return (new DataBaseCodeGenerator());
    }
}
