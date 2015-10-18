package du.uCase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;

class UCore {

    private static du.uCase.UConfig uconfig = new du.uCase.UConfig();

    public UCore() {
        //this.setConfig();
    }

    //private void setConfig() {uconfig = new UConfig();}

    private Connection getMYConnection() {
        Connection connection = null;
        String dbpath;
        if (uconfig.getDebugmode().equals("1")) {
            dbpath = "jdbc:sqlite:src/du/uCase/db/debug.mydb.sqlite3";
            //dbpath = "jdbc:sqlite:src/du/uCase/db/debug.mydb.sqlite3";
        } else {
            dbpath = "jdbc:sqlite:src/du/uCase/db/mydb.sqlite3";
        }
        System.out.println(dbpath);
        try {
            Class.forName("org.sqlite.JDBC");
            connection = java.sql.DriverManager.getConnection(dbpath);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("SQL Connection error" + e.toString());
        }
        return connection;
    }

    private Connection getCSConnection() {
        Connection connection = null;
        String dbpath;
        if (uconfig.getDebugmode().equals("1")) {
            dbpath = "jdbc:sqlite:src/du/uCase/db/debug.csdb.sqlite3";
        } else {
            dbpath = "jdbc:sqlite:src/du/uCase/db/csdb.sqlite3";
        }
        System.out.println(dbpath);
        try {
            Class.forName("org.sqlite.JDBC");
            connection = java.sql.DriverManager.getConnection(dbpath);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("SQL Connection error" + e.toString());
        }
        return connection;
    }

