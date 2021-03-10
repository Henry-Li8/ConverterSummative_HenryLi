package application;

import java.net.URL;

/*
 * Date: 1 Mars 2021
 * Nom de programmeur : Henry Li
 * Description: ce projet est une application qui convertira 4 unités différentes: argent, longueur, température et volume.
 */
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ConvertController implements Initializable{
	
		//déclaration de tous les éléments nécessaires à l'interface du programme
		//convertira 4 unités différentes: argent, longueur, température et volume.
		

	    @FXML
	    private TextField u1L;

	    @FXML
	    private ComboBox<String> c1C;

	    @FXML
	    private ComboBox<String> c2T;

	    @FXML
	    private ComboBox<String> c2C;

	    @FXML
	    private TextField u1C;

	    @FXML
	    private TextField u2T;

	    @FXML
	    private TextField u2C;

	    @FXML
	    private ComboBox<String> c1T;

	    @FXML
	    private TextField u1T;

	    @FXML
	    private ComboBox<String> c2L;

	    @FXML
	    private TextField u2L;

	    @FXML
	    private ComboBox<String> c1L;
	    
	    @FXML
	    private ComboBox<String> c1V;
	    
	    @FXML
	    private ComboBox<String> c2V;
	    
	    @FXML
	    private TextField u1V;

	    @FXML
	    private TextField u2V;


	    
	    //créer une liste d'unités, puis faire une liste de nombres, chacun correspondant à une unité dans sa liste respective et étant proportionnels les uns aux autres.
    
    private ObservableList<String> currency = (ObservableList<String>)FXCollections.observableArrayList("CAD", "USD", "EUR");
    double [] clist = {1.0, 0.75, 0.69};
    private ObservableList<String> length = (ObservableList<String>)FXCollections.observableArrayList("Kilometer", "Meter", "Centimeter", "Millimetre", "Mile", "Foot", "Inch");
    double [] llist = {0.001, 1.0, 100.0, 1000, 0.00062137, 3.28084, 39.37008};
    private ObservableList<String> temp = (ObservableList<String>)FXCollections.observableArrayList("Celcius", "Fahrenheit", "Celvin");
    private ObservableList<String> volume = (ObservableList<String>)FXCollections.observableArrayList("US liquid gallon", "US teaspoon", "Fluid ounce", "Cubic Meter", "Imperial cup", "Liter");
    double [] vlist = {0.264172, 202.884, 33.814, 0.001, 3.51951, 1.0};
    
    	//mettre la liste des choix dans la Combobox pour chaque unité, puis sélectionner le premier choix

    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		c1C.setItems(currency);
		c1C.getSelectionModel().selectFirst();
		c2C.setItems(currency);
		c2C.getSelectionModel().selectFirst();
		c1L.setItems(length);
		c1L.getSelectionModel().selectFirst();
		c2L.setItems(length);
		c2L.getSelectionModel().selectFirst();
		c1T.setItems(temp);
		c1T.getSelectionModel().selectFirst();
		c2T.setItems(temp);
		c2T.getSelectionModel().selectFirst();
		c1V.setItems(volume);
		c1V.getSelectionModel().selectFirst();
		c2V.setItems(volume);
		c2V.getSelectionModel().selectFirst();
	}
	
	//les lignes suivantes sont les méthodes à exécuter quand un nombre est tapé et quand une unité est commutée
	//currency
	@FXML
	void a1C() {
		convert(u1C, c1C, u2C, c2C, clist);
	}
	@FXML
	void a2C() {
		convert(u2C, c2C, u1C, c1C, clist);
	//length
	}
	@FXML
	void a1L() {
		convert(u1L, c1L, u2L, c2L, llist);
	}
	@FXML
	void a2L() {
		convert(u2L, c2L, u1L, c1L, llist);
	}
	//temperature
	@FXML
	void a1T() {
		tConvert(u1T, c1T, u2T, c2T);
	}
	@FXML
	void a2T() {
		tConvert(u2T, c2T, u1T, c1T);
	}
	//volume
	@FXML
	void a1V() {
		convert(u1V, c1V, u2V, c2V, vlist);
	}
	@FXML
	void a2V() {
		convert(u2V, c2V, u1V, c1V, vlist);
	}
	//exit le code
	@FXML
	void quit() {
		System.exit(0);
	}
	
	//une méthode qui est réutilisée dans la conversion de trois unités
	//elle fonctionne en trouvant combien d'unités que l'utilisateur veut convertir dans l'unité que l'utilisateur a entrée,
	//puis en multipliant la valeur donnée par le coefficient résultant pour trouver la valeur de l'unité convertie
	
	public void convert (TextField convertingText, ComboBox convertingUnit, TextField convertedText, ComboBox convertedUnit, double [] unit) {
		verify(convertingText);
		int t1 = convertingUnit.getSelectionModel().getSelectedIndex();
		int t2 = convertedUnit.getSelectionModel().getSelectedIndex();
		double rate = unit[t2]/unit[t1];
		double result = rate * Double.parseDouble(convertingText.getText());
		convertedText.setText(String.format("%.8f", result));
		}
	//cette méthode est appelée dans la méthode ci-dessus pour s'assurer que les non-nombres ne sont pas saisis dans le programme
	@FXML
	public void verify(TextField a) {
		if (a.getText().equals("")) {
			a.setText("0");
		}
		a.textProperty().addListener((observable, oldValue, newValue)->{
			if (!newValue.matches("^[0-9](\\.[0-9]+)?$+")) {
				a.setText(newValue.replaceAll("[^\\d*\\.]",""));
			}
		});
	}
	//cette méthode est spécifiquement adaptée à la conversion de l'unité de température, l'entrée est d'abord convertie en degrés Celsius,
	//puis convertie en l'unité souhaitée à partir de là.
	public void tConvert (TextField convertingText, ComboBox convertingUnit, TextField convertedText, ComboBox convertedUnit) {
		verify(convertingText);
		int t1 = convertingUnit.getSelectionModel().getSelectedIndex();
		int t2 = convertedUnit.getSelectionModel().getSelectedIndex();
		double temp = Double.parseDouble(convertingText.getText());
		if (t1 == 0) {
			
		}
		else if (t1 == 1) {
			temp = (temp - 32) * 5/9;
		}
		else {
			temp = temp - 273.15;
		}
		if (t2 == 0) {
			convertedText.setText(Double.toString(temp));
		}
		else if (t2 == 1) {
			convertedText.setText(Double.toString((temp * 9/5) + 32));
		}
		else {
			convertedText.setText(Double.toString(temp + 273.15));
		}
	}

}
