package application.view.venta.cruds;

import application.model.calculo.Finca;
import application.model.calculo.Ingenio;
import application.model.info.Empleado;
import application.model.venta.Viaje;
import application.repository.calculo.FincaRepository;
import application.repository.calculo.IngenioRepository;
import application.repository.info.EmpleadoRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.*;

import javax.xml.crypto.dom.DOMCryptoContext;
import java.util.List;


public class ViajeEditController {

    @FXML
    private TableView<Finca> origenTable;
    @FXML
    private TableView<Ingenio> destinoTable;
    @FXML
    private TableColumn<Finca, String > fincaColumn;
    @FXML
    private TableColumn<Ingenio, String> ingenioNombreColumn;
    @FXML
    private TableColumn<Ingenio, String> arranqueColumn;
    @FXML
    private TableColumn<Ingenio, String> tarifaColumn;
    @FXML
    private DatePicker diaPicker;
    @FXML
    private ComboBox<String> horaCombo;
    @FXML
    private ComboBox<String> minutosCombo;
    @FXML
    private TextField taraTextField;
    @FXML
    private TextField brutoTextField;
    @FXML
    private ComboBox<String> conductorCombo;
    @FXML
    private Label pesoNetoLabel;
    @FXML
    private Label distanciaLabel;
    @FXML
    private Label montoLabel;

    private boolean isNew;
    private FincaRepository fincaRepository = new FincaRepository();
    private IngenioRepository ingenioRepository = new IngenioRepository();
    private EmpleadoRepository conductorRepository = new EmpleadoRepository();

    private Stage dialogStage;
    private boolean okClicked;

    List<Empleado> conductoresList = conductorRepository.getEmpleadosByCategoriaEmpleado(2);// todo Hardcodeado
    List<Finca> fincasList = fincaRepository.view();
    List<Ingenio> ingeniosList = ingenioRepository.view();
    public void setIsNew(boolean aNew){this.isNew = aNew;}

    @FXML
    private void initialize(){
        setHoraCombo(); setMinutosCombo();
        fincaColumn.setCellValueFactory(cellData->cellData.getValue().nombreProperty());
        ingenioNombreColumn.setCellValueFactory(cellData->cellData.getValue().nombreProperty());
        arranqueColumn.setCellValueFactory(cellData->cellData.getValue().arranqueProperty().asString());
        tarifaColumn.setCellValueFactory(cellData->cellData.getValue().tarifaProperty().asString());
        setConductorComboBox();

    }

    public void setConductorComboBox(){
        ObservableList<String> conList = FXCollections.observableArrayList();
        for (Empleado conductor : conductoresList) conList.add(conductor.getNombre() + " " + conductor.getApellido());
        conductorCombo.setItems(conList);
    }

   public void setHoraCombo(){
        ObservableList<String> horaList = FXCollections.observableArrayList();
        for(int i = 0; i < 24 ; i++)
            horaList.add( i < 10 ? "0"+i : ""+i);
   }

    public void setMinutosCombo(){
        ObservableList<String> minutosList = FXCollections.observableArrayList();
        for(int i = 0; i < 60 ; i++)
            minutosList.add( i < 10 ? "0"+i : ""+i);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setViaje(Viaje viaje){}

    public boolean isOkClicked(){return okClicked;}

    @FXML
    private void handleOk(){

    }

    @FXML
    private void handleCancel(){

    }

    private boolean isInputValid(){
        return false;
    }

}
