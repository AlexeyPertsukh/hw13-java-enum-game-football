/*
Все английские футбольные термины в одном посте
https://clck.ru/ThQJY

 */

import java.util.Scanner;

public class Game {

    private final static String VERSION = "1.4";

    private final static String CMD_HELP = "?";
    private final static String CMD_PRINT_TEAMS = "@";
    private final static String CMD_GAME = "#";
    private final static String CMD_PENALTY = "$";
    private final static String CMD_END = "end";
    public final static String CMD_SW_PAUSE = "~";

    public final static int PAUSE = 1500; //мс
    public final static boolean DEFAULT_PAUSE_STATE = true;

    private final static int MATCH_TIME_STEP_INC = 3;   //шаг увеличения времени в матче, мин.
    private final static int NUM_PENALTY = 5;           //количество ударов в пенальти до "правила мгновенной смерти"


    private final static String COLOR_TEAM1 = My.ANSI_GREEN;
    private final static String COLOR_TEAM2 = My.ANSI_PURPLE;
    private final static String COLOR_HEADER = My.ANSI_BLUE;

    private final static int NUM_PASS_FOR_KICK_ON_GATE = 2; //количество пасов до того, как появится возможность ударить по воротам

    private Team team1;
    private Team team2;

    private Team focusTeam;
    private Team otherTeam;

    private Footballer focusFootballer;
    private Match match;

    public boolean pauseOn;

    public Game() {
        pauseOn = DEFAULT_PAUSE_STATE;     //включение паузы по умолчанию
    }


    //=========== ОСНОВНОЙ БЛОК =====================
    public void go(){

        String cmd = "";
        boolean end = false;

        addTeams();

        Scanner sc = new Scanner(System.in);
        match = new Match(team1, team2, MATCH_TIME_STEP_INC);

        setFocus(team1);

        System.out.println("ФУТБОЛ " + VERSION);
        printTeams();

        do {
            printHeader();
            System.out.print("Введите команду: ");
            cmd = sc.next();
            switch(cmd.toLowerCase()) {
                case CMD_PRINT_TEAMS:
                    printTeams();
                    break;
                case CMD_HELP:
                    Help.print();
                    break;
                case CMD_END:
                    end = true;
                    break;
                case CMD_GAME:
                    match.on(focusTeam, pauseOn);
                    break;
                case CMD_PENALTY:
                    actionPenalty();
                    break;
                case CMD_SW_PAUSE:
                    pauseOn = !pauseOn;
                    String str = (pauseOn) ? "Пауза включена" : "Пауза отключена";
                    System.out.println(str);
                    break;
                default:
                    System.out.println("Неизвестная команда");
                    break;
            }

            //игра
            while(match.isOn()) {
                actionMatch();
                //анимация паузы
                if(match.isOn()) {
                    sleepAnimatLn();
                }
                //увеличиваем время в матче
                match.timeInc();
            }

        }while(!end);

        //конец программы
        System.out.println();
        System.out.println("JAVA A01 \"ШАГ\", Запорожье 2021");
        System.out.println("Перцух Алексей");
    }
    //===============================================

