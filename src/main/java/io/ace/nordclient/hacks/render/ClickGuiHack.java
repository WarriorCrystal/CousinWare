package io.ace.nordclient.hacks.render;

import io.ace.nordclient.NordClient;
import io.ace.nordclient.hacks.Hack;
import io.ace.nordclient.utilz.clientutil.Setting;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

public class ClickGuiHack extends Hack {
    public ClickGuiHack INSTANCE;
    public ClickGuiHack() {
        super("ClickGUI", Category.CLIENT, "Opens the ClickGUI");
        setBind(Keyboard.KEY_P);
        INSTANCE = this;


        NordClient.INSTANCE.settingsManager.rSetting(new Setting("Rainbow", this, false, "ClickGuiRainbow"));
        NordClient.INSTANCE.settingsManager.rSetting(new Setting("Red", this, 255, 0, 255, true, "ClickGuiRed"));
        NordClient.INSTANCE.settingsManager.rSetting(new Setting("Green", this, 26, 0, 255, true, "ClickGuiGreen"));
        NordClient.INSTANCE.settingsManager.rSetting(new Setting("Blue", this, 42, 0, 255, true, "ClickGuiBlue"));
        NordClient.INSTANCE.settingsManager.rSetting(customFont = new Setting("CFont", this, true, "GlickGuiCustomFont"));
        NordClient.INSTANCE.settingsManager.rSetting(new Setting("Tooltips", this, true, "ClickGuiTooltips"));
    }
    public static Setting customFont;


    public void onEnable(){
        //mc.displayGuiScreen(NordClient.INSTANCE.clickGui);

        disable();
    }
}