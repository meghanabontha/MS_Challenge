//author: Meghana Priyanka Bontha
package com.util;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class CSVUtils {

	//comma is the default separator
    private static final char DEFAULT_SEPARATOR = ',';
    
    public static void writeLine(Writer wr, List<String> values) throws IOException 
	{
        writeLine(wr, values, DEFAULT_SEPARATOR, ' ');
    }

    public static void writeLine(Writer wr, List<String> values, char separators) throws IOException 
	{
        writeLine(wr, values, separators, ' ');
    }


    private static String followCVSformat(String value) 
	{
        String result = value;
        if (result.contains("\"")) {
            result = result.replace("\"", "\"\"");
        }
        return result;

    }

	//build separators for the table data
    public static void writeLine(Writer wr, List<String> values, char separators, char customQuote) throws IOException 
	{
        boolean first = true;

        //the customQuote is empty by default

        if (separators == ' ') 
		{
            separators = DEFAULT_SEPARATOR;
        }

        StringBuilder sb = new StringBuilder();
        for (String value : values) 
		{
            if (!first) 
			{
                sb.append(separators);
            }
            if (customQuote == ' ') 
			{
                sb.append(followCVSformat(value));
            } else 
			{
                sb.append(customQuote).append(followCVSformat(value)).append(customQuote);
            }

            first = false;
        }
        sb.append("\n");
        wr.append(sb.toString());
    }
    
	//add the value to the string
    public static void writeLine( Writer wr, String value ) throws IOException 
	{
    	StringBuilder sb = new StringBuilder();
    	sb.append(value);
        sb.append("\n");
        wr.append(sb.toString());    	
    }

} 
