package InventoryRE.Weaponry.Weapons;

import InventoryRE.Weaponry.Ammunition.Ammunition;

public final class ReloadResult {

        private Weapon weapon;
        private Ammunition returnedAmmo;

        public ReloadResult(Weapon weapon, Ammunition returnedAmmo) {
            this.weapon = weapon;
            this.returnedAmmo = returnedAmmo;
        }

        public Weapon getWeapon() {
            return weapon;
        }

        public Ammunition getReturnedAmmo() {
            return returnedAmmo;
        }
    }