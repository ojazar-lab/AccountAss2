/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import Model.Accounts;
import Model.AccountsDAO;
import View.ViewManager;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Yahya
 */
public class AccountsManagmentController implements Initializable {

    @FXML
    private Button usersManagmentPageBtn;
    @FXML
    private Button accountsPageBtn;
    @FXML
    private Button operationsPageBtn;
    @FXML
    private Button createNewAccountrBtn;
    @FXML
    private Button showAllAccountsBtn;
    @FXML
    private Button updateSelectedAccountBtn;
    @FXML
    private Button deleteSelectedAccountBtn;
    @FXML
    private Button searchAccountBtn;
    @FXML
    private TextField accontSearchTF;
    @FXML
    private TableView<Accounts> accountsTableView;
    @FXML
    private TableColumn<Accounts, Integer> accountIdColumn;
    @FXML
    private TableColumn<Accounts, Integer> userIdColumn;
    @FXML
    private TableColumn<Accounts, Integer> accountNumberColumn;
    @FXML
    private TableColumn<Accounts, String> usernameColumn;
    @FXML
    private TableColumn<Accounts, String> currencyColumn;
    @FXML
    private TableColumn<Accounts, Double> balanceColumn;
    @FXML
    private TableColumn<Accounts, Date> creationDateColumn;

    private AccountsDAO accountsDAO;
    private ObservableList<Accounts> accountsList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        accountsDAO = new AccountsDAO();
        initializeTableView();
        loadAllAccounts();
    }

    @FXML
    private void showUsersManagmentPage(ActionEvent event) {
        ViewManager.adminPage.changeSceneToUsersManagment();
    }

    @FXML
    private void showAccountsPage(ActionEvent event) {
    }

    @FXML
    private void showOperationsPage(ActionEvent event) {
    }

    @FXML
    private void showAccountCreationPage(ActionEvent event) {
    }

    @FXML
    private void showAllAccounts(ActionEvent event) {
        loadAllAccounts();
    }

    @FXML
    private void updateSelectedAccount(ActionEvent event) {
        Accounts selectedAccount = accountsTableView.getSelectionModel().getSelectedItem();
        if (selectedAccount != null) {
            // Retrieve the updated account details from the user input fields
            // and update the selected account
            String username = ""; // Get the updated username
            String currency = ""; // Get the updated currency
            double balance = 0.0; // Get the updated balance

            selectedAccount.setUsername(username);
            selectedAccount.setCurrency(currency);
            selectedAccount.setBalance(balance);

            // Update the account in the database
            accountsDAO.updateAccount(selectedAccount);

            // Refresh the TableView
            accountsTableView.refresh();
        }
    }

    @FXML
    private void deleteSelectedAccount(ActionEvent event) {
        Accounts selectedAccount = accountsTableView.getSelectionModel().getSelectedItem();
        if (selectedAccount != null) {
            // Delete the selected account from the database
            accountsDAO.deleteAccount(selectedAccount);

            // Remove the account from the TableView
            accountsList.remove(selectedAccount);
        }
    }

    @FXML
    private void searchForAnAccount(ActionEvent event) {
        
    }

    private void loadAllAccounts() {
        List<Accounts> allAccounts = accountsDAO.getAllAccounts();
        accountsList.clear();
        accountsList.addAll(allAccounts);
    }

    private void initializeTableView() {
        accountIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        accountNumberColumn.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        currencyColumn.setCellValueFactory(new PropertyValueFactory<>("currency"));
        balanceColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));
        creationDateColumn.setCellValueFactory(new PropertyValueFactory<>("creationDate"));

        accountsList = FXCollections.observableArrayList();
        accountsTableView.setItems(accountsList);
    }

}
