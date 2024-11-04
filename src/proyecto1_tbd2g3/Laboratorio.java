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
    private String id, name, address, info_cntc;    

    public Laboratorio() {
    }

    public Laboratorio(String id, String name, String address, String info_cntc) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.info_cntc = info_cntc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
    
}