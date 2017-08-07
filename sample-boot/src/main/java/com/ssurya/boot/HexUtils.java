/*******************************************************************************
 * Copyright © 2016 Oracle and/or its affiliates. All rights reserved.
 *******************************************************************************/
package com.ssurya.boot;

import java.nio.ByteBuffer;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.DatatypeConverter;

public class HexUtils {
    
    private HexUtils() {}
    
    private static final Pattern uuidPattern;
    static {
        String p = "^\\p{XDigit}{32}$";
        uuidPattern = Pattern.compile(p);
    }
    
    public static String toHexString(byte[] byteArray) {
        return DatatypeConverter.printHexBinary(byteArray);
    }
    
    public static byte[] toByteArr(String input) {
        // just in case we have a standard hyphenated UUID
        String hexDigits = input.replace("-", "");
        return DatatypeConverter.parseHexBinary(hexDigits);
    }
    
    public static UUID toUUID(byte[] byteArray) {
        if (byteArray.length != 16) {
            throw new IllegalArgumentException("Wrong length byte array for UUID");
        }
        ByteBuffer bb = ByteBuffer.wrap(byteArray);
        long msb = bb.getLong();
        long lsb = bb.getLong();
        UUID uuid = new UUID(msb, lsb);
        return uuid;
    }
    
    public static byte[] toByteArr(UUID uuid) {
        ByteBuffer bb = ByteBuffer.allocate(16);
        long msb = uuid.getMostSignificantBits();
        long lsb = uuid.getLeastSignificantBits();
        bb.putLong(msb).putLong(lsb);
        return bb.array();
    }
    
    public static String toHexString(UUID uuid) {
        return toHexString(toByteArr(uuid));
    }
    
    public static UUID toUUID(String input) {
        String hexDigits = cleanAndValidateUUIDString(input);
        return toUUID(toByteArr(hexDigits));
    }
    
    public static UUID makeSurrogateID(UUID...uuids) {
        ByteBuffer bb = ByteBuffer.allocate(16*uuids.length);
        for (UUID uuid : uuids) {
            if (uuid != null) {
                bb.putLong(uuid.getMostSignificantBits());
                bb.putLong(uuid.getLeastSignificantBits());
            } else {
                bb.putLong(0L).putLong(0L);
            }
        }
        return makeSurrogateID(bb.array());
    }
    
    public static UUID makeSurrogateID(byte[] input) {
        return UUID.nameUUIDFromBytes(input);
    }
    	
	public static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}	
		
	public static String toHex (UUID uuid) {
        byte[] b = new byte[16];
        ByteBuffer bb = ByteBuffer.wrap(b);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return toHexString(b);
	}
	
	static String cleanAndValidateUUIDString(String uuid) {
	    String hexDigits = uuid.replace("-", "").trim();
	    Matcher m = uuidPattern.matcher(hexDigits);
	    if (m.find()) {
	        return hexDigits;
	    } else {
	        throw new IllegalArgumentException("Not a valid UUID string");
	    }
	}
}
