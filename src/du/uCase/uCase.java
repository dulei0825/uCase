package du.uCase;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class uCase extends Application {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub

        MiniBox root = new MiniBox();
        root.setId("minibox");
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("res/uCaseStyle.css").toExternalForm());
        primaryStage.setScene(scene);
        //primaryStage.setX(Toolkit.getDefaultToolkit().getScreenSize().getWidth()*0.5 - scene.getWidth());
        //primaryStage.setY(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.1);
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("uCase App");
        primaryStage.show();
        primaryStage.setAlwaysOnTop(true);
        root.stageWidth = primaryStage.getWidth();
        root.stageHeight = primaryStage.getHeight();
        root.setShow();

        root.setOnMouseEntered(e -> {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    root.setShow();
                }
            };

            thread.start();
        });
        root.setOnMouseExited(e -> {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    root.setHide();
                }
            };

            thread.start();
        });
    }
}
