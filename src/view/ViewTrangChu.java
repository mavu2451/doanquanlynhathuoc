package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewTrangChu  extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception{
//		try {
//			BorderPane root = new BorderPane();
//			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
				Parent root = FXMLLoader.load(getClass().getResource("TrangChu.fxml"));
				primaryStage.setScene(new Scene(root));
				primaryStage.setResizable(false);
				primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
