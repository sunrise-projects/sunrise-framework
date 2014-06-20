/*
* Copyright 2002-2013 the original author or authors.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.sunriseframework.util;


/**
* Utility to motp algorithm
* Java function to authenticate Mobile-OTP tokens on web servers with Java.
* Mobile One Time Passwords -- Mobile-OTP -- strong, two-factor authentication with mobile phones
* http://motp.sourceforge.net/
* test this on https://kelvin.nu/potato/index.php
* @author Apache Opensource
* @since 1.0-rc1
*/
public class MobileOTP {
   
    // Function to generate an OTP based on the init-secret and the PIN 43980e
    public static String generateOTP(String initsecret, String pin) {              
            long time = System.currentTimeMillis() / 1000L;
            String tm = String.valueOf(time);
            return ((MD5(tm.substring(0, tm.length()-1)+initsecret+pin)).substring(0,6));
    }
   
    /*******************************
     * Function checkOTP($user,$otp,$initsecret,$pin,$offset = 0)
     * $user       : username
     * $otp        : one-time-password that is to be checked
     * $initsecret : init-secret from token
     * $pin        : user PIN
     * $offset     : time difference between token and server in 10 seconds increments (360 = 1 hour)
     *
     * This function returns 0 if the OTP is accepted, and different error codes otherwise (the same as the shell version)
     */
   
    public static boolean checkOTP(String user, String otp, String initsecret, String pin, long offset) {
           
            // Here is the allowed +/- timeframe in minutes
            long timeframe = 3;
            long maxperiod = timeframe * 60; // maxperiod in seconds = +/- 3 minutes
            long time = System.currentTimeMillis() / 1000L; // time in seconds
           
            for(long i = time + (offset * 10) - maxperiod; i <= time + (offset * 10) + maxperiod; i++)
            {
                    String tm = String.valueOf(i);
                    String md5 = ((MD5(tm.substring(0, tm.length()-1)+initsecret+pin)).substring(0,6));
                    if(md5.equals(otp)) return true;
            }      
            return false;
           
    }
    public static String MD5(String md5) {
               try {
                    java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
                    byte[] array = md.digest(md5.getBytes());
                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < array.length; ++i) {
                      sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
                   }
                    return sb.toString();
                } catch (java.security.NoSuchAlgorithmException e) {
                }
                return null;
	}
}
