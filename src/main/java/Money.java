class Money {
    private String money;
    private String nominal;

    public Money(String money, String nominal) {
        this.money = money;
        this.nominal = nominal;
    }

    public String getMoney() {
        return money;
    }

    public String getNominal() {
        return nominal;
    }
}