    //собираем команды
    public void addTeams() {
        //Шинник
        team1 = new Team("Шинник", "Ярославль", COLOR_TEAM1);
        team1.addFootballer(new Footballer("Дмитрий Яшин",  Role.GOALKEEPER.ordinal(), 10, 80));
        team1.addFootballer(new Footballer("Артем Щадин", Role.DEFENDER.ordinal(), 55, 70));
        team1.addFootballer(new Footballer("Денис Кутин", Role.DEFENDER.ordinal(), 50, 65));
        team1.addFootballer(new Footballer("Дмитрий Тананеев", Role.DEFENDER.ordinal(), 60, 75));
        team1.addFootballer(new Footballer("Вячеслав Зинков", Role.MIDFIELDER.ordinal(), 65, 60));
        team1.addFootballer(new Footballer("Иван Олейников", Role.MIDFIELDER.ordinal(), 65, 70));
        team1.addFootballer(new Footballer("Никита Мацхарашвили", Role.MIDFIELDER.ordinal(), 60, 60));
        team1.addFootballer(new Footballer("Тимур Пухов", Role.ATTACKER.ordinal(), 90, 45));
        team1.addFootballer(new Footballer("Альбек Гонгапшев", Role.ATTACKER.ordinal(), 90, 45));
        team1.addFootballer(new Footballer("Сергей Самодин", Role.ATTACKER.ordinal(), 85, 40));
        team1.addFootballer(new Footballer("Эльдар Низамутдинов", Role.ATTACKER.ordinal(), 90, 45));

        //
        team2 = new Team("Ворскла", "Полтава", COLOR_TEAM2);
        team2.addFootballer(new Footballer("Александр Ткаченко",  Role.GOALKEEPER.ordinal(), 10, 80));
        team2.addFootballer(new Footballer("Наджиб Якубу", Role.DEFENDER.ordinal(), 50, 60));
        team2.addFootballer(new Footballer("Брэдли де Нойер", Role.DEFENDER.ordinal(), 45, 65));
        team2.addFootballer(new Footballer("Евгений Опанасенко", Role.DEFENDER.ordinal(),50, 60));
        team2.addFootballer(new Footballer("Сергей Яворский", Role.DEFENDER.ordinal(), 50, 70));
        team2.addFootballer(new Footballer("Луизао", Role.MIDFIELDER.ordinal(), 60, 50));
        team2.addFootballer(new Footballer("Оливье Тилль", Role.MIDFIELDER.ordinal(), 70, 50));
        team2.addFootballer(new Footballer("Александр Скляр", Role.ATTACKER.ordinal(), 80, 30));
        team2.addFootballer(new Footballer("Владислав Кулач", Role.ATTACKER.ordinal(), 90, 40));
        team2.addFootballer(new Footballer("Алишер Азамов", Role.ATTACKER.ordinal(), 85, 40));
        team2.addFootballer(new Footballer("Руслан Степанюк", Role.ATTACKER.ordinal(), 85, 40));

    }

    //печатает состав команд
    private void printTeams() {
        System.out.println();
        System.out.println("СОСТАВ КОМАНД");
        System.out.println("-------------");
        System.out.printf("%-69s %-50s \n", team1.getInfoFlag(), team2.getInfoFlag());
        for (int i = 0; i < team1.getCntFootballer(); i++)
        {
            String str1 = "";
            String str2 = "";

            if(i < team1.getCntFootballer() ) {
                str1 = team1.getFootballer(i).getInfo();
            }
            if(i < team2.getCntFootballer() ) {
                str2 = team2.getFootballer(i).getInfo();
            }

            System.out.printf("%-54s %-60s   \n", str1, str2);
        }
    }

    //печатает заголовок
    private void printHeader() {
        System.out.println();
        System.out.println( team1.getInfoColored() + " : " + team2.getInfoColored());
        My.setTextColor(COLOR_HEADER);
        System.out.println("......................................................................................");
        System.out.printf ("%s помощь,  %s пауза вкл/откл,  %s состав команд,  %s играть,  %s пенальти,  %s выход  \n", CMD_HELP, CMD_SW_PAUSE, CMD_PRINT_TEAMS, CMD_GAME, CMD_PENALTY, CMD_END);
        System.out.println("......................................................................................");
        My.resetTextColor();
    }

    //фокус на команду и игрока
    private void setFocus(Team team) {
        Team oldTeam = focusTeam;

        focusTeam = (team == team1)? team1 : team2;
        otherTeam = (focusTeam == team1)? team2 : team1;

        //поменяли команду - фокусируемся на случайном игроке
        if(oldTeam != focusTeam) {
            focusFootballer = focusTeam.getRandomFootballer();
        }

    }

    private void setFocus(Footballer footballer) {

        if(team1.isTeamFootballer(footballer)) {
            setFocus(team1);
        }
        else {
            setFocus(team2);
        }

        focusFootballer = footballer;
      }

    //фокус на следующего случайного игрока(не вратаря) в команде
    private void nextFocusFootballer() {
        focusFootballer = focusTeam.getRandomFootballer(focusFootballer); // случайный игрок, кроме вратаря и текущего
    }

    private void nextFocus() {
        Team team = (focusTeam == team1) ? team2 : team1;
        setFocus(team);
        team1.resetPass();
        team2.resetPass();
    }

    private void nextFocus(Footballer footballer) {
        nextFocus();
        focusFootballer = footballer;
    }


    //выбор события при автоматической игре
    private void actionMatch() {
        int num;
        //время матча закончилось?
        if(match.isEndTime()) {
            match.off();
            setFocus(team1);
            return;
        }

        //сбили игрока - пенальти
        //происходит всегда на фиксированных ходах пенальти, рандом в том, что неизвестно какая будет бить команда
        //в реальной игре вызов пенальти делать по рандому
        //но здесь жестко 2 пенальти на игру(для красоты)
        if(match.getTurn() == 7 || match.getTurn() == 22)
        {
            penalty();
            return;
        }

        num = My.random(2);   //вероятность выхода на перехват мяча

        if(num == 1) {  //попытка перехвата мяча
            if(takeAway()) {
                return;
            }
        }

        //если выше не перехватили - пас дальше или удар по воротам
        if(focusTeam.getCntPass() >= NUM_PASS_FOR_KICK_ON_GATE) //пора бить по воротам
        {
            kickOnGate();
        }
        else {
            pass();
        }
    }

