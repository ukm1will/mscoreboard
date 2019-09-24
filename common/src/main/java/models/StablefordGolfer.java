package models;

import java.util.List;

public class StablefordGolfer extends Golfer {

    private int pts;

    public int getPts() {
        return pts;
    }


    public static String toString(List<StablefordGolfer> stablefordGolfers) {
        StringBuilder sb = new StringBuilder();
        for (StablefordGolfer stablefordGolfer: stablefordGolfers) {
            sb.append(stablefordGolfer.toString());
        }
        return sb.toString();
    }

//    @Override
//    public String toString() {
//        return "StablefordGolfer{" +
//                "pts=" + pts +
//                ", position=" + position +
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
        pts = calculatePoints();
        gross = calculateGross();
        nett = calculateNett();
    }

    @Override
    public int calculateGross() {
        int retval;
        int ptsOver36 = this.pts - 36;
        int expectedGross = 72 + handicap;
        retval = expectedGross - ptsOver36;
        return retval;
    }

    @Override
    public int calculateNett() {
        nett = gross - handicap;
        return nett;
    }

    private int calculatePoints() {
        return Integer.parseInt(partsOfScore[0]);
    }

}

