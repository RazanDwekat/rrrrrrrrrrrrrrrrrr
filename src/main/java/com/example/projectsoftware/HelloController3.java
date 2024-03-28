package com.example.projectsoftware;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HelloController3 {

    @FXML
    private ListView<Halls> hallListView=new ListView<>();



    @FXML
    private TextField txt1=new TextField();
    public ListView<Halls> getHallListView() {
        return hallListView;
    }
    private ObservableList<Halls> allHalls;


    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "1482003";

    @FXML
    public void initialize() {
        allHalls = fetchHallsFromDatabase();
        hallListView.setItems(allHalls);
        hallListView.setVisible(false);
        txt1.textProperty().addListener((observable, oldValue, newValue) -> filterHalls(newValue));
        hallListView.setOnMouseClicked(this::showHallInformationDialog);
        servicelist.setOnMouseClicked(this::sshowHallInformationDialog);

        allServices = fetchAllServices();
        servicelist.setItems(allServices);

        // Listen for changes in the text field and perform search
        txt2.textProperty().addListener((observable, oldValue, newValue) -> searchServices(newValue));
    }

    private void showHallInformationDialog(MouseEvent event) {
        Halls selectedHall = hallListView.getSelectionModel().getSelectedItem();
        if (selectedHall != null) {
            String hallName = selectedHall.getName();
            try {
                FXMLLoader loader = new FXMLLoader();
                Parent root;
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                if ("Rose".equals(hallName)) {
                    loader.setLocation(getClass().getResource("newhall.fxml"));
                    root = loader.load();
                    HelloController controller = loader.getController();
                    controller.populateFields(selectedHall);
                } else if ("Masaya".equals(hallName)) {
                    loader.setLocation(getClass().getResource("Masaya.fxml"));
                    root = loader.load();
                } else if ("Dreamsh".equals(hallName)) {
                    loader.setLocation(getClass().getResource("newhall.fxml"));
                    root = loader.load();
                } else {

                    loader.setLocation(getClass().getResource("newhall.fxml"));
                    root = loader.load();
                    // Populate textfields and image label with information from selected hall
                    HelloController controller = loader.getController();
                    controller.populateFields(selectedHall);

                }


                HelloController controller = new HelloController();
                controller=loader.getController();
                controller.performInitialization();



                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace(); // Log the exception or provide user feedback
            }
        }
    }

    public ObservableList<Halls> fetchHallsFromDatabase() {
        ObservableList<Halls> halls = FXCollections.observableArrayList();

        String query = "SELECT hallname, priceperhour, capacity, location, image FROM software.Halls";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String name = resultSet.getString("hallname");
                double price = resultSet.getDouble("priceperhour");
                int capacity = resultSet.getInt("capacity");
                String location = resultSet.getString("location");
                byte[] imageBytes = resultSet.getBytes("image");

                Halls hall = new Halls(name, price, capacity, location, imageBytes);
                halls.add(hall);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception based on your application's needs
        }

        return halls;
    }

    private void filterHalls(String query) {
        ObservableList<Halls> filteredHalls = FXCollections.observableArrayList();

        // Filter based on the query (you can customize the filtering logic)
        for (Halls hall : allHalls) {
            if (hall.getName().toLowerCase().contains(query.toLowerCase()) ||
                    (isNumeric(query) && (String.valueOf(hall.getPrice()).equals(query) || String.valueOf(hall.getCapacity()).equals(query)))) {
                filteredHalls.add(hall);
            }
        }

        // Update the hallListView with filtered data
        hallListView.setItems(filteredHalls);
        hallListView.setVisible(!query.isEmpty());
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }




    // Method to display alerts/messages
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Method to convert byte array to javafx.scene.image.Image

    @FXML
    private Button cancclllee;

    @FXML
    private Button ert;

    @FXML
    private Label hallImageLabel;

    @FXML
    private DatePicker newcalender;

    @FXML
    private TextField newcapacity;

    @FXML
    private ChoiceBox<?> newchoice;

    @FXML
    private TextField newhallname;

    @FXML
    private TextField newlocation;

    @FXML
    private TextField newprice;

    @FXML
    void choicesnew(MouseEvent event) {

    }




    public void newreserve(javafx.event.ActionEvent actionEvent) {
    }

    public void canclenew(javafx.event.ActionEvent actionEvent) {
    }

    public void eeeee(javafx.event.ActionEvent actionEvent) {
    }


    @FXML
    private ListView<Services> servicelist=new ListView<>();

    @FXML
    private TextField txt2=new TextField();

    private ObservableList<Services> allServices;




    private ObservableList<Services> fetchAllServices() {
        ObservableList<Services> services = FXCollections.observableArrayList();

        String query = "SELECT * FROM software.services";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int serviceId = resultSet.getInt("serviceid");
                String serviceName = resultSet.getString("servicename");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                int userId = resultSet.getInt("userid");
                byte imagee = resultSet.getByte("image");



                // You might need to handle image retrieval here if needed

                Services service = new Services(serviceId, serviceName, description, price, userId, new byte[]{imagee});
                services.add(service);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception based on your application's needs
        }

        return services;
    }



    private void searchServices(String query) {
        ObservableList<Services> filteredServices = FXCollections.observableArrayList();

        for (Services service : allServices) {
            // Convert the price to String for comparison
            String priceString = String.valueOf(service.getPrice());
            if (service.getServiceName().toLowerCase().contains(query.toLowerCase()) ||
                    priceString.equals(query))

            {
                filteredServices.add(service);
            }
        }

        servicelist.setItems(filteredServices);
    }

    @FXML
    private Button addser;

    @FXML
    private CheckBox deccheck;

    @FXML
    private CheckBox djcheck;

    @FXML
    private CheckBox opencheck;





    private void sshowHallInformationDialog(MouseEvent event) {
        Services selectedHall = servicelist.getSelectionModel().getSelectedItem();
        if (selectedHall != null) {
            String hallName = selectedHall.getServiceName();
            try {
                FXMLLoader loader = new FXMLLoader();
                Parent root;
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                if ("Dj".equals(hallName)) {
                    loader.setLocation(getClass().getResource("servicepageee.fxml"));
                    root = loader.load();
                    HelloController controller = loader.getController();
                    controller.populateFieldss(selectedHall);

                } else if ("wewe".equals(hallName)) {
                    loader.setLocation(getClass().getResource("servicepageee.fxml"));
                    root = loader.load();
                } else if ("erg".equals(hallName)) {
                    loader.setLocation(getClass().getResource("servicepageee.fxml"));
                    root = loader.load();
                } else {

                    loader.setLocation(getClass().getResource("servicepageee.fxml"));
                    root = loader.load();
                    // Populate textfields and image label with information from selected hall
                   HelloController controller = loader.getController();
                    controller.populateFieldss(selectedHall);

                }


                HelloController controller = new HelloController();
                controller=loader.getController();
                controller.performInitialization();



                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace(); // Log the exception or provide user feedback
            }
        }
    }


  @FXML
  public void pp(ActionEvent actionEvent) throws IOException {
      Parent root = FXMLLoader.load(getClass().getResource("custointer.fxml"));
      Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
  }

}
