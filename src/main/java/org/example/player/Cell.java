package org.example.player;

public class Cell {

    enum cellType{
        EMPTY,
        X,
        O
    }

    private cellType cellT;

    private String representation = "   ";

    public String getRepresentation(){

        //switch(ct){
//        case empty:
//            case String "iufi";

        return this.representation;
    }

//    boolean isEqual(Cell c2){
//        return (this.cellT == c2.cellT);
//    }

    public void setRepresentation(String representation){

        this.representation = representation;
    }

}
