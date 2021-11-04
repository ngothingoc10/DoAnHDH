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
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
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
import javax.swing.ImageIcon;

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
                    timeLabel.setFont(new Font("Arial", Font.BOLD, 15));
                    timeLabel.setText(time);
                    timeLabel.setVisible(true);
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
        jPanel7 = new javax.swing.JPanel();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        panel1 = new java.awt.Panel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        panel2 = new java.awt.Panel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        panel3 = new java.awt.Panel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel(){
            ImageIcon icon = new ImageIcon("src/main/java/Power_Magerment/AnhNen15.jpg");
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }

        };
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        sleep = new javax.swing.JRadioButton();
        shutdown = new javax.swing.JRadioButton();
        hibernate = new javax.swing.JRadioButton();
        restart = new javax.swing.JRadioButton();
        textHour = new javax.swing.JTextField();
        textMinutes = new javax.swing.JTextField();
        hours = new javax.swing.JLabel();
        minutes = new javax.swing.JLabel();
        dat = new javax.swing.JButton();
        huy = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        timeLabel = new javax.swing.JLabel();
        sliderBrightness = new javax.swing.JSlider();
        powerLabel = new javax.swing.JLabel();
        batteryLabel = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jMenu1.setText("jMenu1");

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        jMenu4.setText("jMenu4");

        jMenu5.setText("jMenu5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(panel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(panel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel9.setLayout(null);

        jPanel10.setBackground(new java.awt.Color(0, 0, 0));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel11.setBackground(new java.awt.Color(0, 204, 204));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel11.setForeground(new java.awt.Color(255, 255, 255));

        buttonGroup1.add(sleep);
        sleep.setText("Sleep");
        sleep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sleepActionPerformed(evt);
            }
        });

        buttonGroup1.add(shutdown);
        shutdown.setText("Shut down");
        shutdown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shutdownActionPerformed(evt);
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

        textHour.setText("00");

        textMinutes.setText("00");

        hours.setText("Hour: ");

        minutes.setText("Minutes");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(hibernate, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(restart, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(shutdown, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sleep, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(hours, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(minutes, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textHour, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(76, 76, 76))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(sleep)
                        .addGap(4, 4, 4)
                        .addComponent(shutdown)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(restart))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textHour, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hours, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(minutes, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hibernate)
                .addContainerGap(12, Short.MAX_VALUE))
        );

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

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(dat, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(huy, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dat)
                    .addComponent(huy))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.add(jPanel10);
        jPanel10.setBounds(290, 60, 490, 200);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel9.add(jPanel12);
        jPanel12.setBounds(70, 60, 100, 100);

        timeLabel.setText("Time And Date");
        jPanel9.add(timeLabel);
        timeLabel.setBounds(350, 10, 390, 40);

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
        jPanel9.add(sliderBrightness);
        sliderBrightness.setBounds(490, 450, 310, 50);

        powerLabel.setFont(new java.awt.Font("MV Boli", 1, 14)); // NOI18N
        powerLabel.setForeground(new java.awt.Color(204, 51, 0));
        powerLabel.setText("jLabel1");
        jPanel9.add(powerLabel);
        powerLabel.setBounds(260, 460, 210, 40);

        batteryLabel.setFont(new java.awt.Font("MV Boli", 1, 14)); // NOI18N
        batteryLabel.setForeground(new java.awt.Color(204, 0, 0));
        batteryLabel.setText("jLabel2");
        jPanel9.add(batteryLabel);
        batteryLabel.setBounds(30, 460, 190, 40);

        getContentPane().add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 500));

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
    private javax.swing.JLabel hours;
    private javax.swing.JButton huy;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel minutes;
    private java.awt.Panel panel1;
    private java.awt.Panel panel2;
    private java.awt.Panel panel3;
    private javax.swing.JLabel powerLabel;
    private javax.swing.JRadioButton restart;
    private javax.swing.JRadioButton shutdown;
    private javax.swing.JRadioButton sleep;
    private javax.swing.JSlider sliderBrightness;
    private javax.swing.JTextField textHour;
    private javax.swing.JTextField textMinutes;
    private javax.swing.JLabel timeLabel;
    // End of variables declaration//GEN-END:variables
}
