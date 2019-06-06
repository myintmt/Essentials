package me.Henry;

import me.Henry.utils.Initialize;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private Initialize initialize;

    @Override
    public void onEnable() {
        initialize = new Initialize(this);
        initialize.init();

    }

    @Override
    public void onDisable() {

    }
}
