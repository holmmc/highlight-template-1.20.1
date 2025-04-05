package com.holmityd.hightlight.events.client;

import com.holmityd.hightlight.events.Event;
import com.holmityd.hightlight.events.EventFactory;
import net.minecraft.world.item.ItemStack;

import java.util.UUID;

public interface NewItemPickupEvent {
    Event<NewItemPickupEvent> EVENT = EventFactory.create(NewItemPickupEvent.class,
        listeners -> (playerUUID, itemStack) -> {
            for (NewItemPickupEvent listener : listeners) {
                listener.onItemPickup(playerUUID, itemStack);
            }
        }
    );

    void onItemPickup(UUID playerUUID, ItemStack itemStack);
}