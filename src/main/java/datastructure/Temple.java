package datastructure;

public class Temple {

    public static void main(String[] args) {
        System.out.println(Integer.parseInt("00743"));
    }

    private void pro2(String str){
        for (int i = 1; i < str.length(); i++) {
            String left = str.substring(0, i);
            String right = str.substring(i, str.length());

            int l1 = Integer.parseInt(left);
            int r1 = Integer.parseInt(right);
        }
    }


}
