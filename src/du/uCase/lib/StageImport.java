package du.uCase;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class StageImport extends Stage {

    //define component

    private Scene scene;
    private VBox root;
    private Accordion accordion;
    private TitledPane titledPaneImportXls;
    //define gridpane start
    private GridPane gridPaneImportXls;
    private RowConstraints rowConstraints0;
    private ColumnConstraints columnConstraints0;
    private ColumnConstraints columnConstraints1;
    private ColumnConstraints columnConstraints2;
    //define gridpane over
    private Label labelFilePath;
    private TextField textFieldFilePath;
    private Button buttonChooseFile;
    private Button buttonImportXls;
    private TitledPane titledPaneDbCount;
    private TextArea textAreaMessage;

    private UCore core;

    public StageImport() {
        super();
        this.initGUI();
        this.initCore();
    }

    private void initGUI() {
        //assemble pane import xls
        this.gridPaneImportXls = new GridPane();
        //this.gridPaneImportXls.setGridLinesVisible(true);
        this.gridPaneImportXls.setPadding(new Insets(18, 18, 18, 18));

        this.rowConstraints0 = new RowConstraints();
        this.columnConstraints0 = new ColumnConstraints();
        this.columnConstraints1 = new ColumnConstraints();
        this.columnConstraints2 = new ColumnConstraints();
        this.rowConstraints0.setPercentHeight(50);
        this.rowConstraints0.setFillHeight(true);
        this.columnConstraints0.setPercentWidth(10);
        this.columnConstraints0.setFillWidth(true);
        this.columnConstraints1.setPercentWidth(70);
        this.columnConstraints1.setFillWidth(true);
        this.columnConstraints2.setPercentWidth(20);
        this.columnConstraints2.setFillWidth(true);
        this.columnConstraints2.setHalignment(HPos.CENTER);
        this.gridPaneImportXls.getRowConstraints().add(this.rowConstraints0);
        this.gridPaneImportXls.getRowConstraints().add(this.rowConstraints0);
        this.gridPaneImportXls.getColumnConstraints().add(this.columnConstraints0);
        this.gridPaneImportXls.getColumnConstraints().add(this.columnConstraints1);
        this.gridPaneImportXls.getColumnConstraints().add(this.columnConstraints2);

        this.labelFilePath = new Label("Path");
        GridPane.setMargin(this.labelFilePath, new Insets(10, 10, 10, 10));
        GridPane.setConstraints(this.labelFilePath, 0, 0);

        this.textFieldFilePath = new TextField();
        GridPane.setConstraints(this.textFieldFilePath, 1, 0);

        this.buttonChooseFile = new Button("File...");
        GridPane.setConstraints(this.buttonChooseFile, 2, 0);

        this.buttonImportXls = new Button("Import");
        GridPane.setConstraints(this.buttonImportXls, 2, 1);

        this.gridPaneImportXls.getChildren().addAll(this.labelFilePath,
                this.textFieldFilePath,
                this.buttonChooseFile,
                this.buttonImportXls);

        this.titledPaneImportXls = new TitledPane();
        this.titledPaneImportXls.setText("Import From XLS");
        this.titledPaneImportXls.setContent(this.gridPaneImportXls);

        this.titledPaneDbCount = new TitledPane();
        this.titledPaneDbCount.setText("Check DB Count");

        this.accordion = new Accordion();
        this.accordion.getPanes().addAll(this.titledPaneImportXls,
                this.titledPaneDbCount);

        this.textAreaMessage = new TextArea();

        //assemble
        this.root = new VBox();
        this.root.getChildren().addAll(this.accordion,
                this.textAreaMessage);

        this.root.setVgrow(this.accordion, Priority.ALWAYS);

        this.scene = new Scene(root, 600, 400);
        this.setScene(this.scene);
        //set event

        this.buttonChooseFile.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                chooseFile();
            }
        });

        this.buttonImportXls.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                core.setRowsFullcase(textFieldFilePath.getText().trim(), textAreaMessage);
            }
        });

        this.setTitle("uCase - Import");
        this.show();
        //final show

    }

    private void initCore() {
        core = new UCore();
    }

    private void chooseFile() {
        File file;
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Open XLS File");
        file = filechooser.showOpenDialog(this);
        if (file != null) {
            this.textAreaMessage.appendText("File: " + file.getPath() + " selected. \n");
            this.textFieldFilePath.setText(file.getPath());
        }
    }
    
    protected void setFocusTitledPaneImportXls() {
        this.titledPaneImportXls.setExpanded(true);
    }
}

