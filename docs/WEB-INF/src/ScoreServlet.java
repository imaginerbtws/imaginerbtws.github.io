import java.io.*;
import java.util.Vector;
import java.util.Collections;
import javax.servlet.*;
import javax.servlet.http.*;

/**
  *@author:imaginerbtws
  *Time:20090121
  *
  */

public class ScoreServlet extends HttpServlet{

	private static Vector scoreList = new Vector();

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doPost(req,res);
		}

	 public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException{
		 InputStream is=req.getInputStream();
		 int reqLength=3;//req.getContentLength();
		 //if(reqLength<1) throw new IOException("ERROR IN REQUEST LENGTH!!!");
		 StringBuffer sb=new StringBuffer(reqLength);
		 for(int i=0;i<reqLength;i++){
			 int c=is.read();
			 if(c==-1) break;//等于-1说明已经读到了末尾
			 else sb.append((char)c);
			 }
		String str=sb.toString();
		is.close();
		System.out.println("新的分数已经添加了: "+str);
		//string---->int
		int newScore=1000-Integer.parseInt(str);
		Integer newEntry=new Integer(newScore);
        scoreList.add(newEntry);
        //系统排序函数
        Collections.sort(scoreList);
        int order=scoreList.indexOf(newEntry)+1;
        //对名次进行字符添加
        String myOrder=String.valueOf(order);
        System.out.println("Rank:"+myOrder);
        //下面的代码是将名次字符串发回到客户端
        res.setContentType("text/plain");
        PrintWriter out=res.getWriter();
		out.write(myOrder);
        out.close();
		}
	}
