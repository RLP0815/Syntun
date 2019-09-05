package com.syntun.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class HuoQuIP {
	
	private final static char[] HEX = "0123456789ABCDEF".toCharArray();
	
    public static List<String> getNetworkAddress() {
        List<String> result = new ArrayList<String>();
        Enumeration<NetworkInterface> netInterfaces;
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip;
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> addresses=ni.getInetAddresses();
                while(addresses.hasMoreElements()){
                    ip = addresses.nextElement();
                    if (!ip.isLoopbackAddress() && ip.getHostAddress().indexOf(':') == -1) {
                        result.add(ip.getHostAddress());
                        System.out.println("IP: " +ip.getHostAddress()+"   NAME: " + ni.getDisplayName());
//                        System.out.println("MAC: " + toMacString(ni.getHardwareAddress()));
                        
                    }
                }
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }
    
    @SuppressWarnings("unused")
	private static String toMacString(byte[] bys) {
        if(bys == null) {
            return null;
        }
        char[] chs = new char[bys.length * 3 - 1];
        for(int i = 0, k = 0; i < bys.length; i++) {
            if(i > 0) {
                chs[k++] = '-';
            }
            chs[k++] = HEX[(bys[i] >> 4) & 0xf];
            chs[k++] = HEX[bys[i] & 0xf];
        }
        return new String(chs);
    }
    
    public static void main(String[] args) {
    	getNetworkAddress();
	}

}
