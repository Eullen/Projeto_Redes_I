package input;

import java.io.File;
import java.io.FileInputStream;

import Cliente.Arquivo;
import Cliente.ClienteSocket;
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

public class Input extends Application {

	private TextField textFieldFileChoosed;
	private File currentFile = null;
	private FileChooser fileChooser;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		this.fileChooser = new FileChooser();
		this.fileChooser.setTitle("Escolha um arquivo");

		Button sendButton = new Button();
		sendButton.setText("Enviar");
		sendButton.setPrefSize(120, 30);
		sendButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					if (Input.this.currentFile.exists()) {
						byte[] conteudo = new byte[(int) Input.this.currentFile.length()];
						FileInputStream fis = new FileInputStream(Input.this.currentFile);
						fis.read(conteudo);
						fis.close();

						Arquivo arq = new Arquivo();
						arq.setNomeArquivo(Input.this.currentFile.getName());
						arq.setConteudo(conteudo);
						arq.setIpServer("127.0.0.1");
						arq.setPortaServer(6060);
						arq.setTamanhoArquivo(conteudo.length);

						ClienteSocket client = new ClienteSocket();
						if (client.enviarArquivo(arq)) {
							// renderizar mensagem de sucesso
						} else {
							// renderizar mensagem de fracasso
						}

					}
				} catch (Exception e) {
					Input.this.showAlert();
				}
			}
		});

		Button chooseButton = new Button();
		chooseButton.setText("Escolha o arquivo");
		chooseButton.setPrefSize(120, 30);
		chooseButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Input.this.chooseFile();
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
		this.textFieldFileChoosed.setMinSize(250, 30);

		HBox hbox = new HBox(10);
		hbox.setAlignment(Pos.CENTER_LEFT);
		hbox.getChildren().addAll(chooseButton, sendButton);

		grid.add(label1, 0, 0);
		grid.add(this.textFieldFileChoosed, 1, 0, 3, 1);
		grid.add(hbox, 1, 1, 4, 1);

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
			File temp = this.fileChooser.showOpenDialog(null);
			if (temp.exists()) {
				this.currentFile = temp;
				String name = this.currentFile.getName();
				this.textFieldFileChoosed.setText(name);
			}
		} catch (Exception e) {
			if (this.currentFile == null) {
				this.showAlert();
			}
		}
	}

}
