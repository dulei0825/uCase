package du.uCase;

import java.awt.Toolkit;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

class MiniBox extends VBox {

    private static StageMycase stageMycase = null;
    private static StageFullcase stageFullcase = null;
    private static StageImport stageImport = null;
    private static StageInput stageInput = null;

    public double stageWidth;
    public double stageHeight;
    private double screenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private double screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    private HBox hBox;
    private Button buttonUCase;
    private MenuButton menuButtonTools;
    private MenuItem menuItemFullcase;
    private MenuItem menuItemFtpconvert;
    private MenuButton menuButtonShortcut;
    private TextField textFieldCmd;
    private Button buttonExit;
    private UCore core = new UCore();

    public MiniBox() {
        super(20);
        this.initGUI();
    }

    private void initGUI() {
        this.hBox = new HBox(5);
        this.buttonUCase = new Button("uCase");
        this.menuButtonShortcut = new MenuButton("Shortcuts");
        this.menuButtonTools = new MenuButton("Tools");
        this.menuItemFullcase = new MenuItem("Manage Fullcase");
        this.menuItemFtpconvert = new MenuItem("Convert FTP to HTTP");
        this.menuButtonTools.getItems().addAll(this.menuItemFullcase, this.menuItemFtpconvert);
        this.buttonExit = new Button("Exit");
        this.textFieldCmd = new TextField();

        this.hBox.getChildren().addAll(this.buttonUCase,
                this.menuButtonShortcut,
                this.menuButtonTools,
                this.textFieldCmd,
                this.buttonExit);
        this.getChildren().addAll(this.hBox);

        this.buttonUCase.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                showStageMycase();
            }
        });

        this.menuItemFullcase.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                showStageFullcase();
            }
        });

        this.buttonExit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        this.menuItemFtpconvert.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                core.mytest();
            }
        });
    }

    public void setShow() {
        this.getScene().getWindow().setX(screenWidth * 0.5 - this.stageWidth * 0.5);
        this.getScene().getWindow().setY(0.0);
    }

    public void setHide() {
        this.getScene().getWindow().setX(screenWidth * 0.5 - this.stageWidth * 0.5);
        this.getScene().getWindow().setY(1 - stageHeight);
    }

    public static void showStageMycase() {
        if (stageMycase == null || !stageMycase.isShowing()) {
            stageMycase = new StageMycase();
        } else {
            stageMycase.setIconified(false);
            stageMycase.toFront();
        }
    }

    public static void showStageFullcase() {
        if (stageFullcase == null || !stageFullcase.isShowing()) {
            stageFullcase = new StageFullcase();
        } else {
            stageFullcase.setIconified(false);
            stageFullcase.toFront();
        }
    }

    public static void showStageImport() {
        if (stageImport == null || !stageImport.isShowing()) {
            stageImport = new StageImport();
        } else {
            stageImport.setIconified(false);
            stageImport.toFront();
        }
    }

    public static void showStageInput() {
        if (stageInput == null || !stageInput.isShowing()) {
            stageInput = new StageInput();

        } else {
            stageInput.setIconified(false);
            stageInput.toFront();
        }
    }

    public static void showStageInput(String tablename) {
        if (stageInput == null || !stageInput.isShowing()) {
            stageInput = new StageInput();

        } else {
            stageInput.setIconified(false);
            stageInput.toFront();
        }

        switch(tablename) {
            case "MYCASE":
                stageInput.setFocusTabMycase();
                break;
            case "ACTION":
                stageInput.setFocusTabAction();
                break;
            case "PART":
                stageInput.setFocusTabPart();
                break;
            case "SRMS":
                stageInput.setFocusTabSrms();
                break;
        }
    }

    public static void setFocusStageImportXls() {
        stageImport.setFocusTitledPaneImportXls();
    }
}

