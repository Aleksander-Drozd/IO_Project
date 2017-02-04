package Util;

public class DataVerificationUtil {
    public boolean verifyCustomer(String[] args){
        int length = args.length;

//        boolean[] resultTab = new boolean[length];

        for (int i = 0; i < length; i++) {
            if (args[i].equals("") || args[i].length() < 3) {
                return false;
            }
        }

        return true;
    }
}
