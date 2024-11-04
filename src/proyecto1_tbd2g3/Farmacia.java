/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_tbd2g3;

/**
 *
 * @author dfcm9
 */
public class Farmacia {
    private String id, address, name;
    private Farmaceutico owner;

    public Farmacia() {
    }

    public Farmacia(String id, String address, String name, Farmaceutico owner) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address; 
    }

    public String getName() {
        return name;
    }

    public Farmaceutico getOwner() {
        return owner;
    }

    public void setOwner(Farmaceutico owner) {
        this.owner = owner;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}