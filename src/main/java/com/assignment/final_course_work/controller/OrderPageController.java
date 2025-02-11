package com.assignment.final_course_work.controller;

import com.assignment.final_course_work.bo.BOFactory;
import com.assignment.final_course_work.bo.Custom.impl.OrderBOImpl;
import com.assignment.final_course_work.bo.CustomerBO;
import com.assignment.final_course_work.bo.MedicineBO;
import com.assignment.final_course_work.bo.OrderBO;
import com.assignment.final_course_work.dao.Custom.CustomerDAO;
import com.assignment.final_course_work.dao.Custom.MedicineDAO;
import com.assignment.final_course_work.dao.Custom.OrderDAO;
import com.assignment.final_course_work.dao.Custom.impl.CustomerDAOImpl;
import com.assignment.final_course_work.dao.Custom.impl.MedicineDAOImpl;
import com.assignment.final_course_work.dao.Custom.impl.OrderDAOImpl;
import com.assignment.final_course_work.db.DBConnection;
import com.assignment.final_course_work.dto.OrderDTO;
import com.assignment.final_course_work.dto.OrderDetailDTO;
import com.assignment.final_course_work.entity.Medicine;
import com.assignment.final_course_work.entity.Order;
import com.assignment.final_course_work.entity.OrderDetail;
import com.assignment.final_course_work.util.Regex;
import com.assignment.final_course_work.view.tdm.OrderTM;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class OrderPageController implements Initializable {

    @FXML
    private JFXButton btnAddToCart;

    @FXML
    private JFXButton btnItemRemove;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private JFXButton btnReset;

    @FXML
    private ComboBox<String> cmbCustomer;

    @FXML
    private ComboBox<String> cmbMedicine;

    @FXML
    private TableColumn<OrderTM, String> colMedicineID;

    @FXML
    private TableColumn<OrderTM, String> colMedicineName;

    @FXML
    private TableColumn<OrderTM, Integer> colMedicineQty;

    @FXML
    private TableColumn<OrderTM, Double> colMedicineUnitPrice;

    @FXML
    private TableColumn<OrderTM, Integer> colPackageQty;

    @FXML
    private TableColumn<OrderTM, Double> colPackageUnitPrice;

    @FXML
    private TableColumn<OrderTM, Integer> colTotal;

    @FXML
    private AnchorPane dashboardPage;

    @FXML
    private Text lblCatogary;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private Text lblCustomerName;

    @FXML
    private Text lblOrderDate;

    @FXML
    private Text lblOrderID;

    @FXML
    private Text lblQOHMedicine;

    @FXML
    private Text lblQOHPackage;

    @FXML
    private Label lblShowPrice;

    @FXML
    private TableView<OrderTM> tblOrder;

    @FXML
    private Text txtPrice;

    @FXML
    private TextField txtQtyOfMedi;

    @FXML
    private TextField txtQtyOfPack;

    private double fullAmount;

    private double totalPrice;

    private MedicineBO medicineBO = (MedicineBO) BOFactory.getInstance().getBO(BOFactory.BOType.MEDICINE);
    private CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOType.CUSTOMER);
    private OrderBO orderBO = (OrderBO) BOFactory.getInstance().getBO(BOFactory.BOType.ORDER);

    private final ObservableList<OrderTM> cartTMS = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnItemRemove.setDisable(true);
        lblOrderDate.setText(String.valueOf(LocalDate.now()));
        try {
            lblOrderID.setText(orderBO.generateNewID());
            loadCustomerNames();
            loadMedicinesNames();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        colMedicineID.setCellValueFactory(new PropertyValueFactory<>("medicineID"));
        colMedicineName.setCellValueFactory(new PropertyValueFactory<>("medicineName"));
        colPackageQty.setCellValueFactory(new PropertyValueFactory<>("packQty"));
        colMedicineQty.setCellValueFactory(new PropertyValueFactory<>("packPrice"));
        colMedicineUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitQty"));
        colPackageUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        tblOrder.setItems(cartTMS);

        try{
            refreshTable();
        } catch (SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR,"Failed to lead data...!").show();
        }
    }

    private void refreshTable() throws SQLException, ClassNotFoundException {
        lblOrderID.setText(orderBO.generateNewID());
        lblOrderDate.setText(LocalDate.now().toString());

        loadCustomerNames();
        loadMedicinesNames();

        cmbMedicine.getSelectionModel().clearSelection();
        cmbCustomer.getSelectionModel().clearSelection();
        lblCustomerName.setText("");
        txtPrice.setText("");
        lblQOHPackage.setText("");
        lblQOHMedicine.setText("");
        txtQtyOfMedi.clear();
        txtQtyOfPack.clear();
        lblCatogary.setText("");
        lblShowPrice.setText("");
        lblUnitPrice.setText("");

        cartTMS.clear();
        tblOrder.refresh();
    }

    private void loadMedicinesNames() throws SQLException, ClassNotFoundException {
//        ArrayList<String> medicineNames = medicineDAO.getAllName();
//        ObservableList<String> observableList = FXCollections.observableArrayList();
//        observableList.addAll(medicineNames);
//        cmbMedicine.setItems(observableList);
        ObservableList<String> observableList = FXCollections.observableArrayList(medicineBO.getAllName());
        cmbMedicine.setItems(observableList);
    }

    private void loadCustomerNames() throws SQLException, ClassNotFoundException {
//        ArrayList<String> customerNames = customerDAO.getAllName();
//        ObservableList<String> observableList = FXCollections.observableArrayList();
//        observableList.addAll(customerNames);
//        cmbMedicine.setItems(observableList);

        ObservableList<String> observableList = FXCollections.observableArrayList(customerBO.getAllName());
        cmbCustomer.setItems(observableList);

    }


    @FXML
    void addToCart(ActionEvent event) {
        try {
            String customerName = cmbCustomer.getValue();
            String medicineName = cmbMedicine.getValue();

            String medicineID = medicineBO.getMedicineID(medicineName);

            if (medicineName == null || customerName == null) {
                new Alert(Alert.AlertType.ERROR, "Please Select Customer and Medicine").show();
                return;
            } else {
                boolean isQtyPackValid = false;
                boolean isQtyOfMediValid = false;

                if (new Regex().isTextValid(txtQtyOfPack.getText(), "qty")) {
                    isQtyPackValid = Integer.parseInt(txtQtyOfPack.getText()) <= Integer.parseInt(lblQOHPackage.getText());
                }
                if (new Regex().isTextValid(txtQtyOfMedi.getText(), "qty")) {
                    isQtyOfMediValid = Integer.parseInt(txtQtyOfMedi.getText()) <= Integer.parseInt(lblQOHMedicine.getText());
                }

                txtQtyOfPack.setStyle("-fx-border-color: none;");
                txtQtyOfMedi.setStyle("-fx-border-color: none;");

                if (!isQtyOfMediValid) {
                    txtQtyOfMedi.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
                    txtQtyOfMedi.requestFocus();
                }
                if (!isQtyPackValid) {
                    txtQtyOfPack.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
                    txtQtyOfPack.requestFocus();
                }

                int mediQty = Integer.parseInt(txtQtyOfMedi.getText());
                int packQty = Integer.parseInt(txtQtyOfPack.getText());

                if ((isQtyOfMediValid && isQtyPackValid) && (!(mediQty == 0 && packQty == 0))) {

                    double medicinePrice = Double.parseDouble(lblUnitPrice.getText());
                    double medicinePackPrice = medicinePrice * 10;

                    int medicine = Integer.parseInt(txtQtyOfMedi.getText());
                    int packs = Integer.parseInt(txtQtyOfPack.getText());

                    double medicineTotal = medicinePrice * (double)medicine;
                    double packageTotal = medicinePackPrice * (double)packs;
                    double totalAmount = medicineTotal + packageTotal;


//                    lblShowPrice.setText(String.valueOf(fullAmount));

                    for (OrderTM orderTM : cartTMS){

                        if (orderTM.getMedicineName().equals(medicineName)) {

                            double addNewPrice = 0;

                            int newMediQty = orderTM.getUnitQty() + medicine;
                            int newPackQty = orderTM.getPackQty() + packs;


                            orderTM.setUnitQty(newMediQty);
                            orderTM.setPackQty(newPackQty);

                            double newMediPrice = medicinePrice * (double)newMediQty;
                            double newPackPrice = medicinePackPrice * (double)newPackQty;

                            double newTotal = newMediPrice + newPackPrice;
                            addNewPrice = newMediPrice + newPackPrice;

                            orderTM.setUnitPrice(newMediPrice);
                            orderTM.setPackPrice(newPackPrice);
                            orderTM.setTotalPrice(newTotal);

                            fullAmount += addNewPrice;

                            tblOrder.refresh();
                            return;
                        }
                    }

//                    lblShowPrice.setText(String.valueOf(fullAmount));

                    OrderTM newOrderTM = new OrderTM(
                            medicineID,
                            medicineName,
                            packs,
                            packageTotal,
                            medicine,
                            medicineTotal,
                            totalAmount
                    );

                    fullAmount += newOrderTM.getTotalPrice();

                    lblShowPrice.setText(String.valueOf(fullAmount));
                    cartTMS.add(newOrderTM);

                }
            }

            txtQtyOfPack.setText("");
            txtQtyOfMedi.setText("");

        } catch (NullPointerException e){
            throw new NullPointerException();
        } catch (RuntimeException e){
            throw new RuntimeException();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void itemRemove(ActionEvent event) {

    }

    @FXML
    void onClickItem(MouseEvent event) {

    }

    @FXML
    void placeOrder(ActionEvent event) throws SQLException, ClassNotFoundException, JRException {

        if (cartTMS.isEmpty()) {
            new Alert(Alert.AlertType.ERROR,"You must add items to the cart").show();

        } else{

             OrderDTO order = new OrderDTO();

            String orderID = lblOrderID.getText();
            System.out.println(orderID);
            order.setOrderID(orderID);
            order.setOrderDate(String.valueOf(LocalDate.now()));
            order.setCustomerID(customerBO.getID(cmbCustomer.getSelectionModel().getSelectedItem()));
            order.setTotalPrice(Double.parseDouble(lblShowPrice.getText()));


            ArrayList<OrderDetailDTO> orderDetailsDtos = new ArrayList<>();

            for (OrderTM orderTM : cartTMS){
                OrderDetailDTO orderDetail = new OrderDetailDTO(
                        lblOrderID.getText(),
                        orderTM.getMedicineID(),
                        orderTM.getPackQty(),
                        orderTM.getUnitQty(),
                        orderTM.getPackPrice(),
                        orderTM.getUnitPrice()
                );

                orderDetailsDtos.add(orderDetail);
            }

            order.setOrderDetailDTOs(orderDetailsDtos);

            System.out.println("Order ID is : " + order.getOrderID());
            System.out.println("Customer ID is : " + order.getCustomerID());
            System.out.println("Order Date is : " + order.getOrderDate());
            System.out.println("Total amount is : " + order.getTotalPrice());


            System.out.println();
            System.out.println();

            for (OrderDetailDTO orderDetailsDto : orderDetailsDtos){
                System.out.println(orderDetailsDto);
            }



            boolean isSaveOrder = orderBO.placeOrder(order);

            if (isSaveOrder) {
                new Alert(Alert.AlertType.INFORMATION, "Order saved Successfully..!").show();

                printBill(orderID);

                refreshPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Order Saved Unsuccessfully !!!").show();
            }

        }

    }

    private void refreshPage() {

        txtQtyOfMedi.clear();
        txtQtyOfPack.clear();

        cmbCustomer.getSelectionModel().clearSelection();
        cmbMedicine.getSelectionModel().clearSelection();
        cartTMS.clear();
        tblOrder.refresh();
    }

    @FXML
    void reset(ActionEvent event) throws SQLException, ClassNotFoundException {
        refreshTable();
    }

    @FXML
    void selectMedicineOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        String selectedMedicine = cmbMedicine.getSelectionModel().getSelectedItem();

        if (selectedMedicine != null){
            String[] medicineInfo = medicineBO.getMedicineInfo(selectedMedicine);
//            lblCustomerName.setText(medicineInfo[0]);
            lblUnitPrice.setText(medicineInfo[1]);
            lblQOHPackage.setText(medicineInfo[4]);
            lblQOHMedicine.setText(medicineInfo[3]);
        }
    }

    @FXML
    void selectTheCustomerOnAction(ActionEvent event) {

    }

    private void printBill(String orderID) throws JRException, SQLException, ClassNotFoundException {

        String reportPath = "/reports/pharmacyBill.jrxml";
        InputStream reportStream = getClass().getResourceAsStream(reportPath);

        if (reportStream == null) {
            throw new JRException("Report file not found: " + reportPath);
        }

        // Compile the Jasper report
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        // Establish database connection
        Connection connection = DBConnection.getDbConnection().getConnection();
        if (connection == null) {
            throw new SQLException("Failed to establish database connection.");
        }

        // Set parameters for the report
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("orderID", orderID); // Make sure "orderID" matches the parameter in your JRXML file

        // Fill the report with data
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);

        // Display the report
        JasperViewer.viewReport(jasperPrint, false);
    }
}
