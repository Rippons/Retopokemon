package org.pokemones;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TrainerDAO {
    private static Connection con;
    private static PreparedStatement ps;
    private static ResultSet rs;
    public static ArrayList<Trainer> getTrainers(){
     ArrayList<Trainer> trainers = new ArrayList<>();
     String sql = "SELECT * FROM trainer";
     try{
         con= Database.getConnection();
         ps= con.prepareStatement(sql);
         rs=ps.executeQuery();
         Trainer trainerData;
         while(rs.next()){
             trainerData = new Trainer(rs.getInt("id"),rs.getString("name"),rs.getInt("age"),rs.getString("region"));
             trainers.add(trainerData);
         }
         return trainers;

     }
     catch(Exception e){
         e.printStackTrace();
     }
        return trainers;
    }
    public static void enlistarentrenadores(){
        ArrayList<Trainer> trainers = getTrainers();
        for(Trainer trainer : trainers){
            System.out.println("id:"+trainer.getId()+" name: "+trainer.getName()+" age: "+trainer.getAge()+" region: "+trainer.getRegion());
        }

    }
    public void saveTrainer(Trainer trainer) throws SQLException {
        String sql = "INSERT INTO Trainer (name, age, region) VALUES (?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, trainer.getName());
            stmt.setInt(2, trainer.getAge());
            stmt.setString(3, trainer.getRegion());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                trainer.setId(generatedKeys.getInt(1));
            }
        }
    }
}




