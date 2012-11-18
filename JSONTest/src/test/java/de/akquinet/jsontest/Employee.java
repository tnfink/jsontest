package de.akquinet.jsontest;

public class Employee {

    private Employee superior;

    private String login;

    public Employee(final String login, final Employee superior) {
        this.login = login;
        this.superior = superior;
    }

    public Employee getSuperior() {

        return superior;
    }

    public void setSuperior(final Employee superior) {
        this.superior = superior;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }
}
