    package JFAXChefe;
   
    import java.awt.TextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import DAO.ChefeDAO;
import Entidades.Chefe;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import viewChefe.viewChefe;

    public class FXMLControleChefe {
    	private viewChefe viewChefe = new viewChefe();
        private ChefeDAO ChefeDAO = new ChefeDAO();
        private ArrayList<Chefe> listaChefes = new ArrayList<Chefe>();	
        private Chefe chefeEscolhido;
    
        @FXML
        private TextField txtName;

        @FXML
        private TextField txtNumeroPis;

        @FXML
        private TextField txtNumeroCpf;

        @FXML
        private Button btnCadastrar;

        @FXML
        private TableView<Chefe> table;

        @FXML
        private TableColumn<?, ?> id;

        @FXML
        private TableColumn<?, ?> nome;

        @FXML
        private TableColumn<?, ?> cpf;

        @FXML
        private TableColumn<?, ?> pis;

        @FXML
        private Button btnExcluir;

        @FXML
        private Button btnEditar;
        
        @FXML
        private Button btnSair;
    
    
    public void initialize(URL location, ResourceBundle resources) {
	    id.setCellValueFactory(new PropertyValueFactory<>("ID"));
		nome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
		cpf.setCellValueFactory(new PropertyValueFactory<>("CPF"));
		refreshTable();
		
		table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Chefe>() {


			@Override
			public void changed(ObservableValue<? extends Chefe> observable, Chefe oldValue, Chefe newValue) {
				chefeEscolhido = newValue;
			}
		});
    }
	
    @FXML
    private void cadastrar(ActionEvent event) {
    	
    	Chefe Chefes = viewChefe.inserir(txtName.getText(), txtNumeroCpf.getText(), txtNumeroPis.getText());
    	ChefeDAO.inserirChefe(Chefes);
    	refreshTable();

    }
    
    @FXML
    private void refreshTable() {
		listaChefes = new ChefeDAO().listarChefe();
		ObservableList<Chefe> observableList = FXCollections.observableArrayList(listaChefes);
		table.setItems(observableList);	
	}
    
    @FXML
	 private void deletar() {
			ChefeDAO.excluirChefe(chefeEscolhido);
			refreshTable();	 
	 }
    
    @FXML
	 private void editarChefe() {
		 txtName.setText(chefeEscolhido.getNome());
		 txtNumeroCpf.setText(chefeEscolhido.getCPF());
		 txtNumeroPis.setText(chefeEscolhido.getPIS());
		 
		 ChefeDAO.alterarChefe(chefeEscolhido);
		 refreshTable();	
	 }
    
    @FXML
    private void editar() {
    	 chefeEscolhido.setNome(txtName.getText());
    	 chefeEscolhido.setCPF(txtNumeroCpf.getText());
    	 chefeEscolhido.setPIS(txtNumeroPis.getText());
		 
		 ChefeDAO.alterarChefe(chefeEscolhido);
		 refreshTable();	
	 }
    @FXML
   private  void sair(ActionEvent event ) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Sair");
    	String s ="Voce quer sair mesmo";
    	alert.setContentText(s);
    	
    	Optional<ButtonType> result = alert.showAndWait();
    	
    	if((result.isPresent()) && (result.get() == ButtonType.OK)){
    		System.exit(0);
    	
    }
    }
   

}



