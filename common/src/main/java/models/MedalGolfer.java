package models;

public class MedalGolfer extends Golfer {

    @Override
    public int calculateGross() {
        return Integer.parseInt(partsOfScore[0]);
    }

    @Override
    public int calculateNett() {
        return gross - handicap;

    }

//    @Override
//    public String toString() {
//        return "MedalGolfer{" +
//                "position=" + position +
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", gross=" + gross +
//                ", nett=" + nett +
//                ", handicap=" + handicap +
//                "}\n";
//    }

    @Override
    public void assignAttributes() {
        super.assignAttributes();
        gross = calculateGross();
        nett = calculateNett();
    }
}
