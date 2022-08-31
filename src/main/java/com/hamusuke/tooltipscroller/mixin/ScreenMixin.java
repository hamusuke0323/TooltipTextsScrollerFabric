package com.hamusuke.tooltipscroller.mixin;

import com.hamusuke.tooltipscroller.TooltipScrollerClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Screen.class)
public class ScreenMixin {
    @Shadow
    protected TextRenderer textRenderer;

    @ModifyVariable(method = "renderTooltipFromComponents", at = @At(value = "STORE", ordinal = 2), ordinal = 5)
    private int renderTooltipFromComponents(int m) {
        return m + TooltipScrollerClient.AMOUNT.get();
    }

    @Inject(method = "keyPressed", at = @At("HEAD"), cancellable = true)
    private void keyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        if (TooltipScrollerClient.UP.matchesKey(keyCode, scanCode)) {
            this.slide(1);
            cir.setReturnValue(true);
            cir.cancel();
        } else if (TooltipScrollerClient.DOWN.matchesKey(keyCode, scanCode)) {
            this.slide(-1);
            cir.setReturnValue(true);
            cir.cancel();
        } else if (TooltipScrollerClient.RESET.matchesKey(keyCode, scanCode)) {
            TooltipScrollerClient.AMOUNT.set(0);
            cir.setReturnValue(true);
            cir.cancel();
        }
    }

    private void slide(int direction) {
        if (TooltipScrollerClient.AMOUNT.get() >= Integer.MAX_VALUE && direction > 0) {
            TooltipScrollerClient.AMOUNT.set(Integer.MAX_VALUE);
            return;
        } else if (TooltipScrollerClient.AMOUNT.get() <= Integer.MIN_VALUE && direction < 0) {
            TooltipScrollerClient.AMOUNT.set(Integer.MIN_VALUE);
            return;
        }

        TooltipScrollerClient.AMOUNT.set(TooltipScrollerClient.AMOUNT.get() + direction * this.textRenderer.fontHeight);
    }
}
