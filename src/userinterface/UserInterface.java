/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
//import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.collections.*;

/**
 *
 * @author Vlad
 */
public class UserInterface extends Application {
    
    @Override
    public void start(Stage primaryStage) {

        
        /*--------делаем панель, разделённую на столбцы и строки-------------*/
        GridPane root = new GridPane();
        root.setPadding(new Insets(10,10,10,10));
        root.setHgap(5);
        root.setVgap(5);
        
        /*--------делаем выпадающий список-----------------------------------*/
        ComboBox<String> comboBox = new ComboBox<String>();
        ObservableList<String> listForComboBox = FXCollections.observableArrayList(
                "low", "medium", "hight"
        );
        comboBox.setItems(listForComboBox);
        comboBox.getSelectionModel().select(0);
        
        /*--------делаем текстовое поле первой группы для ввода--------------*/
        TextField firstTextField = new TextField();
        
        /*--------делаем кнопку для копирования текста в ComboBox------------*/
        Button firstButton = new Button();
        firstButton.setText("First edit button");
        firstButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                String newItem = firstTextField.getText();
                String prevItem = null;
                int numOfItem = 0;
                if(newItem.equals(""))
                    return;
                while(comboBox.getValue() != null){ //пересмотреть условие
                    comboBox.getSelectionModel().select(numOfItem);
                    if(comboBox.getValue().equals(prevItem) ){   
                       comboBox.getItems().add(newItem);
                       break;
                    }
                    //сравнение с помощью == не катит
                    if(comboBox.getValue().equals(newItem)){
                        Alert firstAlert = new Alert(AlertType.ERROR);
                        firstAlert.setTitle("Error alert");
                        firstAlert.setHeaderText("ERROR! You can't add already existing element!"); 
                        firstAlert.showAndWait();
                        break;
                    }
                    prevItem = comboBox.getValue();
                    numOfItem++;
                }
                comboBox.getSelectionModel().select(numOfItem);
            }
        });

        /*--------делаем первую группу---------------------------------------*/        
        Group firstGroup = new Group();
        HBox firstHBox = new HBox();
        firstHBox.setSpacing(3);
        firstHBox.setPadding(new Insets(5));
        firstHBox.getChildren().addAll(firstTextField, firstButton, comboBox);
        firstGroup.getChildren().addAll(firstHBox);
        GridPane.setConstraints(firstGroup, 0, 0);
        
        /*--------делаем текстовое поле второй группы для ввода--------------*/
        TextField secondTextField = new TextField();
        
        /*--------делаем кнопку второй группы для взаимозамены текста на обеих кнопках этой группы--------------*/
        Button secondButtonForExchange = new Button();
        secondButtonForExchange.setText("Second exchange button");
        
        /*--------делаем кнопку второй группы для изменение текста на другой кнопке этой группы--------------*/
        Button secondButtonForEdit = new Button();
        secondButtonForEdit.setText("Second edit button");
        secondButtonForEdit.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event){
                String newText = secondTextField.getText();
                secondButtonForExchange.setText(newText);
            }
        });
        
        /*--------пишем обработчик событий для кнопки взаимозамены-----------*/
        secondButtonForExchange.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event){
                String newTextButtonForEdit = secondButtonForEdit.getText();
                String newTextButtonForExchange = secondButtonForExchange.getText();
                secondButtonForEdit.setText(newTextButtonForExchange);
                secondButtonForExchange.setText(newTextButtonForEdit);
            }
        });
        
        /*--------делаем вторую группу---------------------------------------*/
        Group secondGroup = new Group();
        HBox secondHBox = new HBox();
        secondHBox.setSpacing(3);
        secondHBox.setPadding(new Insets(5));
        secondHBox.getChildren().addAll(secondTextField, secondButtonForEdit,
                                        secondButtonForExchange);
        secondGroup.getChildren().setAll(secondHBox);
        GridPane.setConstraints(secondGroup, 0, 1);
        
        
        
        
        
        root.getChildren().addAll(firstGroup, secondGroup);
        
        Scene scene = new Scene(root, 300, 250); //стандарт 300 на 250
        
        primaryStage.setTitle("User Interface");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
