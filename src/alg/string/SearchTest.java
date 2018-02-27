package alg.string;

/**
 * @author QFJiang on 2018/01/04 15:05
 */
public class SearchTest {

    public static void main(String[] args) {

        // KMP test
        System.out.println(KMP.kmpSearch("ABCDEFGH", "DEF"));
        System.out.println(KMP.kmpSearch("ABCDEFGHIJ", "EDF"));
        System.out.println(KMP.kmpSearch("华中科技大学", "科技"));

        // Sunday test
        System.out.println(Sunday.sundaySearch("ABCDEFGH", "DEF"));
        System.out.println(Sunday.sundaySearch("ABCDEFGHIJ", "EDF"));
        System.out.println(Sunday.sundaySearch("华中科技大学", "科技"));
    }
}
