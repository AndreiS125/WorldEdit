package org.andreis.mc.worldedit;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class repository {
    public Connection c;
    private String url;
    public repository() throws Exception {

        url = "jdbc:sqlite:region.db";
        Class.forName("org.sqlite.JDBC").newInstance();

        c = getConnection();
        Statement s = c.createStatement();

        s.executeUpdate("CREATE TABLE IF NOT EXISTS regions ('name' TEXT, 'owner' TEXT, 'pos1' TEXT, 'pos2' TEXT, 'friends' TEXT)");



    }

    public Connection getConnection() throws Exception {
        return DriverManager.getConnection(url);
    }

    public void add(String name, String owner, String pos1, String pos2, String friends) {
        try {

            Statement s = c.createStatement();
            s.executeUpdate("INSERT INTO regions VALUES ('" + name +"', '"+owner+ "', '"+pos1+"', '"+pos2+"', '"+friends+"')");
            s.close();

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public String get(String what, String name) {
        try {

            Statement s = c.createStatement();
            ResultSet resultSet = s.executeQuery("Select * from regions WHERE name = '"+name+"'");
            String answ=resultSet.getString(what);
            s.close();

            return answ;
        }
        catch(Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    public boolean cantbreakblocks(Location loc, String name) {
        //if the answer = true, then u cant break blocks
        if (loc.getWorld().getName().equals("world")){
        try {

            Statement s = c.createStatement();
            ResultSet resultSet = s.executeQuery("Select * from regions");
            String pos2;
            boolean onx=false;
            String pos1;

            String friends;
            while (resultSet.next()){

                pos1=resultSet.getString("pos1");
                pos2=resultSet.getString("pos2");


                if(getifmiddle(Integer.parseInt(pos1.split(":")[0]),Integer.parseInt(pos2.split(":")[0]),loc.getBlockX())){
                    onx=true;
                }
                if(getifmiddle(Integer.parseInt(pos1.split(":")[1]),Integer.parseInt(pos2.split(":")[1]),loc.getBlockZ())){
                    if(onx){
                        if (resultSet.getString("owner").equals(name)){
                            return false;
                        }
                        friends=resultSet.getString("friends");
                        for(String n:friends.split(":")){
                            if(name.equals(n)){
                                return false;
                            }
                        }
                        return true;
                    }
                }

            }
            s.close();

            return false;
        }
        catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
        else{
            return false;
        }
    }

    public void remove(String name) {
        try {

            Statement s = c.createStatement();
            s.executeUpdate("DELETE FROM regions WHERE name = '" + name + "'");
            s.close();

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void update(String what, String namewho, String namerg) {
        try {

            Statement s = c.createStatement();
            String st="";
            if(what.equals("friends")){
                st=String.format("UPDATE regions SET friends = '%s' WHERE name = '%s'",namewho+":", namerg);
            }
            s.executeUpdate(st);
            s.close();

        }
        catch(Exception e) {

        }
    }
    public boolean getifmiddle(int first,int second, int tocheck){
        //true=middle
        int max=first;
        int min=first;
        int[] list = {first,second,tocheck};
        for(int i:list){
            if(i>max){
                max=i;
            }
            if(i<min){
                min=i;
            }
        }
        if (tocheck != max && tocheck != min) {
            return true;
        }
        return false;
    }
    public String rgnameuin(Location loc) {
        //if the answer = true, then u cant break blocks
        if (loc.getWorld().getName().equals("world")){
            try {

                Statement s = c.createStatement();
                ResultSet resultSet = s.executeQuery("Select * from regions");
                String pos2;
                boolean onx=false;
                String pos1;

                String friends;
                while (resultSet.next()){

                    pos1=resultSet.getString("pos1");
                    pos2=resultSet.getString("pos2");


                    if(getifmiddle(Integer.parseInt(pos1.split(":")[0]),Integer.parseInt(pos2.split(":")[0]),loc.getBlockX())){
                        onx=true;
                    }
                    if(getifmiddle(Integer.parseInt(pos1.split(":")[1]),Integer.parseInt(pos2.split(":")[1]),loc.getBlockZ())){
                        if(onx){
                            return resultSet.getString("name");
                        }
                    }

                }
                s.close();
                return null;

            }
            catch(Exception e) {
                e.printStackTrace();
               return null;
            }
        }
        else{

        }
        return null;
    }

    public ArrayList<String> getallmyrgs(String name) {
        //if the answer = true, then u cant break blocks

            try {

                Statement s = c.createStatement();
                ResultSet resultSet = s.executeQuery("Select * from regions");

                ArrayList<String> exit=new ArrayList<>();

                String friends;
                while (resultSet.next()) {
                    if (resultSet.getString("owner").equals(name)){
                        exit.add(resultSet.getString("name"));
                    }


                }
                s.close();
                return exit;

            }
            catch(Exception e) {
                e.printStackTrace();
                return null;
            }



    }
}
