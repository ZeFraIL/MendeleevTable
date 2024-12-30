package zeev.fraiman.mendeleevtable;

import java.io.Serializable;

public class Element implements Serializable {
    private String symbol;
    private String name;
    private int atomicNumber;
    private double atomicMass;
    private String imageUrl;

    public Element(String symbol, String name, int atomicNumber, double atomicMass, String imageUrl) {
        this.symbol = symbol;
        this.name = name;
        this.atomicNumber = atomicNumber;
        this.atomicMass = atomicMass;
        this.imageUrl = imageUrl;
    }

    // Getters
    public String getSymbol() { return symbol; }
    public String getName() { return name; }
    public int getAtomicNumber() { return atomicNumber; }
    public double getAtomicMass() { return atomicMass; }
    public String getImageUrl() { return imageUrl; }
}
