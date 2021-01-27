package tfar.every5minutesyourkeybindschange;

import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

import java.util.*;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Every5MinutesYourKeybindsChange.MODID)
public class Every5MinutesYourKeybindsChange {
    // Directly reference a log4j logger.

    public static final String MODID = "every5minutesyourkeybindschange";

    public Every5MinutesYourKeybindsChange() {
        MinecraftForge.EVENT_BUS.addListener(this::clientTick);
    }

    public void clientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START && Minecraft.getInstance().world != null && Minecraft.getInstance().world.getGameTime() % (300 * 20) == 0) {
            randomizeKeybinds();
            Minecraft.getInstance().player.sendMessage(new StringTextComponent("Keybinds Randomized!"), Util.DUMMY_UUID);
        }
    }

    public static List<Integer> remaining = new ArrayList<>();

    private static void randomizeKeybinds() {
        remaining.clear();
        remaining.addAll(keycodes);
        for (KeyBinding keyBinding : Minecraft.getInstance().gameSettings.keyBindings) {
            Minecraft.getInstance().gameSettings.setKeyBindingCode(keyBinding, InputMappings.getInputByCode(getRandomKeyCode(), 0));
        }
        KeyBinding.resetKeyBindingArrayAndHash();
    }

    private static final List<Integer> keycodes = Lists.newArrayList(
            GLFW.GLFW_KEY_SPACE,
            GLFW.GLFW_KEY_APOSTROPHE,
            GLFW.GLFW_KEY_COMMA,
            GLFW.GLFW_KEY_MINUS,
            GLFW.GLFW_KEY_PERIOD,
            GLFW.GLFW_KEY_SLASH,
            GLFW.GLFW_KEY_0,
            GLFW.GLFW_KEY_1,
            GLFW.GLFW_KEY_2,
            GLFW.GLFW_KEY_3,
            GLFW.GLFW_KEY_4,
            GLFW.GLFW_KEY_5,
            GLFW.GLFW_KEY_6,
            GLFW.GLFW_KEY_7,
            GLFW.GLFW_KEY_8,
            GLFW.GLFW_KEY_9,
            GLFW.GLFW_KEY_SEMICOLON,
            GLFW.GLFW_KEY_EQUAL,
            GLFW.GLFW_KEY_A,
            GLFW.GLFW_KEY_B,
            GLFW.GLFW_KEY_C,
            GLFW.GLFW_KEY_D,
            GLFW.GLFW_KEY_E,
            GLFW.GLFW_KEY_F,
            GLFW.GLFW_KEY_G,
            GLFW.GLFW_KEY_H,
            GLFW.GLFW_KEY_I,
            GLFW.GLFW_KEY_J,
            GLFW.GLFW_KEY_K,
            GLFW.GLFW_KEY_L,
            GLFW.GLFW_KEY_M,
            GLFW.GLFW_KEY_N,
            GLFW.GLFW_KEY_O,
            GLFW.GLFW_KEY_P,
            GLFW.GLFW_KEY_Q,
            GLFW.GLFW_KEY_R,
            GLFW.GLFW_KEY_S,
            GLFW.GLFW_KEY_T,
            GLFW.GLFW_KEY_U,
            GLFW.GLFW_KEY_V,
            GLFW.GLFW_KEY_W,
            GLFW.GLFW_KEY_X,
            GLFW.GLFW_KEY_Y,
            GLFW.GLFW_KEY_Z,
            GLFW.GLFW_KEY_LEFT_BRACKET,
            GLFW.GLFW_KEY_BACKSLASH,
            GLFW.GLFW_KEY_RIGHT_BRACKET,
            GLFW.GLFW_KEY_GRAVE_ACCENT
            );

    private static final Random rand = new Random();

    private static int getRandomKeyCode() {
        int keycode = remaining.get(rand.nextInt(remaining.size()));
        remaining.remove((Integer)keycode);
        return keycode;
    }
}
