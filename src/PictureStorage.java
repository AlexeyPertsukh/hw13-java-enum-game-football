/*
https://www.asciiart.eu/sports-and-outdoors/soccer
https://wprock.fr/ru/t/kaomoji/
https://coremission.net/raznoe/smailiki-iz-simvolov/

 https://pixelplus.ru/samostoyatelno/stati/vnutrennie-faktory/tablica-simvolov-unicode.html
 */

//картинки для игры
public class PictureStorage {

        private final static String[] GATE_RIGHT1 =    {
                "                           ___      ",
                "     o___         o___    |   |\\   ",
                "    /|            |\\      |   |X\\ ",
                "    / > o          <\\     |   |XX\\",
    };

    private final static String[] GATE_RIGHT2 = {
            "       .                                       ",
            "    -   \\O                   ,  .---..  ",
            "  -    / \\                 O/   |   |X\\  ",
            " -  __/ \\ `               / \\   |   |XX\\  ",
            "    `    \\, ()           `  <<  |   |XXX\\  ",
    };

    private final static String[] GATE_RIGHT3 = {
            "                    ______________      ",
            "                   |     0        |\\   ",
            "    0              |   --()--     |X\\  ",
            "  -()-   o         |     <<       |XX\\ ",
            "   |\\,				                 ",
    };

    private final static String[][] GATES_RIGHT = {GATE_RIGHT1, GATE_RIGHT2, GATE_RIGHT3};

    private final static String[] GATE_LEFT1 = {
            "     ___                          ",
            "   /|   |    ___o         ___o    ",
            "  /X|   |      /|            |\\    ",
            " /XX|   |     />          o < \\    ",
    };

    private final static String[] GATE_LEFT2= {
            "                                 ,       ",
            "    ..---.  ,                  O/    -    ",
            "   /X|   |   \\O               / \\      -  ",
            "  /XX|   |   / \\             ' / \\__    - ",
            " /XXX|   |   >> `         () ./          ",
    };

    private final static String[] GATE_LEFT3= {
            "    ______________                      ",
            "  /|       0      |                     ",
            " /X|    --()--    |            0        ",
            "/XX|      >>      |        o  -()-      ",
            "                               <\\       " ,
    };


    private final static String[][] GATES_LEFT = {GATE_LEFT1, GATE_LEFT2, GATE_LEFT3};

    private final static String[] MAN_WIN1 = {
            "\\_(*_*) ",
            "   ()Z  ",
            "  /  \\ ",
    };

    private final static String[] MAN_WIN2 = {
            "╰(▔-▔)╯",
            "  ()   ",
            " /'\\  ",
    };

    private final static String[][] MANS_WIN = {MAN_WIN1, MAN_WIN2};

    private final static String[] MAN_LOOSE1 = {
            "(._.) ",
            " /|\\  ",
            " / \\   ",
    };

    private final static String[] MAN_LOOSE2 = {
            "   (ｏ・_・)ノ”(ノ_<、)",
            "     /|        /|\\  ",
            "     / \\       / \\   ",
    };

    private final static String[][] MANS_LOOSE = {MAN_LOOSE1, MAN_LOOSE2};


    public final static String[] SHOT_DOWN = {

            "(╯°□°）╯             t(-_-t)    ",
            "  ()   (`━') ﾉ          ()	   ",
            " / \\   / \\___,,        / \\     ",
    };

    public final static String[] LOGO = {
            "                             _ajjaa                      ",
            "                            _Q???4Qf                     ",
            "         _,...,_            ) a/]QQb                     ",
            "       .'@/~~~\\@'.              jQQba                   ",
            "      //~~\\___/~~\\          _, .?QQ#[ _                ",
            "     |@\\__/@@@\\__/@|         ]m _.7    asLaas_a/       ",
            "     |@/  \\@@@/  \\@|        , ,\\J#L -!4Wba            ",
            "       \\__/~~~\\__//       [aL[    \\    \\jmm     jP   ",
            "       '.@\\___/@.'  	 ,b#' [     \\jmmmmm    _P.       ",
            "         `------        a##'       /ef^gh?   _ya         ",
            "                       _P          !4#13#m  ?]aa/        ",
            "                      /'        aaJ#U###m#   4QP'        ",
            "                     '         aa,/4!44! '               ",
            "                 jf         _'jQQQQyb7b /                ",
            "                 '.         '' JAVA A01 ')?              ",
            "                             \"ШАГ\" '+?%                ",
            "                             Запорожье ',                ",
            "                          a#W?' 2021 ')?                 ",
            "                          ##' %eman%'                    ",
            "                        .j?  [ jQQ'                      ",
            "                   aJ  jmaaX#L???                        ",
            "                   ? am'                                 ",
            "                 _QjQQQ/                                 ",
            "                 )QQQP?                                  ",
            "                  4QQQ/                                  ",
    };



    private PictureStorage() {

    }

    public static void printPic(String[] pic, String color1, String color2)
    {
        String str1;
        String str2;

        for (String str : pic) {
            int length = str.length()/2;
            str1 = str.substring(0, length);
            str2 = str.substring(length);

            Color.printColor(str1, color1);
            Color.printlnColor(str2, color2);
        }
    }

    public static void printPic(String[] pic, String color)
    {
        printPic(pic, color, color);
    }

    public static void printPicGateLeft(String color1, String color2)
    {
        printRandomPic(GATES_LEFT, color1, color2);
    }

    public static void printPicGateRight(String color1, String color2)
    {
        printRandomPic(GATES_RIGHT, color1, color2);
    }

    public static void printManWin(String color) {
        printRandomPic(MANS_WIN, color);
    }

    public static void printManLoose(String color) {
        printRandomPic(MANS_LOOSE, color);
    }

    public static void printRandomPic(String[][] arr, String color)
    {
        int rand = Util.random(arr.length);
        String[] pic = arr[rand];

        printPic(pic, color);
    }

    public static void printRandomPic(String[][] arr, String color1, String color2)
    {
        int rand = Util.random(arr.length);
        String[] pic = arr[rand];

        printPic(pic, color1, color2);
    }

    public static void printShotDown(String color1, String color2)
    {
        printPic(SHOT_DOWN, color1, color2);
    }

    public static void printPicEnd(String color1, String color2) {
        printPic(LOGO, color1, color2);
    }

    public static String getStrPic(String[] pic, String color1, String color2, int num) {
        if(num < 0 || num >= pic.length) {
            return "";
        }

        int half = pic[0].length() / 2;

        String str1 = color1 + pic[num].substring(0, half);
        String str2 = color2 + pic[num].substring(half) + Color.ANSI_RESET;

        return str1 + str2;
    }


    public static String getHalfPic(String[] pic, int num, int part) {
        if(num < 0 || num >= pic.length || part < 0 || part >= 2 ) {
            return "";
        }
        int half = pic[0].length() / 2;
        return  (part == 0) ? pic[num].substring(0, half) : pic[num].substring(half);
    }

    public static int getArrLength(String[] arr) {
        return arr.length;
    }

}
