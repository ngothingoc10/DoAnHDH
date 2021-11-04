/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Power_Magerment.BaterrySystem;

/**
 *
 * @author Ngoc
 */

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Library;

import com.sun.jna.Native;
import com.sun.jna.NativeLong;// a ve di em di nau ăn pp vk nha.<3 <3  chut chut phun nuoc mieng hahahow
import com.sun.jna.Pointer;// bamams zô project -> clicj phai zo dependci -> add -> sau do install jar vô nua
import com.sun.jna.Structure;

public interface PowerProfile extends Library {

    @SuppressWarnings("deprecation")
    PowerProfile INSTANCE = (PowerProfile) Native.loadLibrary("PowrProf", PowerProfile.class);// cai gi zhuhu

    static int SYSTEM_BATTERY_STATE = 5;

    static class SystemBatteryState extends Structure {

        public byte acOnLine;
        public byte batteryPresent;
        public byte charging;
        public byte discharging;
        public byte[] spare1 = new byte[4];
        public int maxCapacity;
        public int remainingCapacity;
        public int rate;
        public int estimatedTime;
        public int defaultAlert1;
        public int defaultAlert2;

        @SuppressWarnings({"unchecked", "rawtypes"})
        @Override
        protected List getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList(new String[]{"acOnLine", "batteryPresent", "charging", "discharging", "spare1",
                "maxCapacity", "remainingCapacity", "rate", "estimatedTime", "defaultAlert1", "defaultAlert2"});
        }

    }

    int CallNtPowerInformation(int informationLevel, Pointer lpInputBuffer, NativeLong nInputBufferSize,
            Structure lpOutputBuffer, NativeLong nOutputBufferSize);
}
