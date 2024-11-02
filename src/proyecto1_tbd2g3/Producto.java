/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_tbd2g3;

/**
 *
 * @author dfcm9
 */
public class Producto {
    private int id, unidades;
    private String name, fabricante, tipo;
    private double p_coste, p_venta;
    private boolean s_s;

    public Producto() {
    }

    public Producto(int id, int unidades, String name, String fabricante, String tipo, double p_coste, double p_venta, boolean s_s) {
        this.id = id;
        this.unidades = unidades;
        this.name = name;
        this.fabricante = fabricante;
        this.tipo = tipo;
        this.p_coste = p_coste;
        this.p_venta = p_venta;
        this.s_s = s_s;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getP_coste() {
        return p_coste;
    }

    public void setP_coste(double p_coste) {
        this.p_coste = p_coste;
    }

    public double getP_venta() {
        return p_venta;
    }

    public void setP_venta(double p_venta) {
        this.p_venta = p_venta;
    }

    public boolean isS_s() {
        return s_s;
    }

    public void setS_s(boolean s_s) {
        this.s_s = s_s;
    }
    
    
}