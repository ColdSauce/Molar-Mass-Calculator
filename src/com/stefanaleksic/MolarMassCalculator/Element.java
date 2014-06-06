package com.stefanaleksic.MolarMassCalculator;

/**
 * Created by stefan987 on 7/9/13.
 */
public class Element {
    private double atomicNumber;
    private double atomicMass;
    private String elementName;
    public Element(String elementName,double atomicMass){
        this.atomicMass = atomicMass;
        this.elementName = elementName;
    }
    public Element getElement(){
        return this;
    }
    public Element setAtomicNumber(double atomicNumber){
        this.atomicNumber = atomicNumber;
        return this;
    }
    public Element setAtomicMass(double atomicMass){
        this.atomicMass = atomicMass;
        return this;
    }
    public Element setElementName(String elementName){
        this.elementName = elementName;
        return this;
    }
    public double getAtomicNumber(){
        return atomicNumber;
    }
    public double getAtomicMass(){
        return atomicMass;
    }
    public String toString(){
        return elementName;
    }



}
