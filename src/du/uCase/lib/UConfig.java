package du.uCase;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

class UConfig {
    private static final String CONFIG = "res/uCaseConfig.properties";
    private static String DEBUGMODE = null;
    private static Map<String, String[]> FIELDS = new HashMap<String,String[]>();
    private static Map<String, String[]> FULLFIELDS = new HashMap<String,String[]>();

    public UConfig() {
        this.readConfig();
    }
    
    private void readConfig() {
        try {
            InputStream inputstream = this.getClass().getResourceAsStream(CONFIG);
            Properties p = new Properties();
            p.load(inputstream);

            this.DEBUGMODE = p.getProperty("DEBUGMODE");

            this.FIELDS.put("FULLCASE", p.getProperty("FIELDS_FULLCASE").split(","));
            this.FIELDS.put("MYCASE", p.getProperty("FIELDS_MYCASE").split(","));
            this.FIELDS.put("ACTION", p.getProperty("FIELDS_ACTION").split(","));
            this.FIELDS.put("PART", p.getProperty("FIELDS_PART").split(","));
            this.FIELDS.put("SRMS", p.getProperty("FIELDS_SRMS").split(","));

            this.FULLFIELDS.put("FULLCASE", p.getProperty("FULLFIELDS_FULLCASE").split(","));
            this.FULLFIELDS.put("MYCASE", p.getProperty("FULLFIELDS_MYCASE").split(","));
            this.FULLFIELDS.put("ACTION", p.getProperty("FULLFIELDS_ACTION").split(","));
            this.FULLFIELDS.put("PART", p.getProperty("FULLFIELDS_PART").split(","));
            this.FULLFIELDS.put("SRMS", p.getProperty("FULLFIELDS_SRMS").split(","));
        } catch(IOException e) {
            System.err.println(e);
        }
    }
    
    public String getDebugmode() {
        return this.DEBUGMODE;
    }

    public String[] getFieldsFullcase() throws IOException {
        return this.FIELDS.get("FULLCASE");
    }

    public String[] getFieldsMycase() throws IOException {
        return this.FIELDS.get("MYCASE");
    }

    public String[] getFieldsAction() throws IOException {
        return this.FIELDS.get("ACTION");
    }

    public String[] getFieldsPart() throws IOException {
        return this.FIELDS.get("PART");
    }

    public String[] getFieldsSrms() throws IOException {
        return this.FIELDS.get("SRMS");
    }
    
    public String[] getFields(String tablename) {
        return this.FIELDS.get(tablename);
    }
    
    public String[] getFullFields(String tablename) {
        return this.FULLFIELDS.get(tablename);
    }
}
