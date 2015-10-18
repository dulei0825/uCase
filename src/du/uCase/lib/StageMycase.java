package du.uCase;

import java.awt.MouseInfo;
import java.util.Map;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

class StageMycase extends Stage {

    //define component
    private Scene scene;
    private VBox root;
    private MenuBar menuBar;
    private Menu menuFile;
    private MenuItem menuItemExit;
    private Menu menuQuery;
    private MenuItem menuItemQueryFullcase;
    private Menu menuInsert;
    private MenuItem menuItemInsertAction;
    private MenuItem menuItemInsertPart;
    private MenuItem menuItemInsertSrms;
    private Menu menuImport;
    private MenuItem menuItemImportXls;
    private MenuItem menuItemImportWeb;
    private Menu menuTools;
    private MenuItem menuItemFtpConvert;
    private HBox hBoxFilter;
    private TextField textFieldUissr;
    private CheckBox checkBoxStatus;
    private Button buttonMycaseQuery;
    private TableView tableViewMycase;
    private TabPane tabPane;
    private Tab tabAction;
    private HBox hBoxAction;
    private TableView tableViewAction;
    private VBox vBoxAction;
    private Button buttonActionInsert;
    private Button buttonActionUpdate;
    private TextField textFieldActionUissr;
    private Button buttonActionQuery;
    private Tab tabPart;
    private HBox hBoxPart;
    private TableView tableViewPart;
    private VBox vBoxPart;
    private Button buttonPartInsert;
    private Button buttonPartUpdate;
    private TextField textFieldPartUissr;
    private Button buttonPartQuery;
    private Tab tabSrms;
    private HBox hBoxSrms;
    private TableView tableViewSrms;
    private VBox vBoxSrms;
    private Button buttonSrmsInsert;
    private Button buttonSrmsUpdate;
    private TextField textFieldSrmsUissr;
    private Button buttonSrmsQuery;

    private ContextMenu contextMenu;
    private MenuItem contextMenuItemQueryAction;
    private MenuItem contextMenuItemQueryPart;
    private MenuItem contextMenuItemQuerySrms;
    private MenuItem contextMenuItemInsertAction;
    private MenuItem contextMenuItemInsertPart;
    private MenuItem contextMenuItemInsertSrms;
    private MenuItem contextMenuItemEditMycase;
    private MenuItem contextMenuItemEditAction;
    private MenuItem contextMenuItemEditPart;
    private MenuItem contextMenuItemEditSrms;

    private UCore core;

    public StageMycase() {
        super();
        this.initGUI();
        this.initCore();
        this.setTableColumnsMycase();
        this.setTableColumnsAction();
        this.setTableColumnsPart();
        this.setTableColumnsSrms();
    }

