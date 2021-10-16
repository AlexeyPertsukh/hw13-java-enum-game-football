//время в игре
public class Match {
    private int min;
    private int sec;
    private int step;
    private boolean matchOn;
    private int[] goal;
    private int turn;       //ход, счетчик ходов


    private Team team1;
    private Team team2;

    private boolean pauseOn;

    public Match(Team team1, Team team2) {
        this(team1, team2,  1);
    }

    public Match(Team team1, Team team2, int step) {
        this.step = step;
        this.team1 = team1;
        this.team2 = team2;
        reset();
    }

    public void reset() {
        min = 0;
        sec = 0;
        matchOn = false;
        team1.resetPass();
        team2.resetPass();
        goal = new int[] {0, 0};
        turn = 0;
    }

    public String getTime() {
        return String.format("%02d:%02d", min, sec);
    }

    public void timeInc() {
        min += step;
        sec = Util.random(59);
        turn++;
    }

    //время матча вышло
    public boolean isEndTime() {
        return min >= 90;
    }

    public void on(Team team, boolean pauseOn) {
        this.pauseOn = pauseOn;
        reset();
        matchOn = true;
        System.out.println();
        String str = String.format("\"%s\"", team.getName());
        str = team.getColor() + str + Color.ANSI_RESET;
        System.out.printf("Игра началась, мяч у игроков команды %s!   \n", str);
        Util.sleepAnimationLn(Game.PAUSE, pauseOn);
    }

    public void off() {
        System.out.printf("%s Матч окончен! \n", getTime());
        Color.printlnColor("СЧЁТ " + getScoreAdditional(), getWinColor());
        Util.sleepAnimationLn(Game.PAUSE * 3, pauseOn);
        reset();
    }

    //счет + кто ведет
    public String getScoreAdditional() {
        String str = getScore();
        if(goal[0] == goal[1]) {
            str += ", НИЧЬЯ";
        }
        else if(goal[0] > goal[1]) {
            str += " в пользу команды \"" + team1.getName() + "\"";
        }
        else {
            str += " в пользу команды \"" + team2.getName() + "\"";
        }
        return str;
    }


    public String getWinColor() {
        String color = Color.ANSI_RESET;
        if(goal[0] > goal[1]) {
            color = team1.getColor();
        }
        else
        if(goal[0] < goal[1]) {
            color = team2.getColor();
        }
        return color;
    }

    public boolean isOn() {
        return matchOn;
    }

    public String getScore() {
        return goal[0] + ":" + goal[1];
    }

    public int getTurn() {
        return turn;
    }

    //считаем голы
    public void addGoal(Team team) {

        if(team == team1) {
            goal[0]++;
        } else
        if(team == team2) {
            goal[1]++;
        }

    }

}
