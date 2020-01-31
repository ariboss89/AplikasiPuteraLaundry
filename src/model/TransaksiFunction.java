/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author User
 */
public class TransaksiFunction {

    private static String IdTransaksi;
    private String namaPelanggan;
    private String status;
    private String idPelanggan;
    private String jumlahLaundry;
    private int jumlahData;
    private static String IdDetailTransaksi;
    private int totalBiaya;

    public String getIdPelanggan() {
        return idPelanggan;
    }

    public void setIdPelanggan(String aIdPelanggan) {
        idPelanggan = aIdPelanggan;
    }

   public static String getIdTransaksi() {
        return IdTransaksi;
    }

    public static void setIdTransaksi(String aIdTransaksi) {
        IdTransaksi = aIdTransaksi;
    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }
    
    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJumlahLaundry() {
        return jumlahLaundry;
    }

    public void setJumlahLaundry(String ajumlahLaundry) {
        this.jumlahLaundry = ajumlahLaundry;
    }

    public int getJumlahData() {
        return jumlahData;
    }

    public void setJumlahData(int aJumlahData) {
        this.jumlahData = aJumlahData;
    }

    public static String getIdDetailTransaksi() {
        return IdDetailTransaksi;
    }

    public static void setIdDetailTransaksi(String aIdDetailTransaksi) {
        IdDetailTransaksi = aIdDetailTransaksi;
    }

    public int getTotalBiaya() {
        return totalBiaya;
    }

    public void setTotalBiaya(int totalBiaya) {
        this.totalBiaya = totalBiaya;
    }
}
