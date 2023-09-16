package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewPhieuNhap extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getResource("PhieuNhap.fxml"));
		primaryStage.setScene(new Scene(root));
		primaryStage.setResizable(true);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
