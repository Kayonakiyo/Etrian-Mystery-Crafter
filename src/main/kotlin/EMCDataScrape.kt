import java.io.File
import java.util.*

/*
 * Meant to scrape a webpage (saved locally but can be retrieved if live)
 * for all relevant data, and if there are no updates to be made, keep data.
 * Uses jsoup for this HTML Parsing.
 */
class EMCDataScrape {

    /*
     * Scrape data from gameFAQs into a csv file for parsing.
     *
     * WEBSITE DATA -> CSV
     */
    fun scrapeRawData(){

    }

    /*
     * Takes in CSV, either prepared or scraped, and converts data
     * in a generalized pattern into kotlin usable data structures,
     * mainly arrays. Will be passed to make the material list
     * of easily usable objects that can be serialized for simpler use
     * in the future.
     *
     * CSV -> Material objects (as well as changing abbreviations to full names)
     *
     */
    fun parseMaterialData(csvData: File){
        val databaseScanner = Scanner(csvData) // Pass in the database .csv for scanning.
        var currLine = ""
        var data: Array<String> // formatted material data, split by categories.
        var materialCollection: Array<Material>; // the collection of easily accessible material objects.
        var itemName = ""
        var obtainVia = ""
        var locations = ""

        var obtainArray: Array<String>
        var locationArray: Array<String>

        // Scan until EOF
        while (databaseScanner.hasNextLine()){
            currLine = databaseScanner.nextLine()
            data = currLine.split(",").toTypedArray()
            // Now construct respective material object.
            itemName = data[0]
            obtainVia = data[1].replace("\"","") // will strip quotes if available
            locations = data[2].replace("\"","") // will strip quotes if available, leaves easy commas behind
        }


        // Unabbreviate locations [once locations are parsed]

    }


    /**
     * Un-abbreviates an abbreviated location name.
     *
     * @param abbreviatedLoc the abbreviated location name
     * @return the un-abbreviated string if possible.
     */
    fun unabbreviateLocations(abbreviatedLoc: String): String {
        when (abbreviatedLoc){
            // Main Dungeons
            "ML" -> return "Mysterious Labyrinth"
            "EC" -> return "Emerald Copse"
            "PS" -> return "Pristine Stream"
            "HW" -> return "Haunted Woods"
            "CP" -> return "Crystalline Peak"
            "TR" -> return "Torrential Ravine"
            "MH" -> return "Muspelheim"
            "FG" -> return "Forest of Gates"
            "EG" -> return "Equinox Garden"
            "BC" -> return "Burning Cave"
            "GC" -> return "Glacial Cave"
            "TC" -> return "Thunderous Cave"
            "UT" -> return "Utopia"
            "MF" -> return "Mysterious Forest"
            "PD" -> return "Phantom Depths"
            "CH" -> return "Champion's Hall"

            // Dungeon Extensions
            "ECex" -> return "Emerald Copse Extension"
            "PSex" -> return "Pristine Stream Extension"
            "HWex" -> return "Haunted Woods Extension"
            "CPex" -> return "Crystalline Peak Extension"
            "TRex" -> return "Torrential Ravine Extension"

            else -> {
                println("Invalid location!")
                return "N/A"
            }
        }
    }

    /*
     * Takes all parsed data from web scrape / csv and puts them into an enumeration.
     */
    fun makeMaterialList(){

    }

    /*
     * Serializes sorted Material objects.
     */
    fun serializeSave(){

    }
}