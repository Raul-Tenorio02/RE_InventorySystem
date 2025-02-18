package InventoryRE.Recovery;

import InventoryRE.Item;
import InventoryRE.ItemType;

public class RecoveryItem extends Item implements MixInterface {

    RecoveryType typeRecovery;

    public RecoveryItem(String name, String description, ItemType type, RecoveryType typeRecovery) {
        super(name, description, type);
        this.typeRecovery = typeRecovery;
    }

    public RecoveryType getTypeRecovery() {
        return typeRecovery;
    }

    public void setTypeRecovery(RecoveryType typeRecovery) {
        this.typeRecovery = typeRecovery;
    }

    @Override
    public String toString() {
        return "{" + getName() + ", " + getDescription()
                + "}";
    }

    @Override
    public RecoveryItem mixHerb(RecoveryItem otherHerb) { // setting herb items to be combined
        if (this.getType() == ItemType.RECOVERY && otherHerb.getType() == ItemType.RECOVERY) {
            if (this.typeRecovery == RecoveryType.GREEN && otherHerb.typeRecovery == RecoveryType.GREEN) {
                return new RecoveryItem("Mixed Herb G+G","\"A mixture of 2 green herbs.\"", ItemType.RECOVERY, RecoveryType.GG );
            } else if (this.typeRecovery == RecoveryType.GG && otherHerb.typeRecovery == RecoveryType.GREEN || typeRecovery == RecoveryType.GREEN && otherHerb.typeRecovery == RecoveryType.GG) {
                return new RecoveryItem("Mixed Herb G+G+G","\"A mixture of 3 green herbs.\"", ItemType.RECOVERY, RecoveryType.GGG );
            } else if (this.typeRecovery == RecoveryType.GREEN && otherHerb.typeRecovery == RecoveryType.RED || typeRecovery == RecoveryType.RED && otherHerb.typeRecovery == RecoveryType.GREEN) {
                return new RecoveryItem("Mixed Herb G+R","\"A mixture of 1 green herb and 1 red herb.\"", ItemType.RECOVERY, RecoveryType.GR );
            } else if (this.typeRecovery == RecoveryType.GREEN && otherHerb.typeRecovery == RecoveryType.BLUE || typeRecovery == RecoveryType.BLUE && otherHerb.typeRecovery == RecoveryType.GREEN) {
                return new RecoveryItem("Mixed Herb G+B","\"A mixture of 1 green herb and 1 blue herb.\"", ItemType.RECOVERY, RecoveryType.GB );
            } else if (this.typeRecovery == RecoveryType.GG && otherHerb.typeRecovery == RecoveryType.BLUE || typeRecovery == RecoveryType.BLUE && otherHerb.typeRecovery == RecoveryType.GG) {
                return new RecoveryItem("Mixed Herb G+G+B","\"A mixture of 2 green herbs and 1 blue herb.\"", ItemType.RECOVERY, RecoveryType.GGB );
            } else if (this.typeRecovery == RecoveryType.GR && otherHerb.typeRecovery == RecoveryType.BLUE || typeRecovery == RecoveryType.BLUE && otherHerb.typeRecovery == RecoveryType.GR || typeRecovery == RecoveryType.GB && otherHerb.typeRecovery == RecoveryType.RED || typeRecovery == RecoveryType.RED && otherHerb.typeRecovery == RecoveryType.GB) {
                return new RecoveryItem("Mixed Herb G+R+B","\"A mixture of 1 green herb, 1 red herb and 1 blue herb.\"", ItemType.RECOVERY, RecoveryType.GRB );
            }
        } else {
            System.out.println("There is no need of mixing these.\n");
        }
        return null;
    }
}
