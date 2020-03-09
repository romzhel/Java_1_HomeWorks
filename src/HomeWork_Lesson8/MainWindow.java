package HomeWork_Lesson8;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));
        GameArea gameArea = new GameArea();

        HBox buttonArea = new HBox();
        buttonArea.setAlignment(Pos.CENTER);
        buttonArea.setSpacing(60);
        Button btnStartNewGame = new Button("Новая игра");
        btnStartNewGame.setPrefWidth(250);
        btnStartNewGame.setOnAction(event -> {
            GameParameters gameParameters = new GameParametersSelectionWindow().getGameParameters();
            gameArea.init(gameParameters);
            PlayersTurnsProcessor.startGame(gameArea, gameParameters);
        });

        Button btnExit = new Button("Выход");
        btnExit.setPrefWidth(250);
        btnExit.setOnAction((event) -> primaryStage.close());

        buttonArea.getChildren().addAll(btnStartNewGame, btnExit);

        root.getChildren().addAll(gameArea, buttonArea);


        Scene scene = new Scene(root, 620, 655);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Игра Крестики-нолики");
        primaryStage.show();
    }
}
