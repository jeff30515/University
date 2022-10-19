package tw.tiffany.jdbc;

import java.awt.List;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import tw.tiffany.jdbc.model.IMemberDao;
import tw.tiffany.jdbc.model.MemberDaoJdbcImpl;

public class hw_1 {
	private Connection conn;
	public void createConn() throws Exception
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String urlstr ="jdbc:sqlserver://localhost:1433;databaseName=tiffany;user=watcher;"
					       +"password=P@ssw0rd";
			conn =DriverManager.getConnection(urlstr);
			
			boolean status =!conn.isClosed();
			System.out.println("SQL Server Connection status:" +status);
			Statement state = conn.createStatement();
			String sqlstr ="Select * From Table_1";
			ResultSet rs =state.executeQuery(sqlstr);
			rs.close();	
			state.close();	
		}
	

	public static void main(String args[]) throws Exception
	{	
		hw_1 storeImage1 = new hw_1();
		storeImage1.createConn();
		int num[]=new int[100000]; 
		int tnum[]=new int[43];
		int t2num[]=new int[43];
	for(int i=0;i<num.length;i++) 
            num[i]=(int)(Math.random()*42+1); 
    for(int i=0;i<num.length;i++) 
    for(int j=1;j<tnum.length;j++)
    if(num[i]==j) {
    	tnum[j]++; 
    	t2num[j]++;
    }
    Arrays.sort(t2num); 
    int a=1;
    int k=0,t=0;
    for(int i=42;i>=37;i--) 
    {
    	if(a<=6) 
    	{
    		for(int j=1;j<tnum.length;j++) 
    		{
    			if(tnum[j]==t2num[i]&&j!=k&&j!=t) 
    			{
    				System.out.println(j);
    				storeImage1.processStoreAction(j);
    				k=j;
    				break;
    			}
    			else 
    			{
    				a--;
    				
    			}
    			t=k;
    		}
    		a++;
    	}
      }
    storeImage1.closeConn();

    }
	private void processStoreAction(int j)throws SQLException, IOException{
				String sqlstr ="Insert Into Table_1(number) values(?)";
				PreparedStatement preState = conn.prepareStatement(sqlstr);
				
				preState.setInt(1,j);
				preState.executeUpdate();
				
				System.out.println("Stroe Success !!");
				preState.close();
	
	
	}
	public void closeConn() throws SQLException {
		if (conn != null) {
			conn.close();
			System.out.println("Conncetion Closed.");
		}
	}
	public static IMemberDao createMemberDao() {
		IMemberDao mDao = new MemberDaoJdbcImpl();
		return mDao;
	}


}