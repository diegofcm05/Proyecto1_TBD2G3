/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_tbd2g3;

/**
 *
 * @author dfcm9
 */
public class Laboratorio {
    private int id, plazo_entrg;
    private String name, address, info_cntc;    

    public Laboratorio() {
    }

    public Laboratorio(int id, String name, String address, String info_cntc, int plazo_entrg) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.info_cntc = info_cntc;
        this.plazo_entrg = plazo_entrg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInfo_cntc() {
        return info_cntc;
    }

    public void setInfo_cntc(String info_cntc) {
        this.info_cntc = info_cntc;
    }

    public int getPlazo_entrg() {
        return plazo_entrg;
    }

    public void setPlazo_entrg(int plazo_entrg) {
        this.plazo_entrg = plazo_entrg;
    }
    
    
}