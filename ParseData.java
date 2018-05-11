package com.util;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.xml.bind.DatatypeConverter;

public class ParseData {
	
	//Using sdf as a part of Marvin, learnt from "http://sdformat.org/tutorials?tut=quickstart&cat=get_started"
	
	public static void main(String sdf[]){
		
		//initializing all the strings 
		
		String A = "";
		String B = "";
		String C = "";
		String D = "";
		
		//the long blob string that we created in the database. 
		String E = 		   "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAABGdBTUEAAK/INwWK6QAAABl0RVh0U29mdHdhcmUAQWRvYmUgSW1hZ2VSZWFkeXHJZTwAAAK3SURBVDjLdVM9TFNRFP5e+0p/KcQIrZZXYCCBdIMoaGqESGxCTBqCg5suxsRF44IjgzG6mZjYwTB2Mg6YOLQdNKUFTKwYEJ2koYZSqi20j9ff91rPvbEEbXzJyb03Oef7vvOd84Rms4mTXzablXQ63Vyj0fCpqjpGgXq9niiVSqFCofDa6/X+OJkvnATY39+/IAjCvMFg8NMJAgIDqFarODo6QiqVWioWi09nZ2dXWzW61mVvb08i1nmTyeQ3Go1gwIlEgketVoPZbIbb7fYfHh7OBwIBqQ2AZM6JosiZWQED8Xov4fLkJDo7O1Eul0HK4HK5/JlMZq5VJ7YulUrFZ7PZ2MnZviWzWFtd4UrGxyfQ7+xi/qC3txcHBwc+Knn2lwLqc4wls347iH1tNQ67+xzsg1P4mFRht9uZSlitViiKMtamgFzmhjH5RItGA6jBAk3rQE3o4jmapoFMZABo84AAErIs8yQaFy5OnIciF1AoVXBluIlcLsfzdnZ2mB+JNgByN0Tm8Hs8HocBZdycduH2lA11JYNoNMoVrq+vszZDbXuwuLgokYqXHo/Hx9rJ5/O8Zxot3wfn7gcYv4Qg5NJQ9UgLaD6/GlafHHtAzo/TCB2xWGxpdHTUPzIywntlBKatCMzyFoZv3YNx0IPyRvjs1+XIo8i0QeEKgsHgdcIIEmPH5uamm5YqxVhZ38yT21jDtfsLMH9/D+zGgK5u/BL78Sm8nOQKSOaroaEhMA8kSUo5nU5YLBak02k+nVMb72ByDgIzD47dFxfOQN8QBsQ/S8QL+vr60NPTw98sHA4HP2vb3Sh9fgvrm7uoljMoUY1c1EMjLzhAMplEJBLhS8SiBcCCvWdOm9G9EsUAnaLeAPmniu0M2YjmC+Hf3/l/X/yG+6GST9/Ra0K/pm/uUlXAF1Yf/wakxYbML/JgHwAAAABJRU5ErkJggg==";
		String F = "";
		String G = "";
		String H = "";
		String I = "";
		String J = "";
		
		byte[] imgdata = null;
		int flag = 0;
		
		//Make sure to change the path according to your local computer of where your project is located, either locally, or in eclipse
		String project = "C:\\Meghana\\MS3Challenge";
		String database = "D:\\Other\\sqlite-tools-win32-x86-3230100";
		
		String csvFile = project + "\\ReadCSVToDB\\ms3Interview.csv";
		String failercsvFile = project + "\\ReadCSVToDB\\bad-data-file"+ new java.util.Date().getTime() +".csv";
        BufferedReader br = null;
        String line = "";
		
		//Splitting the data when you encounter comma
        String csvSplitBy = ",";
		
        //String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        int vcount = 0;
        int totalcount = 0;
		int failurecount = 0;
        int successcount = 0;
		
        String[] country = null;
        
		try{
			//Class.forName("org.postgresql.Driver");
			Class.forName("org.sqlite.JDBC");
			Connection connection = null;
			FileWriter writer = new FileWriter(failercsvFile);	        
	        
			
			//Setting the connection using JDBC drivers
			connection = DriverManager.getConnection(
			   //"jdbc:postgresql://hostname:port/dbname","username", "password");
				// "jdbc:postgresql://localhost:5432/postgres","postgres", "admin");
					"jdbc:sqlite:"+database+"\\csv.db");
			
			Statement stmt = connection.createStatement();		      
		    String sql = "create table IF NOT EXISTS table_data_csv(  A varchar(50), B varchar(50), C varchar(50), D varchar950, E blob, F varchar(100),G varchar(100), H varchar(50), I varchar(100), J varchar(100) );";
		    stmt.executeUpdate(sql);
			stmt.close();
		    
			PreparedStatement ps = connection.prepareStatement("Insert into table_data_csv Values(?,?,?,?,?,?,?,?,?,?)");
			
			//read data from the given csv file
			br = new BufferedReader(new FileReader(csvFile));
            while ( (line = br.readLine()) != null && line.trim().length() !=0 ) {
            	
            	//System.out.println( "Line="+ line );
            	totalcount++;
            	vcount = 0;
                // use comma as separator
                //String[] country = line.split(csvSplitBy);
            	country = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            	
            	if( country.length != 10 ){            		
            		System.out.println( "Less count="+ line );
            		failurecount++;
            		ParseCSV.writeLine(writer, line );
            		continue;
            	}            	
                
                A = country[vcount++];
                B = country[vcount++];
                C = country[vcount++];
                D = country[vcount++];
                E = country[vcount++];
                F = country[vcount++];
                G = country[vcount++];
                H = country[vcount++];
                I = country[vcount++];
                J = country[vcount++];                
                System.out.println( "A="+ A +";B=" + B + ";C=" +C + ";D+" +D + ";E=" +E + ";F=" +F + ";G=" +G+ ";H=" +H + ";I=" + I + ";J=" + J);                
               
                try{
                	flag = 0;
	                ps.setString(1, A);
	    			ps.setString(2, B);
	    			ps.setString(3, C);
	    			ps.setString(4, D);
	    			ps.setBytes(5, DatatypeConverter.parseBase64Binary(E.substring(E.indexOf(",") + 1)) );
	    			ps.setString(5, E);
	    			ps.setString(6, F);
	    			ps.setString(7, G);
	    			ps.setString(8, H);
	    			ps.setString(9, I);
	    			ps.setString(10, J);
	    			
	    			flag = ps.executeUpdate();
                }catch(Exception e){
                	System.out.println( "SQL="+ e + ""+ e.getMessage() +","+ e.toString() );
                }
                
                if(flag == 1)
                	successcount++;
                else
                	failurecount++;
            }
            
			ps.close();					
			connection.close();
			
			writer.flush();
	        writer.close();
		}
		catch(Exception e){
			System.out.println( e + ""+ e.getMessage() +","+ e.toString() );
		}
		
		System.out.println( "The Statistics can be seen as follows \n");
		System.out.println( "Total count = "+ totalcount );
		System.out.println( "Successful count = "+ successcount );
		System.out.println( "Failure count = "+ failurecount );
	}	

}
