/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * BarcodeGenerator.java
 *
 * Created on Dec 15, 2011, 3:23:51 PM
 */
package belajar.barcode;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import org.krysalis.barcode4j.impl.upcean.EAN13Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

/**
 *
 * @author adi
 */
public class BarcodeGenerator extends javax.swing.JPanel {
    
    private BitmapCanvasProvider canvas = new BitmapCanvasProvider(72, BufferedImage.TYPE_BYTE_BINARY, false, 0);

    /** Creates new form BarcodeGenerator */
    public BarcodeGenerator() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtBarcode = new javax.swing.JTextField();
        btnGenerate = new javax.swing.JButton();
        pnlOutput = new javax.swing.JPanel();
        lblImage = new javax.swing.JLabel();
        btnPrint = new javax.swing.JButton();

        jLabel1.setText("Nomer Barcode");

        txtBarcode.setText("9789791816304");
        txtBarcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBarcodeActionPerformed(evt);
            }
        });

        btnGenerate.setText("Generate");
        btnGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateActionPerformed(evt);
            }
        });

        pnlOutput.setBackground(new java.awt.Color(253, 253, 247));

        lblImage.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        pnlOutput.add(lblImage);

        btnPrint.setText("Print");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlOutput, javax.swing.GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBarcode, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGenerate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrint)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnGenerate, btnPrint});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenerate)
                    .addComponent(btnPrint))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlOutput, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateActionPerformed
        generateBarcode();
    }//GEN-LAST:event_btnGenerateActionPerformed

    private void txtBarcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBarcodeActionPerformed
        generateBarcode();
    }//GEN-LAST:event_txtBarcodeActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        PrinterJob printer = PrinterJob.getPrinterJob();
        printer.setPrintable(new Printable() {

            public int print(Graphics grphcs, PageFormat pf, int i) throws PrinterException {
                if(i > 0) {
                    return NO_SUCH_PAGE;
                }
                
                Graphics2D g2d = (Graphics2D)grphcs;
                g2d.translate(pf.getImageableX(), pf.getImageableY());
                lblImage.print(grphcs);
                return PAGE_EXISTS;
            }
        });
        
        if(printer.printDialog()) {
            try {
                printer.print();
            } catch (PrinterException ex) {
                Logger.getLogger(BarcodeGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerate;
    private javax.swing.JButton btnPrint;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblImage;
    private javax.swing.JPanel pnlOutput;
    private javax.swing.JTextField txtBarcode;
    // End of variables declaration//GEN-END:variables

    private void generateBarcode() {
        try {
            String input = txtBarcode.getText();
            EAN13Bean bean = new EAN13Bean(); 
            
            bean.generateBarcode(canvas, input);
            canvas.finish();
            
            lblImage.setIcon(new ImageIcon(canvas.getBufferedImage()));
            pnlOutput.repaint();
            
        } catch (IOException ex) {
            Logger.getLogger(BarcodeGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    public static void main(String[] args) {
        JFrame fr = new JFrame();
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.getContentPane().add(new BarcodeGenerator());
        fr.setSize(800,600);
        fr.setVisible(true);
    }
}
