package du.uCase;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Du
 */
public class StageInput extends Stage {
    private UCore core;
    //define component
    private Scene scene;
    private HBox root;
    private TabPane tabPane;
    private TableView tableViewCaseInfoTableView;
    //tab action start
    private Tab tabAction;
    private GridPane gridPaneAction;
    private Button buttonSaveAction; //save button
    private TextField textFieldUissrAction; //uissr
    private DatePicker datePickerAction; //date
    private TextArea textAreaActionAction; //action
    private ChoiceBox choiceBoxTravelTypeAAction;  //traveltypea
    private TextField textFieldTravelDisAAction; //traveldisa
    private ChoiceBox choiceBoxTravelTypeBAction; //traveltypeb
    private TextField textFieldTravelDisBAction; //traveldisb
    private CheckBox checkBoxOtAction; //ot
    private TextField textFieldOtFromAction; //otfrom
    private TextField textFieldOtToAction; //otto
    private RowConstraints rowConstraints;
    private ColumnConstraints columnConstraints10; //for button column
    private ColumnConstraints columnConstraints15; //for label column
    private ColumnConstraints columnConstraints30; //for content column
    private ColumnConstraints columnConstraints35; //for content column
    //tab action over
    //tab part start
    private Tab tabPart;
    private GridPane gridPanePart;
    private Button buttonSavePart;
    private Button buttonQuitPart;
    private TextField textFieldUissrPart;
    private DatePicker datePickerPart;
    private TextField textFieldInPnPart;
    private TextField textFieldInSnPart;
    private TextField textFieldRmPnPart;
    private TextField textFieldRmSnPart;
    private ChoiceBox choiceBoxReturnedPart;
    //tab part over
    //tab mycase start
    private Tab tabMycase;
    private GridPane gridPaneMycase;
    private Button buttonSaveMycase;
    //tab mycase over
    //tab srms start
    private Tab tabSrms;
    private GridPane gridPaneSrms;
    private Button buttonSaveSrms;
    private Button buttonQuitSrms;
    private TextField textFieldUissrSrms;
    private DatePicker datePickerSrms;
    private TextField textFieldActionSrms;
    private TextField textFieldTrdiSrms;
    private TextField textFieldTrtiSrms;
    private TextField textFieldco11Srms;
    private TextField textFieldco26Srms;
    private TextField textFieldco29Srms;

    //tab srms over
    public StageInput() {
        super();
        this.initGUI();
        this.initCore();
    }

