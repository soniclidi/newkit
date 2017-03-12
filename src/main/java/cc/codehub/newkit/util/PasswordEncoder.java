package cc.codehub.newkit.util;

import org.apache.commons.codec.digest.DigestUtils;



public class PasswordEncoder {

    private static final String publicSalt = "woshiyitouzhu";

    /**
     * 加密密码。
     *
     *
     * @param password，明文密码
     * @param salt，随机盐
     * @return 加密后的密码
     */
    public static String encryptPassword(String password, String salt) {

        String sha1Password = DigestUtils.sha1Hex(password);
        byte[] passwordBytes = sha1Password.getBytes();

        byte tmp = passwordBytes[0];
        passwordBytes[0] = passwordBytes[7];
        passwordBytes[7] = tmp;

        tmp = passwordBytes[3];
        passwordBytes[3] = passwordBytes[12];
        passwordBytes[12] = tmp;

        tmp = passwordBytes[6];
        passwordBytes[6] = passwordBytes[19];
        passwordBytes[19] = tmp;

        tmp = passwordBytes[8];
        passwordBytes[8] = passwordBytes[10];
        passwordBytes[10] = tmp;

        passwordBytes[2] = (byte)(passwordBytes[2]^passwordBytes[5]);
        passwordBytes[5] = (byte)(passwordBytes[5]^passwordBytes[9]);
        passwordBytes[9] = (byte)(passwordBytes[9]^passwordBytes[15]);
        passwordBytes[15] = (byte)(passwordBytes[15]^passwordBytes[18]);
        passwordBytes[18] = (byte)(passwordBytes[18]^passwordBytes[2]);

        return DigestUtils.sha256Hex(concatBytes(salt.getBytes(), concatBytes(passwordBytes, publicSalt.getBytes())));
    }

    public static byte[] concatBytes(byte[] data1, byte[] data2) {
        byte[] data3 = new byte[data1.length + data2.length];
        System.arraycopy(data1, 0, data3, 0, data1.length);
        System.arraycopy(data2, 0, data3, data1.length, data2.length);
        return data3;
    }
}
