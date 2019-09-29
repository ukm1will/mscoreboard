package models;

public class Colourise {
    public static void out(String value) {
        Character escapeChar = (char)27;
        StringBuilder sb = new StringBuilder();
        sb.append(escapeChar);
        sb.append("[31;46m"); // red on cyan
        sb.append(value);
        System.out.println(sb.toString());
        sb.setLength(0);
        sb.append(escapeChar);
        sb.append("[30m"); // reset to black with no message
        System.out.println(sb.toString());
    }

    public static void out(int str) {
        Character escapeChar = (char)27;
        StringBuilder sb = new StringBuilder();
        sb.append(escapeChar);
        sb.append("[31;46m"); // red on cyan
        sb.append(str);
        System.out.println(sb.toString());
        sb.setLength(0);
        sb.append(escapeChar);
        sb.append("[30m"); // reset to black with no message
        System.out.println(sb.toString());
    }


}