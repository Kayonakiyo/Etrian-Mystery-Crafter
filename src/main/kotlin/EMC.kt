import java.io.File
import java.util.Scanner
import kotlin.system.exitProcess
import EMCDataScrape

fun main() {
    // Setup
    var requestedItem = "" // String representing user item.
    val scnr = Scanner(System.`in`)
    val scraper = EMCDataScrape();

    // Run
    println("Welcome to the Etrian Mystery Crafter! No UI yet but very functional!")
    println("Name of equipment or item you're searching for?")

    // Do not move forward unless item query is a valid string.
    while(true){
        if (scnr.hasNext()){
            requestedItem = scnr.next()
            if (requestedItem.isEmpty()){
                println("Response is empty! Try again.")
                continue;
            } else {
                break;
            }
        } else {
            println("No token in scanner, execution will end.")
            exitProcess(1); // exit with error
        }
    }


    // Run Scrape/Updater if changes have been made [ do not implement yet, this is a separate web parser]


    // Search for item and output relevant data.
    try {

        var database = File("EMCDatabase.txt"); // Looks for file named EMCDatabase.txt

        // Checks for file existence as well as not being a path/directory.
        if (database.exists() && !database.isDirectory){
            println("File found! Moving forward with item finding.")
            scraper.parseMaterialData(database); // turn csv -> objects
        } else { // If file does NOT exist, or is a path/directory, try to make a new one.

            println("File not found! Creating one for you!.")

            // Checks to see if the file was made properly.
            if (database.createNewFile()){
                println("New empty database file successfully made! Please allow sometime for generation.")
                // Generate Database!

            } else {
                println("New database failed to be created. Exiting program.")
                exitProcess(1);
            }
        }

        // Now launch search method to start finding.
        searchForItem(database, requestedItem)
    } catch (e : NullPointerException){
        println("Database could not be opened. Is EMCDatabase.txt in this same directory?")
    }

    // Cleanup
    scnr.close()

}

/*
 *
 * Given a database file and a user query, find item and return relevant info
 * such as location, price, materials needed, and where to find them.
 * O(n) naive runtime.
 * Likely Optimization : O(log n) binary search (needs sorted database)
 *
 */
fun searchForItem(databaseFile: File, userQuery: String){
    if (userQuery == null){
        println("Null input!!! Exiting program.")
        exitProcess(1) // null db or string name, cant move forward.
    }

    // Search for item name first.


}