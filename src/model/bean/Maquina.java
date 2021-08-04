/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import controller.MaquinaController;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;

/**
 *
 * @author r-r20
 */
public class Maquina {

    public static void codigoBarras(int numPatrimonio, String secao) {
        try {
            Barcode barcode = BarcodeFactory.createCode128(String.valueOf(numPatrimonio));
            PrinterJob printerJob = PrinterJob.getPrinterJob();

            PageFormat pf0 = printerJob.defaultPage();
            Paper p = new Paper();
            p.setImageableArea(-10, 20, 500, 500);
            pf0.setPaper(p);
            PageFormat pf2 = printerJob.validatePage(pf0);

            printerJob.setJobName(secao + "_" + numPatrimonio);
            printerJob.setPrintable(barcode);
            if (printerJob.printDialog()) {
                printerJob.print();
            }
        } catch (BarcodeException ex) {
            Logger.getLogger(MaquinaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PrinterException ex) {
            Logger.getLogger(MaquinaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int id;
    private int numPatrimomio;
    private String secaoPertencente;
    private String militarResponsavel;

    public Maquina() {
    }

    public Maquina(int id, int numPatrimomio, String secaoPertencente, String militarResponsavel) {
        this.id = id;
        this.numPatrimomio = numPatrimomio;
        this.secaoPertencente = secaoPertencente;
        this.militarResponsavel = militarResponsavel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumPatrimomio() {
        return numPatrimomio;
    }

    public void setNumPatrimomio(int numPatrimomio) {
        this.numPatrimomio = numPatrimomio;
    }

    public String getSecaoPertencente() {
        return secaoPertencente;
    }

    public void setSecaoPertencente(String secaoPertencente) {
        this.secaoPertencente = secaoPertencente;
    }

    public String getMilitarResponsavel() {
        return militarResponsavel;
    }

    public void setMilitarResponsavel(String militarResponsavel) {
        this.militarResponsavel = militarResponsavel;
    }

}
