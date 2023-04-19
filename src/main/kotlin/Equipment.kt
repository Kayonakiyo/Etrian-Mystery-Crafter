import kotlinx.serialization.Serializable

/**
 * A more intricate data class that will contain details on equipment, the crux of this
 * programs purpose. Includes stats, effects, costs, and crafting materials.
 *
 * @param type type of equipment, e.g. swords, bows, wands, etc.
 * @param name name of equipment, e.g. "Balmung", "Knife", "Greatsword", etc.
 * @param stats numerical attributes tied to equipping this piece of equipment, e.g. "ATK +10, DEF 0" etc.
 * @param elements attack/defense/magic attributes, e.g. CUT, STAB, PIERCE, ICE, FIRE.
 * @param extraEffects status effects this can apply to enemies, e.g. SLEEP, POISON, FEAR, BREAKING WALLS
 * @param classes who can wield the equipment, e.g. Protector, Wanderer, etc.
 * @param compositeMaterials what materials are needed to make this equipment, e.g. 2x ore and 3x ferns
 * @param cost the amount to purchase this equipment from Chano Retail
 */
@Serializable
data class Equipment constructor(
    var type:String,
    var name:String,
    var stats:MutableList<Int>,
    var elements:MutableList<String>,
    var extraEffects:MutableList<String>,
    var classes:MutableList<String>,
    var compositeMaterials:MutableList<Material>,
    var cost:Int)
fun outputEquipmentData():String{

    return ""
}