    //начальная часть строки слева- имя команды и текущее время матча
    private void printLeft(String teamName, String color) {
        String str = String.format("[%s] ", teamName);
        My.printColor(str, color);
        System.out.printf("%s ", match.getTime());
    }

    private void printLeft() {
        printLeft(focusTeam.getName(),focusTeam.getColor());
    }

    //пас
    private void pass() {
        printLeft();
        System.out.printf("%s передает пас на", focusFootballer.getName());
        focusFootballer = focusFootballer.pass(focusTeam);
        System.out.println(" " + focusFootballer.getName());
        focusTeam.incPass();
    }

    //удар по воротам
    private void kickOnGate() {
        printLeft();
        System.out.printf("%s %s бьет по воротам...  \n", focusFootballer.getStrRole(), focusFootballer.getName());
        //рисуем картинку
        if(focusTeam == team1) {
            Picture.printPicGateRight(focusTeam.getColor(), otherTeam.getColor());
        }
        else {
            Picture.printPicGateLeft(otherTeam.getColor(), focusTeam.getColor());
        }

        sleepAnimatLn(PAUSE * 3);

        int code = focusFootballer.kickOnGate(otherTeam.getGoalkeeper());

        //был гол?

        switch (code) {
            case Footballer.CODE_GOAL:
                match.addGoal(focusTeam);
                System.out.println("ГООООЛ!!!");
                Picture.printManWin(focusTeam.getColor());
                My.printlnColor("Счет " + match.getScoreAdditional(), match.getWinColor());
                break;
            case Footballer.CODE_MISS:
                System.out.println("МИМО");
                Picture.printManLoose(focusTeam.getColor());
                break;
            case Footballer.CODE_CATCH:
                System.out.println("и-и-и-иии ВРАТАРЬ ЛОВИТ МЯЧ!");
                Picture.printManLoose(focusTeam.getColor());
                break;
            default:
                break;
        }

        sleepAnimatLn(); //пауза
        nextFocus();    //передаем мяч следующей команде
        printLeft();
        System.out.printf("мяч ведет %s %s \n", focusFootballer.getStrRole(), focusFootballer.getName());

    }

    //попытка отобрать мяч
    private boolean takeAway() {
        Footballer enemy = otherTeam.getRandomFootballer();

        Footballer result = enemy.takeAway(); //пробуем отобрать мяч
        if(result == null) {        //не удалось- выходим
            printLeft(otherTeam.getName(), otherTeam.getColor());
            System.out.printf("%s команды \"%s\" %s не смог отобрать мяч\n", enemy.getStrRole(), otherTeam.getName(), enemy.getName());
            sleepAnimatLn();
            return false;
        }

        //удалось отобрать мяч(игрок в фокусе изменился)
        nextFocus(result);
        printLeft();
        System.out.printf("мяч перехватил %s команды \"%s\" %s \n", focusFootballer.getStrRole(), focusTeam.getName(), focusFootballer.getName());
        return true;
    }


    private void sleepAnimatLn() {
        sleepAnimatLn(PAUSE);
    }

    private void sleepAnimatLn(int pause) {
        My.sleepAnimationLn(pause, pauseOn);
    }

    private void sleepAnimat(int pause) {
        My.sleepAnimation(pause, pauseOn);
    }

    private void sleepAnimat() {
        My.sleepAnimation(PAUSE, pauseOn);
    }

    private void penalty() {
        printLeft();
        System.out.printf("нарушение в штрафной площадке! %s %s сбит с ног \n", focusFootballer.getStrRole(), focusFootballer.getName());
        if(focusTeam == team1) {
            Picture.printShotDown(team1.getColor(), team2.getColor());
        }
        else {
            Picture.printShotDown(team2.getColor(), team1.getColor());
        }
        sleepAnimatLn(PAUSE * 3);
        nextFocusFootballer();
        printLeft();
        System.out.printf("на пенальти выходит %s %s \n", focusFootballer.getStrRole(), focusFootballer.getName());
        kickOnGate();
    }

