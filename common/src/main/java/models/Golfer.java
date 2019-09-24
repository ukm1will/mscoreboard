package models;

public abstract class Golfer implements Comparable {

    protected String[] partsOfScore;
    private int position;
    private String forename;
    private String surname;

    public String getFullName() {
        return forename + " " + surname;
    }

//    public String getAft() {
//        return surname;
//    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

//    public String getFore() {
//        return forename;
//    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    //    String firstName;
//    String lastName;
    int gross;
    int nett;
    int handicap;
    private String[] parts;


    public void assignAttributes() {
        String[] partsOfName = this.parts[1].split(",");
        partsOfScore = this.parts[2].split(" ");
        position = Integer.parseInt(parts[0]);
        surname = partsOfName[0].trim();
        forename = partsOfName[1].trim();
//        lastName = partsOfName[0].trim();
//        firstName = partsOfName[1].trim();
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

//    public String getName() {
//        return firstName + " " + lastName;
//    }

    public int getGross() {
        return gross;
    }

    public int getNett() {
        return nett;
    }

//    public String getFirstName() {
//        return firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }

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
//            if (this.lastName.compareTo(that.lastName) == 0) {
//                return this.firstName.compareTo(that.firstName);
//            } else {
//                return this.lastName.compareTo(that.lastName);
//            }
        } else {
            return Integer.compare(this.gross, that.gross);
        }
        return 0;
    }
}
