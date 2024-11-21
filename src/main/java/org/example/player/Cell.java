package org.example.player;

public class Cell {

    private String representation = "   ";

//    enum CellType{
//        EMPTY,
//        X,
//        O
//    }
//
//    private CellType cellT;
//
//    Cell(){
//        this.cellT = CellType.EMPTY;
//    }


    public String getRepresentation(){
//
//        switch(this.cellT){
//        case X: return " X ";
//            case O: return " 0 ";
//            default: return "   ";

        return this.representation;
    }

//    boolean isEqual(Cell c2){
//        return (this.cellT == c2.cellT);
//    }

    public void setRepresentation(String representation){

        this.representation = representation;
    }

//    public void setCellType(CellType cellT){
//        this.cellT = cellT;
//    }
//
//
//    public String toString() {
//        return getRepresentation();
//    }
    // public void String getRep(){
//    return this.state.getValue();}

//    cells[0][0].setRepresentation(CellType.getRepresentation);
}
