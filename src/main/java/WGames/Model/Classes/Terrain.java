package WGames.Model.Classes;

/**
 * Enum which defines the terrains
 */
public enum Terrain {

    /**
     * PLAINS Terrain
     * CavalryUnit gets bonus damage
     * Mage gets a reduced bonus damage
     */
    PLAINS,

    /**
     * HILL Terrain
     * RangedUnit gets bonus damage
     */
    HILL,

    /**
     * FOREST Terrain
     * InfantryUnit gets bonus damage
     * RangedUnit gets a reduced bonus damage
     * InfantryUnit gets bonus resistance
     * CavalryUnit gets a reduced bonus resistance
     */
    FOREST,

    /**
     * DESERT Terrain
     * Mage gets bonus damage
     * THE DEFAULT TERRAIN
     */
    DESERT;
}
