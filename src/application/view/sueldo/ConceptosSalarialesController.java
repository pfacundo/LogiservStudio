package application.view.sueldo;

import application.Main;
import application.model.sueldo.ConceptoSueldo;
import application.repository.sueldo.ConceptoSueldoRepository;
import application.view.sueldo.cruds.ConceptoEditController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConceptosSalarialesController implements Initializable {
    private Stage owner;
    @FXML
    private TableView<ConceptoSueldo> conceptoSueldoTableView;
    @FXML
    private TableColumn<ConceptoSueldo, String> idConceptoSueldoColumn;
    @FXML
    private TableColumn<ConceptoSueldo, String> descripcionColumn;
    @FXML
    private TableColumn<ConceptoSueldo, String> cantidadColumn;
    @FXML
    private TableColumn<ConceptoSueldo, String> tipoConceptoColumn;
    @FXML
    private TableColumn<ConceptoSueldo, String> tipoCantidadColumn;

    ConceptoSueldoRepository conceptoSueldoRepository = new ConceptoSueldoRepository();
    ObservableList<ConceptoSueldo> conceptoSueldos = FXCollections.observableArrayList();





















    public void setOwner(Stage owner){
        this.owner = owner;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarTable();
        idConceptoSueldoColumn.setCellValueFactory(cellData -> cellData.getValue().idConceptoSueldoProperty().asString());
        descripcionColumn.setCellValueFactory(cellData -> cellData.getValue().descripcionProperty());
        cantidadColumn.setCellValueFactory(cellData -> cellData.getValue().cantidadProperty().asString());
        tipoConceptoColumn.setCellValueFactory(cellData -> cellData.getValue().tipoConceptoProperty());
        tipoCantidadColumn.setCellValueFactory(cellData -> cellData.getValue().tipoCantidadProperty());

    }

    private void cargarTable() {
        conceptoSueldos = conceptoSueldoRepository.view();
        conceptoSueldoTableView.setItems(conceptoSueldos);
    }
    @FXML
    public void handleNew(){
        ConceptoSueldo temp = new ConceptoSueldo();
        boolean okClicked = this.showEdit(temp,true);
        if(okClicked)
            conceptoSueldos.add(temp);
    }
    private boolean showEdit(ConceptoSueldo conceptoSueldo, boolean b) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/sueldo/cruds/ConceptoEdit.fxml"));
            Group page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            if (b)
                dialogStage.setTitle("Nuevo Concepto Salarial");
            else
                dialogStage.setTitle("Editar Concepto Salarial");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(owner);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);


            ConceptoEditController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setIsNew(b);
            controller.setDatos(conceptoSueldo);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
