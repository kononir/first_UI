/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

/**
 *
 * @author Vlad
 */
public class TableClass {
    private String firstColumn;
    private String secondColumn;
    public TableClass(String Column1, String Column2){
        this.firstColumn = Column1;
        this.secondColumn = Column2;
    }
    public void setFirstColumn(String Column1){
        this.firstColumn = Column1;
    }
    public void setSecondColumn(String Column2){
        this.secondColumn = Column2;
    }
    public String getFirstColumn(){
        return this.firstColumn;
    }
    public String getSecondColumn(){
        return this.secondColumn;
    }
}
