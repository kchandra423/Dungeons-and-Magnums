package kchandra423.graphics.screens;

public enum Window {
    HOME(0), BATTLE(1), PERFORMANCE(2), LOADING(3), LOADOUT(4), MUSIC(5);

    Window(int index) {
        this.index = index;
    }

    int index;
}
