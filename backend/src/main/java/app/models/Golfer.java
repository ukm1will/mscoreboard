package app.models;

public abstract class Golfer implements Comparable {

    String[] partsOfScore;
    int position;
    String firstName;
    String lastName;
    int gross;
    int nett;
    int handicap;
    private String[] parts;

    Golfer() {
        position = 0;
        firstName = null;
        lastName = null;
        gross = 0;
        nett = 0;
        handicap = 0;
    }

    public void assignAttributes() {
        String[] partsOfName = this.parts[1].split(",");
        partsOfScore = this.parts[2].split(" ");
        position = Integer.parseInt(parts[0]);
        lastName = partsOfName[0].trim();
        firstName = partsOfName[1].trim();
        handicap = calculateHandicap();
    }

    public abstract int calculateGross();

    public abstract int calculateNett();


    public int getPosition() {
        return position;
    }

    void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return firstName + " " + lastName;
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
            if (this.lastName.compareTo(that.lastName) == 0) {
                return this.firstName.compareTo(that.firstName);
            } else {
                return this.lastName.compareTo(that.lastName);
            }
        } else {
            return Integer.compare(this.gross, that.gross);
        }
    }
}
