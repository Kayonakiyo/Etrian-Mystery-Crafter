/**
 * A container class for EMD materials.
 * Allows for programming simplification of finding items and their locations.
 * Will be used within an enumeration, which can be binary searched due to the
 * data already being sorted. [If need be, it can be sorted, and that sorted database
 * can be used later efficiently]
 *
 * Probably want to serialize the data afterwards.
 *
 *
 * @property name name of material, always capitalized.
 * @property locations list of locations in-game where this material is obtainable, including quests.
 * @property obtainVia list of ways to obtain the material at hand. Can include DOE, enemy, and 'spots' drops.
 *
 */
class Material constructor(name: String, obtainVia: Array<String>, locations: Array<String>){

}