/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cookingNotebook.DAO;

import cookingNotebook.models.Step;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Admin
 */
public class PathDeleted {
    public void add(String path) throws SQLException{
        Connection con = FunctionsDatabase.getConnection();
        String sql = "INSERT INTO PathDeleted (Path) VALUES (?)";
        PreparedStatement ps = con.prepareStatement(sql);
	ps.setString(1, path);
        int result = ps.executeUpdate();
        ps.close();
        con.close();
    }
    public void removeAll() throws SQLException, IOException{
        List<Path> del = new ArrayList<Path>();
        Connection con = FunctionsDatabase.getConnection();
        String sql = "SELECT * FROM PathDeleted";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if(rs != null) {
            while(rs.next()){
                del.add(FileSystems.getDefault().getPath(rs.getString(1)));
            }
	}
        rs.close();
        String sql1 = "DELETE FROM PathDeleted";
        ps = con.prepareStatement(sql1);	
        int result = ps.executeUpdate();
        ps.close();
        con.close();
        for(int i = 0; i<del.size() ; i++){
            Files.delete(del.get(i));
        }
    }
    public List<Path> get() throws SQLException{
        List<Path> path = null;
        Connection con = FunctionsDatabase.getConnection();
        String sql = "SELECT * FROM PathDeleted";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
            if(rs != null) {
                path = new ArrayList<Path>();
            while(rs.next()) {
                Path del = FileSystems.getDefault().getPath(rs.getString(1));
		path.add(del);
            }
	}
        rs.close();
        ps.close();
        con.close();
        return path;
    }
}
