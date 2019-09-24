package dad.javafx.adivinapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdivinApp extends Application {
	
	private TextField introduceText;
	private Button comprobarButton;
	private Label formatoErroneoLabel;
	private Label pistaLabel;
	private Alert fallo;
	private Alert acierto;
	private Alert error;
	private int aleatorio;
	private int intentado;
	private int intentos = 0;
	
	public void start(Stage primaryStage) throws Exception {
		
		generaAleatorio();
		
		pistaLabel = new Label("Introduce un número del 1 al 100");
		
		introduceText = new TextField();
		introduceText.setMaxWidth(150);
		
		comprobarButton = new Button("COMPROBAR");
		comprobarButton.setDefaultButton(true);
		
		formatoErroneoLabel = new Label("");
		
		VBox root = new VBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(pistaLabel, introduceText, comprobarButton, formatoErroneoLabel);
		
		Scene scene = new Scene(root, 320, 200);
		primaryStage.setTitle("CheckPalindrome");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		comprobarButton.setOnAction( e -> onComprobarButtonAction(e));

	}

	private void onComprobarButtonAction(ActionEvent e) {
		
		comprobarIntroducido();
		
	}
	
	private void comprobarIntroducido() {
		
		try {
			this.intentado = Integer.parseInt(introduceText.getText());
			compruebaCorrecto();
		} catch (NumberFormatException e) {
			this.error = new Alert(AlertType.ERROR);
			error.setTitle("AdivinApp");
			error.setHeaderText("Error");
			error.setContentText("El número introducido no es válido.");

			error.showAndWait();
		}
		
	}
	
	private void compruebaCorrecto() {
		
		this.intentos++;
		
		if( this.aleatorio != this.intentado ) {
			if( this.intentado < this.aleatorio ) {
				fallo = new Alert(AlertType.WARNING);
				fallo.setTitle("AdivinApp");
				fallo.setHeaderText("¡Has fallado!");
				fallo.setContentText("El número a adivinar es mayor que " + this.intentado + ", vuelve a intentarlo.");

				fallo.showAndWait();
			}else {
				fallo = new Alert(AlertType.WARNING);
				fallo.setTitle("AdivinApp");
				fallo.setHeaderText("¡Has fallado!");
				fallo.setContentText("El número a adivinar es menor que " + this.intentado + ", vuelve a intentarlo.");

				fallo.showAndWait();
			}
			
			this.intentado = 0;
		}else {
			this.acierto = new Alert(AlertType.INFORMATION);
			acierto.setTitle("AdivinApp");
			acierto.setHeaderText("¡Has ganado!");
			acierto.setContentText("Sólo has necesitado " + this.intentos + " intentos, vuelve a jugar y hazlo mejor.");

			acierto.showAndWait();
			reinicia();
		}
		
	}
	
	private void reinicia() {
		
		generaAleatorio();
		this.intentos = 0;
		
	}

	private void generaAleatorio() {
		
		this.aleatorio = (int)(Math.random() * 100 + 1);
		System.out.println(this.aleatorio);
		
	}

	public static void main(String[] args) {
		
		launch(args);

	}

}
