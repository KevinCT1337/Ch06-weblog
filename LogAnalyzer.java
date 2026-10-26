/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @author Kevin Cox-Torrelio
 * 
 * MODIFIED 10/26/2026
 * 
 * 
 * @version    2026.10.26
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     * 
     * @param fileName File name to read from
     */
    public LogAnalyzer(String fileName)
    { 
        
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader(fileName);
    }

    /**
     * Return the number of accesses recorded in the log file
     * 
     * @return Total number of accesses
     */
    public int numberOfAccesses()
    {
        int total = 0;
        
        for(int hour = 0; hour < hourCounts.length; hour++) {
            total += hourCounts[hour];
        }
        
        return total;
    }
    
    
    /**
     * Return the hour that is the most busiest in the log file
     * 
     * @return The hour that is the most busiest
     */
    public int busiestHour()
    {
        int mostBusiestHour = 0;
        int AccessTotalForHour = 0;
        
        for(int hour = 0; hour < hourCounts.length; hour++) {
            if(hourCounts[hour] > AccessTotalForHour)
            {
                AccessTotalForHour = hourCounts[hour];
                mostBusiestHour = hour;
            }
        }
        
        //Using demoData.txt, I get back hour 6
        return mostBusiestHour;
    }
    
    
    /**
     * Return the hour that is the most quietest in the log file
     * 
     * @return The hour that is the most quietest
     */
    public int quietestHour()
    {
        int mostQuietestHour = 0;
        int AccessTotalForHour = hourCounts[mostQuietestHour];
        
        for(int hour = 0; hour < hourCounts.length; hour++) {
            if(hourCounts[hour] < AccessTotalForHour)
            {
                AccessTotalForHour = hourCounts[hour];
                mostQuietestHour = hour;
            }
        }
        
        //Using demoData.txt, I get back hour 12
        return mostQuietestHour;
    }
    
    
    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
}
