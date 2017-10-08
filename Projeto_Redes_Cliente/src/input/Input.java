package input;

import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

// This class will return a path with the file

public class Input extends Application{

	private TextField textFieldFileChoosed;
	private File currentFile = null;
	private FileChooser fileChooser;
	
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {

		fileChooser = new FileChooser();
		fileChooser.setTitle("Escolha um arquivo");
		
		Button sendButton = new Button();
		sendButton.setText("Enviar");
		sendButton.setPrefSize(120, 30);
		sendButton.setOnAction(new EventHandler<ActionEvent>() {
			 public void handle(ActionEvent event) {
	             try {
	            	 if(currentFile.exists()) {
		            	   //Envia arquivo
		               }
	             }
	             catch(Exception e) {
	            	showAlert(); 
	             }
			 }
		});
		
		Button chooseButton = new Button();
		chooseButton.setText("Escolha o arquivo");
		chooseButton.setPrefSize(120, 30);
		chooseButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				chooseFile();
			}
		});
		
		// Configurando o grid
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 10, 10));
				
		Label label1 = new Label("Arquivo: ");
		label1.setPrefWidth(48);
		
		this.textFieldFileChoosed = new TextField("");
		this.textFieldFileChoosed.setEditable(false);
		textFieldFileChoosed.setMinSize(250, 30);
		
		HBox hbox = new HBox(10);
		hbox.setAlignment(Pos.CENTER_LEFT);
		hbox.getChildren().addAll(chooseButton,sendButton);
		
		
		grid.add(label1,0,0);
		grid.add(textFieldFileChoosed, 1,0,3,1);
		grid.add(hbox,1,1,4,1);
        
        Scene scene = new Scene(grid, 350, 150);
        stage.setResizable(false);
        stage.setTitle("Enviar Arquivo");
        stage.setScene(scene);
        stage.show();
        
	}
	
	private void showAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Informe");
		alert.setHeaderText("Por favor selecione um arquivo para enviar");
		alert.showAndWait();
	}
	
	private void chooseFile() {
		try {
			File temp = fileChooser.showOpenDialog(null);
			if(temp.exists()) {
				currentFile = temp;
				String name = currentFile.getName();
				textFieldFileChoosed.setText(name);
			}
		}
		catch(Exception e) {
			if(currentFile == null) {
				showAlert();
			}
		}
	}

}
