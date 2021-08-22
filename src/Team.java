public class Team {
    private String name;
    private String city;
    private String color;
    private Footballer[] footballers;
    private Footballer  goalkeeper;
    private int cntFootballer;
    private int cntPass;

    public Team(String name, String city, String color) {
        this.name = name;
        this.city = city;
        this.color = color;

        footballers = new Footballer[0];
        cntFootballer = 0;
    }


    public String getName() {
        return name;
    }

    public String getNameColored() {
        return color + name + My.ANSI_RESET;
    }

    public int getCntFootballer() {
        return cntFootballer;
    }

    public void incPass(){
        cntPass++;
    }

    public int getCntPass() {
        return cntPass;
    }

    public void resetPass(){
        cntPass = 0;
    }

    public void addFootballer(Footballer footballer)
    {
        Footballer[] tmp = new Footballer[footballers.length + 1];
        for (int i = 0; i < footballers.length; i++) {
            tmp[i] = footballers[i];
        }

        cntFootballer++;
        footballer.setNum(cntFootballer);


        tmp[tmp.length - 1] = footballer;
        footballers = tmp;

        //вратарь?
        if(footballer.getRole() == Role.GOALKEEPER.ordinal()) {
            goalkeeper = footballer;
        }
    }

    public String getColor() {
        return color;
    }

    public Footballer getFootballer(int num) {
//        System.out.println(num);
        return footballers[num];
    }

    public Footballer getGoalkeeper() {
        return goalkeeper;
    }

    public Footballer[] getFootballers() {
        return footballers;
    }

    public String getInfo() {
        return String.format("\"%s\", %s",  name, city);
    }

    public String getInfoColored() {
        return color + getInfo() + My.ANSI_RESET;
    }


    public String getInfoFlag() {
        return color + "⚑  " + getInfoColored();
    }

    //проверка вхождения игрока в команду
    public boolean isTeamFootballer(Footballer footballer) {
        for (Footballer tmp : footballers)
        {
        if (footballer == tmp) {
                return true;
            }
        }
        return false;
    }


    //возвращает случайного полевого игрока( не вратаря)
    public Footballer getRandomFootballer() {
        Footballer footballer;

        do {
            int rand = My.random(getCntFootballer());
            footballer = getFootballer(rand);
        } while(footballer.getRole() == Role.GOALKEEPER.ordinal());

        return footballer;
    }

    //возвращает случайного полевого игрока( не вратаря), исключая того, кто exceptFootballer
    public Footballer getRandomFootballer(Footballer exceptFootballer) {
        Footballer footballer;

        do {
            footballer = getRandomFootballer();
        }while(footballer == exceptFootballer);

        return footballer;
    }


}
