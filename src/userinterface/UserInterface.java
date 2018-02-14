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
import javafx.scene.Scene;
//import javafx.scene.control.Button;
import javafx.scene.control.*;
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
        GridPane.setConstraints(comboBox, 0, 1);
        
        /*--------делаем текстовое поле для ввода----------------------------*/
        TextField firstTextField = new TextField();
        GridPane.setConstraints(firstTextField, 0, 0);
        //firstTextField.;
        
        /*--------делаем кнопку для копирования текста в ComboBox------------*/
        Button button1 = new Button();
        button1.setText("Edit Button");
        button1.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                String newItem = firstTextField.getText();
                int numOfItem = 0;
                while(comboBox.getValue() != null){
                    comboBox.getSelectionModel().select(numOfItem);
                    if(comboBox.getValue() == null){
                       comboBox.getItems().add(newItem);
                       break;
                    }
                    if(comboBox.getValue() == newItem){
                        //!!!!!!!!!!!!!!!!!!!!!!!!вывод сообщщения об ошибке!!!!!!!!!!!!!!!!!!!!!!!!!
                        break;
                    }
                    numOfItem++;
                }
                
                //System.out.println("Hello World!");
            }
        });
        GridPane.setConstraints(button1, 1, 0);
        

        
        root.getChildren().addAll(comboBox, button1, firstTextField);
        
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
