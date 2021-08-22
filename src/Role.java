public enum Role {
    GOALKEEPER("вратарь"), DEFENDER("защитник"), MIDFIELDER("полузащитник"), ATTACKER("нападающий");

    private String roleRuss;

    Role(String roleRuss) {
        this.roleRuss = roleRuss;
    }

    public String getRoleRuss() {
        return roleRuss;
    }

}
