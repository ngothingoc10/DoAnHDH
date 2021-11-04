/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Power_Magerment;

/**
 *
 * @author Ngoc
 */
import Power_Magerment.BaterrySystem.PowerSource;
import java.awt.Font;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import java.lang.*;
import javax.swing.JOptionPane;
import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Giao_Dien extends javax.swing.JFrame {

    /**
     * Creates new form Giao_Dien
     */
    public Giao_Dien() {

        initComponents();
        this.setLocationRelativeTo(this); //xuất hiện ở giữa màn hình
        setBatterySystem();
        setBrightnessCurrent();
        setDateAndTime();

    }

    public void setDateAndTime() {
        new Thread(new Runnable() {        //tạo luồng hieen thi thoi gian

            public void run() {
                while (true) {
                    SimpleDateFormat f = new SimpleDateFormat("EEEE dd/MM/yyyy \n HH:mm:ss");
                    String time = f.format(new Date());
                    hienthi.setFont(new Font("Arial", Font.BOLD, 15));
                    hienthi.setText(time);
                    hienthi.setVisible(true);
                    try {
                        Thread.sleep(1000); // 1 second
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }
        }).start();
    }

    public void setBrightnessCurrent() {
        try {
            ProcessBuilder pb = new ProcessBuilder("powershell.exe", "/c", "Get-Ciminstance -Namespace root/WMI -ClassName WmiMonitorBrightness | Select -ExpandProperty \"CurrentBrightness\"").redirectErrorStream(true);
            Process process = pb.start(); // thực thi command line;
            //lấy kết quả trả về trên command line
            StringBuilder result = new StringBuilder(80);
            try (BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                while (true) {
                    String line = in.readLine();
                    if (line == null) {
                        break;
                    }
                    result.append(line);
                }
            }

            System.out.println(result.toString());
            int crrBrightnessLevel = Integer.parseInt(result.toString());
            System.out.println(crrBrightnessLevel);
            sliderBrightness.setValue(crrBrightnessLevel);

        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace(); // In cai dong ni ra de debug xem loi.
        }

    }

    private void setBatterySystem() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    StringBuilder sb = new StringBuilder("Power: ");

                    if (PowerSource.getPowerSources().length == 0) {
                        sb.append("Unknown");
                    } else {
                        double timeRemaining = PowerSource.getPowerSources()[0].getTimeRemaining();

                        if (timeRemaining < -1d) {
                            sb.append("Charging");
                        } else if (timeRemaining < 0d) {
                            sb.append("Calculating time remaining");
                        } else {
                            sb.append(String.format("%d:%02d' remaining", (int) (timeRemaining / 3600),
                                    (int) (timeRemaining / 60) % 60));
                        }
                    }

                    for (PowerSource pSource : PowerSource.getPowerSources()) {
                        sb.append(String.format("%n %s  %.1f%%", pSource.getName(),
                                pSource.getRemainingCapacity() * 100d));
                    }
                    final String tmp[] = sb.toString().split("\r");
                    powerLabel.setText(tmp[0].trim());
                    batteryLabel.setText(tmp[1].trim());

                    try {
                        Thread.sleep(30000); // 30s

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Hello");
//                powerLabel.setText(tmp[0].trim());
//                batteryLabel.setText(tmp[1].trim());
//
//            }
//        }).start();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        shutdown = new javax.swing.JRadioButton();
        sleep = new javax.swing.JRadioButton();
        hibernate = new javax.swing.JRadioButton();
        restart = new javax.swing.JRadioButton();
        dat = new javax.swing.JButton();
        huy = new javax.swing.JButton();
        hienthi = new javax.swing.JTextField();
        textHour = new javax.swing.JTextField();
        textMinutes = new javax.swing.JTextField();
        sliderBrightness = new javax.swing.JSlider();
        powerLabel = new javax.swing.JLabel();
        batteryLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        buttonGroup1.add(shutdown);
        shutdown.setText("Shut down");
        shutdown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shutdownActionPerformed(evt);
            }
        });

        buttonGroup1.add(sleep);
        sleep.setText("Sleep");
        sleep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sleepActionPerformed(evt);
            }
        });

        buttonGroup1.add(hibernate);
        hibernate.setText("Hibernate");
        hibernate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hibernateActionPerformed(evt);
            }
        });

        buttonGroup1.add(restart);
        restart.setText("Restart");
        restart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restartActionPerformed(evt);
            }
        });

        dat.setText("Đặt");
        dat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datActionPerformed(evt);
            }
        });

        huy.setText("Hủy");
        huy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                huyActionPerformed(evt);
            }
        });

        textHour.setText("00");

        textMinutes.setText("00");

        sliderBrightness.setForeground(new java.awt.Color(0, 0, 0));
        sliderBrightness.setMajorTickSpacing(50);
        sliderBrightness.setMinorTickSpacing(5);
        sliderBrightness.setPaintLabels(true);
        sliderBrightness.setPaintTicks(true);
        sliderBrightness.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderBrightnessStateChanged(evt);
            }
        });

        powerLabel.setText("jLabel1");

        batteryLabel.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(sleep, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(restart, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hibernate, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(powerLabel)
                        .addGap(40, 40, 40)
                        .addComponent(batteryLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(shutdown, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(textHour, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81)
                        .addComponent(textMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(dat, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89)
                        .addComponent(huy, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(57, 57, 57))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(sliderBrightness, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(257, 257, 257)
                        .addComponent(hienthi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(hienthi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hibernate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(restart)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(huy)
                            .addComponent(dat)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(shutdown)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(powerLabel)
                                .addComponent(batteryLabel)))
                        .addGap(18, 18, 18)
                        .addComponent(sleep)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                .addComponent(sliderBrightness, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(124, 124, 124))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void commandLine() {

        String option = buttonGroup1.getSelection().getActionCommand();
        System.out.println(option);
        if (option.equals("Shut down")) {
            cmd = "c:\\Windows\\System32\\shutdown -s";

        }
        if (option.equals("Hibernate")) {
            cmd = "c:\\Windows\\System32\\shutdown -h";
        }
        if (option.equals("Sleep")) {
            cmd = "c:\\Windows\\System32\\Rundll32.exe powrprof.dll,SetSuspendState Sleep";
        }
        if (option.equals("Restart")) {
            cmd = "c:\\Windows\\System32\\shutdown -r";
        }
//        System.out.println(cmd);
        //  performButton.setDisable(false);

    }

    public void HenGioThread(String nameAction) {

        new Thread(new Runnable() {        //tạo luồng hieen thi thoi gian
            public void run() {

                if (nameAction.equals("dat")) {
                    dat.setEnabled(false);
                    int k = Integer.parseInt(textHour.getText()); // lấy giá trị giờ hẹn
                    int h = Integer.parseInt(textMinutes.getText()); // lấy giá trị phút hẹn

                    //                System.out.println(k);
                    int time = (k * 60 * 60 + h * 60) * 1000;
//                    String message = "Windowns will shutdown after " + String.valueOf(k) + " hour " + String.valueOf(h) + " minutes";
//                    JOptionPane.showMessageDialog(null, message, "Windowns logon reminder", JOptionPane.WARNING_MESSAGE);

                    try {
                        Thread.sleep(time); //Đơn vị miligiay.
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    commandLine();
//                  System.out.println(cmd);

                    System.out.println(cmd);
                    try {
                        Runtime.getRuntime().exec(cmd);

                    } catch (IOException e) {
                    }

                }
                if (nameAction.equals("huy")) {
                    textHour.setText("00"); // lấy giá trị giờ hẹn
                    textMinutes.setText("00"); // lấy giá trị phút hẹn

                    buttonGroup1.clearSelection();
                    String link2 = "c:\\Windows\\System32\\shutdown -a";
                    try {
                        Runtime.getRuntime().exec(link2);

                    } catch (IOException e) {
                    }
//                    String message = "The scheduled shutdown has been cancelled.";
//                    JOptionPane.showMessageDialog(null, message, "Windowns logon reminder", JOptionPane.INFORMATION_MESSAGE);

                    dat.setEnabled(true);
                }

            }
        }
        ).start();
        new Thread(new Runnable() {
            public void run() {
                if (nameAction.equals("dat")) {
                    int k = Integer.parseInt(textHour.getText()); // lấy giá trị giờ hẹn
                    int h = Integer.parseInt(textMinutes.getText()); // lấy giá trị phút hẹn

                    //                System.out.println(k);
                    String message = "Windowns will shutdown after " + String.valueOf(k) + " hour " + String.valueOf(h) + " minutes";
                    JOptionPane.showMessageDialog(null, message, "Windowns logon reminder", JOptionPane.WARNING_MESSAGE);

                }
                if (nameAction.equals("huy")) {
                    String message = "The scheduled shutdown has been cancelled.";
                    JOptionPane.showMessageDialog(null, message, "Windowns logon reminder", JOptionPane.INFORMATION_MESSAGE);
                }
            }

        }).start();
    }

    private void datActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datActionPerformed
        // TODO add your handling code here:
        shutdown.setActionCommand("Shut down");
        sleep.setActionCommand("Sleep");
        restart.setActionCommand("Restart");
        hibernate.setActionCommand("Hibernate");
        HenGioThread("dat");
    }//GEN-LAST:event_datActionPerformed

    private void huyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_huyActionPerformed
        // TODO add your handling code here:
        HenGioThread("huy");
    }//GEN-LAST:event_huyActionPerformed

    private void hibernateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hibernateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hibernateActionPerformed

    private void shutdownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shutdownActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_shutdownActionPerformed

    private void sleepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sleepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sleepActionPerformed

    private void restartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restartActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_restartActionPerformed

    private void sliderBrightnessStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderBrightnessStateChanged
        // TODO add your handling code here:
//        brightnessManagerThread();
        try {
            BrightnessManager.setBrightness(Math.round(sliderBrightness.getValue()));
        } catch (Exception e) {
        }


    }//GEN-LAST:event_sliderBrightnessStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Giao_Dien.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Giao_Dien.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Giao_Dien.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Giao_Dien.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Giao_Dien().setVisible(true);
            }
        });
    }

    static String cmd = "";
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel batteryLabel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton dat;
    private javax.swing.JRadioButton hibernate;
    private javax.swing.JTextField hienthi;
    private javax.swing.JButton huy;
    private javax.swing.JLabel powerLabel;
    private javax.swing.JRadioButton restart;
    private javax.swing.JRadioButton shutdown;
    private javax.swing.JRadioButton sleep;
    private javax.swing.JSlider sliderBrightness;
    private javax.swing.JTextField textHour;
    private javax.swing.JTextField textMinutes;
    // End of variables declaration//GEN-END:variables
}
