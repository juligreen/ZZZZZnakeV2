package de.is2.ZZZZZnake.Utility.TeleporterImplementations;

import de.is2.ZZZZZnake.GameObjects.Player;
import de.is2.ZZZZZnake.GameObjects.Teleporter;
import de.is2.ZZZZZnake.Utility.Teleportable;

public class CircleTeleporter implements Teleportable {
    private final String name = "Circle";

    @Override
    public void teleport(Teleporter[] teleporters, Player player) {

        for( int counter = 0; counter < teleporters.length; counter++ ){
            if ( teleporters[counter].positionEquals( player ) ) {
                if( counter < ( teleporters.length - 1 ) ){
                    player.setPositionToOtherObject( teleporters[ counter + 1 ] );
                    break;
                }
                else {
                    player.setPositionToOtherObject(teleporters[0]);
                    break;

                }
            }
        }




    }

    public String getName() {
        return name;
    }
}
