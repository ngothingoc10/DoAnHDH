/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Power_Magerment;
import java.awt.Font;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Ngoc
 */
public class HenGioThread extends GiaoDien implements Runnable {
    public String nameAction
            
    public HenGioThread(String nameAction){
        this.nameAction = nameAction;
        
    }
    public void run() {
        while (true) {
            if (this.nameAction == "dat") {
                tatNgay.setEnabled(false); //tắt button
                int k = hour.getSelectedIndex(); // lấy giá trị giờ hẹn
                int h = minutes.getSelectedIndex(); // lấy giá trị phút hẹn
                int time = k * 60 * 60 + h * 60;
                String comMandLine = "c:\\Windows\\System32\\shutdown -s -t " + String.valueOf(time);
                try {
                    Runtime.getRuntime().exec(comMandLine);
                } catch (IOException e) {
                }
                dat.setEnabled(false);

            }
//            if (this.button == sleep) {
//
//            }
        }
    }

}
