import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

public class jsouptest 
{
    public static void  insert(String sql_insert)throws Exception
        {
        String conUrl = "jdbc:sqlserver://localhost:1433;databaseName=rr114;user=sa;password=sa;";
        Connection con = null;
            try 
            {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                con = DriverManager.getConnection(conUrl);
                String SQL = sql_insert;
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);
            }
            catch (Exception ex) 
            {
                ex.printStackTrace();
            }
        }
    public static void select(String sql_select)throws Exception
        {
            String conUrl = "jdbc:sqlserver://localhost:1433;databaseName=rr114;user=sa;password=sa;";
           Connection con = null;
               try 
               {
                   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                   con = DriverManager.getConnection(conUrl);
                   String SQL = sql_select;
                   Statement stmt = con.createStatement();
                   ResultSet rs = stmt.executeQuery(SQL);
               }
               catch (Exception ex) 
               {
                   ex.printStackTrace();
               }
        }
   

    
    
    
    public static void WebCatch() throws MalformedURLException, IOException, Exception
    {
        
        int data_count=0;
        String team="",a="",ab="",hits="",hr="",rbi="",sb="",r="",bb="",k="",avg="",obp="",slg="";
        URL url = new URL("http://www.msn.com/zh-tw/sports/mlb/team-stats"); 
		Document xmlDoc =  Jsoup.parse(url,3000);
		//Elements title = xmlDoc.select("title");
		Elements td = xmlDoc.select("td");
		
                for(int i = 0;i<160;i++)//每16個td tag換一隊
                {
                  data_count++;    // 前三筆資料不抓
                  
                   if(data_count>3)// 前三筆資料不抓
                   {
                        //System.out.println(td.get(i).text());
                        if(data_count==4){team=td.get(i).text();}
                        else if(data_count==5){a=td.get(i).text();}
                        else if(data_count==6){ab=td.get(i).text().replaceAll(",","");}
                        else if(data_count==7){hits=td.get(i).text();}
                        else if(data_count==8){hr=td.get(i).text();}
                        else if(data_count==9){rbi=td.get(i).text();}
                        else if(data_count==10){sb=td.get(i).text();}
                        else if(data_count==11){r=td.get(i).text();}
                        else if(data_count==12){bb=td.get(i).text();}
                        else if(data_count==13){k=td.get(i).text();}
                        else if(data_count==14){avg=td.get(i).text();}
                        else if(data_count==15){obp=td.get(i).text();}
                        else if(data_count==16){slg=td.get(i).text();}
                        
                        if(data_count==16)
                        {
                            insert("Insert into mlb_game(team,a,ab,hits,hr,rbi,sb,r,bb,k,avg,obp,slg)values('"+team+"',"+a+","+ab+","+hits+","+hr+","+rbi+","+sb+","+r+","+bb+","+k+","+avg+","+obp+","+slg+")");
                            //insert("Insert into mlb_game(team,a,hits)values('"+team+"',"+a+","+hits+")");
                            System.out.println("insert : "+team+" "+a+" "+ab+" "+hits+" "+hr+" "+rbi+" "+sb+" "+r+" "+bb+" "+k+" "+avg+" "+obp+" "+slg);
                            /*
                            team="";
                            a="";
                            ab="";
                            hits="";
                            hr="";
                            rbi="";
                            sb="";
                            bb="";
                            r="";
                            k="";
                            avg="";
                            obp="";
                            slg="";
                            */
                            data_count=0;//重置data_count 換隊
                        
                        }
                        
                   }
                   else
                   {
                      
                   }
                       
                    
                  
                        
                }
                
    }
    public static void main(String []args) throws IOException, Exception
    {
        WebCatch();
    }
}
