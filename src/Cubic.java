import java.math.BigDecimal;
import java.math.BigInteger;

public class Cubic
{

    private static final BigDecimal TWO_PI = new BigDecimal( Math.PI*2.0);
    private static final BigDecimal FOUR_PI = new BigDecimal(4.0 * Math.PI);

    public int nRoots;

    public BigDecimal getX1() {
        if(x1 != null) {
            return x1.setScale(12, BigDecimal.ROUND_HALF_UP);
        }else return null;
    }

    public BigDecimal getX2() {
        if(x2 != null) {
            return x2.setScale(12, BigDecimal.ROUND_HALF_UP);
        }else return null;
    }

    public BigDecimal getX3() {
        if(x3 != null) {
            return x3.setScale(12, BigDecimal.ROUND_HALF_UP);
        }else return null;
}

    public BigDecimal x1;

    public BigDecimal x2;

    public BigDecimal x3;

    public Cubic()
    {
    }

    public void solve
    (double aD,
     double bD,
     double cD,
     double dD)
    {

        BigDecimal a = new BigDecimal(aD).setScale(12, BigDecimal.ROUND_HALF_UP);
        BigDecimal b = new BigDecimal(bD).setScale(12, BigDecimal.ROUND_HALF_UP);
        BigDecimal c = (new BigDecimal(cD)).setScale(12, BigDecimal.ROUND_HALF_UP);
        BigDecimal d = (new BigDecimal(dD)).setScale(12, BigDecimal.ROUND_HALF_UP);

        if (a.equals(new BigDecimal(0.0)))
        {
            throw new RuntimeException ("Cubic.solve(): a = 0");
        }

        BigDecimal denom = new BigDecimal(a.doubleValue());

        a = b.divide(denom, BigDecimal.ROUND_HALF_EVEN);
        b = c.divide(denom, BigDecimal.ROUND_HALF_EVEN);
        c = d.divide(denom, BigDecimal.ROUND_HALF_EVEN);

        BigDecimal a_over_3 = new BigDecimal(a.doubleValue()/3.0).setScale(12, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal Q = ((b.multiply(new BigDecimal(3))).subtract(a.pow(2))).divide(new BigDecimal(9.0),BigDecimal.ROUND_HALF_EVEN);
        BigDecimal Q_CUBE = Q.pow(3);
        BigDecimal R =  (((a.multiply(b).multiply(new BigDecimal(9))).subtract(c.multiply(new BigDecimal(27)))).subtract(a.pow(3).multiply(new BigDecimal(2)))).divide(new BigDecimal(54.0), BigDecimal.ROUND_HALF_EVEN);
        BigDecimal R_SQR = R.pow(2);
        BigDecimal D = Q_CUBE.add(R_SQR);

        if (D.doubleValue() < 0)
        {
            nRoots = 3;

            BigDecimal theta =  new BigDecimal( Math.acos ((R.divide(new BigDecimal(Math.sqrt((Q_CUBE.negate()).doubleValue())), BigDecimal.ROUND_HALF_EVEN)).doubleValue()));
            BigDecimal SQRT_Q = new BigDecimal(Math.sqrt (Q.negate().doubleValue()));
            x1 = (new BigDecimal(Math.cos (((theta.divide(new BigDecimal(3.0),BigDecimal.ROUND_HALF_EVEN))).doubleValue()))).multiply(SQRT_Q.multiply(new BigDecimal(2.0))).subtract(a_over_3);
            x2 = ((new BigDecimal(Math.cos ((((theta.add(TWO_PI)).divide(new BigDecimal(3.0),BigDecimal.ROUND_HALF_EVEN))).doubleValue()))).multiply(SQRT_Q.multiply(new BigDecimal(2.0)))).subtract(a_over_3);
            x3 = ((new BigDecimal(Math.cos ((((theta.add(FOUR_PI)).divide(new BigDecimal(3.0),BigDecimal.ROUND_HALF_EVEN))).doubleValue()))).multiply(SQRT_Q.multiply(new BigDecimal(2.0)))).subtract(a_over_3);
            sortRoots();
        }

        else if (D.doubleValue() > 0)
        {

            nRoots = 1;
            BigDecimal SQRT_D = new BigDecimal(Math.sqrt (D.doubleValue()));
            BigDecimal S = new BigDecimal(Math.cbrt( (R.add( SQRT_D)).doubleValue()));
            BigDecimal T = new BigDecimal(Math.cbrt ((R.subtract(SQRT_D)).doubleValue()));
            x1 = (S.add(T)).subtract(a_over_3);
            x2 = null;
            x3 = null;
        }
        else
        {
            nRoots = 3;
            System.out.println("IN else");
            BigDecimal CBRT_R = new BigDecimal(Math.cbrt (R.doubleValue()));
            x1 = (CBRT_R.multiply(new BigDecimal(2))).subtract(a_over_3);
            x2 = x3 = CBRT_R.subtract(a_over_3);
            sortRoots();
        }
    }

    private void sortRoots()
    {
        if (x1.doubleValue() < x2.doubleValue())
        {
            BigDecimal tmp = x1; x1 = x2; x2 = tmp;
        }
        if (x2.doubleValue() < x3.doubleValue())
        {
            BigDecimal tmp = x2; x2 = x3; x3 = tmp;
        }
        if (x1.doubleValue() < x2.doubleValue())
        {
            BigDecimal tmp = x1; x1 = x2; x2 = tmp;
        }
    }

    public static void main(String[] args) throws Exception {


        Cubic cubic = new Cubic();
    }

}