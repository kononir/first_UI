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
import javafx.scene.control.cell.*;
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
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10,10,10,10));
        
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
        firstButton.setText("New element");
        firstButton.setOnAction(event -> {
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
                if(comboBox.getValue().equals(newItem)){
                    Alert firstAlert = new Alert(AlertType.ERROR);
                    firstAlert.setTitle("Exist element");
                    firstAlert.setHeaderText("ERROR! You can't add already existing element!"); 
                    firstAlert.showAndWait();
                    break;
                }
                prevItem = comboBox.getValue();
                numOfItem++;
            }
            comboBox.getSelectionModel().select(numOfItem);
        });

        /*--------делаем первую группу---------------------------------------*/        
        Group firstGroup = new Group();
        HBox firstHBox = new HBox();
        firstHBox.setSpacing(3);
        firstHBox.setPadding(new Insets(5));
        firstHBox.getChildren().addAll(firstTextField, firstButton, comboBox);
        firstGroup.getChildren().add(firstHBox);
        root.setTop(firstGroup);
        BorderPane.setAlignment(firstGroup, Pos.TOP_CENTER);
        
        /*--------делаем текстовое поле второй группы для ввода--------------*/
        TextField secondTextField = new TextField();
        
        /*--------делаем кнопку второй группы для взаимозамены текста на обеих кнопках этой группы--------------*/
        Button secondButtonForExchange = new Button();
        secondButtonForExchange.setText("Exchange texts");
        
        /*--------делаем кнопку второй группы для изменение текста на другой кнопке этой группы--------------*/
        Button secondButtonForEdit = new Button();
        secondButtonForEdit.setText("Change text");
        secondButtonForEdit.setOnAction(action -> {
            String newText = secondTextField.getText();
            secondButtonForExchange.setText(newText);
        });
        
        /*--------пишем обработчик событий для кнопки взаимозамены-----------*/
        secondButtonForExchange.setOnAction(action -> {
                String newTextButtonForEdit = secondButtonForEdit.getText();
                String newTextButtonForExchange = secondButtonForExchange.getText();
                secondButtonForEdit.setText(newTextButtonForExchange);
                secondButtonForExchange.setText(newTextButtonForEdit);
        });
        
        /*--------делаем вторую группу---------------------------------------*/
        Group secondGroup = new Group();
        HBox secondHBox = new HBox();
        secondHBox.setSpacing(3);
        secondHBox.setPadding(new Insets(5));
        secondHBox.getChildren().addAll(secondTextField, secondButtonForEdit,
                                        secondButtonForExchange);
        secondGroup.getChildren().add(secondHBox);
        root.setBottom(secondGroup);
        BorderPane.setAlignment(secondGroup, Pos.BOTTOM_CENTER);
        
        /*--------делаем текстовое поле третьей группы для ввода-------------*/
        TextField thirdTextField = new TextField();
        
        /*--------делаем элементы выбора типа RadioButton--------------------*/
        ToggleGroup groupOfRadioButtons = new ToggleGroup();
        RadioButton thirdRadioButton1 = new RadioButton("He");
        RadioButton thirdRadioButton2 = new RadioButton("She");
        RadioButton thirdRadioButton3 = new RadioButton("It");
        thirdRadioButton1.setToggleGroup(groupOfRadioButtons);
        thirdRadioButton2.setToggleGroup(groupOfRadioButtons);
        thirdRadioButton3.setToggleGroup(groupOfRadioButtons);
        
        /*--------делаем кнопку третьей группы-------------------------------*/
        Button thirdButton = new Button();
        thirdButton.setText("Tick element");
        thirdButton.setOnAction(action -> {
            String textInTextField = thirdTextField.getText();
            if(thirdRadioButton1.getText().equals(textInTextField)){
                thirdRadioButton1.setSelected(true);
            }
            else if(thirdRadioButton2.getText().equals(textInTextField)){
                thirdRadioButton2.setSelected(true);
            }
            else if(thirdRadioButton3.getText().equals(textInTextField)){
                thirdRadioButton3.setSelected(true);
            }
            else{
                Alert thirdAlert = new Alert(AlertType.ERROR);
                thirdAlert.setTitle("Wrong name");
                thirdAlert.setHeaderText("ERROR! There are no item with such name!");
                thirdAlert.showAndWait();
            }
        });
        
        /*--------делаем третью группу---------------------------------------*/
        Group thirdGroup = new Group();
        VBox thirdVBox = new VBox();
        thirdVBox.setSpacing(3);
        thirdVBox.setPadding(new Insets(5));
        thirdVBox.getChildren().addAll(thirdTextField, thirdRadioButton1, 
                                       thirdRadioButton2, thirdRadioButton3,
                                       thirdButton);
        thirdGroup.getChildren().add(thirdVBox);
        root.setLeft(thirdGroup);
        BorderPane.setAlignment(thirdGroup, Pos.CENTER_LEFT);
        
        /*--------делаем текстовое поле четвёртой группы для ввода-----------*/
        TextField fourthTextField = new TextField();
        
        /*--------делаем элементы выбора типа CheckBox-----------------------*/
        CheckBox[] arrayOfCheckBoxes = new CheckBox[]{
                new CheckBox("English"),
                new CheckBox("Math"),
                new CheckBox("Physics")
        };
        
        /*--------делаем кнопку четвёртой группы-----------------------------*/
        Button fourthButton = new Button();
        fourthButton.setText("Tick element");
        fourthButton.setOnAction(event->{
            String textInTextField = fourthTextField.getText();
            boolean elementExist = false;
            for(int numberOfCheckBox = 0; numberOfCheckBox < 3; numberOfCheckBox++){
                CheckBox currentCheckBox = arrayOfCheckBoxes[numberOfCheckBox];
                if(currentCheckBox.getText().equals(textInTextField)){
                    elementExist = true;
                    if(currentCheckBox.isSelected())
                        currentCheckBox.setSelected(false);
                    else
                        currentCheckBox.setSelected(true);
                }
            }
            if(!elementExist){
                Alert fourthAlert = new Alert(AlertType.ERROR);
                fourthAlert.setTitle("Wrong name");
                fourthAlert.setHeaderText("ERROR! There are no item with such name!");
                fourthAlert.showAndWait();
            }
        });
        
        /*--------делаем четвёртую группу------------------------------------*/
        Group fourthGroup = new Group();
        VBox fourthVBox = new VBox();
        fourthVBox.setSpacing(3);
        fourthVBox.setPadding(new Insets(5));
        fourthVBox.getChildren().add(fourthTextField);
        for(int numberOfCheckBox = 0; numberOfCheckBox < 3; numberOfCheckBox++){
            CheckBox currentCheckBox = arrayOfCheckBoxes[numberOfCheckBox];
            fourthVBox.getChildren().add(currentCheckBox);
        }
        fourthVBox.getChildren().add(fourthButton);
        fourthGroup.getChildren().add(fourthVBox);
        root.setRight(fourthGroup);
        BorderPane.setAlignment(fourthGroup, Pos.CENTER_RIGHT);
        
        /*---------делаем текстовое поле пятой группы------------------------*/
        TextField fifthTextField = new TextField();
        
        /*---------делаем элементы таблицы-----------------------------------*/
        TableView<TableClass> table = new TableView<TableClass>();
        table.setEditable(true); //нужно ли???????
        TableColumn column1 = new TableColumn();
        TableColumn column2 = new TableColumn();
        column1.setText("First column");
        column2.setText("Second column");
        column1.setMinWidth(200);
        column2.setMinWidth(200);
        column1.setCellValueFactory(
                new PropertyValueFactory<TableClass, String>("firstColumn")
        );
        column2.setCellValueFactory(
                new PropertyValueFactory<TableClass, String>("secondColumn")
        );
        table.getColumns().addAll(column1, column2);
        ObservableList<TableClass> listForTable = FXCollections.observableArrayList();
        table.setItems(listForTable);
        root.setCenter(table);
        BorderPane.setAlignment(table, Pos.CENTER);
        
        /*---------делаем кнопку добавления в таблицу------------------------*/
        Button fifthButtonToAdd = new Button();
        fifthButtonToAdd.setText("Add new");
        fifthButtonToAdd.setOnAction(event -> {
            String textInTextField = fifthTextField.getText();
            TableClass newTableLine = new TableClass(textInTextField, "");
            listForTable.add(newTableLine);
        });
        
        /*---------делаем кнопку перемещения во второй столбец---------------*/
        Button fifthButtonToMove1 = new Button();
        fifthButtonToMove1.setText("Move to second");
        fifthButtonToMove1.setOnAction(event -> {
            int numberOfLines = listForTable.size();
            TableClass lastLineOfTable = listForTable.get(numberOfLines - 1);
            String textInFirstColumn = lastLineOfTable.getFirstColumn();
            listForTable.remove(numberOfLines - 1);
            TableClass newLineOfTable = new TableClass("", textInFirstColumn);
            listForTable.add(newLineOfTable);
        });
        
        /*---------делаем кнопку перемещения в первый столбец----------------*/
        Button fifthButtonToMove2 = new Button();
        fifthButtonToMove2.setText("Move to first");
        fifthButtonToMove2.setOnAction(event -> {
            int numberOfLines = listForTable.size();
            TableClass lastLineOfTable = listForTable.get(numberOfLines - 1);
            String textInSecondColumn = lastLineOfTable.getSecondColumn();
            listForTable.remove(numberOfLines - 1);
            TableClass newLineOfTable = new TableClass(textInSecondColumn, "");
            listForTable.add(newLineOfTable);
        });
        
        /*---------делаем пятую группу---------------------------------------*/
        Group fifthGroup = new Group();
        VBox fifthVBox = new VBox();
        HBox fifthHBox = new HBox();
        fifthHBox.getChildren().addAll(fifthTextField, fifthButtonToAdd,
                                       fifthButtonToMove1, fifthButtonToMove2);
        fifthVBox.getChildren().addAll(fifthHBox, table);
        fifthGroup.getChildren().addAll(fifthVBox);
        root.setCenter(fifthGroup);
        BorderPane.setAlignment(fifthGroup, Pos.CENTER);
                
        
        Scene scene = new Scene(root, 800, 640); //стандарт 300 на 250(500*400; )
        
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
