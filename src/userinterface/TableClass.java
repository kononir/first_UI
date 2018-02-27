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
    public final void setFirstColumn(String Column1){
        this.firstColumn = Column1;
    }
    public final void setSecondColumn(String Column2){
        this.secondColumn = Column2;
    }
    public final String getFirstColumn(){
        return this.firstColumn;
    }
    public final String getSecondColumn(){
        return this.secondColumn;
    }
}
