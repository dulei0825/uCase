package du.uCase;

import java.util.Map;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

class StageFullcase extends Stage {

    //define component

    private ContextMenu contextMenu;
    private MenuItem menuItemAddtoMycase;
    private VBox root;
    private Scene scene;
    private HBox hBoxFilter;
    private TextField textFieldUissr;
    private CheckBox checkBoxStatus;
    private Button buttonQueryFullcase;
    private Button buttonImportFullcase;
    private TableView tableViewFullcase;
    private UCore core;

    public StageFullcase() {
        super();
        this.initGUI();
        this.initCore();
        this.setTableColumnsFullcase();
    }

    private void initGUI() {
        //crate contentmenu
        this.contextMenu = new ContextMenu();
        this.menuItemAddtoMycase = new MenuItem("Add to Mycase");
        this.contextMenu.getItems().addAll(this.menuItemAddtoMycase);
        //create filter box
        this.hBoxFilter = new HBox(10);
        this.checkBoxStatus = new CheckBox("Status OPEN");
        this.checkBoxStatus.setSelected(true);
        this.textFieldUissr = new TextField();
        this.textFieldUissr.setPromptText("Unisys SRMS SR");
        this.buttonQueryFullcase = new Button("Query");
        this.buttonImportFullcase = new Button("Import");
        this.hBoxFilter.getChildren().addAll(this.textFieldUissr,
                this.checkBoxStatus,
                this.buttonQueryFullcase);
        //create table
        this.tableViewFullcase = new TableView();
        this.tableViewFullcase.setContextMenu(this.contextMenu);
        this.tableViewFullcase.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        this.root.setVgrow(this.tableViewFullcase, Priority.ALWAYS);

        this.buttonQueryFullcase.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                setTableRowsFullcase();
            }
        });

        this.buttonImportFullcase.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                MiniBox.showStageImport();
            }
        });

        this.menuItemAddtoMycase.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                insTableMycase();
            }
        });

        //assemble
        this.root = new VBox();

        this.root.getChildren().addAll(this.hBoxFilter,
                this.tableViewFullcase);

        this.scene = new Scene(this.root, 800, 600);
        this.setScene(this.scene);

        //final show
        this.setTitle("uCase - Query Fullcase");
        this.show();
    }

    private void initCore() {
        core = new UCore();
    }

    private void insTableMycase() {
        TableColumn<?, ?> selectedCol = (TableColumn) this.tableViewFullcase.getColumns().get(0);
        String selectedIncident = (String) selectedCol.getCellData(tableViewFullcase.getSelectionModel().getSelectedIndex());
        if (selectedIncident != null && !selectedIncident.equals("")) {
            // no message pad in this version, maybe try message box
            if (this.core.setRowsMycase(selectedIncident)) {
                //this.returninfo.setText(selectedIncident + " YES");
                System.out.println(selectedIncident + " ins YES");
            } else {
                //this.returninfo.setText(selectedIncident + " NO");
                System.out.println(selectedIncident + " ins NO");
            }
        } else {
            System.out.println("NONE selected");
        }
    }

    private void setTableColumnsFullcase() {
        TableColumn<Map, String>[] columns = this.core.getColumnsFullcase();
        this.tableViewFullcase.getColumns().clear();
        this.tableViewFullcase.getColumns().setAll(columns);
    }

    private void setTableRowsFullcase() {
        ObservableList<Map> rows = this.core.getRowsFullcase(this.getFilter());
        this.tableViewFullcase.getItems().clear();
        this.tableViewFullcase.setItems(rows);
    }

    private String getFilter() {
        String filter = "";
        String filter_status = "";
        String filter_incident = "";
        if (this.checkBoxStatus.isSelected()) {
            filter_status = " and Status='OPEN'";
        }
        if (this.textFieldUissr.getText().trim().length() >= 8) {
            filter_incident = " and Incident='" + this.textFieldUissr.getText().trim() + "'";
        }

        filter = filter_status + filter_incident;
        if (filter.length() > 0) {
            filter = "WHERE" + filter.substring(4);
        }

        return filter;
    }
}