    private void initGUI() {
        // TODO Auto-generated method stub
        //create menu
        this.menuBar = new MenuBar();

        this.menuFile = new Menu("File");
        this.menuItemExit = new MenuItem("Exit");
        this.menuFile.getItems().addAll(this.menuItemExit);

        this.menuQuery = new Menu("Query");
        this.menuItemQueryFullcase = new MenuItem("Query Fullcase");
        this.menuQuery.getItems().addAll(this.menuItemQueryFullcase);

        this.menuInsert = new Menu("Insert");
        this.menuItemInsertAction = new MenuItem("Insert Action");
        this.menuItemInsertPart = new MenuItem("Insert Part");
        this.menuItemInsertSrms = new MenuItem("Insert SRMS");
        this.menuInsert.getItems().addAll(this.menuItemInsertAction,
                this.menuItemInsertPart,
                this.menuItemInsertSrms);

        this.menuImport = new Menu("Import");
        this.menuItemImportXls = new MenuItem("Import XLS");
        this.menuItemImportWeb = new MenuItem("Import Web");
        this.menuImport.getItems().addAll(this.menuItemImportXls, this.menuItemImportWeb);

        this.menuTools = new Menu("Tools");
        this.menuItemFtpConvert = new MenuItem("Convert FTP to HTPP");
        this.menuTools.getItems().addAll(this.menuItemFtpConvert);

        this.menuBar.getMenus().addAll(this.menuFile,
                this.menuQuery,
                this.menuInsert,
                this.menuImport,
                this.menuTools);

        //create contextmenu
        this.contextMenu = new ContextMenu();

        this.contextMenuItemQueryAction = new MenuItem("Query Action");
        this.contextMenuItemQueryPart = new MenuItem("Query Part");
        this.contextMenuItemQuerySrms = new MenuItem("Query Srms");
        this.contextMenuItemInsertAction = new MenuItem("Insert Action");
        this.contextMenuItemInsertPart = new MenuItem("Insert Part");
        this.contextMenuItemInsertSrms = new MenuItem("Insert SRMS");
        this.contextMenuItemEditMycase = new MenuItem("Edit Mycase");
        this.contextMenuItemEditAction = new MenuItem("Edit Action");
        this.contextMenuItemEditPart = new MenuItem("Edit Part");
        this.contextMenuItemEditSrms = new MenuItem("Edit SRMS");

        this.hBoxFilter = new HBox(10);
        this.checkBoxStatus = new CheckBox("Status OPEN");
        this.checkBoxStatus.setSelected(true);
        this.textFieldUissr = new TextField();
        this.textFieldUissr.setPromptText("Unisys SRMS SR");
        this.buttonMycaseQuery = new Button("Query");
        this.hBoxFilter.getChildren().addAll(this.textFieldUissr,
                this.checkBoxStatus,
                this.buttonMycaseQuery);
        //create table
        this.tableViewMycase = new TableView();
        this.tableViewMycase.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //create tabpane
        this.tabPane = new TabPane();
        this.tabPane.setId("tabpane-mycase");
        //create tabaction
        this.tabAction = new Tab();
        this.tabAction.setText("Action");
        this.buttonActionInsert = new Button("Insert");
        this.buttonActionUpdate = new Button("Update");
        this.buttonActionQuery = new Button("Query");
        this.vBoxAction = new VBox(10);
        this.vBoxAction.getChildren().addAll(this.buttonActionInsert,
                this.buttonActionUpdate,
                this.buttonActionQuery);
        this.hBoxAction = new HBox(10);
        this.tableViewAction = new TableView();
        this.tableViewAction.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        this.hBoxAction.getChildren().addAll(this.tableViewAction, this.vBoxAction);
        this.hBoxAction.setHgrow(this.tableViewAction, Priority.ALWAYS);
        this.tabAction.setContent(this.hBoxAction);
        //create tabpart
        this.tabPart = new Tab();
        this.tabPart.setText("Part");
        this.buttonPartInsert = new Button("Insert");
        this.buttonPartUpdate = new Button("Update");
        this.buttonPartQuery = new Button("Query");
        this.vBoxPart = new VBox(10);
        this.vBoxPart.getChildren().addAll(this.buttonPartInsert,
                this.buttonPartUpdate,
                this.buttonPartQuery);
        this.hBoxPart = new HBox(10);
        this.tableViewPart = new TableView();
        this.tableViewPart.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        this.hBoxPart.getChildren().addAll(this.tableViewPart, this.vBoxPart);
        this.hBoxAction.setHgrow(this.tableViewPart, Priority.ALWAYS);
        this.tabPart.setContent(this.hBoxPart);
        //create tabsrms
        this.tabSrms = new Tab();
        this.tabSrms.setText("Srms");
        this.buttonSrmsInsert = new Button("Insert");
        this.buttonSrmsUpdate = new Button("Update");
        this.buttonSrmsQuery = new Button("Query");
        this.vBoxSrms = new VBox(10);
        this.vBoxSrms.getChildren().addAll(this.buttonSrmsInsert,
                this.buttonSrmsUpdate,
                this.buttonSrmsQuery);
        this.hBoxSrms = new HBox(10);
        this.tableViewSrms = new TableView();
        this.tableViewSrms.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        this.hBoxSrms.getChildren().addAll(this.tableViewSrms, this.vBoxSrms);
        this.hBoxAction.setHgrow(this.tableViewSrms, Priority.ALWAYS);
        this.tabSrms.setContent(this.hBoxSrms);

        //assemble tabpane
        this.tabPane.getTabs().addAll(this.tabAction,
                this.tabPart,
                this.tabSrms);
        //assemble
        this.root = new VBox();
        this.root.getChildren().addAll(this.menuBar,
                this.hBoxFilter,
                this.tableViewMycase,
                this.tabPane);
        this.root.setVgrow(this.tableViewMycase, Priority.ALWAYS);
        this.scene = new Scene(this.root, 1280, 800);
        this.scene.getStylesheets().add(getClass().getResource("res/uCaseStyle.css").toExternalForm());
        this.setScene(this.scene);

        //set event
        this.menuItemQueryFullcase.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                MiniBox.showStageFullcase();
            }
        });

        this.menuItemImportXls.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                MiniBox.showStageImport();
                MiniBox.setFocusStageImportXls();
            }
        });

        this.buttonMycaseQuery.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                setTableRowsMycase();
            }
        });

        this.buttonActionInsert.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                MiniBox.showStageInput();
            }
        });

        this.contextMenuItemQueryAction.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                onActionContextMenuQuery("ACTION");
            }
        });

        this.contextMenuItemQueryPart.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                onActionContextMenuQuery("PART");
            }
        });

        this.contextMenuItemQuerySrms.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                onActionContextMenuQuery("SRMS");
            }
        });

        this.contextMenuItemInsertAction.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                onActionContextMenuInsert("ACTION");
            }
        });

        this.contextMenuItemInsertPart.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                onActionContextMenuInsert("PART");
            }
        });

        this.contextMenuItemInsertSrms.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                onActionContextMenuInsert("SRMS");
            }
        });

        this.tableViewMycase.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

            @Override
            public void handle(ContextMenuEvent event) {
                contextMenu.getItems().clear();
                contextMenu.getItems().addAll(contextMenuItemQueryAction,
                        contextMenuItemQueryPart,
                        contextMenuItemQuerySrms,
                        new SeparatorMenuItem(),
                        contextMenuItemEditMycase,
                        new SeparatorMenuItem(),
                        contextMenuItemInsertAction,
                        contextMenuItemInsertPart,
                        contextMenuItemInsertSrms);
                contextMenu.show(tableViewMycase,
                        MouseInfo.getPointerInfo().getLocation().x,
                        MouseInfo.getPointerInfo().getLocation().y);
            }
        });

        this.tableViewAction.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

            @Override
            public void handle(ContextMenuEvent event) {
                contextMenu.getItems().clear();
                contextMenu.getItems().addAll(contextMenuItemQueryPart,
                        contextMenuItemQuerySrms,
                        new SeparatorMenuItem(),
                        contextMenuItemEditAction,
                        new SeparatorMenuItem(),
                        contextMenuItemInsertAction);
                contextMenu.show(tableViewAction,
                        MouseInfo.getPointerInfo().getLocation().x,
                        MouseInfo.getPointerInfo().getLocation().y);
            }
        });

        this.tableViewPart.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

            @Override
            public void handle(ContextMenuEvent event) {
                contextMenu.getItems().clear();
                contextMenu.getItems().addAll(contextMenuItemQueryAction,
                        contextMenuItemQuerySrms,
                        new SeparatorMenuItem(),
                        contextMenuItemEditPart,
                        new SeparatorMenuItem(),
                        contextMenuItemInsertPart);
                contextMenu.show(tableViewPart,
                        MouseInfo.getPointerInfo().getLocation().x,
                        MouseInfo.getPointerInfo().getLocation().y);
            }
        });

        this.tableViewSrms.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

            @Override
            public void handle(ContextMenuEvent event) {
                contextMenu.getItems().clear();
                contextMenu.getItems().addAll(contextMenuItemQueryAction,
                        contextMenuItemQueryPart,
                        new SeparatorMenuItem(),
                        contextMenuItemEditSrms,
                        new SeparatorMenuItem(),
                        contextMenuItemInsertSrms);
                contextMenu.show(tableViewSrms,
                        MouseInfo.getPointerInfo().getLocation().x,
                        MouseInfo.getPointerInfo().getLocation().y);
            }
        });

        //final show
        this.setTitle("uCase - Query Mycase");
        this.show();
    }

    private void initCore() {
        core = new UCore();
    }

    private void setTableColumnsMycase() {
        TableColumn<Map, String>[] columns = this.core.getColumnsMycase();
        this.tableViewMycase.getColumns().clear();
        this.tableViewMycase.getColumns().setAll(columns);
    }

    private void setTableRowsMycase() {
        ObservableList<Map> rows = this.core.getRowsMycase(this.getFilter());
        this.tableViewMycase.getItems().clear();
        this.tableViewMycase.setItems(rows);
    }

    private void setTableColumnsAction() {
        TableColumn<Map, String>[] columns = this.core.getColumnsAction();
        this.tableViewAction.getColumns().clear();
        this.tableViewAction.getColumns().setAll(columns);
    }

    private void setTableColumnsPart() {
        TableColumn<Map, String>[] columns = this.core.getColumnsPart();
        this.tableViewPart.getColumns().clear();
        this.tableViewPart.getColumns().setAll(columns);
    }

    private void setTableColumnsSrms() {
        TableColumn<Map, String>[] columns = this.core.getColumnsSrms();
        this.tableViewSrms.getColumns().clear();
        this.tableViewSrms.getColumns().setAll(columns);
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

    private void onActionContextMenuQueryAction() {
        TableView tv = new TableView();
        if (this.tableViewMycase.isFocused()) {
            tv = this.tableViewMycase;
        } else if (this.tableViewPart.isFocused()) {
            tv = this.tableViewPart;
        } else if (this.tableViewSrms.isFocused()) {
            tv = this.tableViewSrms;
        }
        TableColumn<?, ?> selectedCol = (TableColumn) tv.getColumns().get(1);
        String selectedIncident = (String) selectedCol.getCellData(tv.getSelectionModel().getSelectedIndex());
        if (selectedIncident != null && !selectedIncident.equals("")) {

            ObservableList<Map> rows = this.core.getRowsAction("WHERE uissr = '" + selectedIncident + "'");
            this.tableViewAction.getItems().clear();
            this.tableViewAction.setItems(rows);
            this.tabPane.getSelectionModel().select(this.tabAction);
        } else {
            System.out.println("NONE selected");
        }
    }

    private void onActionContextMenuQueryPart() {
        TableView tv = new TableView();
        if (this.tableViewMycase.isFocused()) {
            tv = this.tableViewMycase;
        } else if (this.tableViewPart.isFocused()) {
            tv = this.tableViewPart;
        } else if (this.tableViewSrms.isFocused()) {
            tv = this.tableViewSrms;
        }
        TableColumn<?, ?> selectedCol = (TableColumn) tv.getColumns().get(1);
        String selectedIncident = (String) selectedCol.getCellData(tv.getSelectionModel().getSelectedIndex());
        if (selectedIncident != null && !selectedIncident.equals("")) {

            ObservableList<Map> rows = this.core.getRowsPart("WHERE uissr = '" + selectedIncident + "'");
            this.tableViewPart.getItems().clear();
            this.tableViewPart.setItems(rows);
            this.tabPane.getSelectionModel().select(this.tabPart);
        } else {
            System.out.println("NONE selected");
        }
    }

    private void onActionContextMenuQuerySrms() {
        TableView tv = new TableView();
        if (this.tableViewMycase.isFocused()) {
            tv = this.tableViewMycase;
        } else if (this.tableViewPart.isFocused()) {
            tv = this.tableViewPart;
        } else if (this.tableViewSrms.isFocused()) {
            tv = this.tableViewSrms;
        }
        TableColumn<?, ?> selectedCol = (TableColumn) tv.getColumns().get(1);
        String selectedIncident = (String) selectedCol.getCellData(tv.getSelectionModel().getSelectedIndex());
        if (selectedIncident != null && !selectedIncident.equals("")) {

            ObservableList<Map> rows = this.core.getRowsSrms("WHERE uissr = '" + selectedIncident + "'");
            this.tableViewSrms.getItems().clear();
            this.tableViewSrms.setItems(rows);
            this.tabPane.getSelectionModel().select(this.tabSrms);
        } else {
            System.out.println("NONE selected");
        }
    }

    private void onActionContextMenuQuery(String tablename) {
        TableView tv = new TableView();
        if(this.tableViewMycase.isFocused()) {
            tv = this.tableViewMycase;
        } else if(this.tableViewAction.isFocused()) {
            tv = this.tableViewAction;
        } else if(this.tableViewPart.isFocused()) {
            tv = this.tableViewPart;
        } else if(this.tableViewSrms.isFocused()) {
            tv = this.tableViewSrms;
        }

        ObservableList<Map> rows;
        TableColumn<?, ?> selectedCol = (TableColumn) tv.getColumns().get(0);
        String selectedIncident = (String) selectedCol.getCellData(tv.getSelectionModel().getSelectedIndex());

        if (selectedIncident != null && !selectedIncident.equals("")) {

            switch(tablename) {
                case "ACTION":
                    rows = this.core.getRowsAction("WHERE uissr = '" + selectedIncident + "'");
                    this.tableViewAction.getItems().clear();
                    this.tableViewAction.setItems(rows);
                    this.tabPane.getSelectionModel().select(this.tabAction);
                    break;
                case "PART":
                    rows = this.core.getRowsPart("WHERE uissr = '" + selectedIncident + "'");
                    this.tableViewPart.getItems().clear();
                    this.tableViewPart.setItems(rows);
                    this.tabPane.getSelectionModel().select(this.tabPart);
                    break;
                case "SRMS":
                    rows = this.core.getRowsSrms("WHERE uissr = '" + selectedIncident + "'");
                    this.tableViewSrms.getItems().clear();
                    this.tableViewSrms.setItems(rows);
                    this.tabPane.getSelectionModel().select(this.tabSrms);
                    break;
            }
        } else {
            System.out.println("NONE selected");
        }
    }

    private void onActionContextMenuInsert(String tablename) {
        MiniBox.showStageInput(tablename);
    }
}

