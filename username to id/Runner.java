import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Runner{

    public static void main(String[] args){
        //printMap(mapFile());
        //System.out.println(getUserNames());
        mapIntputandMakeCSV(getUserNames(),mapFile());
    }

    //creates a username to Id map from file named usernamesToId.csv
    private static Map<String, String> mapFile(){
        Map<String, String> rtn = new HashMap<String,String>();

        try{
            Scanner s = new Scanner(new File("usernamesToId.csv"));

            //is 0 if usersname is in 0 position
            int i = 0;

            //gets if username or id in first column
            if(s.hasNext()){
                String temp = s.nextLine().split(",")[0];
                if(!temp.toLowerCase().equals("USERNAME".toLowerCase())){
                    i= 1;
                }
            }

            //loops though file and maps username to id
            while( s.hasNext()){
                String[] line = s.nextLine().split(",");
                String userName = line[i];
                String Id = line[1-i];
                rtn.put(userName, Id);
            }


        }catch(Exception e){
            System.out.println("no file usernamesToId.csv");
        }

        return rtn;
    }

    private static void printMap(Map<String,String> arg){
        for(String t:arg.keySet()){
            System.out.println(t+" , "+arg.get(t));
        }
    }

    //creates a list of usernames from file usernames.csv
    private static List<String> getUserNames(){
        List<String> rtn = new ArrayList<String>();

        try{
            Scanner s = new Scanner(new File("usernames.csv"));

            s.nextLine();

            while(s.hasNext()){
                rtn.add(s.nextLine());
            }

        }catch(Exception e){
            System.out.println("no file named usernames.csv");
        }
        
        return rtn;
    }

    private static void mapIntputandMakeCSV(List<String> usernames, Map<String,String> usernamesToIds){
        String s="username,ID";

        //makes string for file
        for(String username:usernames){
            String id= usernamesToIds.get(username);
            if(id == null){
                id = "null";
            }
            s = s+"\n"+username+","+id;
        }

        //writes it to file export.csv
        try{
            FileWriter file= new FileWriter("export.csv");
            file.write(s);
            file.close();

        }catch(Exception e){
            System.out.println("failed to write file");
        }


    } 

}