package com.hamusuke.tooltipscroller;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

import java.util.concurrent.atomic.AtomicInteger;

public class TooltipScrollerClient implements ClientModInitializer {
    public static final KeyBinding UP = KeyBindingHelper.registerKeyBinding(new KeyBinding("tooltipscroller.up", GLFW.GLFW_KEY_PAGE_UP, "key.categories.inventory"));
    public static final KeyBinding DOWN = KeyBindingHelper.registerKeyBinding(new KeyBinding("tooltipscroller.down", GLFW.GLFW_KEY_PAGE_DOWN, "key.categories.inventory"));
    public static final KeyBinding RESET = KeyBindingHelper.registerKeyBinding(new KeyBinding("tooltipscroller.reset", GLFW.GLFW_KEY_HOME, "key.categories.inventory"));
    public static final AtomicInteger AMOUNT = new AtomicInteger();

    @Override
    public void onInitializeClient() {
    }
}
