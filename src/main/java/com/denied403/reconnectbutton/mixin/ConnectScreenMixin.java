package com.denied403.reconnectbutton.mixin;

import com.denied403.reconnectbutton.LastConnectionAttempt;
import net.minecraft.client.multiplayer.TransferState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ConnectScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.resolver.ServerAddress;

@Mixin(ConnectScreen.class)
public class ConnectScreenMixin {
	@Inject(method = "startConnecting", at = @At("HEAD"))
	private static void onStartConnecting(Screen parent, Minecraft minecraft, ServerAddress hostAndPort, ServerData data, boolean isQuickPlay, TransferState transferState, CallbackInfo ci) {
		LastConnectionAttempt.set(hostAndPort, data, isQuickPlay, transferState);
	}
}
