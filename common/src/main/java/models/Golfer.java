package models;

import org.apache.commons.codec.binary.StringUtils;

import java.util.Objects;

public class Golfer implements Comparable {

    protected String[] partsOfScore;
    int gross;
    int nett;
    int handicap;
    int position;
    int pts;
    private String forename;
    private String surname;
    protected String fullName;
    private String[] parts;

    public int getPosition() {
        return position;
    }

    void setPosition(int position) {
        this.position = position;
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
        pts = calculatePoints();
        gross = calculateGross();
        nett = calculateNett();
        fullName = forename + ' ' + surname;
    }

    public int calculateGross() {
        int retval;
        int ptsOver36 = this.pts - 36;
        int expectedGross = 72 + handicap;
        retval = expectedGross - ptsOver36;
        return retval;
    }

    private int calculatePoints() {
        return Integer.parseInt(partsOfScore[0]);
    }


    public int calculateNett() {
        nett = gross - handicap;
        return nett;
    }

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

    public int getPts() {
        return pts;
    }
}
