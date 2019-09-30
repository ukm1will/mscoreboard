package models;

import org.apache.commons.codec.binary.StringUtils;

import java.util.Objects;

public abstract class Golfer implements Comparable {

    protected String[] partsOfScore;
    int gross;
    int nett;
    int handicap;
    private int position;
    private String forename;
    private String surname;
    private String fullName;
    private String[] parts;

    public int getPosition() {
        return position;
    }

    void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Golfer{" +
                "gross=" + gross +
                ", nett=" + nett +
                ", handicap=" + handicap +
                ", position=" + position +
                ", fullName='" + fullName + '\'' +
                '}';
    }

    public int getGross() {
        return gross;
    }

    public int getNett() {
        return nett;
    }

    public int getHandicap() {
        return handicap;
    }

    public void assignAttributes() {
        String[] partsOfName = this.parts[1].split(",");
        partsOfScore = this.parts[2].split(" ");
        position = Integer.parseInt(parts[0]);
        surname = partsOfName[0].trim();
        forename = partsOfName[1].trim();
        handicap = calculateHandicap();
        fullName = forename + ' ' + surname;
    }

    public abstract int calculateGross();

    public abstract int calculateNett();

    void split(String s) {
        this.parts = s.split("\t");
    }

    private int calculateHandicap() {
        partsOfScore[2] = partsOfScore[2].replaceAll("\\(", "");
        partsOfScore[2] = partsOfScore[2].replaceAll("\\)", "");
        return Integer.parseInt(partsOfScore[2]);
    }

    @Override
    public int compareTo(Object obj) {
        Golfer that = (Golfer) obj;
        if (Integer.compare(this.gross, that.gross) == 0) {
            if (this.surname.compareTo(that.surname) == 0) {
                return this.forename.compareTo(that.forename);
            } else {
                return this.surname.compareTo(that.surname);
            }
        } else {
            return Integer.compare(this.gross, that.gross);
        }
    }

    public String getFullName() {
        return fullName;
    }
}
