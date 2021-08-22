public class Help {

    private final static String COLOR1 = My.ANSI_YELLOW;
    private final static String COLOR2 = My.ANSI_RED;
    private final static String COLOR_HELP = My.ANSI_RESET;

    private final static String GIT_URL = "https://github.com/AlexeyPertsukh/hw13-java-enum-game-football";

    private static final String[] HELP = {
            "--------------",
            Footballer.STR_ATTACK + " Атака, способность попасть по воротам   ",
            Footballer.STR_DEFENDER + " Защита, способность перехватить мяч(полевые игроки) или поймать его(вратарь)",
            "....",
            "Паузу в игре и при выполнении серии пенальти можно включить ",
            "или отключить с помощью команды: " + Game.CMD_SW_PAUSE,
            "....                                                                           ",
            "Правила послематчевых пенальти:                                                ",
            "• Обе команды выполняют по пять ударов.                                        ",  //константа NUM_PENALTY
            "• Команда, забившая большее количество голов, становится победителем матча.    ",
            "• Если одна из команд забьёт больше голов, чем могла бы забить другая после    ",
            "  выполнения пяти ударов, выполнение ударов прекращается. Например, если после ",
            "  того, как обе команды выполнят по 3 удара, счёт будет 3:1, и игрок           ",
            "  выигрывающей команды успешно исполнит 4-й удар, то дальнейшие удары          ",
            "  не исполняются — проигрывающая команда смогла бы забить максимум 2 гола      ",
            "  (досрочное прекращение серии пенальти).    ",
            "• Если после выполнения пяти ударов обе команды забьют равное количество голов ",
            "  или не забьют ни одного, выполнение ударов продолжается до тех пор,          ",
            "  пока одна из команд не забьёт на один гол больше при одинаковом количестве   ",
            "  выполненных ударов (правило мгновенной смерти).                              ",
            "--------------",
            GIT_URL,

    };

    private Help() {
    }

    public static void print() {
        int helpLength = HELP.length;
        int logoLength = Picture.getArrLength(Picture.LOGO);

        int max = 0;
        for (String tmp : HELP) {
            if(tmp.length() > max) {
                max = tmp.length();
            }
        }

        String format = "%-" + max + "s" + " %" + "-100s   \n";      //форматированная строка для вывода тексчта

        int length = (helpLength > logoLength) ? helpLength : logoLength;

        for (int i = 0; i < length ; i++) {
            String str1 = getStrArr(HELP, i);
            String str2 = Picture.getStrPic(Picture.LOGO, COLOR1, COLOR2, i) + COLOR_HELP;
            System.out.printf(format, str1, str2);
        }
        System.out.print(My.ANSI_RESET);
    }

    private static String getStrArr(String[] arr, int num) {
        if(num < 0 || num >= arr.length) {
            return "";
        }
        return arr[num];
    }

}
