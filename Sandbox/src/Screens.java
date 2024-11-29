/// Enum holds the different options for screens
public enum Screens {
    BLACKJACK("Blackjack"),
    POKER("Poker"),
    SLOTS("Slots"),
    YATHZEE("Yathzee"),
    HORSE_RACING("Horse Racing"),
    HOME("Home"),
    ROULETTE("Roulette");

    private final String name;

    Screens(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
