package org.pokemones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PokemonDAO {
    private static Connection con;
    private static PreparedStatement ps;
    private static ResultSet rs;
    public static ArrayList<Pokemon> getPokemons(){
        ArrayList<Pokemon> pokemons = new ArrayList<>();
        String sql = "SELECT * FROM pokemon";
        try{
            con= Database.getConnection();
            ps= con.prepareStatement(sql);
            rs=ps.executeQuery();
            Pokemon pokemonData;
            while(rs.next()){
                pokemonData = new Pokemon(rs.getInt("id"), rs.getString("name"), rs.getString("image"), rs.getInt("trainer_id"));
                pokemons.add(pokemonData);
            }
            return pokemons;

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return pokemons;
    }
    public static void enlistarpokemon(){
        ArrayList<Pokemon> pokemons = getPokemons();
        for(Pokemon pokemon : pokemons){
            System.out.println("id:"+pokemon.getId()+" Name: "+pokemon.getName()+" Imagen: "+pokemon.getImage()+" trainer id: "+pokemon.getTrainerId());
        }

    }
    public void savePokemon(Pokemon pokemon) throws SQLException {
        String sql = "INSERT INTO Pokemon (name, image, trainer_id) VALUES (?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, pokemon.getName());
            stmt.setString(2, pokemon.getImage());
            stmt.setInt(3, pokemon.getTrainerId());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                pokemon.setId(generatedKeys.getInt(1));
            }
        }
    }

    public void savePokemonType(int pokemonId, int typeId) throws SQLException {
        String sql = "INSERT INTO PokemonType (pokemon_id, type_id) VALUES (?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pokemonId);
            stmt.setInt(2, typeId);
            stmt.executeUpdate();
        }
    }

    public void savePokemonMove(int pokemonId, int moveId) throws SQLException {
        String sql = "INSERT INTO PokemonMove (pokemon_id, move_id) VALUES (?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pokemonId);
            stmt.setInt(2, moveId);
            stmt.executeUpdate();
        }
    }
}


