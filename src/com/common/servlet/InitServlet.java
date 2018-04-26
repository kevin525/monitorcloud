package com.common.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apps.tasks.AutoMonitorAppSystemTask;
import com.apps.tasks.AutoMonitorDatabaseTask;
import com.apps.tasks.AutoMonitorMiddlewareTask;
import com.apps.tasks.AutoMonitorServerTask;
import com.apps.tasks.AutoMonitorTomcatTask;
import com.apps.tasks.ErrorLogTask;
import com.apps.tasks.WarningLogTask;
import com.common.threadPool.ErrorLogPool;
import com.common.threadPool.WarningLogPool;

/**
 * @ClassName: InitServlet
 * @Description: 初始化启动
 * @author LG
 * @date 2017年9月22日 下午5:04:34
 */
@WebServlet("/InitServlet")
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static WarningLogPool pool = new WarningLogPool();
	private static ErrorLogPool errorPool = new ErrorLogPool(); 
	public void init(ServletConfig config) throws ServletException{
    	super.init(config);
    	startTask();
    }
	
	private void startTask(){
		WarningLogTask task = new WarningLogTask();			
		task.start();
		
		ErrorLogTask errorLogTask = new ErrorLogTask();			
		errorLogTask.start();
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		if("server".equals(type)){
			AutoMonitorServerTask serverTask = new AutoMonitorServerTask();
			serverTask.mointorServer();
		}else if("system".equals(type)){
			AutoMonitorAppSystemTask appTask = new AutoMonitorAppSystemTask();
			appTask.mointorAppSystem();
		}else if("database".equals(type)){
			AutoMonitorDatabaseTask databaseTask = new AutoMonitorDatabaseTask();
			databaseTask.mointorDatabase();
		}else if("tomcat".equals(type)){
			AutoMonitorTomcatTask tomcatTask = new AutoMonitorTomcatTask();
			tomcatTask.mointorTomcat();
		}else if("middleware".equals(type)){
			AutoMonitorMiddlewareTask middlewareTask = new AutoMonitorMiddlewareTask();
			middlewareTask.mointorMiddleware();
			
		}else{
			AutoMonitorServerTask serverTask = new AutoMonitorServerTask();
			serverTask.mointorServer();
			AutoMonitorAppSystemTask appTask = new AutoMonitorAppSystemTask();
			appTask.mointorAppSystem();
		}
		
	}

	public static WarningLogPool getPool() {
		return pool;
	}

	public static void setPool(WarningLogPool pool) {
		InitServlet.pool = pool;
	}

	public static ErrorLogPool getErrorPool() {
		return errorPool;
	}

	public static void setErrorPool(ErrorLogPool errorPool) {
		InitServlet.errorPool = errorPool;
	}

}
