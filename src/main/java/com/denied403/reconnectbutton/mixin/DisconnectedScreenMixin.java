package com.denied403.reconnectbutton.mixin;

import com.denied403.reconnectbutton.LastConnectionAttempt;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.ConnectScreen;
import net.minecraft.client.gui.screens.DisconnectedScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

@Mixin(DisconnectedScreen.class)
public abstract class DisconnectedScreenMixin extends Screen {

	@Shadow
	@Final
	private Screen parent;

	@Shadow
	@Final
	private LinearLayout layout;

	private DisconnectedScreenMixin(Component title) {
		super(title);
	}

	@Inject(method = "init", at = @At("TAIL"))
	private void reconnectbutton$addReconnectButton(CallbackInfo ci) {
		if (!LastConnectionAttempt.isAvailable()) {
			return;
		}

		Button reconnectButton = Button.builder(Component.literal("Reconnect"), _ -> ConnectScreen.startConnecting(parent, this.minecraft, LastConnectionAttempt.getAddress(), LastConnectionAttempt.getServerData(), LastConnectionAttempt.isQuickPlay(), LastConnectionAttempt.getTransferState())).width(200).build();

		layout.addChild(reconnectButton);
		layout.arrangeElements();
		this.addRenderableWidget(reconnectButton);
	}
}
