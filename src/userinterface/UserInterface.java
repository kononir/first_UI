/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import javafx.application.Application;
import javafx.animation.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.collections.*;

/**
 *
 * @author Vlad
 * 
 */
public class UserInterface extends Application {
    
    @Override
    public void start(Stage primaryStage) {

        
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10,10,10,10));
        
        ComboBox<String> comboBox = new ComboBox<String>();
        ObservableList<String> listForComboBox = FXCollections.observableArrayList(
                "low", "medium", "hight"
        );
        comboBox.setItems(listForComboBox);
        comboBox.getSelectionModel().select(0);
        
        TextField firstTextField = new TextField();
        
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
            firstTextField.clear();
        });
        
        Group firstGroup = new Group();
        HBox firstHBox = new HBox();
        firstHBox.setSpacing(3);
        firstHBox.setPadding(new Insets(5));
        firstHBox.getChildren().addAll(firstTextField, firstButton, comboBox);
        firstGroup.getChildren().add(firstHBox);
        root.setTop(firstGroup);
        BorderPane.setAlignment(firstGroup, Pos.TOP_CENTER);
        
        TextField secondTextField = new TextField();
        
        Button secondButtonForExchange = new Button();
        secondButtonForExchange.setText("Exchange texts");
        
        Button secondButtonForEdit = new Button();
        secondButtonForEdit.setText("Change text");
        secondButtonForEdit.setOnAction(action -> {
            String newText = secondTextField.getText();
            secondButtonForExchange.setText(newText);
            secondTextField.clear();
        });
        
        secondButtonForExchange.setOnAction(action -> {
                String newTextButtonForEdit = secondButtonForEdit.getText();
                String newTextButtonForExchange = secondButtonForExchange.getText();
                secondButtonForEdit.setText(newTextButtonForExchange);
                secondButtonForExchange.setText(newTextButtonForEdit);
        });
        
        Group secondGroup = new Group();
        HBox secondHBox = new HBox();
        secondHBox.setSpacing(3);
        secondHBox.setPadding(new Insets(5));
        secondHBox.getChildren().addAll(secondTextField, secondButtonForEdit,
                                        secondButtonForExchange);
        secondGroup.getChildren().add(secondHBox);
        root.setBottom(secondGroup);
        BorderPane.setAlignment(secondGroup, Pos.BOTTOM_CENTER);
        
        TextField thirdTextField = new TextField();
        
        ToggleGroup groupOfRadioButtons = new ToggleGroup();
        RadioButton thirdRadioButton1 = new RadioButton("1");
        RadioButton thirdRadioButton2 = new RadioButton("2");
        RadioButton thirdRadioButton3 = new RadioButton("3");
        thirdRadioButton1.setToggleGroup(groupOfRadioButtons);
        thirdRadioButton2.setToggleGroup(groupOfRadioButtons);
        thirdRadioButton3.setToggleGroup(groupOfRadioButtons);
        
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
            thirdTextField.clear();
        });
        
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
        
        TextField fourthTextField = new TextField();
        
        CheckBox[] arrayOfCheckBoxes = new CheckBox[]{
            
                new CheckBox("1"),
                new CheckBox("2"),
                new CheckBox("3")
        };
        
        Button fourthButton = new Button();
        fourthButton.setText("Tick element");
        fourthButton.setOnAction(event->{
            String textInTextField = fourthTextField.getText();
            boolean elementExist = false;
            for(int numberOfCheckBox = 0; numberOfCheckBox < 3; numberOfCheckBox++){
                CheckBox currentCheckBox = arrayOfCheckBoxes[numberOfCheckBox];
                if(currentCheckBox.getText().equals(textInTextField)){
                    elementExist = true;
                    currentCheckBox.setSelected(currentCheckBox.isSelected() ? false : true);
                }
            }
            if(!elementExist){
                Alert fourthAlert = new Alert(AlertType.ERROR);
                fourthAlert.setTitle("Wrong name");
                fourthAlert.setHeaderText("ERROR! There are no item with such name!");
                fourthAlert.showAndWait();
            }
            fourthTextField.clear();
        });
        
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
        
        TextField fifthTextField = new TextField();
        
        TableView<TableClass> table = new TableView<TableClass>();
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
        
        Button fifthButtonToAdd = new Button();
        fifthButtonToAdd.setText("Add new");
        fifthButtonToAdd.setOnAction(event -> {
            String textInTextField = fifthTextField.getText();
            TableClass newTableLine = new TableClass(textInTextField, "");
            listForTable.add(newTableLine);
            fifthTextField.clear();
        });
        
        Button fifthButtonToMove1 = new Button();
        fifthButtonToMove1.setText("Move to second");
        fifthButtonToMove1.setOnAction(event -> {
            int numberOfLines = listForTable.size();
            TableClass lastLineOfTable = listForTable.get(numberOfLines - 1);
            String textInFirstColumn = lastLineOfTable.getFirstColumn();
            if(!textInFirstColumn.equals("")){
                listForTable.remove(numberOfLines - 1);
                TableClass newLineOfTable = new TableClass("", textInFirstColumn);
                listForTable.add(newLineOfTable);
            }
        });
        
        Button fifthButtonToMove2 = new Button();
        fifthButtonToMove2.setText("Move to first");
        fifthButtonToMove2.setOnAction(event -> {
            int numberOfLines = listForTable.size();
            TableClass lastLineOfTable = listForTable.get(numberOfLines - 1);
            String textInSecondColumn = lastLineOfTable.getSecondColumn();
            if(!textInSecondColumn.equals("")){
                listForTable.remove(numberOfLines - 1);
                TableClass newLineOfTable = new TableClass(textInSecondColumn, "");
                listForTable.add(newLineOfTable);
            }
        });
        
        Group fifthGroup = new Group();
        VBox fifthVBox = new VBox();
        HBox fifthHBox = new HBox();
        fifthHBox.getChildren().addAll(fifthTextField, fifthButtonToAdd,
                                       fifthButtonToMove1, fifthButtonToMove2);
        fifthVBox.getChildren().addAll(fifthHBox, table);
        fifthGroup.getChildren().addAll(fifthVBox);
        root.setCenter(fifthGroup);
        BorderPane.setAlignment(fifthGroup, Pos.CENTER);
                
        Scene scene = new Scene(root);
        primaryStage.setWidth(800);
        primaryStage.setHeight(640);
        
        final String textInTitle = "User Interface";
        final int startWidth = (int)primaryStage.getWidth(); 
        final int widthOfButtons = 250;
        final double widthOfLetter = 2.9;
        final int startNumberOfSpaces = (int)((double)(startWidth - widthOfButtons)/widthOfLetter - (double)textInTitle.length());
        final String startTitle = addSpacesToString(textInTitle, startNumberOfSpaces);
        primaryStage.setTitle(startTitle);
        
        new AnimationTimer(){
            private long lastUpdate = 0;
            final private long timeDelay = 17000000;
            private int width = startWidth;
            private int numberOfSpaces = startNumberOfSpaces;
            @Override
            public void handle(long now){
                if(now - lastUpdate >= timeDelay){
                    String curentTitle = primaryStage.getTitle();
                    int curentWidth = (int)primaryStage.getWidth();
                    if(curentWidth != width){
                        numberOfSpaces = (int)((double)(curentWidth - widthOfButtons)/widthOfLetter - (double)textInTitle.length());
                        width = curentWidth;
                        curentTitle = addSpacesToString(textInTitle, numberOfSpaces);
                    }
                    String newTitle = getNewTitle(curentTitle);
                    primaryStage.setTitle(newTitle);
                    lastUpdate = now;
                }
            }
        }.start();
               
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public String getNewTitle(String curentTitle){
        int curentTitleLength = curentTitle.length();
        char lastSymbol = curentTitle.charAt(curentTitleLength - 1);
        String cutCurentTitleForNew = curentTitle.substring(0, curentTitleLength - 1);
        StringBuilder newTitle = new StringBuilder();
        newTitle.append(lastSymbol).append(cutCurentTitleForNew);
        return newTitle.toString();
    }
    
    public String addSpacesToString(String oldTitle, int numberOfSpaces){
        StringBuilder newTitle = new StringBuilder();
        newTitle.append(oldTitle);
        for(int iter = 0; iter < numberOfSpaces; iter++){
            newTitle.append(' ');
        }
        return newTitle.toString();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
