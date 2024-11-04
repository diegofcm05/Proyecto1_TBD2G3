/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_tbd2g3;

/**
 *
 * @author dfcm9
 */
public class Persona {
    private String id, first_n, last_n, info_cntc;    

    public Persona() {
    }

    public Persona(String id, String first_n, String last_n, String info_cntc) {
        this.id = id;
        this.first_n = first_n;
        this.last_n = last_n;
        this.info_cntc = info_cntc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_n() {
        return first_n;
    }

    public void setFirst_n(String first_n) {
        this.first_n = first_n;
    }

    public String getLast_n() {
        return last_n;
    }

    public void setLast_n(String last_n) {
        this.last_n = last_n;
    }

    public String getInfo_cntc() {
        return info_cntc;
    }

    public void setInfo_cntc(String info_cntc) {
        this.info_cntc = info_cntc;
    }
    
    
}
