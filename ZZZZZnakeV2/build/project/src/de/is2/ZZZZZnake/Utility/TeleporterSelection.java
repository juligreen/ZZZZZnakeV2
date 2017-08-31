package de.is2.ZZZZZnake.Utility;

import de.is2.ZZZZZnake.GameObjects.Player;
import de.is2.ZZZZZnake.GameObjects.Teleporter;
import de.is2.ZZZZZnake.Utility.TeleporterImplementations.CircleTeleporter;

public class TeleporterSelection {

    public void selectTeleporter( String teleporterType, Teleporter[] teleporters, Player player ) {
        CircleTeleporter circleTeleporter = new CircleTeleporter();
        if ( circleTeleporter.getName().equals( teleporterType ) ) {
            circleTeleporter.teleport( teleporters, player );
        }
    }
}
