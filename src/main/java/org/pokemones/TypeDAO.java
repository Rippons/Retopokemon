package org.pokemones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TypeDAO {
    private static Connection con;
    private static PreparedStatement ps;
    private static ResultSet rs;
    public static ArrayList<Type> getTypes(){
        ArrayList<Type> types = new ArrayList<>();
        String sql = "SELECT * FROM type";
        try{
            con= Database.getConnection();
            ps= con.prepareStatement(sql);
            rs=ps.executeQuery();
            Type typeData;
            while(rs.next()){
                typeData = new Type(rs.getInt("id"), rs.getString("name"));
                types.add(typeData);
            }
            return types;

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return types;
    }
    public static void enlistartipos(){
        ArrayList<Type> types = getTypes();
        for(Type type : types){
            System.out.println("id:"+type.getId()+" tipo: "+type.getName());
        }

    }
    public void saveType(Type type) throws SQLException {
        String sql = "INSERT INTO Type (name) VALUES (?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, type.getName());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                type.setId(generatedKeys.getInt(1));
            }
        }
    }
}

