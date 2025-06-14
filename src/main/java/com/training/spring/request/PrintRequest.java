package com.training.spring.request;

public class PrintRequest {
    private String printDeskripsi;
    private String printJudul;
    private String rfidData;

    public String getPrintDeskripsi() {
        return printDeskripsi;
    }

    public void setPrintDeskripsi(String printDeskripsi) {
        this.printDeskripsi = printDeskripsi;
    }

    public String getPrintJudul() {
        return printJudul;
    }

    public void setPrintJudul(String printJudul) {
        this.printJudul = printJudul;
    }

    public String getRfidData() {
        return rfidData;
    }

    public void setRfidData(String rfidData) {
        this.rfidData = rfidData;
    }
}
