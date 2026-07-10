package com.denied403.reconnectbutton;

import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.TransferState;
import net.minecraft.client.multiplayer.resolver.ServerAddress;

public final class LastConnectionAttempt {

	private static ServerAddress address;
	private static ServerData serverData;
	private static boolean isQuickPlay;
	private static TransferState transferState;

	private LastConnectionAttempt() {}

	public static void set(ServerAddress newAddress, ServerData newServerData, boolean newIsQuickPlay, TransferState newTransferState) {
		address = newAddress;
		serverData = newServerData;
		isQuickPlay = newIsQuickPlay;
		transferState = newTransferState;
	}

	public static boolean isAvailable() {
		return address != null;
	}

	public static ServerAddress getAddress() {
		return address;
	}

	public static ServerData getServerData() {
		return serverData;
	}

	public static boolean isQuickPlay() {
		return isQuickPlay;
	}

	public static TransferState getTransferState() {
		return transferState;
	}
}
