package net.explorie.mod;
import net.explorie.mod.item.ModItems;
import net.explorie.mod.util.ModLootTableModifier;
import net.fabricmc.api.ModInitializer;

public class ExplorieMod implements ModInitializer {
    @Override
    public void onInitialize() {
        ModItems.initialize();
        ModLootTableModifier.modifyLootTables();
    }
}
