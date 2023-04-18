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
    fun parseMaterialData(csvData: File): MutableList<Material>{
        val databaseScanner = Scanner(csvData) // Pass in the database .csv for scanning.
        var currLine = ""
        val materialCollection: MutableList<Material> = ArrayList(); // the collection of easily accessible material objects.
        var itemName = ""
        var obtainVia = ""
        var locations = ""

        var obtainArray: Array<String>
        var locationArray: Array<String>
        var skipFirstLine = false;

        // Scan until EOF
        while (databaseScanner.hasNextLine()){
            if(!skipFirstLine){ // skips 1st line of csv (the column labels)
                databaseScanner.nextLine();
                skipFirstLine = true;
                continue;
            }

            // Following a 'consumption' pattern to deal with quote marks, since at least two columns can have these.
            currLine = databaseScanner.nextLine()

            // Get item name section, then consume that input.
            itemName = currLine.substring(0,currLine.indexOf(',')) // grab first element of comma separated string
            currLine = currLine.substring(currLine.indexOf(',')+1);

            // Get obtain methods section, then consume that input.
            obtainVia = currLine.substring(0,currLine.indexOf(','));
            currLine = currLine.substring(currLine.indexOf(',') + 1);

            // get location tags
            locations = currLine.trim('"'); // if possible, trim quotes at end then you have all locations (unabbreviated)

            obtainArray = obtainVia.split(",").toTypedArray();
            locationArray = locations.split(",").toTypedArray();

            // construct object and add to array of material objects
            materialCollection.add(Material(itemName,obtainArray,locationArray));
        }

        // Unabbreviate locations [once locations are parsed]
        // Unfortunate lack of for each being able to edit string refs in place, so no for-i loop either >:(
        var i = 0;
        var j = 0;
        while(i < materialCollection.size){
            while(j < materialCollection.get(i).locations.size)
            {
                materialCollection.get(i).locations[j] = unabbreviateLocations(materialCollection.get(i).locations[j].trim()); // unabbreviate!
                j++;
            }
            i++;
            j = 0;
        }

        // Return the completed materials object list.
        return materialCollection
    }

    /**
     * Un-abbreviates an abbreviated location name.
     *
     * @param abbreviatedLoc the abbreviated location name
     * @return the un-abbreviated string if possible.
     */
    private fun unabbreviateLocations(abbreviatedLoc: String): String {
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

            // Side Dungeons
            "RF" -> return "Ravine Fork"
            "AT" -> return "Animal Trail"
            "SC" -> return "Snowy Cavern"
            "MC" -> return "Mountain Cleft"

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