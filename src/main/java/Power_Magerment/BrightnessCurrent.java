/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Power_Magerment;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author Ngoc
 */
public class BrightnessCurrent {
    public static void setBrightnessCurrent() {
        try {
            ProcessBuilder pb = new ProcessBuilder("powershell.exe", "/c", "Get-Ciminstance -Namespace root/WMI -ClassName WmiMonitorBrightness | Select -ExpandProperty \"CurrentBrightness\"").redirectErrorStream(true);
            Process process = pb.start();
            StringBuilder result = new StringBuilder(80);
            try (BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream())))
            {
                while (true)
                {
                    String line = in.readLine();
                    if (line == null)
                        break;
                    result.append(line);
                }
            }
                System.out.println(result.toString());
    
            } catch (Exception e) {
                System.out.println("Error");
                e.printStackTrace(); // In cai dong ni ra de debug xem loi.
            }
        
    }
}