    private int penalty(Team team, Footballer footballer) {
        setFocus(footballer);
        String color = team.getColor();
        String str = String.format("[%s] ", team.getName());
        My.printColor(str, color);
        System.out.printf("на пенальти выходит %s %s. Удар", focusFootballer.getStrRole(), focusFootballer.getName());
        int code = focusFootballer.kickOnGate(otherTeam.getGoalkeeper());
        sleepAnimat(PAUSE);
        switch (code) {
            case Footballer.CODE_GOAL:
                System.out.println(" ГОЛ!");
                break;
            case Footballer.CODE_MISS:
                System.out.println(" МИМО");
                break;
            case Footballer.CODE_CATCH:
                System.out.println(" И ВРАТАРЬ ЛОВИТ МЯЧ!");
                break;
            default:
                break;
        }

        return code;
    }


    /*
    Обе команды выполняют по пять ударов.  <NUM_PENALTY>
    Команда, забившая большее количество голов, становится победителем матча (серии матчей).

    Если одна из команд забьёт больше голов, чем могла бы забить другая после выполнения пяти ударов,
    выполнение ударов прекращается (например, если после того, как обе команды выполнят по 3 удара, счёт будет 3:1,
    и игрок выигрывающей команды успешно исполнит 4-й удар, то дальнейшие удары не исполняются — проигрывающая
    команда смогла бы забить максимум 2 гола).

    Если после выполнения пяти ударов обе команды забьют равное количество голов или не забьют ни одного,
    выполнение ударов продолжается до тех пор, пока одна из команд не забьёт на один гол больше при одинаковом
    количестве выполненных ударов («Правило мгновенной смерти»).
     */
    private void actionPenalty() {
        int maxNum1 = team1.getCntFootballer() - 1;
        int num1 = maxNum1;
        int maxNum2 = team2.getCntFootballer() - 1;
        int num2 = maxNum2;

        int goal1 = 0;
        int hit1 = 0;

        int goal2 = 0;
        int hit2 = 0;

        int code;

        System.out.println();
        System.out.println("СЕРИЯ ПЕНАЛЬТИ");
        System.out.println("* * * * * * * *");

        int step = 1;
        boolean end = false;

        while (!end)
        {
            System.out.printf("ПЕНАЛЬТИ %d \n", step);
            System.out.println("------------- ");

            //
            if(earlyEndPenalties(step, hit1, goal1, hit2, goal2))
            {

                end = true;
                printScope(goal1, goal2);
                continue;
            }

            code = penalty(team1, team1.getFootballer(num1));
            hit1++;
            if( code == Footballer.CODE_GOAL) {
                goal1++;
            }

            num1--;
            if(num1 < 0) {
                num1 = maxNum1;
            }

            //
            if(earlyEndPenalties(step, hit1, goal1, hit2, goal2))
            {
                end = true;
                printScope(goal1, goal2);
                continue;
            }

            code = penalty(team2, team2.getFootballer(num2));
            hit2++;
            if( code == Footballer.CODE_GOAL) {
                goal2++;
            }

            num2--;
            if(num2 < 0) {
                num2 = maxNum2;
            }

            printScope(goal1, goal2);
            //конец серии пенальти - кто-то должен выииграть
            if((step >= NUM_PENALTY  && goal1 != goal2) ||
                earlyEndPenalties(step, hit1, goal1, hit2, goal2))
            {
                end = true;
                if(step > NUM_PENALTY) {
                    System.out.println("\"Мгновенная смерть\"...");
                }
            }
            step++;
        }
        //

        String color = (goal1 > goal2) ? team1.getColor() : team2.getColor();
        String strWin  = (goal1 > goal2) ? team1.getName() : team2.getName();
        strWin = String.format("Победила команда \"%s\"", strWin);
        My.printlnColor(strWin, color);
        System.out.println("............................");
    }

    //досрочное окончание пенальти
    private boolean earlyEndPenalties(int step, int hit1, int goal1, int hit2, int goal2) {

        if((step <= NUM_PENALTY) &&
              ((goal1 > (NUM_PENALTY - hit2) + goal2) ||
               (goal2 > (NUM_PENALTY - hit1) + goal1)))
        {
            System.out.println("Досрочное окончание серии пенальти");
            return true;
        }
        return  false;
    }


    //распечатывает счет при пенальти
    private void printScope(int goalTeam1, int goalTeam2) {
        String color = My.ANSI_RESET;

        if(goalTeam1 > goalTeam2) {
            color = team1.getColor();
        }
        else if(goalTeam1 < goalTeam2) {
            color = team2.getColor();
        }
        String str = String.format("СЧЕТ %d:%d   \n", + goalTeam1, goalTeam2);
        My.printlnColor(str, color);
    }


}

