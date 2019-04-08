/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.bici.ocr.model;

import mg.bici.ocr.exception.GenericException;

/**
 *
 * @author Mickael
 */
public class WordPosition {
    private int X1;
    private int X2;
    private int Y1;
    private int Y2;

    public int getX1() {
        return X1;
    }

    public void setX1(int X1) {
        this.X1 = X1;
    }
    
    public void setX1(String X1) throws GenericException {
        try {
            this.X1 = Integer.parseInt(X1);
        }
        catch(NumberFormatException ex) {
            throw new GenericException("Nombre invalide");
        }
    }

    public int getX2() {
        return X2;
    }

    public void setX2(int X2) {
        this.X2 = X2;
    }
    
    public void setX2(String X2) throws GenericException {
        try {
            this.X1 = Integer.parseInt(X2);
        }
        catch(NumberFormatException ex) {
            throw new GenericException("Nombre invalide");
        }
    }

    public int getY1() {
        return Y1;
    }

    public void setY1(int Y1) {
        this.Y1 = Y1;
    }
    
    public void setY1(String Y1) throws GenericException {
        try {
            this.X1 = Integer.parseInt(Y1);
        }
        catch(NumberFormatException ex) {
            throw new GenericException("Nombre invalide");
        }
    }
    
    public int getY2() {
        return Y2;
    }

    public void setY2(int Y2) {
        this.Y2 = Y2;
    }
    
    public void setY2(String Y2) throws GenericException {
        try {
            this.X1 = Integer.parseInt(Y2);
        }
        catch(NumberFormatException ex) {
            throw new GenericException("Nombre invalide");
        }
    }
    
    public WordPosition(String x1,String y1,String x2,String y2) throws GenericException {
        this.setX1(x1);
        this.setY1(y1);
        this.setX2(x2);
        this.setY2(y2);
    }
    
    @Override
    public String toString() {
        return String.format("X1:{0}, X2:{1}, Y1:{2}, Y2:{3}",getX1(),getX2(),getY1(),getY2());
    }
}
