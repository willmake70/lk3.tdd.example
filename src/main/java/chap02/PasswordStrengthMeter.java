package chap02;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String s) {

        if(s == null || s.isEmpty()) return PasswordStrength.INVALID;

        int meetCounts = getMetCriteriaCounts(s);
        if(meetCounts <= 1) return PasswordStrength.WEAK;
        if(meetCounts == 2) return PasswordStrength.NORMAL;

        return PasswordStrength.STRONG;
    }

    private int getMetCriteriaCounts(String s){
        int meetCounts = 0;
        if(s.length() >= 8) meetCounts++;
        if(meetsContainingNumberCriteria(s)) meetCounts++;
        if(meetsContainingUppercaseCriteria(s)) meetCounts++;

        return meetCounts;
    }
    private Boolean meetsContainingNumberCriteria(String s){

        for(char ch : s.toCharArray()){
            if(ch >= '0' && ch <= '9'){
               return true;
            }
        }

        return false;
    }

    private Boolean meetsContainingUppercaseCriteria(String s) {
        for (char ch : s.toCharArray()) {
            if(Character.isUpperCase(ch)){
                return true;
            }
        }

        return false;
    }

}
