package models;

public class StablefordGolfer extends Golfer {

    private int pts;


    @Override
    public void assignAttributes() {
        super.assignAttributes();
        pts = calculatePoints();
        gross = calculateGross();
        nett = calculateNett();
    }

    @Override
    public String toString() {
        return "StablefordGolfer{" +
                " position=" + position +
                ", fullName='" + fullName + '\'' +
                ", pts=" + pts +
                ", gross=" + gross +
                ", nett=" + nett +
                ", handicap=" + handicap +
                '}';
    }


    public int getPts() {
        return pts;
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