    private void initGUI() {
        this.tabMycase = new Tab();
        this.tabAction = new Tab();
        this.tabPart = new Tab();
        this.tabSrms = new Tab();
        this.tabMycase.setText("Mycase");
        this.tabAction.setText("Action");
        this.tabPart.setText("Part");
        this.tabSrms.setText("SRMS");

        //tab action start
        this.gridPaneAction = new GridPane();
        this.gridPaneAction.setPadding(new Insets(10, 10, 10, 10));
        this.rowConstraints = new RowConstraints(30);
        //this.rowConstraints.setPrefHeight(30);
        this.columnConstraints10 = new ColumnConstraints();
        this.columnConstraints15 = new ColumnConstraints();
        this.columnConstraints30 = new ColumnConstraints();
        this.columnConstraints35 = new ColumnConstraints();
        this.columnConstraints10.setPercentWidth(10);
        this.columnConstraints15.setPercentWidth(15);
        this.columnConstraints30.setPercentWidth(30);
        this.columnConstraints35.setPercentWidth(35);

        this.gridPaneAction.getColumnConstraints().add(this.columnConstraints15); //col 0
        this.gridPaneAction.getColumnConstraints().add(this.columnConstraints30); //col 1
        this.gridPaneAction.getColumnConstraints().add(this.columnConstraints30); //col 2
        this.gridPaneAction.getColumnConstraints().add(this.columnConstraints10); //col 3
        this.gridPaneAction.getColumnConstraints().add(this.columnConstraints10); //col 4
        this.gridPaneAction.getRowConstraints().add(this.rowConstraints); //row 0
        this.gridPaneAction.getRowConstraints().add(this.rowConstraints); //row 1
        this.gridPaneAction.getRowConstraints().add(this.rowConstraints); //row 2
        this.gridPaneAction.getRowConstraints().add(this.rowConstraints); //row 3
        this.gridPaneAction.getRowConstraints().add(this.rowConstraints); //row 4
        this.gridPaneAction.getRowConstraints().add(this.rowConstraints); //row 5
        this.gridPaneAction.getRowConstraints().add(this.rowConstraints); //row 6
        this.gridPaneAction.getRowConstraints().add(this.rowConstraints); //row 7
        this.gridPaneAction.getRowConstraints().add(this.rowConstraints); //rwo 8
        this.gridPaneAction.getRowConstraints().add(this.rowConstraints); //rwo 9

        Label lbl0Action = new Label("UNISYS SR");
        GridPane.setConstraints(lbl0Action, 0, 0);
        Label lbl1Action = new Label("Date");
        GridPane.setConstraints(lbl1Action, 0, 1);
        Label lbl2Action = new Label("Action");
        GridPane.setConstraints(lbl2Action, 0, 2);
        Label lbl3Action = new Label("Travel A");
        GridPane.setConstraints(lbl3Action, 0, 4);
        Label lbl4Action = new Label("Travel B");
        GridPane.setConstraints(lbl4Action, 0, 5);
        Label lbl5Action = new Label("OT");
        GridPane.setConstraints(lbl5Action, 0, 6);
        Label lbl6Action = new Label("OT Time");
        GridPane.setConstraints(lbl6Action, 0, 7);

        this.textFieldUissrAction = new TextField();
        GridPane.setConstraints(this.textFieldUissrAction, 1, 0);
        this.datePickerAction = new DatePicker();
        this.datePickerAction.setValue(LocalDate.now());
        this.datePickerAction.setMinWidth(this.textFieldUissrAction.getPrefWidth());
        GridPane.setConstraints(this.datePickerAction, 1, 1);
        this.textAreaActionAction = new TextArea();
        GridPane.setConstraints(this.textAreaActionAction, 1, 2, 2, 2);
        this.choiceBoxTravelTypeAAction = new ChoiceBox();
        this.choiceBoxTravelTypeAAction.getItems().addAll("Taxi", "Self Drive", "Public Traffic");
        this.choiceBoxTravelTypeAAction.getSelectionModel().select("Taxi");
        this.textFieldTravelDisAAction = new TextField();
        this.textFieldTravelDisAAction.setPromptText("Distanse");
        this.textFieldTravelDisAAction.setDisable(true);
        GridPane.setConstraints(this.choiceBoxTravelTypeAAction, 1, 4);
        GridPane.setConstraints(this.textFieldTravelDisAAction, 2, 4);
        this.choiceBoxTravelTypeBAction = new ChoiceBox();
        this.choiceBoxTravelTypeBAction.getItems().addAll("Taxi", "Self Drive", "Public Traffic");
        this.choiceBoxTravelTypeBAction.getSelectionModel().select("Taxi");
        this.textFieldTravelDisBAction = new TextField();
        this.textFieldTravelDisBAction.setPromptText("Distanse");
        this.textFieldTravelDisBAction.setDisable(true);
        GridPane.setConstraints(this.choiceBoxTravelTypeBAction, 1, 5);
        GridPane.setConstraints(this.textFieldTravelDisBAction, 2, 5);
        this.checkBoxOtAction = new CheckBox("OT");
        GridPane.setConstraints(this.checkBoxOtAction, 1, 6);
        this.textFieldOtFromAction = new TextField();
        this.textFieldOtFromAction.setPromptText("OT from");
        this.textFieldOtFromAction.setDisable(true);
        this.textFieldOtToAction = new TextField();
        this.textFieldOtToAction.setPromptText("OT to");
        this.textFieldOtToAction.setDisable(true);
        GridPane.setConstraints(this.textFieldOtFromAction, 1, 7);
        GridPane.setConstraints(this.textFieldOtToAction, 2, 7);
        this.buttonSaveAction = new Button("Save");
        this.buttonSaveAction.disableProperty().set(true);
        GridPane.setConstraints(this.buttonSaveAction, 3, 9);

        this.gridPaneAction.getChildren().addAll(lbl0Action,
                lbl1Action,
                lbl2Action,
                lbl3Action,
                lbl4Action,
                lbl5Action,
                lbl6Action,
                this.textFieldUissrAction,
                this.datePickerAction,
                this.textAreaActionAction,
                this.choiceBoxTravelTypeAAction,
                this.textFieldTravelDisAAction,
                this.choiceBoxTravelTypeBAction,
                this.textFieldTravelDisBAction,
                this.checkBoxOtAction,
                this.textFieldOtFromAction,
                this.textFieldOtToAction,
                this.buttonSaveAction);
        this.gridPaneAction.setVgap(5.0);
        this.gridPaneAction.setHgap(10.0);
        this.tabAction.setContent(this.gridPaneAction);
        //tab action over
        //tab part start
        this.gridPanePart = new GridPane();
        this.gridPanePart.setPadding(new Insets(10, 10, 10, 10));
        this.gridPanePart.getColumnConstraints().add(this.columnConstraints15);
        this.gridPanePart.getColumnConstraints().add(this.columnConstraints30);
        this.gridPanePart.getColumnConstraints().add(this.columnConstraints30);
        this.gridPanePart.getColumnConstraints().add(this.columnConstraints10);
        this.gridPanePart.getColumnConstraints().add(this.columnConstraints10);
        this.gridPanePart.getRowConstraints().add(this.rowConstraints); //uissr
        this.gridPanePart.getRowConstraints().add(this.rowConstraints); //date
        this.gridPanePart.getRowConstraints().add(this.rowConstraints); //inpn
        this.gridPanePart.getRowConstraints().add(this.rowConstraints); //insn
        this.gridPanePart.getRowConstraints().add(this.rowConstraints); //rmpn
        this.gridPanePart.getRowConstraints().add(this.rowConstraints); //rmsn
        this.gridPanePart.getRowConstraints().add(this.rowConstraints); //returned
        this.gridPanePart.getRowConstraints().add(this.rowConstraints);
        this.gridPanePart.getRowConstraints().add(this.rowConstraints);
        this.gridPanePart.getRowConstraints().add(this.rowConstraints); //button

        Label lbl0Part = new Label("UNISYS SR");
        GridPane.setConstraints(lbl0Part, 0, 0);
        Label lbl1Part = new Label("Date");
        GridPane.setConstraints(lbl1Part, 0, 1);
        Label lbl2Part = new Label("Installed PN");
        GridPane.setConstraints(lbl2Part, 0, 2);
        Label lbl3Part = new Label("Installed SN");
        GridPane.setConstraints(lbl3Part, 0, 3);
        Label lbl4Part = new Label("Removed PN");
        GridPane.setConstraints(lbl4Part, 0, 4);
        Label lbl5Part = new Label("Removed SN");
        GridPane.setConstraints(lbl5Part, 0, 5);
        Label lbl6Part = new Label("Returned");
        GridPane.setConstraints(lbl6Part, 0, 6);

        this.textFieldUissrPart = new TextField();
        this.textFieldUissrPart.setPromptText("UNISYS SR");
        GridPane.setConstraints(this.textFieldUissrPart, 1, 0);
        this.datePickerPart = new DatePicker();
        this.datePickerPart.setValue(LocalDate.now());
        GridPane.setConstraints(this.datePickerPart, 1, 1);
        this.textFieldInPnPart = new TextField();
        GridPane.setConstraints(this.textFieldInPnPart, 1, 2);
        this.textFieldInSnPart = new TextField();
        GridPane.setConstraints(this.textFieldInSnPart, 1, 3);
        this.textFieldRmPnPart = new TextField();
        GridPane.setConstraints(this.textFieldRmPnPart, 1, 4);
        this.textFieldRmSnPart = new TextField();
        GridPane.setConstraints(this.textFieldRmSnPart, 1, 5);
        this.choiceBoxReturnedPart = new ChoiceBox();
        this.choiceBoxReturnedPart.getItems().addAll("Returned", "At Customer Site", "At Home");
        this.choiceBoxReturnedPart.getSelectionModel().select("Returned");
        GridPane.setConstraints(this.choiceBoxReturnedPart, 1, 6);
        this.buttonSavePart = new Button("Save");
        this.buttonQuitPart = new Button("Quit");
        GridPane.setConstraints(this.buttonSavePart, 3, 9);
        GridPane.setConstraints(this.buttonQuitPart, 4, 9);
        this.gridPanePart.getChildren().addAll(lbl0Part,
                lbl1Part,
                lbl2Part,
                lbl3Part,
                lbl4Part,
                lbl5Part,
                lbl6Part,
                this.textFieldUissrPart,
                this.datePickerPart,
                this.textFieldInPnPart,
                this.textFieldInSnPart,
                this.textFieldRmPnPart,
                this.textFieldRmSnPart,
                this.choiceBoxReturnedPart,
                this.buttonSavePart,
                this.buttonQuitPart);
        this.gridPanePart.setVgap(5.0);
        this.gridPanePart.setHgap(10.0);
        this.tabPart.setContent(this.gridPanePart);
        //tab part over
        //tab srms start



        this.gridPaneSrms = new GridPane();
        this.gridPaneSrms.setPadding(new Insets(10, 10, 10, 10));
        this.gridPaneSrms.getColumnConstraints().add(this.columnConstraints15);
        this.gridPaneSrms.getColumnConstraints().add(this.columnConstraints30);
        this.gridPaneSrms.getColumnConstraints().add(this.columnConstraints30);
        this.gridPaneSrms.getColumnConstraints().add(this.columnConstraints10);
        this.gridPaneSrms.getColumnConstraints().add(this.columnConstraints10);
        this.gridPaneSrms.getRowConstraints().add(this.rowConstraints); //uissr row 0
        this.gridPaneSrms.getRowConstraints().add(this.rowConstraints); //date row 1
        this.gridPaneSrms.getRowConstraints().add(this.rowConstraints); //action row 2
        this.gridPaneSrms.getRowConstraints().add(this.rowConstraints); //travel distanse and travel time
        this.gridPaneSrms.getRowConstraints().add(this.rowConstraints); //co11 row 4
        this.gridPaneSrms.getRowConstraints().add(this.rowConstraints); //co26 row 5
        this.gridPaneSrms.getRowConstraints().add(this.rowConstraints); //co29 row 6
        this.gridPaneSrms.getRowConstraints().add(this.rowConstraints); //
        this.gridPaneSrms.getRowConstraints().add(this.rowConstraints);
        this.gridPaneSrms.getRowConstraints().add(this.rowConstraints); //button row 9

        Label lbl0Srms = new Label("UNISYS SR");
        GridPane.setConstraints(lbl0Srms, 0, 0);
        Label lbl1Srms = new Label("Date");
        GridPane.setConstraints(lbl1Srms, 0, 1);
        Label lbl2Srms = new Label("Action");
        GridPane.setConstraints(lbl2Srms, 0, 2);
        Label lbl3Srms = new Label("Travel");
        GridPane.setConstraints(lbl3Srms, 0, 3);
        Label lbl4Srms = new Label("Code11 Time");
        GridPane.setConstraints(lbl4Srms, 0, 4);
        Label lbl5Srms = new Label("Code26 Time");
        GridPane.setConstraints(lbl5Srms, 0, 5);
        Label lbl6Srms = new Label("Code29 Time");
        GridPane.setConstraints(lbl6Srms, 0, 6);

        this.textFieldUissrSrms = new TextField();
        this.textFieldUissrSrms.setPromptText("UNISYS SR");
        GridPane.setConstraints(this.textFieldUissrSrms, 1, 0);
        this.datePickerSrms = new DatePicker();
        this.datePickerSrms.setValue(LocalDate.now());
        GridPane.setConstraints(this.datePickerSrms, 1, 1);
        this.textFieldActionSrms = new TextField();
        GridPane.setConstraints(this.textFieldActionSrms, 1, 2, 2, 1);
        this.textFieldTrdiSrms = new TextField();
        this.textFieldTrdiSrms.setPromptText("Travel Distanse");
        GridPane.setConstraints(this.textFieldTrdiSrms, 2, 3);
        this.textFieldTrtiSrms = new TextField();
        this.textFieldTrtiSrms.setPromptText("Travel Time");
        GridPane.setConstraints(this.textFieldTrtiSrms, 1, 3);
        this.textFieldco11Srms = new TextField();
        GridPane.setConstraints(this.textFieldco11Srms, 1, 4);
        this.textFieldco26Srms = new TextField();
        GridPane.setConstraints(this.textFieldco26Srms, 1, 5);
        this.textFieldco29Srms = new TextField();
        GridPane.setConstraints(this.textFieldco29Srms, 1, 6);
        this.buttonSaveSrms = new Button("Save");
        this.buttonQuitSrms = new Button("Quit");
        GridPane.setConstraints(this.buttonSaveSrms, 3, 9);
        GridPane.setConstraints(this.buttonQuitSrms, 4, 9);
        this.gridPaneSrms.getChildren().addAll(lbl0Srms,
                lbl1Srms,
                lbl2Srms,
                lbl3Srms,
                lbl4Srms,
                lbl5Srms,
                lbl6Srms,
                this.textFieldUissrSrms,
                this.datePickerSrms,
                this.textFieldActionSrms,
                this.textFieldTrdiSrms,
                this.textFieldTrtiSrms,
                this.textFieldco11Srms,
                this.textFieldco26Srms,
                this.textFieldco29Srms,
                this.buttonSaveSrms,
                this.buttonQuitSrms);
        this.gridPaneSrms.setVgap(5.0);
        this.gridPaneSrms.setHgap(10.0);
        this.tabSrms.setContent(this.gridPaneSrms);
        //tab srms over
        this.tabPane = new TabPane();
        this.tabPane.setId("tabpane-input");
        this.tabPane.getTabs().addAll(this.tabMycase,
                this.tabAction,
                this.tabPart,
                this.tabSrms);
        this.tableViewCaseInfoTableView = new TableView();
        this.root = new HBox();
        this.root.getChildren().addAll(this.tabPane);
        this.root.setHgrow(this.tabPane, Priority.ALWAYS);

        //set event
        this.choiceBoxTravelTypeAAction.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (choiceBoxTravelTypeAAction.getSelectionModel().getSelectedItem().equals("Self Drive")) {
                    textFieldTravelDisAAction.setDisable(false);
                } else {
                    textFieldTravelDisAAction.setDisable(true);
                }
            }
        });

        this.choiceBoxTravelTypeBAction.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (choiceBoxTravelTypeBAction.getSelectionModel().getSelectedItem().equals("Self Drive")) {
                    textFieldTravelDisBAction.setDisable(false);
                } else {
                    textFieldTravelDisBAction.setDisable(true);
                }
            }
        });

        this.checkBoxOtAction.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (checkBoxOtAction.isSelected()) {
                    textFieldOtFromAction.setDisable(false);
                    textFieldOtToAction.setDisable(false);
                } else {
                    textFieldOtFromAction.setDisable(true);
                    textFieldOtToAction.setDisable(true);
                }
            }
        });

        this.buttonSaveAction.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (!textFieldTravelDisAAction.getText().isEmpty() && !isNumber(textFieldTravelDisAAction.getText())) {
                    textFieldTravelDisAAction.setStyle("-fx-background-color: RED;");

                } else if (!textFieldTravelDisBAction.getText().isEmpty() && !isNumber(textFieldTravelDisBAction.getText())) {
                    textFieldTravelDisBAction.setStyle("-fx-background-color: RED;");

                } else {
                    textFieldTravelDisAAction.setStyle("-fx-background-color: WHITE;");
                    textFieldTravelDisBAction.setStyle("-fx-background-color: WHITE;");
                    insTableAction();
                }
            }
        });

        this.textFieldUissrAction.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    //System.out.println("Textfield on focus");
                } else {
                    //System.out.println("Textfield out focus");
                    if (textFieldUissrAction.getText().length() == 8) {
                        buttonSaveAction.disableProperty().set(false);
                    } else {
                        buttonSaveAction.disableProperty().set(true);
                    }
                }
            }
        });

        //
        this.scene = new Scene(this.root, 600, 400);
        this.scene.getStylesheets().add(getClass().getResource("res/uCaseStyle.css").toExternalForm());
        this.setScene(this.scene);
        this.initModality(Modality.APPLICATION_MODAL);
        this.show();
    }

    private void initCore() {
        core = new UCore();
    }

    public void setFocusTabMycase() {
        this.tabPane.getSelectionModel().select(this.tabMycase);
    }

    public void setFocusTabAction() {
        this.tabPane.getSelectionModel().select(this.tabAction);
    }

    public void setFocusTabPart() {
        this.tabPane.getSelectionModel().select(this.tabPart);
    }

    public void setFocusTabSrms() {
        this.tabPane.getSelectionModel().select(this.tabSrms);
    }

    private void insTableAction() {
        //Map[] rows = new HashMap[1];
        Map<String, String> row = new HashMap<>();
        if (this.textFieldUissrAction.getText().length() >= 8) {
            //
            row.put("uissr", this.textFieldUissrAction.getText());
            //
            row.put("date", this.datePickerAction.getValue().toString());
            //
            if (!this.textAreaActionAction.getText().isEmpty()) {
                row.put("action", this.textAreaActionAction.getText());
            } else {
                row.put("action", "");
            }
            //
            row.put("traveltypea", this.choiceBoxTravelTypeAAction.getSelectionModel().getSelectedItem().toString());
            //
            if (!this.textFieldTravelDisAAction.getText().isEmpty()) {
                row.put("traveldisa", this.textFieldTravelDisAAction.getText());
            } else {
                row.put("traveldisa", "");
            }
            //
            row.put("traveltypeb", this.choiceBoxTravelTypeBAction.getSelectionModel().getSelectedItem().toString());
            //
            if (!this.textFieldTravelDisBAction.getText().isEmpty()) {
                row.put("traveldisb", this.textFieldTravelDisBAction.getText());
            } else {
                row.put("traveldisb", "");
            }
            //
            row.put("ot", this.checkBoxOtAction.isSelected() ? "1" : "0");
            //
            if (!this.textFieldOtFromAction.getText().isEmpty()) {
                row.put("otfrom", this.textFieldOtFromAction.getText());
            } else {
                row.put("otfrom", "");
            }
            //
            if (!this.textFieldOtToAction.getText().isEmpty()) {
                row.put("otto", this.textFieldOtToAction.getText());
            } else {
                row.put("otto", "");
            }
        }
        System.out.println(row.toString());
        Map[] rows = {row};
        this.core.setRowsAction(rows);

    }

    private boolean isNumber(String s) {
        boolean isint = true;
        boolean isdouble = true;
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            isint = false;
        }

        try {
            Double.parseDouble(s);
        } catch (NumberFormatException e) {
            isdouble = false;
        }

        return ((isint || isdouble) ? true : false);
    }
}

