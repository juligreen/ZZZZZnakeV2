package de.is2.ZZZZZnake.Utility;

import de.is2.ZZZZZnake.GameObjects.Player;
import de.is2.ZZZZZnake.GameObjects.Teleporter;

public interface Teleportable {
    //private String name;
     void teleport( Teleporter[] teleporters, Player player );
 /*   protected boolean teleporterSelection (String teleportType) {
        return teleportType.equals(this.name);
    }
    */
}
