package com.xaklor.util.general;

import com.xaklor.TheAbandonedZoneMod;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

/**
 * Class for simplifying item initialization and registration
 */
public class AbandonedZoneItem extends Item {

    public final Identifier id;
    public final int burnTime;

    /**
     * @param id string id, registered in the form "the_abandoned_zone:id"
     * @param settings item settings
     * @param burnTime burn time of the item in a furnace, if 0 does not add to FuelRegistry
     */
    public AbandonedZoneItem(String id, Settings settings, int burnTime) {
        super(settings);
        TheAbandonedZoneMod.ITEMS.add(this);
        this.burnTime = burnTime;
        this.id = new Identifier(TheAbandonedZoneMod.MOD_ID, id);
        this.register();
    }

    public AbandonedZoneItem(String id, Settings settings) {
        this(id, settings, 0);
    }

    private void register() {
        Registry.register(Registries.ITEM, this.id, this);
        if (this.burnTime > 0)
            FuelRegistry.INSTANCE.add(this, this.burnTime);
    }

}
