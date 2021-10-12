public class Footballer {
    public final static String STR_ATTACK = "↯"; //"↯" "\uD83D\uDDF2"
    public final static String STR_DEFENDER = "⛨";

    public final static int CODE_GOAL = 1;
    public final static int CODE_MISS = 0;
    public final static int CODE_CATCH = -1;

    private String name;
    private int num;
    private Role role;   //должность(позиция) игрока
    private int attack;  //0-100
    private int defender;


    public Footballer(String name, Role role, int attack, int defender) {
        this(name, 0, role, attack, defender);
    }

    public Footballer(String name, int num, Role role, int attack, int defender) {
        this.name = name;
        this.num = num;
        this.role = role;
        setAttack(attack);
        setDefender(defender);
    }

    public String getName() {
        return name;
    }


    public int getNum() {
        return num;
    }

    public Role getRole() {
        return role;
    }

    public String getStrRole() {
        return role.getRoleRuss();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getDefender() {
        return defender;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setAttack(int attack) {
        if(attack > 100) {
            attack = 100;
        }
        this.attack = attack;
    }

    public void setDefender(int defender) {
        if(defender > 100) {
            defender = 100;
        }
        this.defender = defender;
    }

    public String getInfo() {
        String str1 = num + ".";
        String str2 = name +" "; //","
        String str3 = role.getRoleRuss() +" "; //","

        return String.format("%-3s %-20s %-13s %s%-2d %s%d", str1, str2, str3, STR_ATTACK, attack, STR_DEFENDER, defender);
    }

    //пас случайному игроку кроме самому себя и вратаря
    public Footballer pass(Team team) {
        return team.getRandomFootballer(this);

    }

    //перехватим мячь?
    public Footballer takeAway() {
        int rand = My.random();
        if(defender > rand) {
            return this;
        }
        return null;
    }


    //удар по воротам
    public int kickOnGate(Footballer enemyGoalKeeper) {
        //удар по воротам
        int hit = My.random();
        int code = (attack > hit) ? CODE_GOAL : CODE_MISS;

        //у вратаря- шанс поймать
        if(code == CODE_GOAL) {
            if(enemyGoalKeeper.catchBall()) {
                code = CODE_CATCH;
            }
        }

        return code;
    }

    //поймать мяч - только для вратаря
    public boolean catchBall() {
        int hit = My.random();
        if((role != Role.GOALKEEPER) || (hit > getDefender()/2)) {
            return false;
        }
        return true;        //поймал
    }

}
