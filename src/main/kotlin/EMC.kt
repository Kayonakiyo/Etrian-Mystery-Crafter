import java.io.File
import java.util.Scanner
import kotlin.system.exitProcess
import kotlin.collections.ArrayList

fun main() {

    // --------------------- Setup + Initializations ------------------
    var requestedItem:String // String representing user item.
    val userInput = Scanner(System.`in`)
    val scraper = EMCDataScrape()
    var unpackedMaterialList: MutableList<Material> = ArrayList()

    // --------------------- Database Setup/Check ---------------------
    try {
        println("Initializing application...")
        println("Looking for text-based database...")
        val database = File("EMCDatabase.txt") // Looks for file named EMCDatabase.txt

        // Checks for file existence as well as not being a path/directory.
        if (database.exists() && !database.isDirectory){

            println("Text Database File found! Checking for serialized data...")
            val jsonFile = File("database.json") // Attempt to prepare JSON file.

            if(jsonFile.exists() && !jsonFile.isDirectory){ // If JSON is present, unpack data into runtime objects.
                println("Serialized data found! Unpacking JSON...")
                unpackedMaterialList = scraper.serializeLoad(jsonFile)
            } else {
                print("Serialized data not found. Building JSON database...")
                unpackedMaterialList = scraper.parseMaterialData(database) // turn csv/txt -> objects
                scraper.serializeSave(unpackedMaterialList) // turn objects -> serialize (Saving data for later) [as a JSON string]
            }

        } else { // If text file does NOT exist, or is a path/directory, try to make a new one.

            println("Text Database File not found! Creating one for you!")

            // Checks to see if the file was made properly.
            if (database.createNewFile()){
                println("New empty text database file successfully made! Please allow sometime for generation.")
                // Generate Database from Online Source + serialize!
                // call method here [stub]

            } else {
                println("New text database failed to be created. Exiting program.")
                exitProcess(1)
            }
        }
    } catch (e : NullPointerException){
        println("Database could not be opened. Is EMCDatabase.txt in this same directory?")
    }

    // --------------------- Run Main Program ---------------------
    println("Welcome to Etrian Mystery Crafter!")
    println("Name of equipment or item you're searching for?")

    // Do not move forward unless item query is a valid string.
    while(true){
        if (userInput.hasNext()){
            requestedItem = userInput.next()
            if (requestedItem.isEmpty()){
                println("Response is empty! Try again.")
                continue
            } else {
                break
            }
        } else {
            println("No token in scanner, execution will end.")
            exitProcess(1) // exit with error
        }
    }

    // Call the search method!
    searchForMaterial(unpackedMaterialList, requestedItem)

    // --------------------- Cleanup ---------------------
    userInput.close()

}

/**
 * Given a list of Material objects, find the material in question and return
 * said material, to be used in tandem with a crafting table.
 *
 * @param materialList list of provided Material objects to look through
 * @param userQuery material name to search for
 * @return the relevant Material object
 */
fun searchForMaterial(materialList: MutableList<Material>, userQuery: String): Material{
    // Search for item name first.
    return materialList[0]
}

/**
 * Given a list of Equipment objects, find the equipment in question and return
 * said equipment, will then be used to display crafting data.
 *
 * @param equipmentList list of provided Equipment objects to look through
 * @param userQuery equipment name to search for
 * @return the relevant Equipment object
 */
fun searchForEquipment(equipmentList: MutableList<Equipment>, userQuery:String): Equipment{
    return equipmentList[0]
}