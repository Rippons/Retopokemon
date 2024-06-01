package org.pokemones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MoveDAO {
    private static Connection con;
    private static PreparedStatement ps;
    private static ResultSet rs;
    public static ArrayList<Move> getMoves(){
        ArrayList<Move> moves = new ArrayList<>();
        String sql = "SELECT * FROM move";
        try{
            con= Database.getConnection();
            ps= con.prepareStatement(sql);
            rs=ps.executeQuery();
            Move moveData;
            while(rs.next()){
                moveData = new Move(rs.getInt("id"), rs.getString("name"), rs.getInt("damage"));
                moves.add(moveData);
            }
            return moves;

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return moves;
    }
    public static void enlistarmovimientos(){
        ArrayList<Move> moves = getMoves();
        for(Move move : moves){
            System.out.println("id:"+move.getId()+" Name: "+move.getName()+" Daño: "+move.getDamage());
        }

    }
    public void saveMove(Move move) throws SQLException {
        String sql = "INSERT INTO Move (name, damage) VALUES (?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, move.getName());
            stmt.setInt(2, move.getDamage());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                move.setId(generatedKeys.getInt(1));
            }
        }
    }
}
