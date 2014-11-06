package no.uio.inf5750.webhook;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WebhookServlet
 */
@WebServlet("/WebhookServlet")
public class WebhookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WebhookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();        
		String str = runScript();
		out.println("Script result:"+str);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter(); 
		String str = runScript();
		out.println("Script result:"+str);
	}
	
	String runScript()
	{
		// Get runtime
        java.lang.Runtime rt = java.lang.Runtime.getRuntime();
        // Start a new process: UNIX command ls
        java.lang.Process p;
		try {
			p = rt.exec("/opt/webhook/webhook.sh");
			p.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
			return "Failed:"+e;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return "Failed:"+e;
		}
        // Show exit code of process
        return "Process exited with code = " + p.exitValue();
	}

}