    private String[] getCount(String tablename) {
        String[] result = new String[2];
        Connection connection;
        ResultSet rs;
        if (tablename.equals("FULLCASE")) {
            connection = this.getCSConnection();
        } else {
            connection = this.getMYConnection();
        }
        try {
            Statement stat = connection.createStatement();
            rs = stat.executeQuery("SELECT COUNT(Incident) FROM " + tablename);
            if (rs != null) {
                result[0] = rs.getString(1);
            }
            rs = stat.executeQuery("SELECT COUNT(Incident) FROM " + tablename + " WHERE Status='OPEN'");
            if (rs != null) {
                result[1] = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (java.sql.SQLException e) {
                System.err.println(e);
            }
        }
        return result;
    }

    public String[] getCountFullcase() {
        return getCount("FULLCASE");
    }

    public String[] getCountMycase() {
        return getCount("MYCASE");
    }

    public String[] getCountAction() {
        return getCount("ACTION");
    }

    public String[] getCountPart() {
        return getCount("PART");
    }

    public String[] getCountSrms() {
        return getCount("SRMS");
    }

    private TableColumn<Map, String>[] getColumns(String tablename) {
        String[] FIELDS = this.uconfig.getFields(tablename);

        TableColumn<Map, String>[] columns = new TableColumn[FIELDS.length];

        Callback<TableColumn<Map, String>, TableCell<Map, String>> cellFactoryForMap = new Callback<TableColumn<Map, String>, TableCell<Map, String>>() {
            @Override
            public TableCell call(TableColumn p) {
                return new TextFieldTableCell(new StringConverter() {
                    @Override
                    public String toString(Object t) {
                        return t.toString();
                    }

                    @Override
                    public Object fromString(String string) {
                        return string;
                    }
                });
            }
        };

        int colIndex = 0;
        for (String field : FIELDS) {
            TableColumn<Map, String> column = new TableColumn<>(field);
            column.setCellValueFactory(new MapValueFactory(field));
            //column.setMinWidth(100);
            column.setCellFactory(cellFactoryForMap);
            columns[colIndex++] = column;
        }
        //System.out.println("GO");
        return columns;
    }

    public TableColumn<Map, String>[] getColumnsFullcase() {
        return getColumns("FULLCASE");
    }

    public TableColumn<Map, String>[] getColumnsMycase() {
        return getColumns("MYCASE");
    }

    public TableColumn<Map, String>[] getColumnsAction() {
        return getColumns("ACTION");
    }

    public TableColumn<Map, String>[] getColumnsPart() {
        return getColumns("PART");
    }

    public TableColumn<Map, String>[] getColumnsSrms() {
        return this.getColumns("SRMS");
    }

    private ObservableList<Map> getRows(String tablename, String filter) {
        String[] FIELDS = this.uconfig.getFields(tablename);
        Connection connection;
        if (tablename.equals("FULLCASE")) {
            connection = this.getCSConnection();
        } else {
            connection = this.getMYConnection();
        }
        System.out.println(connection);
        ObservableList<Map> rows = FXCollections.observableArrayList();
        rows.clear();
        String SQL = "";
        String FIELD = "";
        //MERGE SQL
        FIELD = Arrays.asList(FIELDS).toString();
        FIELD = FIELD.substring(1, FIELD.length() - 1);
        SQL = "SELECT " + FIELD + " FROM " + tablename + " " + filter;
        //TEST CONN
        try {
            Statement stat = connection.createStatement();
            System.out.println(SQL);
            ResultSet rs = stat.executeQuery(SQL);
            while (rs.next()) {
                Map<String, String> row = new HashMap<>();
                int i = 1;
                for (String field : FIELDS) {

                    row.put(field, rs.getString(i++));
                }
                rows.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println(e.toString());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (java.sql.SQLException e) {
                System.err.println(e);
            }
        }
        return rows;
    }

    public ObservableList<Map> getRowsFullcase(String filter) {
        return this.getRows("FULLCASE", filter);
    }

    public ObservableList<Map> getRowsMycase(String filter) {
        return this.getRows("MYCASE", filter);
    }

    public ObservableList<Map> getRowsAction(String filter) {
        return this.getRows("ACTION", filter);
    }

    public ObservableList<Map> getRowsPart(String filter) {
        return this.getRows("PART", filter);
    }

    public ObservableList<Map> getRowsSrms(String filter) {
        return this.getRows("SRMS", filter);
    }

    public boolean setRowsFullcase(String filepath, TextArea ta) {
        String result;
        String row;
        String[] count = new String[2];
        int insertcount = 0;
        int replacecount = 0;
        int ignorecount = 0;
        File file;
        BufferedReader buffer;

        Connection connection = this.getCSConnection();;
        ResultSet rs;
        PreparedStatement prep;

        //PRINT CURRENT DB STATUS
        ta.appendText("Check current database status...\n");
        count = this.getCountFullcase();
        ta.appendText("Total FULL records: " + count[0] + "\n");
        ta.appendText("Total OPEN records: " + count[1] + "\n");
        ta.appendText("\n");
        ta.appendText("Start importing...\n");
        //read file
        try {
            System.out.println(filepath);
            file = new File(filepath);
            buffer = new BufferedReader(new FileReader(file));

            if ((row = buffer.readLine()) != null) {
                if (row.split("\t")[0].contains("Primary")) {
                    ta.appendText("!!! ERROR: Wrong File selected !!!");
                    return false;
                } else if (row.split("\t")[0].contains("Street")) {
                    ta.appendText("!!! Correct File selected !!!");
                } else {
                    ta.appendText("!!! ERROR: Wrong File selected !!!");
                    return false;
                }
            }
        } catch (java.io.FileNotFoundException e) {
            System.err.println(e);
            return false;
        } catch (java.io.IOException e) {
            System.err.println(e);
            return false;
        }
        //
        try {
            Statement stat = connection.createStatement();
            prep = connection.prepareStatement("insert or replace into FULLCASE values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            while ((row = buffer.readLine()) != null) {
                String[] r = row.split("\t");
                for (int i = 0; i < r.length; i++) {
                    prep.setString(i + 1, r[i]);

                }
                rs = stat.executeQuery("select * from FULLCASE where incident=\"" + r[22] + "\";");
                if (!rs.next()) {
                    prep.addBatch();
                    connection.setAutoCommit(false);
                    prep.executeBatch();
                    connection.setAutoCommit(true);
                    //System.out.println(r[22] + " inserted");
                    insertcount++;
                } else if (r[55].equals("CLOSED") && rs.getString("Status").equals("OPEN")) {
                    prep.addBatch();
                    connection.setAutoCommit(false);
                    prep.executeBatch();
                    connection.setAutoCommit(true);
                    //System.out.println(r[22] + " replaced");
                    replacecount++;
                } else {
                    //System.out.println(r[22] + " ignored");
                    ignorecount++;
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
            ta.appendText("!!! ERROR: SQL ERROR !!! \n");
            return false;
        } catch (IOException e) {
            ta.appendText("!!! ERROR: FILE CANNOT BE OPENED !!! \n");
            return false;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (java.sql.SQLException e) {
                System.err.println(e);
            }
        }

        ta.appendText("Import completed.\n");
        ta.appendText("Inserted records: " + insertcount + "\n");
        ta.appendText("Replaced records: " + replacecount + "\n");
        ta.appendText("Ignored  records: " + ignorecount + "\n");
        ta.appendText("\n");
        ta.appendText("Check current database status...\n");
        count = this.getCountFullcase();
        ta.appendText("Total FULL records: " + count[0] + "\n");
        ta.appendText("Total OPEN records: " + count[1] + "\n");
        result = "!!! FILE IMPORTED !!!";
        return true;
    }

    public boolean setRowsMycase(String uissr) {
        Connection myconn = this.getMYConnection();
        Connection csconn = this.getCSConnection();
        try {

            Statement csstat = csconn.createStatement();
            Statement mystat = myconn.createStatement();

            ResultSet csrs = csstat.executeQuery("select * from FULLCASE where Incident='" + uissr + "'");

            //prepare data from FULLCASE
            PreparedStatement myprep = myconn.prepareStatement("insert or replace into MYCASE values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            myprep.setString(1, null); //id auto-increment
            myprep.setString(2, csrs.getString("Status"));
            myprep.setString(3, csrs.getString("Incident"));
            myprep.setString(4, csrs.getString("ClientRef"));
            myprep.setString(5, "NA"); //emcsr
            myprep.setString(6, csrs.getString("Company"));
            myprep.setString(7, csrs.getString("Headline"));
            myprep.setString(8, csrs.getString("SN"));
            myprep.setString(9, csrs.getString("Pri"));
            myprep.setString(10, csrs.getString("Street"));
            myprep.setString(11, csrs.getString("City"));
            myprep.setString(12, csrs.getString("CustIA"));
            myprep.setString(13, csrs.getString("SelfID"));
            myprep.setString(14, csrs.getString("UStyle"));
            myprep.setString(15, csrs.getString("Opened"));

            ResultSet myrs = mystat.executeQuery("select * from MYCASE where uissr='" + uissr + "'");
            if (!myrs.next()) {
                myprep.addBatch();
                myconn.setAutoCommit(false);
                myprep.executeBatch();
                myconn.setAutoCommit(true);
            }
            return true;//else for update record else {}
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (csconn != null) {
                    csconn.close();
                }
            } catch (java.sql.SQLException e) {
                System.err.println(e);
            }

            try {
                if (myconn != null) {
                    myconn.close();
                }
            } catch (java.sql.SQLException e) {
                System.err.println(e);
            }
        }
    }

    public boolean setRowsAction(Map<String, String>[] rows) {
        String[] FIELDS = this.uconfig.getFullFields("ACTION");
        String sqlfields = "";
        String sqlvalues = "";
        String sql = "";
        Connection connection = null;

        Map<String, String> tableMeta = this.getMyTableMetaData("ACTION");

        for (Map<String, String> row : rows) {
            for (String key : tableMeta.keySet()) {
                if (!row.get(key).isEmpty()) {
                    sqlfields = sqlfields + key + ",";
                    if (tableMeta.get(key).equals("TEXT")) {
                        sqlvalues = sqlvalues + "'" + row.get(key) + "',";
                    } else if (tableMeta.get(key).equals("INTEGER")) {
                        sqlvalues = sqlvalues + row.get(key) + ",";
                    } else if (tableMeta.get(key).equals("REAL")) {
                        sqlvalues = sqlvalues + row.get(key) + ",";
                    }
                }

            }
            sqlfields = sqlfields.substring(0, sqlfields.length() - 1);
            sqlvalues = sqlvalues.substring(0, sqlvalues.length() - 1);
            sql = "INSERT OR REPLACE INTO ACTION (" + sqlfields + ") VALUES (" + sqlvalues + ")";
            System.out.println(sql);
            try {
                connection = this.getMYConnection();
                Statement stat = connection.createStatement();
                stat.executeUpdate(sql);
            } catch (SQLException e) {
                System.out.println(e.toString());
                return false;
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    System.out.println("SQLException");
                    return false;
                }
            }
        }

	/*
	
	 for(Map<String, String> row : rows) {
	 for(String field : FIELDS) {
	 System.out.println("DEBUG:"+field+"|"+row.get(field));
	 if(!row.get(field).isEmpty()) {
	 sqlfields = sqlfields + field + ",";
	 sqlvalues = sqlvalues + row.get(field) + ",";
	 }
	 }
	    
	 sqlfields = sqlfields.substring(0, sqlfields.length() - 1);
	 sqlvalues = sqlvalues.substring(0, sqlvalues.length() - 1);
	 sql = "INSERT OR REPLACE INTO ACTION (" + sqlfields + ") VALUES (" + sqlvalues + ")";
	 System.out.println(sql);
	    
	 try {
	 connection = this.getMYConnection();
	 Statement stat = connection.createStatement();
	 stat.executeUpdate(sql);
	 } catch(SQLException e) {
	 System.out.println(e.toString());
	 return false;
	 } finally {
	 try {
	 if(connection != null)
	 connection.close();
	 } catch(SQLException e) {
	 System.out.println("SQLException");
	 return false;
	 } 
	 }
	    	
	 } */
        return true;
    }

    private Map<String, String> getMyTableMetaData(String tablename) {
        Connection connection = null;
        Map<String, String> tableMeta = new HashMap<>();
        try {
            connection = this.getMYConnection();
            Statement stat = connection.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM " + tablename);
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                if (!rsmd.getColumnName(i).equals("id")) {
                    tableMeta.put(rsmd.getColumnName(i), rsmd.getColumnTypeName(i));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("SQLException");
            }
        }
        return tableMeta;
    }
    
    

    public void mytest() {
        System.out.println("HERE");
        try {
            Statement stat = this.getMYConnection().createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM SRMS");
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println(rsmd.getColumnCount());
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.println("columnname:" + rsmd.getColumnName(i));
                System.out.println("columntype:" + rsmd.getColumnType(i));
                System.out.println("columnlabel:" + rsmd.getColumnLabel(i));
                System.out.println("columntypename:" + rsmd.getColumnTypeName(i));
                System.out.println("");
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
}

