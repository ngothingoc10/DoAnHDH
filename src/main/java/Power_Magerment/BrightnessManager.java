/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Power_Magerment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Ngoc
 */
public class BrightnessManager {

    public static void setBrightness(int brightnessLevel) throws IOException {
        // Creates a powerShell command that will set the brightness to the requested
        // value (0-100), after the requested delay (in milliseconds) has passed.
        String s = String.format("$brightnessLevel = %d;", brightnessLevel) + "$delay = 0;"
                + "$myMonitor = Get-WmiObject -Namespace root\\wmi -Class WmiMonitorBrightnessMethods;"
                + "$myMonitor.wmisetbrightness($delay, $brightnessLevel)";
        String command = "powershell.exe  " +s ;
        System.out.println(command);
        // Executing the command
        Process powerShellProcess = Runtime.getRuntime().exec(command);

        powerShellProcess.getOutputStream().close();

        // Report any error messages
        String line;

        BufferedReader stderr = new BufferedReader(new InputStreamReader(powerShellProcess.getErrorStream()));
        line = stderr.readLine();
        if (line != null) {
            System.out.println("Standard Error:");
            do {
                System.out.println(line);
            } while ((line = stderr.readLine()) != null);

        }
        stderr.close();

    }

    

}
